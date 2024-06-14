package com.shop.demo;


import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HelloController {

//로그에 8090까지만 입력했을때 나타는 페이지(index.html)
@GetMapping("/")
public String index() {
	log.info("index controller");
	return "index";
}
//로그에 /hello까지 붙히면 나타는 페이지(hello.html)
@GetMapping("/hello")
public String hello(Model model) {
	model.addAttribute("data","hello!!!");
	return "hello";
}
}
