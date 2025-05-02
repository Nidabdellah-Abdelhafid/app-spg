package com.adm.projet_adm.app.controllers;

import com.adm.projet_adm.app.entities.BlogContent;
import com.adm.projet_adm.app.services.BlogContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
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
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        List<BlogContent> blogContents = blogContentService.findAll();
        List<Map<String, Object>> response = blogContents.stream().map(blogContent -> {
            Map<String, Object> blogContentMap = new HashMap<>();
            blogContentMap.put("id", blogContent.getId());
            blogContentMap.put("title", blogContent.getTitle());
            blogContentMap.put("imageUrl", blogContent.getImageUrl());
            blogContentMap.put("paragraph1", blogContent.getParagraph1());
            blogContentMap.put("paragraph2", blogContent.getParagraph2());
            blogContentMap.put("paragraph3", blogContent.getParagraph3());
            blogContentMap.put("blog", blogContent.getBlog());
            return blogContentMap;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
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