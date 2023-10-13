package com.example.demoSpringRender.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "catagory")
    private String category;

    @Column(name = "price_first")
    private BigDecimal priceFirst;

    private BigDecimal price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_url_se")
    private String imageUrlSe;

    @Column(name = "product_top")
    private Boolean productTop;

    public Product(String name, String category, BigDecimal priceFirst, BigDecimal price, String imageUrl, String imageUrlSe, Boolean productTop) {
        this.name = name;
        this.category = category;
        this.priceFirst = priceFirst;
        this.price = price;
        this.imageUrl = imageUrl;
        this.imageUrlSe = imageUrlSe;
        this.productTop = productTop;
    }

    public Product() {
        // Empty constructor
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPriceFirst() {
        return priceFirst;
    }

    public void setPriceFirst(BigDecimal priceFirst) {
        this.priceFirst = priceFirst;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlSe() {
        return imageUrlSe;
    }

    public void setImageUrlSe(String imageUrlSe) {
        this.imageUrlSe = imageUrlSe;
    }

    public Boolean getProductTop() {
        return productTop;
    }

    public void setProductTop(Boolean productTop) {
        this.productTop = productTop;
    }
}
