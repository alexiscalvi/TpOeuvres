package com.epul.oeuvres.metier;

import java.io.Serializable;


/**
 * The persistent class for the proprietaire database table.
 * 
 */

public class Proprietaire implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idProprietaire;
	private String nomProprietaire;
    private String prenomProprietaire;
    
	public Proprietaire() {
	}

	public int getIdProprietaire() {
		return this.idProprietaire;
	}

	public void setIdProprietaire(int idProprietaire) {
		this.idProprietaire = idProprietaire;
	}

	public String getNomProprietaire() {
		return this.nomProprietaire;
	}

	public void setNomProprietaire(String nomProprietaire) {
		this.nomProprietaire = nomProprietaire;
	}

	public String getPrenomProprietaire() {
		return this.prenomProprietaire;
	}

	public void setPrenomProprietaire(String prenomProprietaire) {
		this.prenomProprietaire = prenomProprietaire;
	}

	public String getNomComplet() {
		return this.getPrenomProprietaire() + " " + this.getNomProprietaire();
	}

}