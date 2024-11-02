package com.example.testmock.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class User {

    @NotNull
    private String login;
    @NotNull
    private String password;
    private LocalDate date;
    private String email;

    public User(String user, String psw, String email){
        this.login =user;
        this.password=psw;
        this.email=user+"@"+email;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "{\"username\": "+"\""+ login +"\","+
                "\"password\": "+"\""+password+"\","+
                "\"email\": "+"\""+email+"\","+
                "\"date\": "+"\""+ date +"\"}";
    }
}

