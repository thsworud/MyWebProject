package com.shop.demo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {
private static final Logger log=LoggerFactory.getLogger(OrderController.class);
@Autowired
OrderRepository repository;
@Autowired
MemberRepository repository1;
@Autowired
ItemRepository repository2;
@GetMapping(value="/orders/new")
public ModelAndView orders1(
		@ModelAttribute("form") Member0429 member,Order0429 order, Items items,ModelAndView mav) {
		mav.setViewName("orders/createOrderForm");
		Iterable<Order0429>list=repository.findAll();
		Iterable<Member0429>list1=repository1.findAll();
		Iterable<Items>list2=repository2.findAll();
		mav.addObject("datalist",list);
		mav.addObject("members",list1);
		mav.addObject("items",list2);
		return mav;
}
@PostMapping(value="orders/new")
@Transactional(readOnly=false)
public ModelAndView orders2(Order0429 order,Items items,ModelAndView mav) {
	mav.setViewName("/orders/createOrderForm");
	LocalDate now=LocalDate.now();//날짜만 입력하며녀 Time을 뺴면된다
	order.setTimestamp(now);
	
	repository.saveAndFlush(order);
	return new ModelAndView("redirect:/orders");
}
@GetMapping(value="/orders")
public ModelAndView list1(Order0429 order, ModelAndView mav) {
	mav.setViewName("orders/orderList");
	//Iterable<Order0429>list=repository.findAll();
	List<Map<String,Object>>list=repository.findByOrders();
	mav.addObject("orders",list);
	return mav;
}
@GetMapping(value="/orders/edit/{id}")
public ModelAndView edit(@ModelAttribute Member0429 member,Order0429 order, @PathVariable("id")int id, Items items,ModelAndView mav) {
	mav.setViewName("orders/edit");
	Order0429 list=repository.findById((long)id);
	//Iterable<Order0429>list=repository.findAll();
	Iterable<Member0429>list1=repository1.findAll();
	Iterable<Items>list2=repository2.findAll();
	//mav.addObject("datalist",list);
	mav.addObject("orders",list);
	mav.addObject("members",list1);
	mav.addObject("items",list2);
	return mav;
}
@PostMapping(value="/orders/edit/{id}")
@Transactional(readOnly=false)
public ModelAndView update(@ModelAttribute Order0429 order,
		ModelAndView mav) {
	LocalDate now=LocalDate.now();
	//log.info("id100000000000000:"+id);
	order.setTimestamp(now);
	repository.saveAndFlush(order);
	return new ModelAndView("redirect:/orders");
}
@GetMapping(value="/orders/delete/{id}")
public ModelAndView delete(@PathVariable("id") int id,Member0429 member,Order0429 order, Items items,ModelAndView mav) {
	mav.setViewName("orders/delete");
	Order0429 list= repository.findById((long)id);
	Iterable<Member0429>list1=repository1.findAll();
	Iterable<Items>list2=repository2.findAll();
	mav.addObject("orders",list);
	log.info("order100000000000 : "+list);
	mav.addObject("members",list1);
	mav.addObject("items",list2);
	return mav;
}

@PostMapping(value="/orders/delete/{id}")
@Transactional(readOnly=false)
public ModelAndView remove(@ModelAttribute @PathVariable("id")int id, ModelAndView mav) {
	repository.deleteById((long)id);
	return new ModelAndView("redirect:/orders");
}
}
