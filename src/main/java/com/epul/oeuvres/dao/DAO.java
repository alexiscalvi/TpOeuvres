package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.persistance.Connexion;

import java.sql.Connection;


abstract class DAO {

    static Connection connection;

    public DAO() {
        try {
            if (connection == null) {
                connection = Connexion.getInstance().getConnexion();
            }
        } catch (MonException e) {
            e.printStackTrace();
        }
    }
}
