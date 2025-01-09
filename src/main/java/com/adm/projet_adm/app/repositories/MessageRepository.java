package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findMessageById(long id);
}
