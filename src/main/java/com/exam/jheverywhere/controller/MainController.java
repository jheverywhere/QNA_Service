package com.exam.jheverywhere.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {
  private int increaseNo = -1;

  @RequestMapping("/jheverywhere")
  // 아래 함수의 리턴값을 그대로 브라우저에 표시
  // 아래 함수의 리턴값을 문자열화 해서 브라우저 응답을 바디에 담는다.
  @ResponseBody
  public String index() {
    return "반갑습니다";
  }

  @GetMapping("/page1")
  @ResponseBody
  public String showGet() {
    return """
        <form method="POST" action="/page2"/>
          <input type="number" name="age" placeholder="나이 입력" value="page2로 POST 방식으로 이동" />
          <input type="submit" value="page2로 POST 방식으로 이동" />
        </form>
        """;
  }

  @PostMapping("/page2")
  @ResponseBody
  public String showPage2Post(@RequestParam(defaultValue = "0") int age) {
    return """
        <h1>입력된 나이 : %d</h1>
        <h1>안녕하세요. POST 방식으로 오신걸 환영합니다.</h1>
        """.formatted(age);
  }


  @GetMapping("/page2")
  @ResponseBody
  public String showPost(@RequestParam(defaultValue = "0") int age) {
    return """
        <h1>입력된 나이 : %d</h1>
        <h1>안녕하세요. GET 방식으로 오신걸 환영합니다.</h1>

        """.formatted(age);
  }

  @GetMapping("/plus")
  @ResponseBody
  public int showPlus(int a, int b) {
    return a + b;
  }


  //Servlet 방식(JSP)으로 구현하는법
  //입력하면 그대로 리턴값이 나오는 스프링부트에 비해 굉장히 번거롭다
  @GetMapping("/plus2")
  @ResponseBody
  public void showPlus2(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    int a = Integer.parseInt(req.getParameter("a"));
    int b = Integer.parseInt(req.getParameter("b"));

    resp.getWriter().append(a + b + "");
  }


  @GetMapping("/minus")
  @ResponseBody
  public int showMinus(int a, int b) {
    return a - b;
  }

  @GetMapping("/increase")
  @ResponseBody
  public int showincrease() {
    increaseNo++;
    return increaseNo;
  }

  @GetMapping("/gugudan")
  @ResponseBody
  public String showGugudan(Integer dan, Integer limit) {
    //int와 Integer의 차이 : int는 일반 자료형, Integer는 객체 자료형
    //그래서 Integer는 null값을 가질수 있다
    //@RequestParam을 안쓰고도 이렇게 기본값 설정이 가능함
    if (dan == null) {
      limit = 9;
    }
    if (dan == null) {
      limit = 9;
    }

    //하지만 아래와 같이 매개변수를 한번 걸러줘야함
    //일반 타입의 int가 들어올수 없기 때문에
    final Integer finalDan = dan;
    return IntStream.rangeClosed(1, limit)
        .mapToObj(i -> "%d * %d = %d".formatted(finalDan, i, finalDan * i))
        .collect(Collectors.joining("<br>"));
  }

  @GetMapping("/mbti/{name}")
  @ResponseBody
  public String showMbti(@PathVariable String name) {
    return switch (name) {
      case "홍길동" -> "INFP";
      case "홍길순" -> "ENFP";
      case "김동현" -> "ESFJ";
      case "리궁수" -> "ENTJ";
      case "장다현" -> "INTP";
      default -> "모름";
    };
  }

}



