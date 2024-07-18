package com.jiwoong.billingsystem;

import com.jiwoong.billingsystem.streaming.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
public class TestController {

    @GetMapping("/api/login-success")
    @ResponseBody
    public String success() {
        return "소셜 로그인 성공";
    }

    @GetMapping("/auth-confirm")
    @ResponseBody
    public String authConfirm() {
        System.out.println(Objects.requireNonNull(SecurityUtil.getCurrentUser()).getUsername());
        return "JWT 인증 OK";
    }

}
