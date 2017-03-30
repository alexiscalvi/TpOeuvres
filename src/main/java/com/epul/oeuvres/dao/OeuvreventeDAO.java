package com.epul.oeuvres.dao;

import com.epul.oeuvres.exceptions.Exception1;
import com.epul.oeuvres.metier.Oeuvrevente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OeuvreventeDAO extends DAO{

     /*
    builder
     */

    private Oeuvrevente buildDomainObject(ResultSet ajout) throws SQLException, Exception1 {
        Oeuvrevente oeuvrevente = new Oeuvrevente();
        oeuvrevente.setIdOeuvrevente( ajout.getInt( "id_oeuvrevente" ) );
        oeuvrevente.setTitreOeuvrevente( ajout.getString( "titre_oeuvrevente" ) );
        oeuvrevente.setEtatOeuvrevente(ajout.getString("etat_oeuvrevente"));
        oeuvrevente.setPrixOeuvrevente(ajout.getFloat("prix_oeuvrevente"));
        oeuvrevente.setProprietaire(new ProprietaireDAO().get(ajout.getInt("id_proprietaire")));
        return oeuvrevente;
    }

    /*
    ajout d'oeuvre vente dans la db
     */

    public boolean add(Oeuvrevente oeuvreVente) {
        try {
            String query = "insert into oeuvrevente (titre_oeuvrevente, etat_oeuvrevente, prix_oeuvrevente, id_proprietaire) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setString( 1, oeuvreVente.getTitreOeuvrevente());
            ps.setString( 2, oeuvreVente.getEtatOeuvrevente());
            ps.setFloat( 3, oeuvreVente.getPrixOeuvrevente());
            ps.setInt( 4, oeuvreVente.getProprietaire().getIdProprietaire());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    suppression
     */
    public boolean delete( int idOeuvreVente ) {
        try {
            /*
            On supprime l'eventuelle reservation sur cette oeuvre
             */
            new ReservationDAO().delete(idOeuvreVente);

            String query = "delete from oeuvrevente where id_oeuvrevente=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idOeuvreVente);
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

    public boolean update( Oeuvrevente oeuvreVente ) {
        try {
            String query = "update oeuvrevente set titre_oeuvrevente=?, etat_oeuvrevente=?, prix_oeuvrevente=?, id_proprietaire=? where id_oeuvrevente=?";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setString( 1, oeuvreVente.getTitreOeuvrevente());
            ps.setString( 2, oeuvreVente.getEtatOeuvrevente());
            ps.setFloat( 3, oeuvreVente.getPrixOeuvrevente());
            ps.setInt( 4, oeuvreVente.getProprietaire().getIdProprietaire());
            ps.setInt( 5, oeuvreVente.getIdOeuvrevente());
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
    public Oeuvrevente get(int idOeuvreVente) {
        Oeuvrevente oeuvreVente = null;
        try {
            String query = "select * from oeuvrevente where id_oeuvrevente=?";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setInt( 1, idOeuvreVente);
            ResultSet res = ps.executeQuery();
            if (res.first()) {
                oeuvreVente = this.buildDomainObject(res);
            }
            ps.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oeuvreVente;
    }

    /*
    recuperer tout les elements
     */

    public List<Oeuvrevente> get() {
        List<Oeuvrevente> listOeuvresVente = new ArrayList<Oeuvrevente>();
        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery( "select * from oeuvrevente" );
            while( res.next() ) {
                listOeuvresVente.add(this.buildDomainObject(res));
            }
            res.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOeuvresVente;
    }



}
