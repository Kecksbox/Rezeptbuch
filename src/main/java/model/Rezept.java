package model;

import java.util.ArrayList;

import entwurf.Model;

public class Rezept extends Model {

    private String name;

    private ArrayList<String> zutaten;

    private ArrayList<String> arbeitsschritte;

    public Rezept() {
        name = "";
        zutaten = new ArrayList<>();
        arbeitsschritte = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}