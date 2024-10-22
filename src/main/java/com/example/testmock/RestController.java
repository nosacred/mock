package com.example.testmock;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    Random random = new Random();

    @RequestMapping("/get")
    public @ResponseBody ResponcePost sendResponceGet(@RequestParam(name = "username", required = false, defaultValue = "Guest") String username,
                                                      @RequestParam(name = "password", required = false, defaultValue = "Guest") String password) throws InterruptedException {

        int delay =random.nextInt(1001)+1000;
        ResponcePost responcePost= new ResponcePost();
        responcePost.setUsername(username);
        responcePost.setPassword(password);
        Thread.sleep(delay);
        return responcePost;
    }

    @PostMapping(value = "/post", consumes = "Application/json")
    public @ResponseBody ResponcePost sendResponcePost(@RequestBody ResponcePost responcePost) throws InterruptedException {

        int delay =random.nextInt(1001)+1000;
        Thread.sleep(delay);
        return responcePost;
    }

}

