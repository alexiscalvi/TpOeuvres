package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Oeuvrepret;
import com.epul.oeuvres.dao.ProprietaireDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OeuvrepretDAO extends DAO{

     /*
    builder
     */

    private Oeuvrepret buildDomainObject(ResultSet row) throws SQLException, MonException {
        Oeuvrepret oeuvrepret = new Oeuvrepret();
        oeuvrepret.setIdOeuvrepret( row.getInt( "id_oeuvrepret" ) );
        oeuvrepret.setTitreOeuvrepret( row.getString( "titre_oeuvrepret" ) );
        oeuvrepret.setProprietaire(new ProprietaireDAO().get(row.getInt("id_proprietaire")));
        return oeuvrepret;
    }

    /*
    Ajout a la db
 */
    public boolean add(Oeuvrepret oeuvrePret) {
        try {
            String query = "insert into oeuvrepret (titre_oeuvrepret, id_proprietaire) values (?,?)";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setString( 1, oeuvrePret.getTitreOeuvrepret());
            ps.setInt( 2, oeuvrePret.getProprietaire().getIdProprietaire());
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

    public boolean delete( int idOeuvrePret ) {
        try {
            String query = "delete from oeuvrepret where id_oeuvrepret=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idOeuvrePret);
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


    public boolean update( Oeuvrepret oeuvrePret ) {
        try {
            String query = "update oeuvrepret set titre_oeuvrepret=?, id_proprietaire=? where id_oeuvrepret=?";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setString( 1, oeuvrePret.getTitreOeuvrepret());
            ps.setInt( 2, oeuvrePret.getProprietaire().getIdProprietaire());
            ps.setInt( 3, oeuvrePret.getIdOeuvrepret());
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
    public Oeuvrepret get(int idOeuvrePret) {
        Oeuvrepret oeuvrePret = null;
        try {
            String query = "select * from oeuvrepret where id_oeuvrepret=?";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setInt( 1, idOeuvrePret);
            ResultSet res = ps.executeQuery();
            if (res.first()) {
                oeuvrePret = this.buildDomainObject(res);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oeuvrePret;
    }

    /*
    recuperer tout les elements
     */


    public List<Oeuvrepret> get() {
        List<Oeuvrepret> listOeuvresPret = new ArrayList<Oeuvrepret>();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from oeuvrepret";
            ResultSet res = statement.executeQuery( query );
            while( res.next() ) {
                listOeuvresPret.add(this.buildDomainObject(res));
            }
            res.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOeuvresPret;
    }



}
