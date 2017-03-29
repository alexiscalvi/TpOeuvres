package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Proprietaire;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProprietaireDAO extends DAO {


    private Proprietaire buildDomainObject(ResultSet ajout) throws SQLException, MonException {
        Proprietaire proprietaire = new Proprietaire();
        proprietaire.setIdProprietaire( ajout.getInt( "id_proprietaire" ) );
        proprietaire.setNomProprietaire( ajout.getString( "nom_proprietaire" ) );
        proprietaire.setPrenomProprietaire(ajout.getString("prenom_proprietaire"));

        return proprietaire;
    }

    /*
    get(int) revoie l'objet Proprietaire de l'id correspondant
     */
    public Proprietaire get(int idProprietaire) {

        Proprietaire proprietaire = null;
        try {
            String query = "select * from proprietaire where id_proprietaire=?";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setInt( 1, idProprietaire);
            ResultSet res = ps.executeQuery();
            if (res.first()) {
                proprietaire = this.buildDomainObject(res);
            }
            ps.close();
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proprietaire;

    }

    /*
    get() revoie tout les Proprietaire
     */

    public List<Proprietaire> get() {
        List<Proprietaire> listProprietaires = new ArrayList<Proprietaire>();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from proprietaire";
            ResultSet res = statement.executeQuery(query);

            while( res.next() ) {
                listProprietaires.add(this.buildDomainObject(res));
            }

            res.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProprietaires;
    }

    /*
    update la db avec les nouvelles données du proprietaire
     */
    public boolean update( Proprietaire proprietaire ) {

        try {
            String query = "update proprietaire set prenom_proprietaire=?, nom_proprietaire=? where id_proprietaire=?";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setString( 1, proprietaire.getPrenomProprietaire());
            ps.setString( 2, proprietaire.getNomProprietaire());
            ps.setString( 3, proprietaire.getIdProprietaire()+"");
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   /*
   Ajout d'un proprietaire a la db
    */
    public boolean add(Proprietaire proprietaire) {
        try {
            String query = "insert into proprietaire (prenom_proprietaire, nom_proprietaire) values (?,?)";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setString( 1, proprietaire.getPrenomProprietaire());
            ps.setString( 2, proprietaire.getNomProprietaire());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    Suppression d'un proprietaire dans la db
     */

    public boolean delete(int idProprietaire) {
        try {
            String query = "delete from proprietaire where id_proprietaire=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idProprietaire);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
