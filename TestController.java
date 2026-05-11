package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/main")
    public String mainPage() {
        return "로그인 성공! 이 페이지는 인증된 사람만 볼 수 있어요.";
    }

    @GetMapping("/public/hello")
    public String publicPage() {
        return "여기는 로그인 안 해도 들어올 수 있는 자유로운 페이지입니다!";
    }
}
