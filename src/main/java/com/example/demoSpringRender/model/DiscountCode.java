package com.example.demoSpringRender.model;



import java.math.BigDecimal;
import  java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "discount_codes")
public class DiscountCode {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String code;
	    @Column(name = "discount_percent")
	    private BigDecimal discountPercent;
	    @Column(name = "valid_from")
	    private Date validFrom; // Sử dụng java.sql.Date
	    @Column(name = "valid_to")
	    private Date validTo; // Sử dụng java.sql.Date
		public DiscountCode(String code, BigDecimal discountPercent, Date validFrom, Date validTo) {
			super();
			this.code = code;
			this.discountPercent = discountPercent;
			this.validFrom = validFrom;
			this.validTo = validTo;
		}
		public DiscountCode() {
			super();
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public BigDecimal getDiscountPercent() {
			return discountPercent;
		}
		public void setDiscountPercent(BigDecimal discountPercent) {
			this.discountPercent = discountPercent;
		}
		public Date getValidFrom() {
			return validFrom;
		}
		public void setValidFrom(Date validFrom) {
			this.validFrom = validFrom;
		}
		public Date getValidTo() {
			return validTo;
		}
		public void setValidTo(Date validTo) {
			this.validTo = validTo;
		}

	    
		 

    // Constructors, getters, setters, and other methods
}
