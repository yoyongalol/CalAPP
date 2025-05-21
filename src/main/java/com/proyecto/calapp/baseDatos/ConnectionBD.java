package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    private static final String FILE = "connection.xml";
    private static Connection con;
    private static ConnectionBD _instance;

    private ConnectionBD() {
        ConnectionProperties properties = XMLManager.readXML(new ConnectionProperties(), FILE);
        try {
            con = DriverManager.getConnection(properties.getURL(),properties.getUser(),properties.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            con = null;
        }
    }

    public static Connection getConnection() {
        if(_instance == null) {
            _instance = new ConnectionBD();
        }
        return con;
    }
}
