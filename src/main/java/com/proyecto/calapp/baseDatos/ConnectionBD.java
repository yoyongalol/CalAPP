package com.proyecto.calapp.baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    private static final String FILE = "connection.xml";

    private ConnectionBD() {
        // Constructor privado para evitar instancias innecesarias
    }

    public static Connection getConnection() {
        try {
            ConnectionProperties properties = XMLManager.readXML(new ConnectionProperties(), FILE);
            return DriverManager.getConnection(
                    properties.getURL(),
                    properties.getUser(),
                    properties.getPassword()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

