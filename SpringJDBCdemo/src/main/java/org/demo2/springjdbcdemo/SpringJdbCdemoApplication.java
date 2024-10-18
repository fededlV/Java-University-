package org.demo2.springjdbcdemo;

import org.demo2.springjdbcdemo.dao.AlienRepo;
import org.demo2.springjdbcdemo.model.Alien;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringJdbCdemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringJdbCdemoApplication.class, args);

        Alien alien1 = context.getBean(Alien.class);
        alien1.setId(111);
        alien1.setName("Federico");
        alien1.setTech("Java");

        AlienRepo repo = context.getBean(AlienRepo.class);
        repo.save(alien1);

        System.out.println(repo.findAll());
    }

}
