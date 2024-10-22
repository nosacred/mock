package com.example.testmock;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResponceData {


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank
    String username;
    @NotBlank
    String password;

    String currentDate;

    public ResponceData(String user,String psw){
        this.username=user;
        this.password=psw;
        this.currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }


    @Override
    public String toString() {
        return "{\"username\": "+"\""+username+"\","+
                "\"password\": "+"\""+password+"\","+
                "\"currentDate\": "+"\""+currentDate+"\"}";
    }
}

