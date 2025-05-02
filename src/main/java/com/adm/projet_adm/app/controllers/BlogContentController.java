package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.BlogContent;
import com.adm.projet_adm.app.services.BlogContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog-contents")
public class BlogContentController {

    @Autowired
    private BlogContentService blogContentService;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public BlogContent create(@RequestBody BlogContent blogContent) {
        return blogContentService.save(blogContent);
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<BlogContent> getAll() {
        return blogContentService.findAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public BlogContent getById(@PathVariable Long id) {
        return blogContentService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public BlogContent update(@PathVariable Long id, @RequestBody BlogContent blogContent) {
        return blogContentService.update(id, blogContent);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        blogContentService.deleteById(id);
    }
}