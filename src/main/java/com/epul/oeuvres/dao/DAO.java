package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.persistance.Connexion;

import java.sql.Connection;

/**
 * Created by Gaetan on 01/03/2017.
 */
abstract class DAO {

    static Connection conn;

    public DAO() {
        try {
            if (conn == null) {
                conn = Connexion.getInstance().getConnexion();
            }
        } catch (MonException e) {
            e.printStackTrace();
        }
    }
}
