package com.example.demoSpringRender.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    
    private BigDecimal total_price;



	public CartItem(String username, Product product, int quantity, BigDecimal total_price) {
		super();
		this.username = username;
		this.product = product;
		this.quantity = quantity;
		this.total_price = total_price;
	}

	public CartItem(Long id, String username, Product product, int quantity, BigDecimal total_price) {
		super();
		this.id = id;
		this.username = username;
		this.product = product;
		this.quantity = quantity;
		this.total_price = total_price;
	}

	public CartItem() {
		super();
	}

	public BigDecimal getTotal_price() {
		return total_price;
	}

	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    // Constructors, getters, and setters
}