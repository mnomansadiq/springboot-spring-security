package com.maxpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    private final String BASE_PATH = "/login";
    private final String LOGIN_PAGE = BASE_PATH + "/login";

    @RequestMapping("/login")
    public String login() {
        return LOGIN_PAGE;
    }

}
