package com.cnsesan.user.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cnsesan.user.entity.User;
import com.cnsesan.user.entity.UserLoginDTO;
import com.cnsesan.user.service.UserServiceDetail;

@RestController
@RequestMapping("/user")
public class UserController {

    
    @Autowired UserServiceDetail userServiceDetail;
    
    @PostMapping("/register")
    public User postUser(@RequestParam("username")String username,@RequestParam("password")String password){
        return userServiceDetail.insertUser(username, password);
    }
    
    @PostMapping ("/login")
    public UserLoginDTO login(@RequestParam ("username")String username,@RequestParam ("password")String password){
        return userServiceDetail.login(username, password);
    }
    
    @RequestMapping(value = "/foo")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getFoo(OAuth2Authentication authentication) {
    	Authentication token = authentication.getUserAuthentication();
    	String principal = token.getPrincipal().toString();
        return "i'm foo, " + principal + UUID.randomUUID().toString();
    }
}