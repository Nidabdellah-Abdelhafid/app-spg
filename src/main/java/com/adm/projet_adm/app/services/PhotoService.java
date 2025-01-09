package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Photo;
import com.adm.projet_adm.app.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    public Photo findById(Long id) {
        return photoRepository.findPhotoById(id);
    }

    public void deleteById(Long id) {
        photoRepository.deleteById(id);
    }

    public Photo update(Long id, Photo photo) {
        if (photoRepository.existsById(id)) {
            photo.setId(id);
            return photoRepository.save(photo);
        }
        return null; // or throw an exception if needed
    }
}
