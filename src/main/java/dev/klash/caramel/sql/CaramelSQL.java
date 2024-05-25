package dev.klash.caramel.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CaramelSQL {

    public String host, port, database, username, password;
    private Connection connection;

    public CaramelSQL(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if(!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
        }
    }
    public boolean attemptConnect() {
        try {
            connect();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }

    }
    public void disconnect() {
        if(isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
