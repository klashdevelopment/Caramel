package dev.klash.caramel.sql;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@ApiStatus.Experimental
public class CaramelSQLHelper {

    private CaramelSQL sql;
    public CaramelSQLHelper(CaramelSQL sql) {
        this.sql = sql;
    }

    public static String UUID_COLUMN = "UUID VARCHAR(100)";
    public static String PRIMARY_KEY(String key) {
        return "PRIMARY KEY (" + key + ")";
    }

    public void createTable(String table, String columns) {
        PreparedStatement ps;
        try {
            ps = sql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + table + " (" + columns + ")");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUUIDEntry(String table, UUID uuid) {
        try {
            if(!playerExists(table, uuid)) {
                PreparedStatement cr = sql.getConnection().prepareStatement("INSERT IGNORE INTO " + table + " (UUID) VALUES (?)");
                cr.setString(1, uuid.toString());
                cr.executeUpdate();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExists(String table, UUID uuid) {
        try {
            PreparedStatement ps = sql.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setIntValue(String table, UUID uuid, String column, int value) {
        try {
            createUUIDEntry(table, uuid); // Create player if they don't exist
            PreparedStatement ps = sql.getConnection().prepareStatement("UPDATE " + table + " SET " + column + "=? WHERE UUID=?");
            ps.setInt(1, value);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setStringValue(String table, UUID uuid, String column, String value) {
        try {
            PreparedStatement ps = sql.getConnection().prepareStatement("UPDATE " + table + " SET " + column + "=? WHERE UUID=?");
            ps.setString(1, value);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> T getValue (String table, UUID uuid, String column, Class<T> type) {
        try {
            PreparedStatement ps = sql.getConnection().prepareStatement("SELECT " + column + " FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getObject(column, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getIntValue(String table, UUID uuid, String column) {
        return getValue(table, uuid, column, Integer.class);
    }
    public String getStringValue(String table, UUID uuid, String column) {
        return getValue(table, uuid, column, String.class);
    }

}
