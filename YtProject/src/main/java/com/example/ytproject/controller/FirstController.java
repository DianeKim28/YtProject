package com.example.ytproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller   /*1. 콘트롤러 생성*/
public class FirstController {
    @GetMapping("/hi")
    public String niceTomeetyou(Model model) {  /* 2. 메소드 생성, 모델 피라미터로 추가 */
        model.addAttribute("username", "YEJIN");
        return "greetings";  /*3. 응답페이지 설정 return "페이지명";*/
    }

    @GetMapping("/bye")
    public String SeeYouNext(Model model) {
        model.addAttribute("nickname","꺄르르");
        return "goodbye";  /*3. 응답페이지 설정 return "페이지명";*/
    }
}