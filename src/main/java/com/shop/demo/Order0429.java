package com.shop.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="Order0429")
public class Order0429 {
@Id
@Column
@GeneratedValue(strategy=GenerationType.AUTO)
private long orderId;

@Column
private long memberId;

@Column
private long itemId;

@Column(length=11,nullable=false)
private int count;

@Column(nullable=true)
private LocalDate timestamp;//날짜만 넣고싶으면 LocalDate까지만 주면된다능

public void LocalDate(LocalDate now) {
	// TODO Auto-generated method stub
	
}
}
