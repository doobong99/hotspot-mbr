package com.sk.project.member.domain.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/d/{role}/{username}/{password}")
    public Account createAccount(@ModelAttribute Account account) {
    	System.out.println("hi : "+account.toString());
        return accountService.createNew(account);
    }

}

