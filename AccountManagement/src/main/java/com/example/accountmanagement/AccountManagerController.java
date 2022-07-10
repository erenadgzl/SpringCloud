package com.example.accountmanagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account-management")
public class AccountManagerController {

    @GetMapping("/status/check")
    public String statusCheck(){
        return "OK";
    }
}
