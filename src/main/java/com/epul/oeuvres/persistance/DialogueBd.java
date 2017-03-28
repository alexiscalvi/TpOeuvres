package com.epul.oeuvres.persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.epul.oeuvres.meserreurs.*;

public class DialogueBd {

	private static DialogueBd instance = null;

	public static DialogueBd getInstance() {
		if (instance == null) {
			instance = new DialogueBd();
		}
		return instance;
	}

	private DialogueBd() {
		super();
	}

	public void insertionBD(String mysql) throws MonException {
		Connection cnx = null;
		try {
			cnx = Connexion.getInstance().getConnexion();
			Statement unstatement = cnx.createStatement();
			unstatement.execute(mysql);
			// on ferme la connexion
			cnx.close();
		} catch (SQLException e)

		{
			System.out.println("Erreur :" + e.getMessage());
			System.out.println(mysql);
			new MonException(e.getMessage());
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			throw new MonException(e.getMessage());
		}
	}

	public List<Object> lectureParametree(String req, Map mParams) throws MonException {
		Connection cnx = null;
		PreparedStatement ps;
		ResultSet rs;
		List<Object> mesRes = new ArrayList<Object>();
		int i;
		int nbCols;
		try {
			cnx = Connexion.getInstance().getConnexion();
			ps = cnx.prepareStatement(req);
			setParametres(ps, (Map) mParams.get(0));
			ps.executeQuery("SET NAMES UTF8");
			// Execution de la requete
			rs = ps.executeQuery();
			// on retrouve le nombre de colonnes de la requ�te
			ResultSetMetaData rsmd = rs.getMetaData();
			nbCols = rsmd.getColumnCount();
			i = 1;
			// on balaie toutes les lignes
			while (rs.next()) {

				// On balaie les colonnes
				i = 1;
				while (i <= nbCols) {
					mesRes.add(rs.getObject(i));
					i++;
				}
			}
			cnx.close();
			// Retourner la table
			return (mesRes);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new MonException(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new MonException(e.getMessage());
		} finally {
			// S'il y a eu un probl�me, la connexion
			// peut �tre encore ouverte, dans ce cas
			// il faut la fermer.

			if (cnx != null)
				try {
					cnx.close();
				} catch (SQLException e) {
				}
		}
	}

	public List<Object> lecture(String req) throws MonException {
		Connection cnx = null;
		Statement stmt;
		ResultSet rs;
		List<Object> mesRes = new ArrayList<Object>();
		int i;
		int nbCols;
		try {

			cnx = Connexion.getInstance().getConnexion();
			stmt = cnx.createStatement();
			stmt.executeQuery("SET NAMES UTF8");
			// Execution de la requete
			rs = stmt.executeQuery(req);
			// on retrouve le nombre de colonnes de la requ�te
			ResultSetMetaData rsmd = rs.getMetaData();
			nbCols = rsmd.getColumnCount();
			i = 1;
			// on balaie toutes les lignes
			while (rs.next()) {

				// On balaie les colonnes
				i = 1;
				while (i <= nbCols) {
					mesRes.add(rs.getObject(i));
					i++;
				}
			}
			cnx.close();
			// Retourner la table
			return (mesRes);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new MonException(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new MonException(e.getMessage());
		} finally {
			// S'il y a eu un probl�me, la connexion
			// peut �tre encore ouverte, dans ce cas
			// il faut la fermer.

			if (cnx != null)
				try {
					cnx.close();
				} catch (SQLException e) {
				}
		}
	}	
	
	public void execute(String mysql) throws MonException {
		Connection cnx = null;
		try {
			cnx = Connexion.getInstance().getConnexion();
			Statement unstatement = cnx.createStatement();
			unstatement.execute(mysql);
			System.out.println(mysql);
			// on ferme la connexion
			cnx.close();
		} catch (SQLException e) {
			throw new MonException(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new MonException(e.getMessage());
		}
	}

	/*
	 * Affecte les param�tres du Dictionnaire aux param�tres du
	 * PreparedStatement La key du dictionnaire repr�sente la position du
	 * param�tre dans la requ�te La value est un objet dont on recherche le Type
	 * et selon ce type on transtypera la valeur � d�poser
	 * 
	 * @param ps PreparedStatement dont on veut alimenter les param�tres
	 * 
	 * @param mParam Dictionnaire des valeurs � d�poser
	 * 
	 * @return PreparedStatement affect� des valeurs
	 * 
	 * @throws Exception
	 */
	private PreparedStatement setParametres(PreparedStatement ps, Map mParam) throws Exception {
		String classe;
		for (Object indice : mParam.keySet()) {
			classe = mParam.get(indice).getClass().toString();
			if (classe.contains("Integer")) {
				ps.setInt((Integer) indice, (Integer) mParam.get(indice));
			} else if (classe.contains("String")) {
				ps.setString((Integer) indice, (String) mParam.get(indice));
			} else if (classe.contains("Double")) {
				ps.setDouble((Integer) indice, (Double) mParam.get(indice));
			} else if (classe.contains("Date")) {
				ps.setDate((Integer) indice, (Date) mParam.get(indice));
			}
		}
		return ps;
	}

}
