package com.shop.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Items")
public class Items {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column private long id;

@Column(length=40,nullable=false)
private String name;

@Column(length=11,nullable=false)
private String price;

@Column(length=11,nullable=true)
private String stockQuantity;

@Column(length=40,nullable=true)
private String author;

@Column(length=40,nullable=true)
private String isbn;
}
