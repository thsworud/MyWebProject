package com.shop.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {
private static final Logger log= LoggerFactory.getLogger(ItemController.class);

@Autowired
ItemRepository repository;

@GetMapping(value="/items/new")
public ModelAndView items1(
		@ModelAttribute("form")Items items,ModelAndView mav) {
	mav.setViewName("items/createItemForm");
	Iterable<Items>list=repository.findAll();
	mav.addObject("datalist",list);
	return mav;
}
@PostMapping(value="/items/new")
@Transactional(readOnly=false)
public ModelAndView items(Items items, ModelAndView mav) {
	mav.setViewName("items/createItemForm");
	repository.saveAndFlush(items);
	return new ModelAndView("redirect:/items");
}
@GetMapping(value="/items")
public ModelAndView list(Items items,ModelAndView mav) {
	mav.setViewName("items/itemList");
	Iterable<Items> list=repository.findAll();
	mav.addObject("items",list);
	return mav;
}
@GetMapping(value="/items/edit/{id}")
public ModelAndView edit(@ModelAttribute Items item, @PathVariable("id")int id,ModelAndView mav) {
	mav.setViewName("items/edit");
	Items list=repository.findById((long)id);
	
	mav.addObject("form",list);
	return mav;
}
@PostMapping(value="/items/edit/{id}")
@Transactional(readOnly=false)
public ModelAndView update(@ModelAttribute Items item,@PathVariable("id")int id, ModelAndView mav) {
	repository.saveAndFlush(item);
	return new ModelAndView("redirect:/items");
}
@GetMapping(value="/items/delete/{id}")
public ModelAndView delete(@PathVariable("id")int id, ModelAndView mav) {
	mav.setViewName("items/delete");
	Items data=repository.findById((long)id);
	mav.addObject("form",data);
	return mav;
	
}
@PostMapping(value="/items/delete/{id}")
@Transactional(readOnly=false)
public ModelAndView remove(@RequestParam("id")int id, ModelAndView mav) {
	repository.deleteById((long)id);
	return new ModelAndView("redirect:/items");
}
}
