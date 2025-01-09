package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Message;
import com.adm.projet_adm.app.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Message create(@RequestBody Message message) {
        return messageService.save(message);
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Message> getAll() {
        return messageService.findAll();
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
