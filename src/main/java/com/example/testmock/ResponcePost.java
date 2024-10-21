package com.example.testmock;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class ResponcePost {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("currentDate")
    private String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));


//    @Override
//    public String toString() {
//        return "{\"username\":"+"\""+username+"\""+
//                "\"password\":"+"\""+password+"\""+
//                "\"currentDate\":"+"\""+currentDate+"\"}";
//    }
}

