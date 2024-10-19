package org.fede.coursespring1.controllers;

import org.fede.coursespring1.models.Order;
import org.fede.coursespring1.records.OrderRecord;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.ACCEPTED) //Show the status of our program.
    public String sayHello() {
        return "Hello World";
    }

    @PostMapping("/post")
    public String post(
            @RequestBody String message
    ) {
        return "Requested accepted and message is " + message ;
    }

    @PostMapping("/post-order")
    public String post(
            @RequestBody Order order
            ) {
        return "Requested accepted and message is " + order.toString();
    }

    @PostMapping("/post-order-record")
    public String post(
            @RequestBody OrderRecord order
            ) {
        return "Requested accepted and message is " + order.toString();
    }

    @GetMapping("/hello/{var-name}")
    public String pathVariable(
            @PathVariable("var-name") String var
    ) {
        return "my value = " + var;
    }

    @GetMapping("/hellovar")
    public String paramVar(
            @RequestParam("var-name") String var,
            @RequestParam("user-name") String user
    ) {
        return "my value = " + var + " " + user;
    }
}
