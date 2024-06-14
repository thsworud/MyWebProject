package com.shop.demo;

import java.time.LocalDateTime;

import org.slf4j.Logger;  // SLF4J 로거 인터페이스를 사용하기 위한 import 문
import org.slf4j.LoggerFactory;  // SLF4J LoggerFactory 클래스를 사용하기 위한 import 문
import org.springframework.beans.factory.annotation.Autowired;  // 스프링의 @Autowired 애노테이션을 사용하기 위한 import 문
import org.springframework.stereotype.Controller;  // 스프링의 컨트롤러를 선언하기 위한 import 문
import org.springframework.transaction.annotation.Transactional;  // 스프링의 트랜잭션 관련 애노테이션을 사용하기 위한 import 문
import org.springframework.web.bind.annotation.GetMapping;  // 스프링의 GET 요청을 처리하기 위한 import 문
import org.springframework.web.bind.annotation.ModelAttribute;  // 스프링의 @ModelAttribute 애노테이션을 사용하기 위한 import 문
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;  // 스프링의 POST 요청을 처리하기 위한 import 문
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;  // 스프링의 ModelAndView 클래스를 사용하기 위한 import 문

import lombok.RequiredArgsConstructor;  // 롬복의 @RequiredArgsConstructor 애노테이션을 사용하기 위한 import 문

@Controller  // 스프링 컨트롤러로 설정
@RequiredArgsConstructor  // 롬복의 @RequiredArgsConstructor 애노테이션을 사용하여 필요한 final 필드를 자동으로 주입
public class MemberController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);  // 해당 클래스를 위한 로거 객체 생성
	
	@Autowired  // 스프링에 의해 관리되는 빈을 자동으로 주입하기 위한 애노테이션
	MemberRepository repository;  // MemberRepository 타입의 repository 필드 선언 및 주입
	
	@GetMapping(value="/members/new")  // GET 요청을 처리하는 메서드를 지정하는 애노테이션
	public ModelAndView members1(
			@ModelAttribute("memberForm") Member0429 member, ModelAndView mav) {  // GET 요청 핸들러 메서드, ModelAndView를 반환하고 memberForm 속성의 Member0429 객체를 받음
			mav.setViewName("members/creatememberForm");  // 뷰의 이름 설정
			mav.addObject("msg", "sample content.");  // 모델에 속성 추가
			Iterable<Member0429> list = repository.findAll();  // 모든 멤버 조회하여 리스트에 할당
			mav.addObject("datalist", list);  // 모델에 멤버 리스트 추가
			return mav;  // ModelAndView 반환
	}
	
	@PostMapping(value="/members/new")  // POST 요청을 처리하는 메서드를 지정하는 애노테이션
	@Transactional(readOnly=false)  // 해당 메서드의 트랜잭션 처리를 지정하는 애노테이션
	public ModelAndView members(Member0429 member, ModelAndView mav) {  // POST 요청 핸들러 메서드, ModelAndView를 반환하고 Member0429 객체를 받음
		LocalDateTime now = LocalDateTime.now();  // 현재 날짜와 시간을 가져오는 LocalDateTime 객체 생성
		member.setTimestamp(now);  // Member0429 객체에 현재 시간 설정
		repository.saveAndFlush(member);  // 멤버 저장 및 데이터베이스에 변경사항 반영
		return new ModelAndView("redirect:/members");  // ModelAndView를 이용하여 멤버 리스트 페이지로 리다이렉트
	}
	
	@GetMapping(value="/members")  // GET 요청을 처리하는 메서드를 지정하는 애노테이션
	public ModelAndView list(Member0429 member, ModelAndView mav) {  // GET 요청 핸들러 메서드, ModelAndView를 반환하고 Member0429 객체를 받음
		mav.setViewName("members/memberList");  // 뷰의 이름 설정
		Iterable<Member0429> list = repository.findAll();  // 모든 멤버 조회하여 리스트에 할당
		mav.addObject("members", list);  // 모델에 멤버 리스트 추가
		return mav;  // ModelAndView 반환
	}
	@GetMapping(value="/members/edit/{id}")
	public ModelAndView edit(@ModelAttribute Member0429 member,
			@PathVariable("id")int id,ModelAndView mav) {
		mav.setViewName("members/edit");
		Member0429 list=repository.findById((long)id);
		
		mav.addObject("memberForm",list);
		log.info("member : "+list+"mav :"+mav);
		return mav;
	}
	@PostMapping(value="/members/edit/{id}")
	@Transactional(readOnly=false)
	public ModelAndView update(@ModelAttribute Member0429 member,@PathVariable("id")int id,
			ModelAndView mav) {
		LocalDateTime now=LocalDateTime.now();
		member.setTimestamp(now);
		repository.saveAndFlush(member);
		return new ModelAndView("redirect:/members");
	}
	
	@GetMapping(value="/members/delete/{id}")
	public ModelAndView delete(@PathVariable("id")int id, 
			ModelAndView mav) {
		mav.setViewName("members/delete");
		Member0429 data=repository.findById((long)id);

		mav.addObject("memberForm",data);
		return mav;
	}
	@PostMapping(value="/members/delete/{id}")
	@Transactional(readOnly=false)
	public ModelAndView remove(@RequestParam("id")int id, 
			ModelAndView mav) {
		repository.deleteById((long)id);
		return new ModelAndView("redirect:/members");
	}
	
}

