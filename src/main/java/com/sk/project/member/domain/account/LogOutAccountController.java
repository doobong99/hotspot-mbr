package com.sk.project.member.domain.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogOutAccountController {

    @GetMapping("/logout")
    public String logoutForm() {
        return "logout";
    }

}
