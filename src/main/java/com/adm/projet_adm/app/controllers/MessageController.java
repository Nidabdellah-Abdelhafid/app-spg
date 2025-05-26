package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Message;
import com.adm.projet_adm.app.services.FileStorageService;
import com.adm.projet_adm.app.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.databind.JsonNode;
import com.adm.projet_adm.security.services.AccountService;
import com.adm.projet_adm.security.entities.AppUser;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AccountService accountService; 
    
    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        try {
            List<Message> messages = messageService.findAll();
            List<Map<String, Object>> response = messages.stream().map(message -> {
                Map<String, Object> messageMap = new HashMap<>();
                messageMap.put("id", message.getId());
                messageMap.put("contenu", message.getContenu());
                messageMap.put("date", message.getDate());
                messageMap.put("messageType", message.getMessageType());
                messageMap.put("mediaUrl", message.getMediaUrl());
                messageMap.put("mediaType", message.getMediaType());
                messageMap.put("fileName", message.getFileName());
                messageMap.put("fileSize", message.getFileSize());
                messageMap.put("status", message.getStatus());
                
                // Map sender details
                if (message.getSender() != null) {
                    Map<String, Object> senderMap = new HashMap<>();
                    senderMap.put("id", message.getSender().getId());
                    senderMap.put("email", message.getSender().getEmail());
                    senderMap.put("fullName", message.getSender().getFullname());
                    senderMap.put("userPhoto", message.getSender().getUserPhoto());
                    messageMap.put("sender", senderMap);
                }
                
                // Map receiver details
                if (message.getReceiver() != null) {
                    Map<String, Object> receiverMap = new HashMap<>();
                    receiverMap.put("id", message.getReceiver().getId());
                    receiverMap.put("email", message.getReceiver().getEmail());
                    receiverMap.put("userPhoto", message.getSender().getUserPhoto());
                    receiverMap.put("fullname", message.getReceiver().getFullname());
                    messageMap.put("receiver", receiverMap);
                }
                
                return messageMap;
            }).collect(Collectors.toList());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> create(@RequestParam(value = "file", required = false) MultipartFile file,
                                  @RequestParam("message") String messageStr) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode messageNode = mapper.readTree(messageStr);
            
            Message message = new Message();
            message.setContenu(messageNode.get("contenu").asText());
            message.setDate(new Date());
            message.setStatus(false);
            
            // Get sender and receiver emails directly from the JSON
            String senderEmail = messageNode.get("sender").asText();
            String receiverEmail = messageNode.get("receiver").asText();
            
            AppUser sender = accountService.getUserbyEmail(senderEmail);
            AppUser receiver = accountService.getUserbyEmail(receiverEmail);
            
            if (sender == null || receiver == null) {
                return ResponseEntity.badRequest().body("Invalid sender or receiver email");
            }
            
            message.setSender(sender);
            message.setReceiver(receiver);
            
            if (file != null) {
                String mediaUrl = fileStorageService.storeFile(file);
                message.setMediaUrl(mediaUrl);
                message.setFileName(file.getOriginalFilename());
                message.setFileSize(file.getSize());
                message.setMediaType(file.getContentType());
                message.setMessageType(determineMessageType(file.getContentType()));
            } else {
                message.setMessageType(Message.MessageType.TEXT);
            }

            Message savedMessage = messageService.save(message);
            
            messagingTemplate.convertAndSendToUser(
                savedMessage.getReceiver().getEmail(),
                "/queue/messages",
                savedMessage
            );

            return ResponseEntity.ok(savedMessage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating message: " + e.getMessage());
        }
    }

    private Message.MessageType determineMessageType(String contentType) {
        if (contentType.startsWith("image/")) return Message.MessageType.IMAGE;
        if (contentType.startsWith("application/") || contentType.startsWith("text/")) return Message.MessageType.DOCUMENT;
        return Message.MessageType.TEXT;
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Message getById(@PathVariable Long id) {
        return messageService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Message update(@PathVariable Long id, @RequestBody Message message) {
        return messageService.update(id, message);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        messageService.deleteById(id);
    }
}
