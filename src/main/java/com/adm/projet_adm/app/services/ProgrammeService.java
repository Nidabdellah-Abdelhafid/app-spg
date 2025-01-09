package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Programme;
import com.adm.projet_adm.app.repositories.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProgrammeService {

    @Autowired
    private ProgrammeRepository programmeRepository;

    public Programme save(Programme programme) {
        return programmeRepository.save(programme);
    }

    public List<Programme> findAll() {
        return programmeRepository.findAll();
    }

    public Programme findById(Long id) {
        return programmeRepository.findProgrammeById(id);
    }

    public void deleteById(Long id) {
        programmeRepository.deleteById(id);
    }

    public Programme update(Long id, Programme programme) {
        if (programmeRepository.existsById(id)) {
            programme.setId(id);
            return programmeRepository.save(programme);
        }
        return null;
    }

    public List<Programme> getProgrammeWithDetails() {
        return programmeRepository.findByIdWithPlaning();
    }

}
