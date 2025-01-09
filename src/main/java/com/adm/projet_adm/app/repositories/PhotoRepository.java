package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Photo findPhotoById(Long id);
}
