package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.Blog;
import com.adm.projet_adm.app.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
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
    // @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        List<Blog> blogs = blogService.findAll();
        List<Map<String, Object>> response = blogs.stream().map(blog -> {
            Map<String, Object> blogMap = new HashMap<>();
            blogMap.put("id", blog.getId());
            blogMap.put("title", blog.getTitle());
            blogMap.put("subTitle", blog.getSubTitle());
            blogMap.put("imageUrl", blog.getImageUrl());
            blogMap.put("description", blog.getDescription());
            blogMap.put("createdAt", blog.getCreatedAt());
            blogMap.put("pays", blog.getPays());
            blogMap.put("blogContents", blog.getBlogContents());
            return blogMap;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
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