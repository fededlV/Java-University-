import dominio.modelo.Actor;
import jakarta.persistence.*;
import org.sqlite.JDBC;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory(
                        "sakila-pu"
                );

        EntityManager em = emf.createEntityManager();

        var q = em.createQuery(
                "SELECT a " +
                        "FROM Actor a " +
                        "WHERE a.id > :limite",
                Actor.class
        );
        q.setParameter("limite", 20);
        List<Actor> l = q.getResultList();

        for (Actor a : l) {
            System.out.println(a);
        }


        var q2 = em.createNamedQuery("Actor.findAll", Actor.class);
        var res = q2.getResultList();
        for(Actor a : res){
            System.out.println(a);
        }

        em.close();
        emf.close();
    }
}
