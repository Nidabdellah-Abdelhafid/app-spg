package com.adm.projet_adm.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Blog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String imageUrl;
    private String description;

    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
    @JsonBackReference("blog-contents")
    private Collection<BlogContent> blogContents = new ArrayList<>();

    public Blog() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<BlogContent> getBlogContents() {
        return blogContents;
    }

    public void setBlogContents(Collection<BlogContent> blogContents) {
        this.blogContents = blogContents;
    }

    public Blog(Long id, String title, String imageUrl, String description, Collection<BlogContent> blogContents) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.blogContents = blogContents;
    }
}