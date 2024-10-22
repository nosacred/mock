package com.example.testmock;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Random;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    Random random = new Random();

    @RequestMapping(value = "/get",method = RequestMethod.GET, produces = "application/json")
    public  ResponseEntity<String> sendResponseGet() throws InterruptedException {

        int delay =random.nextInt(1001)+1000;
        ResponceData responceData = new ResponceData("user","psww2424w");
        Thread.sleep(delay);
        return  ResponseEntity.status(200).body(responceData.toString());
    }



    @PostMapping(value = "post1", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> sendResponsePost(@RequestBody HashMap<String,String> data) throws InterruptedException {

        if(data.size() !=2 ){
            return ResponseEntity.status(400).body("Не корректный запрос!");
        }
        if (data.get("username").trim().isEmpty() || data.get("password").trim().isEmpty() ){
            return ResponseEntity.status(400).body("Не корректный запрос! Пустые данные!");
        }

        int delay =random.nextInt(1001)+1000;
        Thread.sleep(delay);
        ResponceData responceData = new ResponceData(data.get("username").trim(), data.get("password").trim());
        return ResponseEntity.ok(responceData.toString());
    }


    @PostMapping(value = "post", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> sendResponsePost(@Valid @RequestBody ResponceData data) throws InterruptedException {

        int delay =random.nextInt(1001)+1000;
        Thread.sleep(delay);
        return ResponseEntity.ok(data.toString());
    }

}

