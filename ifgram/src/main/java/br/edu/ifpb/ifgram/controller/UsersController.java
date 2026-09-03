package br.edu.ifpb.ifgram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class UsersController {
    @RestController
    @RequestMapping ("users")
    public class UserController {

        @GetMapping
        public String getUser (){
            return "get user was called";
        }
    }
}
