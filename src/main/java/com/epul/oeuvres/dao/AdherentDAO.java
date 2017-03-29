package com.epul.oeuvres.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.epul.oeuvres.metier.Adherent;
import com.epul.oeuvres.meserreurs.MonException;

public class AdherentDAO extends DAO{

    /*
    builder
     */

    private Adherent buildDomainObject(ResultSet ajout) throws SQLException, MonException {
        Adherent adherent = new Adherent();
        adherent.setIdAdherent( ajout.getInt( "id_adherent" ) );
        adherent.setNomAdherent( ajout.getString( "nom_adherent" ) );
        adherent.setPrenomAdherent( ajout.getString( "prenom_adherent" ) );
        adherent.setVilleAdherent( ajout.getString( "ville_adherent" ) );
        return adherent;
    }

/*
    Ajout a la db
 */
    public boolean add(Adherent adherent) {
        try {
            String query = "insert into adherent (nom_adherent, prenom_adherent, ville_adherent) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setString( 1, adherent.getNomAdherent());
            ps.setString( 2, adherent.getPrenomAdherent());
            ps.setString( 3, adherent.getVilleAdherent());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    Suppression de l'element dans la db
     */

    public boolean delete(int idAdherent) {
        try {
            String query = "delete from adherent where id_adherent=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idAdherent);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    /*
    mise a jour d'un élément
     */

    public boolean update( Adherent adherent ) {
        try {
            String query = "update adherent set nom_adherent=?, prenom_adherent=?, ville_adherent=?  where id_adherent=?";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setString( 1, adherent.getNomAdherent());
            ps.setString( 2, adherent.getPrenomAdherent());
            ps.setString( 3, adherent.getVilleAdherent());
            ps.setString( 4, adherent.getIdAdherent() + "");
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    recuperer un seul élément
     */

    public Adherent get(int idAdherent) {
        Adherent adherent = null;
        try {
            String query = "select * from adherent where id_adherent=?";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setInt( 1, idAdherent);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.first()) {
                adherent = this.buildDomainObject(resultSet);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adherent;
    }

/*
    recuperer tout les elements
     */

    public List<Adherent> get() {
        List<Adherent> listAdherents = new ArrayList<Adherent>();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from adherent";
            ResultSet res = statement.executeQuery( query );
            while( res.next() ) {
                listAdherents.add(this.buildDomainObject(res));
            }
            res.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAdherents;
    }






}
