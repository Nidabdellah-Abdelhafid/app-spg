package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.BlogContent;
import com.adm.projet_adm.app.repositories.BlogContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlogContentService {

    @Autowired
    private BlogContentRepository blogContentRepository;

    public BlogContent save(BlogContent blogContent) {
        return blogContentRepository.save(blogContent);
    }

    public List<BlogContent> findAll() {
        return blogContentRepository.findAll();
    }

    public BlogContent findById(Long id) {
        return blogContentRepository.findBlogContentById(id);
    }

    public void deleteById(Long id) {
        blogContentRepository.deleteById(id);
    }

    public BlogContent update(Long id, BlogContent blogContent) {
        if (blogContentRepository.existsById(id)) {
            blogContent.setId(id);
            return blogContentRepository.save(blogContent);
        }
        return null;
    }
}