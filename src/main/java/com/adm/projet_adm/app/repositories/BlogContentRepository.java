package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.BlogContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogContentRepository extends JpaRepository<BlogContent, Long> {
    BlogContent findBlogContentById(Long id);
}