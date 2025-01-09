package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Notification;
import com.adm.projet_adm.app.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Notification findById(Long id) {
        return notificationRepository.findNotificationById(id);
    }

    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    public Notification update(Long id, Notification notification) {
        if (notificationRepository.existsById(id)) {
            notification.setId(id);
            return notificationRepository.save(notification);
        }
        return null; // or throw an exception if needed
    }


}
