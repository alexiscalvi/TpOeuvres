package com.epul.oeuvres.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.epul.oeuvres.metier.Adherent;
import com.epul.oeuvres.meserreurs.MonException;

/**
 * Created by Gaetan on 25/02/2017.
 */
public class AdherentDAO extends DAO{


    public List<Adherent> findAll() {
        List<Adherent> adherents = new ArrayList<Adherent>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from adherent" );
            while( resultSet.next() ) {
                adherents.add(this.buildDomainObject(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adherents;
    }

    public Adherent find(int id) {
        Adherent adherent = null;
        try {
            String query = "select * from adherent where id_adherent=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setInt( 1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                adherent = this.buildDomainObject(resultSet);
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adherent;
    }

    public void insert(Adherent adherent) {
        try {
            String query = "insert into adherent (nom_adherent, prenom_adherent, ville_adherent) values (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, adherent.getNomAdherent());
            preparedStatement.setString( 2, adherent.getPrenomAdherent());
            preparedStatement.setString( 3, adherent.getVilleAdherent());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String query = "delete from adherent where id_adherent=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update( Adherent adherent ) {
        try {
            String query = "update adherent set nom_adherent=?, prenom_adherent=?, ville_adherent=?  where id_adherent=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, adherent.getNomAdherent());
            preparedStatement.setString( 2, adherent.getPrenomAdherent());
            preparedStatement.setString( 3, adherent.getVilleAdherent());
            preparedStatement.setString( 4, adherent.getIdAdherent() + "");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Adherent buildDomainObject(ResultSet row) throws SQLException, MonException {
        Adherent adherent = new Adherent();
        adherent.setIdAdherent( row.getInt( "id_adherent" ) );
        adherent.setNomAdherent( row.getString( "nom_adherent" ) );
        adherent.setPrenomAdherent( row.getString( "prenom_adherent" ) );
        adherent.setVilleAdherent( row.getString( "ville_adherent" ) );
        return adherent;
    }
}
