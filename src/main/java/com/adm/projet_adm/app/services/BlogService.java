package com.adm.projet_adm.app.services;

import com.adm.projet_adm.app.entities.Blog;
import com.adm.projet_adm.app.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public Blog findById(Long id) {
        return blogRepository.findBlogById(id);
    }

    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

    public Blog update(Long id, Blog blog) {
        if (blogRepository.existsById(id)) {
            blog.setId(id);
            return blogRepository.save(blog);
        }
        return null;
    }
}