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

/**
 * Created by Florian on 29/03/2017.
 */
public class OeuvrepretDAO extends DAO{


    public void insert(Oeuvrepret oeuvrepret) {
        try {
            String query = "insert into oeuvrepret (titre_oeuvrepret, id_proprietaire) values (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, oeuvrepret.getTitreOeuvrepret());
            preparedStatement.setInt( 2, oeuvrepret.getProprietaire().getIdProprietaire());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete( int id ) {
        try {
            String query = "delete from oeuvrepret where id_oeuvrepret=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOeuvrepret( Oeuvrepret oeuvrepret ) {
        try {
            String query = "update oeuvrepret set titre_oeuvrepret=?, id_proprietaire=? where id_oeuvrepret=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, oeuvrepret.getTitreOeuvrepret());
            preparedStatement.setInt( 2, oeuvrepret.getProprietaire().getIdProprietaire());
            preparedStatement.setInt( 3, oeuvrepret.getIdOeuvrepret());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Oeuvrepret> findAll() {
        List<Oeuvrepret> oeuvreprets = new ArrayList<Oeuvrepret>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from oeuvrepret" );
            while( resultSet.next() ) {
                oeuvreprets.add(this.buildDomainObject(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oeuvreprets;
    }

    public Oeuvrepret find(int id) {
        Oeuvrepret oeuvrepret = null;
        try {
            String query = "select * from oeuvrepret where id_oeuvrepret=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setInt( 1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                oeuvrepret = this.buildDomainObject(resultSet);
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oeuvrepret;
    }

    private Oeuvrepret buildDomainObject(ResultSet row) throws SQLException, MonException {
        Oeuvrepret oeuvrepret = new Oeuvrepret();
        oeuvrepret.setIdOeuvrepret( row.getInt( "id_oeuvrepret" ) );
        oeuvrepret.setTitreOeuvrepret( row.getString( "titre_oeuvrepret" ) );
        oeuvrepret.setProprietaire(new ProprietaireDAO().find(row.getInt("id_proprietaire")));
        return oeuvrepret;
    }
}
