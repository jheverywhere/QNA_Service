package com.exam.jheverywhere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
  @RequestMapping("/jheverywhere")
  // 아래 함수의 리턴값을 그대로 브라우저에 표시
  // 아래 함수의 리턴값을 문자열화 해서 브라우저 응답을 바디에 담는다.
  @ResponseBody
  public String index(){
    return "제발 되라 왜 안되는거야 ㅠㅠ";
  }
}
