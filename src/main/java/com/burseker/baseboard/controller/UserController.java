package com.burseker.baseboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @RequestMapping(value = "/greetpage", method = RequestMethod.GET)
    public ResponseEntity<String> getPageName(){
        return ResponseEntity.ok("Hello greet page");
    }

    @RequestMapping(value = "/greetbyname", method = RequestMethod.GET)
    public ResponseEntity<String> getGreetingsByName(@RequestParam(value = "name", defaultValue = "User") String name){
        return ResponseEntity.ok("Hello " + name);
    }

}
