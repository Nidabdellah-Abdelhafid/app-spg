package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Blog;
import com.adm.projet_adm.app.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN')")
    public Blog create(@RequestBody Blog blog) {
        return blogService.save(blog);
    }

    @GetMapping
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Blog> getAll() {
        return blogService.findAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Blog getById(@PathVariable Long id) {
        return blogService.findById(id);
    }

    @PutMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Blog update(@PathVariable Long id, @RequestBody Blog blog) {
        return blogService.update(id, blog);
    }

    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        blogService.deleteById(id);
    }
}