package com.shop.demo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="Member0429")
public class Member0429 {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column private long id;

//@NtoNull
@Size(min=0,message="이름은 필수 입력입니다.")
@Column(length=40,nullable=false)
private String name;

@Column(length=40,nullable=false)
private String password;

@Column(length=10,nullable=true)
private String gender;

@Column(length=10,nullable=true)
private String birth;

@Column(length=45,nullable=true)
private String mail;

@Column(length=13,nullable=true)
private String phone;

@Column(length=80,nullable=true)
private String address;

@Column(nullable=true)
private LocalDateTime timestamp;
}
