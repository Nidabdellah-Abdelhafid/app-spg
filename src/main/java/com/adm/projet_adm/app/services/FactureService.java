package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Facture;
import com.adm.projet_adm.app.repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;

    public Facture save(Facture facture) {
        return factureRepository.save(facture);
    }

    public List<Facture> findAll() {
        return factureRepository.findAll();
    }

    public Facture findById(Long id) {
        return factureRepository.findFactureById(id);
    }

    public void deleteById(Long id) {
        factureRepository.deleteById(id);
    }

    public Facture update(Long id, Facture facture) {
        if (factureRepository.existsById(id)) {
            facture.setId(id);
            return factureRepository.save(facture);
        }
        return null; // or throw an exception if needed
    }
}
