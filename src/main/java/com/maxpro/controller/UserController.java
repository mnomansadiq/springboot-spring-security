package com.maxpro.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class UserController {

    private final String BASE_PATH = "/user";
    private final String USER_INDEX_PAGE = BASE_PATH + "/index";

    @RequestMapping("")
    public String index() {
        return USER_INDEX_PAGE;
    }

}
