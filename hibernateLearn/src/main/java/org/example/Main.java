package org.example;

import com.mysql.cj.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Alien fede = new Alien();
        fede.setAid(101);
        fede.setName("Fede");
        fede.setColor("Green");


        Configuration con = new Configuration();
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        session.save(fede);
    }
}