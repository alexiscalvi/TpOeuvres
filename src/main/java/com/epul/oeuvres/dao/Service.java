package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import java.util.*;

import com.epul.oeuvres.metier.*;
import com.epul.oeuvres.persistance.*;

public class Service {

	// Mise à jour des caractéristiques d'un adhérent
	// Le booleen indique s'il s'agit d'un nouvel adhérent, auquel cas on fait
	// une création

	public void insertAdherent(Adherent unAdherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "insert into adherent  (nom_adherent,prenom_adherent,ville_adherent)  " + "values ('"
					+ unAdherent.getNomAdherent();
			mysql += "'" + ",'" + unAdherent.getPrenomAdherent() + "','" + unAdherent.getVilleAdherent() + "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	// gestion des adherents
	// Consultation d'un adhérent par son numéro
	// Fabrique et renvoie un objet adhérent contenant le résultat de la requête
	// BDD
	public Adherent consulterAdherent(int numero) throws MonException {
		
		 Map mParams = new HashMap();
	     Map mParam;
	  try
	  {
		String mysql = "select * from adherent where numero_adherent=?";
		 mParam = new HashMap();
	     mParam.put(1, numero);
	     mParams.put(0, mParam); 
		List<Adherent> mesAdh = consulterListeAdherents(mysql);
		if (mesAdh.isEmpty())
			return null;
		else {
			return mesAdh.get(0);
		}
	  } catch (MonException e)
		{
			throw e;
		}
	}

	// Consultation des adhérents
	// Fabrique et renvoie une liste d'objets adhérent contenant le résultat de
	// la requête BDD
	public List<Adherent> consulterListeAdherents() throws MonException {
		String mysql = "select * from adherent";
		return consulterListeAdherents(mysql);
	}

	private List<Adherent> consulterListeAdherents(String mysql) throws MonException {
		List<Object> rs;
		List<Adherent> mesAdherents = new ArrayList<Adherent>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs =unDialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On crée un stage
				Adherent unA = new Adherent();
				// il faut redecouper la liste pour retrouver les lignes
				unA.setIdAdherent(Integer.parseInt(rs.get(index + 0).toString()));
				unA.setNomAdherent(rs.get(index + 1).toString());
				unA.setPrenomAdherent(rs.get(index + 2).toString());
				unA.setVilleAdherent(rs.get(index + 3).toString());
				// On incrémente tous les 3 champs
				index = index + 4;
				mesAdherents.add(unA);
			}

			return mesAdherents;
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}
	
	
	public Oeuvrevente rechercherOeuvreIdParam(int id) throws MonException
	{
		
		String mysql = "";
		 Map mParams = new HashMap();
	     Map mParam;
	 	List<Object> rs;
		Oeuvrevente uneOeuvre=null;;
		try
		{
			mysql = "SELECT id_oeuvrevente, titre_oeuvrevente, etat_oeuvrevente,prix_oeuvrevente,id_proprietaire";
			mysql += " FROM Oeuvrevente WHERE id_Oeuvrevente = ? ";
		     mParam = new HashMap();
		     mParam.put(1, id);
		     mParams.put(0, mParam);  
		     rs=DialogueBd.getInstance().lectureParametree(mysql, mParams);
		     if (rs.size() > 0) {
					
					uneOeuvre = new Oeuvrevente();
					uneOeuvre.setIdOeuvrevente(Integer.parseInt(rs.get(0).toString()));
					uneOeuvre.setTitreOeuvrevente(rs.get(1).toString());
					uneOeuvre.setEtatOeuvrevente(rs.get(2).toString());
					uneOeuvre.setPrixOeuvrevente(Float.parseFloat(rs.get(3).toString()));
					int num = Integer.parseInt(rs.get(4).toString());
					// On appelle la recherche d'un propriétaire
					uneOeuvre.setProprietaire(rechercherProprietaire(num));
				}
		} 
		
		catch (MonException e)
		{
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
		return uneOeuvre;
		
	}
	
	 
	public Proprietaire rechercherProprietaire(int  id) throws MonException
	{
		
		String mysql = "";
		 Map mParams = new HashMap();
	     Map mParam;
	 	List<Object> rs;
		Proprietaire  unProprietaire=null;
		String requete = " select * from Proprietaire where id_Proprietaire ?";
		try 
		{
			 mParam = new HashMap();
		     mParam.put(1, id);
		     mParams.put(0, mParam);  
		     rs=DialogueBd.getInstance().lectureParametree(mysql, mParams);
			if (rs.size() > 0) {
			
				unProprietaire = new Proprietaire();
				
				unProprietaire.setIdProprietaire(Integer.parseInt(rs.get(0).toString()));
				unProprietaire.setNomProprietaire(rs.get(1).toString());
				unProprietaire.setPrenomProprietaire(rs.get(2).toString());
			}
		}

		catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
		return unProprietaire;
	}	

	
	

}
