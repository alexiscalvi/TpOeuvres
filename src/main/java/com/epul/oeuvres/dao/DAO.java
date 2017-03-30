package com.epul.oeuvres.dao;

import com.epul.oeuvres.exceptions.Exception1;
import com.epul.oeuvres.persistance.Connexion;
import java.sql.Connection;

abstract class DAO {

    static Connection connection;

    public DAO() {
        try {
            if (connection == null) {
                connection = Connexion.getInstance().getConnexion();
            }
        } catch (Exception1 e) {
            e.printStackTrace();
        }
    }
}
