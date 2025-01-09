package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
    Programme findProgrammeById(long id);

    @Query("SELECT p FROM Programme p LEFT JOIN FETCH p.planing_programmes")
    List<Programme> findByIdWithPlaning();
}
