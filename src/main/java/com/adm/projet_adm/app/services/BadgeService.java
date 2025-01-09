package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Badge;
import com.adm.projet_adm.app.repositories.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    public Badge save(Badge badge) {
        return badgeRepository.save(badge);
    }

    public List<Badge> findAll() {
        return badgeRepository.findAll();
    }

    public Badge findById(Long id) {
        return badgeRepository.findBadgesById(id);
    }

    public void deleteById(Long id) {
        badgeRepository.deleteById(id);
    }

    public Badge update(Long id, Badge badge) {
        if (badgeRepository.existsById(id)) {
            badge.setId(id);
            return badgeRepository.save(badge);
        }
        return null; // or throw an exception if needed
    }
}
