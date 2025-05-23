package com.adm.projet_adm.app.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Pays {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String mapImage;
    @Column(length = 1000)
    private String description;
    private String continent;
    private Float latitude;
    private Float longitude;
    @Column(unique = true)
    private String label;
    private String subTitle;
    private String imageDes;
    private int reviews;
    private boolean visa;
    private String dureeDuVol;
    private String heureLocale;
    private String monnaieLocale;
    private String langueParlee;
    private boolean vaccinsNecessaires;
    private Integer january;
    private Integer february;
    private Integer march;
    private Integer april;
    private Integer may;
    private Integer june;
    private Integer july;
    private Integer august;
    private Integer september;
    private Integer october;
    private Integer november;
    private Integer december;

    @OneToMany(mappedBy = "pays_offres", fetch = FetchType.EAGER)
    @JsonBackReference("pays-offres")
    private Collection<Offre> offres = new ArrayList<>();

    @OneToMany(mappedBy = "pays_photos", fetch = FetchType.LAZY)
    @JsonBackReference("pays-photos")
    private Collection<Photo> photos;

    @OneToMany(mappedBy = "pays_blogs", fetch = FetchType.LAZY)
    @JsonBackReference("pays-blogs")
    private Collection<Blog> blogs = new ArrayList<>();


    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }


    public Pays() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Collection<Offre> getOffres() {
        return offres;
    }

    public void setOffres(Collection<Offre> offres) {
        this.offres = offres;
    }

    public Collection<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<Photo> photos) {
        this.photos = photos;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isVisa() {
        return visa;
    }

    public void setVisa(boolean visa) {
        this.visa = visa;
    }

    public String getDureeDuVol() {
        return dureeDuVol;
    }

    public void setDureeDuVol(String dureeDuVol) {
        this.dureeDuVol = dureeDuVol;
    }

    public String getHeureLocale() {
        return heureLocale;
    }

    public void setHeureLocale(String heureLocale) {
        this.heureLocale = heureLocale;
    }

    public String getMonnaieLocale() {
        return monnaieLocale;
    }

    public void setMonnaieLocale(String monnaieLocale) {
        this.monnaieLocale = monnaieLocale;
    }

    public String getLangueParlee() {
        return langueParlee;
    }

    public void setLangueParlee(String langueParlee) {
        this.langueParlee = langueParlee;
    }

    public boolean isVaccinsNecessaires() {
        return vaccinsNecessaires;
    }

    public void setVaccinsNecessaires(boolean vaccinsNecessaires) {
        this.vaccinsNecessaires = vaccinsNecessaires;
    }

    public String getMapImage() {
        return mapImage;
    }

    public void setMapImage(String mapImage) {
        this.mapImage = mapImage;
    }

    public Integer getJanuary() { return january; }
    public void setJanuary(Integer january) { this.january = january; }
    
    public Integer getFebruary() { return february; }
    public void setFebruary(Integer february) { this.february = february; }
    
    public Integer getMarch() { return march; }
    public void setMarch(Integer march) { this.march = march; }
    
    public Integer getApril() { return april; }
    public void setApril(Integer april) { this.april = april; }
    
    public Integer getMay() { return may; }
    public void setMay(Integer may) { this.may = may; }
    
    public Integer getJune() { return june; }
    public void setJune(Integer june) { this.june = june; }
    
    public Integer getJuly() { return july; }
    public void setJuly(Integer july) { this.july = july; }
    
    public Integer getAugust() { return august; }
    public void setAugust(Integer august) { this.august = august; }
    
    public Integer getSeptember() { return september; }
    public void setSeptember(Integer september) { this.september = september; }
    
    public Integer getOctober() { return october; }
    public void setOctober(Integer october) { this.october = october; }
    
    public Integer getNovember() { return november; }
    public void setNovember(Integer november) { this.november = november; }
    
    public Integer getDecember() { return december; }
    public void setDecember(Integer december) { this.december = december; }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    public String getImageDes() {
        return imageDes;
    }

    public void setImageDes(String imageDes) {
        this.imageDes = imageDes;
    }

    public Collection<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Collection<Blog> blogs) {
        this.blogs = blogs;
    }

    public Pays(String description, Long id, String image, String mapImage, String continent, 
                Float latitude, Float longitude, String label, String subTitle, String imageDes, int reviews, 
                boolean visa, String dureeDuVol, String heureLocale, 
                String monnaieLocale, String langueParlee, boolean vaccinsNecessaires,
                Integer january, Integer february, Integer march, Integer april,
                Integer may, Integer june, Integer july, Integer august,
                Integer september, Integer october, Integer november, Integer december,
                Collection<Offre> offres, Collection<Photo> photos,
                Collection<Blog> blogs) {
        this.description = description;
        this.id = id;
        this.image = image;
        this.mapImage = mapImage;
        this.continent = continent;
        this.latitude = latitude;
        this.longitude = longitude;
        this.label = label;
        this.subTitle = subTitle;
        this.imageDes = imageDes;
        this.reviews = reviews;
        this.visa = visa;
        this.dureeDuVol = dureeDuVol;
        this.heureLocale = heureLocale;
        this.monnaieLocale = monnaieLocale;
        this.langueParlee = langueParlee;
        this.vaccinsNecessaires = vaccinsNecessaires;
        this.offres = offres;
        this.photos = photos;
        this.january = january;
        this.february = february;
        this.march = march;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.august = august;
        this.september = september;
        this.october = october;
        this.november = november;
        this.december = december;
        this.blogs = blogs;
    }

}
