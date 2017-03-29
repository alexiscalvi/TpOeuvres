package com.epul.oeuvres.metier;

import java.io.Serializable;


/**
 * The persistent class for the adherent database table.
 * 
 */

public class Adherent implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idAdherent;
	private String nomAdherent;
	private String prenomAdherent;
	private String villeAdherent;

	
	public Adherent(int idAdherent, String nomAdherent, String prenomAdherent, String villeAdherent) {
		super();
		this.idAdherent = idAdherent;
		this.nomAdherent = nomAdherent;
		this.prenomAdherent = prenomAdherent;
		this.villeAdherent = villeAdherent;
	}

	public Adherent() {
	}

	public int getIdAdherent() {
		return this.idAdherent;
	}

	public void setIdAdherent(int idAdherent) {
		this.idAdherent = idAdherent;
	}

	public String getNomAdherent() {
		return this.nomAdherent;
	}

	public void setNomAdherent(String nomAdherent) {
		this.nomAdherent = nomAdherent;
	}

	public String getPrenomAdherent() {
		return this.prenomAdherent;
	}

	public void setPrenomAdherent(String prenomAdherent) {
		this.prenomAdherent = prenomAdherent;
	}

	public String getVilleAdherent() {
		return this.villeAdherent;
	}

	public void setVilleAdherent(String villeAdherent) {
		this.villeAdherent = villeAdherent;
	}

	public String getPrenomNomAdherent() {
		return this.getPrenomAdherent() + " " + this.getNomAdherent();
	}

}