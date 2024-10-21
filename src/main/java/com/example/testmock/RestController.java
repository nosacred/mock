package com.example.testmock;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    @RequestMapping("/get")
    public String getMethod(@RequestParam(name = "login", required = false, defaultValue = "World") String login) throws InterruptedException {
        Thread.sleep(1234);
        return  "{\"username\": \"Alex\", \"password\": \"12345\", \"currentTime\": " +"\""+
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))+"\"}";
    }


    @PostMapping(value = "/post",produces = "Application/*", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public @ResponseBody ResponcePost getResponcePost(@RequestBody ResponcePost responcePost) throws InterruptedException {
        Thread.sleep(1234);
        return responcePost;
    }

}

