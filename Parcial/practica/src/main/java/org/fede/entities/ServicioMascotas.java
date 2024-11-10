package org.fede.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioMascotas {
    private List<Mascotas> mascotasEnMemoria = new ArrayList<>();
    private Map<String, Habilidades> habilidadesMap = new HashMap<>();

    @PersistenceContext
    private EntityManager em;

    //Constructor

    public ServicioMascotas(EntityManager em) {
        this.em = em;
    }

    //Cargar mascotas desde csv
    public void cargarMascotasDesdeCSV(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            //Saltar primera linea
            br.readLine();

            String linea;
            while((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                //Extrer y asignar valores
                String nombre = datos[0];
                int nivelEnergia = Integer.parseInt(datos[1]);
                int nivelHumor = Integer.parseInt(datos[2]);

                //Crear la lista de habilidades para la mascota
                List<Habilidades> habilidades = new ArrayList<>();
                String[] habilidadesArray = datos[0].split(";");

                for (String habilidadesNombre : habilidadesArray) {
                    Habilidades habilidades1 = habilidadesMap.computeIfAbsent(habilidadesNombre.trim(),
                            h -> new Habilidades());
                    habilidades.add(habilidades1);
                }

                //Crear objeto mascota y asignar sus habilidades
                Mascotas mascota = new Mascotas(nombre,
                        nivelEnergia,
                        nivelHumor,
                        habilidades);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para guardar solo las mascotas vivas en la base de datos
    public void guardarVivas() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            for (Mascotas mascotas : mascotasEnMemoria) {
                if (mascotas.estaViva()) {
                    //Persistir mascota viva
                    em.merge(mascotas); //merge la va a crear si no existe o actualizar si existe
                }
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    //Metodo para obtener mascotas felices (nivel hunmor > 3)
    public List<Mascotas> getFelices() {
        String jpql = "SELECT m FROM Mascotas m WHERE m.nivelHumor > 3";
        return em.createQuery(jpql, Mascotas.class).getResultList();
    }

    //metodo para ver mascotas
    public List<Mascotas> getMascotasEnMemoria() {
        return mascotasEnMemoria;
    }

}
