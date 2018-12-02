package com.maxpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorController {

    private final String BASE_PATH = "/error";
    private final String ACCESS_DENIED_PAGE = BASE_PATH + "/access_denied";

    @RequestMapping("/access-denied")
    public String accessDenied() {
        return ACCESS_DENIED_PAGE;
    }

}
