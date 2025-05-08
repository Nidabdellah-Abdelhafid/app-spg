package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Offre;
import com.adm.projet_adm.app.entities.Pays;
import com.adm.projet_adm.app.entities.Photo;
import com.adm.projet_adm.app.entities.Planing;
import com.adm.projet_adm.app.entities.Programme;
import com.adm.projet_adm.app.repositories.OffreRepository;
import com.adm.projet_adm.app.repositories.PaysRepository;
import com.adm.projet_adm.app.repositories.PlaningRepository;
import com.adm.projet_adm.app.repositories.ProgrammeRepository;
import com.adm.projet_adm.app.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private PlaningRepository planingRepository;

    @Autowired
    private ProgrammeRepository programmeRepository;

    @Autowired
    private OffreRepository offreRepository;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Photo create(@RequestBody Photo photo) {
        if (photo.getPays() == null && photo.getPlaning() == null && photo.getProgramme() == null && photo.getOffre() == null) {
            throw new IllegalArgumentException("All required fields must be filled.");
        }

        if (photo.getPays() != null && (photo.getPlaning() == null && photo.getProgramme() == null && photo.getOffre() == null)) {
            Pays pays = paysRepository.findPaysById(photo.getPays().getId());
            photo.setPays(pays);
            return photoService.save(photo);

        }

        if ( photo.getPlaning() != null && (photo.getPays() == null && photo.getProgramme() == null && photo.getOffre() == null)) {
            Planing planing = planingRepository.findPlaningById(photo.getPlaning().getId());
            photo.setPlaning(planing);
            return photoService.save(photo);

        }

        if (photo.getProgramme() != null && (photo.getPlaning() == null && photo.getPays() == null && photo.getOffre() == null)) {
            Programme programme = programmeRepository.findProgrammeById(photo.getProgramme().getId());
            photo.setProgramme(programme);

            return photoService.save(photo);
        }

        if (photo.getOffre() != null && (photo.getPlaning() == null && photo.getPays() == null && photo.getProgramme() == null)) {
            Offre offre = offreRepository.findOffreById(photo.getOffre().getId());
            photo.setOffre(offre);

            return photoService.save(photo);
        }


    return null;



    }

    @GetMapping
    // @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Photo> getAll() {
        return photoService.findAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Photo getById(@PathVariable Long id) {
        return photoService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Photo update(@PathVariable Long id, @RequestBody Photo photo) {
        if (photo.getPays() == null && photo.getPlaning() == null && photo.getProgramme() == null && photo.getOffre() == null) {
            throw new IllegalArgumentException("All required fields must be filled.");
        }

        if (photo.getPays() != null && (photo.getPlaning() == null && photo.getProgramme() == null && photo.getOffre() == null)) {
            Pays pays = paysRepository.findPaysById(photo.getPays().getId());
            photo.setPays(pays);
            return photoService.update(id, photo);
        }

        if (photo.getPlaning() != null && (photo.getPays() == null && photo.getProgramme() == null && photo.getOffre() == null)) {
            Planing planing = planingRepository.findPlaningById(photo.getPlaning().getId());
            photo.setPlaning(planing);
            return photoService.update(id, photo);
        }

        if (photo.getProgramme() != null && (photo.getPlaning() == null && photo.getPays() == null && photo.getOffre() == null)) {
            Programme programme = programmeRepository.findProgrammeById(photo.getProgramme().getId());
            photo.setProgramme(programme);
            return photoService.update(id, photo);
        }

        if (photo.getOffre() != null && (photo.getPlaning() == null && photo.getPays() == null && photo.getProgramme() == null)) {
            Offre offre = offreRepository.findOffreById(photo.getOffre().getId());
            photo.setOffre(offre);
            return photoService.update(id, photo);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        photoService.deleteById(id);
    }
}
