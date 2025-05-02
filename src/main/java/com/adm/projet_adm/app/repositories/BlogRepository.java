package com.adm.projet_adm.app.repositories;

import com.adm.projet_adm.app.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Blog findBlogById(Long id);
}