package org.demo4.courseexample;

import org.springframework.stereotype.Component;


public class MyFirstClass {

    private String myVar;

    public MyFirstClass(String myVar) {
        this.myVar = myVar;
    }

    public String sayHello() {
        return "Hello Giles ==> myVar = " + myVar;
    }
}
