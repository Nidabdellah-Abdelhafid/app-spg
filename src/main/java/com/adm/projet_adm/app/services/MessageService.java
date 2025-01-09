package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Message;
import com.adm.projet_adm.app.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message findById(Long id) {
        return messageRepository.findMessageById(id);
    }

    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }

    public Message update(Long id, Message message) {
        if (messageRepository.existsById(id)) {
            message.setId(id);
            return messageRepository.save(message);
        }
        return null; // or throw an exception if needed
    }
}
