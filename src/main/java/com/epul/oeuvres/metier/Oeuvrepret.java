package com.epul.oeuvres.metier;

import java.io.Serializable;


/**
 * The persistent class for the oeuvrepret database table.
 */

public class Oeuvrepret implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idOeuvrepret;
    private String titreOeuvrepret;
    private Proprietaire proprietaire;

    public Oeuvrepret() {
    }

    public int getIdOeuvrepret() {
        return this.idOeuvrepret;
    }

    public void setIdOeuvrepret(int idOeuvrepret) {
        this.idOeuvrepret = idOeuvrepret;
    }

    public String getTitreOeuvrepret() {
        return this.titreOeuvrepret;
    }

    public void setTitreOeuvrepret(String titreOeuvrepret) {
        this.titreOeuvrepret = titreOeuvrepret;
    }

    public Proprietaire getProprietaire() {
        return this.proprietaire;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }

}