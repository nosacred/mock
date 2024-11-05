package com.example.testmock.controllers;


import com.example.testmock.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private DBController dbController;

    Random random = new Random();

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<?> selectUserFromDB(@RequestParam(required = false) String login) throws InterruptedException {

        User user;
        int delay = random.nextInt(1001) + 1000;
        Thread.sleep(delay);

        try {
            user = dbController.getUserByLogin(login);
        } catch (SQLException dbCustomException) {
            return ResponseEntity.status(500).body(dbCustomException.getMessage());
        }
        return ResponseEntity.status(200).body(user.toString());
    }


    @PostMapping(value = "/post", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> insertUserToDB(@RequestBody HashMap<String, String> data) throws InterruptedException {

        ResponseEntity<String> responseEntity;
        int delay = random.nextInt(1001) + 1000;
        Thread.sleep(delay);
        try {
            if (data.size() != 3) {
                throw new SQLException("Incorrect request body(Не правильное кол-во)");
            }
            if (data.get("login") == null || data.get("password") == null || data.get("email") == null) {
                throw new SQLException("Incorrect request body(Пустые поля в запросе!)");
            }
            if (data.get("login").trim().isEmpty() || data.get("password").trim().isEmpty() || data.get("email").trim().isEmpty()) {
                throw new SQLException("Incorrect request body(Пустые поля в запросе!)");
            }
            User user = new User(data.get("login").trim(), data.get("password").trim(), data.get("email"));
            dbController.insertUserToDB(user);
            responseEntity = ResponseEntity.status(200).body("Пользователь: " + user.toString() + " успешно добавлен");

        } catch (SQLException exception) {
            responseEntity = ResponseEntity.status(400).body(exception.getMessage());
        }
        return responseEntity;
    }

}


