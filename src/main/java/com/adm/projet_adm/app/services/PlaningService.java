package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Planing;
import com.adm.projet_adm.app.repositories.PlaningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaningService {

    @Autowired
    private PlaningRepository planingRepository;

    public Planing save(Planing planing) {
        return planingRepository.save(planing);
    }

    public List<Planing> findAll() {
        return planingRepository.findAll();
    }

    public Planing findById(Long id) {
        return planingRepository.findPlaningById(id);
    }

    public void deleteById(Long id) {
        planingRepository.deleteById(id);
    }

    public Planing update(Long id, Planing planing) {
        if (planingRepository.existsById(id)) {
            planing.setId(id);
            return planingRepository.save(planing);
        }
        return null; // or throw an exception if needed
    }
}
