package org.example;

import java.nio.file.Paths;

import org.example.service.FileParser;

import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
       private FileParser fileParser;
       private EntityManager em; 
       
       @BeforeEach
       public void setUp() {
        em = mock(EntityManager.class);
        fileParser = new FileParser(em);
       }
    }
}