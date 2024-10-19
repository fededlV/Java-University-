package org.demo4.courseexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseExampleApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(CourseExampleApplication.class, args);

        MyFirstService myFirstService = ctx.getBean(MyFirstService.class);

        System.out.println(myFirstService.tellAStory());
        /*System.out.println(myFirstService.getJavaVersion());
        System.out.println(myFirstService.readProperty());*/
        System.out.println(myFirstService.getCustomProperty());
        System.out.println(myFirstService.getCustomProperty2());
    }

}
