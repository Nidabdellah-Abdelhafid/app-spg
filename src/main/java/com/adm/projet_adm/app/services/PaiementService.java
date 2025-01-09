package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Paiement;
import com.adm.projet_adm.app.repositories.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    public Paiement save(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    public Paiement findById(Long id) {
        return paiementRepository.findPaiementById(id);
    }

    public void deleteById(Long id) {
        paiementRepository.deleteById(id);
    }

    public Paiement update(Long id, Paiement paiement) {
        if (paiementRepository.existsById(id)) {
            paiement.setId(id);
            return paiementRepository.save(paiement);
        }
        return null; // or throw an exception if needed
    }
}
