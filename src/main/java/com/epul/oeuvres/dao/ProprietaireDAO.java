package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Proprietaire;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaetan on 06/03/2017.
 */
public class ProprietaireDAO extends DAO {

    public Proprietaire find(int id) {

        Proprietaire proprietaire = null;
        try {
            String query = "select * from proprietaire where id_proprietaire=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setInt( 1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                proprietaire = this.buildDomainObject(resultSet);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proprietaire;

    }

    public List<Proprietaire> findAll() {
        List<Proprietaire> proprietaires = new ArrayList<Proprietaire>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from proprietaire" );
            while( resultSet.next() ) {
                proprietaires.add(this.buildDomainObject(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proprietaires;
    }

    public void update( Proprietaire proprietaire ) {
        try {
            String query = "update proprietaire set prenom_proprietaire=?, nom_proprietaire=? where id_proprietaire=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, proprietaire.getPrenomProprietaire());
            preparedStatement.setString( 2, proprietaire.getNomProprietaire());
            preparedStatement.setString( 3, proprietaire.getIdProprietaire()+"");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Proprietaire buildDomainObject(ResultSet row) throws SQLException, MonException {
        Proprietaire proprietaire = new Proprietaire();
        proprietaire.setIdProprietaire( row.getInt( "id_proprietaire" ) );
        proprietaire.setNomProprietaire( row.getString( "nom_proprietaire" ) );
        proprietaire.setPrenomProprietaire(row.getString("prenom_proprietaire"));
        return proprietaire;
    }

    public void insert(Proprietaire proprietaire) {
        try {
            String query = "insert into proprietaire (prenom_proprietaire, nom_proprietaire) values (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, proprietaire.getPrenomProprietaire());
            preparedStatement.setString( 2, proprietaire.getNomProprietaire());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String query = "delete from proprietaire where id_proprietaire=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
