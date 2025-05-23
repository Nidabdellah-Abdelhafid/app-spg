package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Pays;
import com.adm.projet_adm.app.repositories.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;

    public Pays save(Pays pays) {
        return paysRepository.save(pays);
    }

    public List<Pays> findAll() {
        return paysRepository.findAll();
    }

    public Pays findById(Long id) {
        return paysRepository.findPaysById(id);
    }

    public void deleteById(Long id) {
        paysRepository.deleteById(id);
    }

    public Pays update(Long id, Pays pays) {
        if (paysRepository.existsById(id)) {
            pays.setId(id);
            return paysRepository.save(pays);
        }
        return null; 
    }

}
