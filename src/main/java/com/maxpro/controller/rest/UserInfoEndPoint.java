package com.maxpro.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/user-info")
public class UserInfoEndPoint {
 
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllUsers() {

      Collection<? extends GrantedAuthority> users =    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return new ResponseEntity<Collection<GrantedAuthority>>((Collection<GrantedAuthority>) users, HttpStatus.OK);
    }
}
