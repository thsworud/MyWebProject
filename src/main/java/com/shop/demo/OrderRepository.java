package com.shop.demo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


	@Repository
	public interface OrderRepository extends JpaRepository<Order0429,Long>{
	public Order0429 findById(long orderId);
	
	@Query(value= "select c.order_id,c.member_id,c.item_id,c.count,c.timestamp,o.name,p.name pname from Order0429 c, Member0429 o ,Items p where c.member_Id=o.id and c.item_id=p.id",nativeQuery=true)
	List<Map<String,Object>> findByOrders();
	}

