package com.maxpro.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private final String BASE_PATH = "/admin";
    private final String ADMIN_INDEX_PAGE = BASE_PATH + "/index";

    @RequestMapping("")
    public String index() {
        return ADMIN_INDEX_PAGE;
    }

}
