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
    private String subTitle;
    private String imageUrl;
    @Column(length = 1000)
    private String description;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT CURRENT_TIMESTAMP")
    private String createdAt = new java.text.SimpleDateFormat("dd MMMM yyyy", java.util.Locale.FRENCH)
            .format(new java.util.Date());

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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Blog(Long id, String title, String subTitle, String imageUrl, String description, Collection<BlogContent> blogContents) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
        this.description = description;
        this.blogContents = blogContents;
    }
}