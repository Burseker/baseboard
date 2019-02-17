package com.burseker.baseboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping(value = "/greetpage", method = RequestMethod.GET)
    public ResponseEntity<String> getPageName(){
        return ResponseEntity.ok("Hello greet page");
    }

    @RequestMapping(value = "/greetbyname", method = RequestMethod.GET)
    public ResponseEntity<String> getGreetingsByName(@RequestParam(value = "name", defaultValue = "User") String name){
        return ResponseEntity.ok("Hello " + name);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(Model model, @RequestParam(value = "name", defaultValue = "User") String name){

        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        model.addAttribute("serverTime", dateFormat.format(new Date()));
        model.addAttribute("toUserMessage", "Hello" + name);
        return "home.html";
    }

}
