package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Notification findNotificationById(long id);
}
