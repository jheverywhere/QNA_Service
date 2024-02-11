package com.exam.jheverywhere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
  @RequestMapping("/jheverywhere")
  public void index(){
    System.out.println("첫 시작");
  }
}
