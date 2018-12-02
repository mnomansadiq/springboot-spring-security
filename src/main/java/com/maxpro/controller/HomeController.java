package com.maxpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping({"", "/"})
public class HomeController {

    private final String BASE_PATH = "/";
    private final String HOME_INDEX_PAGE = BASE_PATH + "index";

    @RequestMapping("")
    public String index() {
        return HOME_INDEX_PAGE;
    }

}
