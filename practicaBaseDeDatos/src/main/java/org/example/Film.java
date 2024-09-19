package org.example;

import java.util.ArrayList;
import java.util.List;

public class Film {
    private List<Title> titles;
    private List<Integer> filmId;
    private List<Lenguage> lenId;


    public Film() {
        this.titles = new ArrayList<>();
        this.filmId = new ArrayList<>();
        this.lenId = new ArrayList<>();
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public void mostrarPeliculas() {
        for (Title t : titles) {
            System.out.println(t);
        }
    }

    public List<Lenguage> getLenId() {
        return lenId;
    }

    public void setLenId(List<Lenguage> lenId) {
        this.lenId = lenId;
    }

    public void agregarTitulos(Title title) {
        titles.add(title);
    }

    public void agregarLenguage(Lenguage lenguage) {
        lenId.add(lenguage);
    }
}
