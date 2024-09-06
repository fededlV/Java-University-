package org.example.Collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EjemploColecciones {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(22);
        l.add(-33);

        l.iterator();
        for(Integer i : l){
            System.out.println(i);
        }

        List<Integer> ld = new LinkedList<>();

        ld.add(1);
        ld.add(22);
        ld.add(-33);



        System.out.println(l);
        System.out.println(ld);
    }
}
