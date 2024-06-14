package com.shop.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member0429, Long>{
public Member0429 findById(long id);
public Member0429 deleteById(long id);
}
