package com.epul.oeuvres.dao;



import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epul.oeuvres.metier.Reservation;


public class ReservationDAO extends DAO{

    /*
        on peut reserver une oeuvre uniquement si elle n'a pas encore été réservée.
     */

    public boolean exist (int idOeuvre, int idAdherent) {
        boolean alreadyExist = false;
        try {
            String query = "select * from reservation where id_oeuvrevente=? and id_adherent=?";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setInt( 1, idOeuvre);
            ps.setInt( 2, idAdherent);
            ResultSet res = ps.executeQuery();

            if (res.first()) {
                alreadyExist = true;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alreadyExist;
    }


    /*
    Methode d'ajout d'une reservation
     */
    public boolean add(Reservation reservation) {

        /*
        On verifie si la reservation existe
         */

        if (this.exist(reservation.getOeuvrevente().getIdOeuvrevente(), reservation.getAdherent().getIdAdherent())) {
            return false;
        }

        try {
            String query = "insert into reservation (statut, id_oeuvrevente, id_adherent, date_reservation) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setString( 1, reservation.getStatut());
            ps.setInt( 2, reservation.getOeuvrevente().getIdOeuvrevente());
            ps.setInt( 3, reservation.getAdherent().getIdAdherent());
            ps.setDate( 4, (Date) reservation.getDate());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

/*
    suppression de la reservation
 */

    public boolean delete(int idOeuvreVente) {
        try {
            String query = "delete from reservation where id_oeuvrevente=?";
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
}
