package com.axa.ch.its.inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.axa.ch.its.inventory.model.UserRole;

public class DaoUser implements DaoInventoryGeneric<Users>{
	private String TABLE_NAME;
	private Connection con;
	private static Logger log = Logger.getLogger(DaoUser.class.getName());
	
	public DaoUser(Connection con, String tableName) {
        this.con = con;
        this.TABLE_NAME = tableName;
    }
 
	public List<Users> loadAll() {
		List<Users> list = new ArrayList<>();

		try {
			PreparedStatement prepStmt = con.prepareStatement(
					"SELECT id, firstname, lastname, phone, role FROM " + TABLE_NAME);
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
                if (result.getInt("role") == 1) {
                    list.add(new Users(result.getString("id"), result.getString("firstname"), result.getString("lastname"),
                            result.getString("phone"), UserRole.ADMIN));
                } else {
                    list.add(new Users(result.getString("id"), result.getString("firstname"), result.getString("lastname"),
                            result.getString("phone"), UserRole.USER));
                }
			}
			
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL-Fehler in LoadAll HighscoreEntry", e);

			for (; e != null; e = e.getNextException()) {
				log.severe("Message: " + e.getMessage());
				log.severe("SQL State: " + e.getSQLState());
				log.severe("Error Code: " + e.getErrorCode());
			}
		}

		return list;
	}
	
	public void save(Users entry) {
		
		try {
			PreparedStatement prepStmt = con.prepareStatement(
					"INSERT INTO " + TABLE_NAME + " (id, firstname, lastname, phone, role) VALUES (?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			prepStmt.setString(1, entry.getId());
			prepStmt.setString(2, entry.getFirstname());
			prepStmt.setString(3, entry.getLastname());
			prepStmt.setString(4, entry.getPhone());
			
			if (entry.getRole() == UserRole.ADMIN) {
				prepStmt.setInt(5, 1);
            } else {
            	prepStmt.setInt(5, 0);
            }
			
			prepStmt.executeUpdate();


		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL-Fehler in SAVE HighscoreEntry", e);

			for (; e != null; e = e.getNextException()) {
				log.severe("Message: " + e.getMessage());
				log.severe("SQL State: " + e.getSQLState());
				log.severe("Error Code: " + e.getErrorCode());
			}
		}
	}
	
	public void update(Users entry) {

		try {
			PreparedStatement prepStmt = con.prepareStatement("UPDATE " + TABLE_NAME
					+ " SET id = ?, firstname = ?, lastname = ?, phone = ?, role = ? WHERE id = ?");

			prepStmt.setString(1, entry.getId());
			prepStmt.setString(2, entry.getFirstname());
			prepStmt.setString(3, entry.getLastname());
			prepStmt.setString(4, entry.getPhone());
			
			if (entry.getRole() == UserRole.ADMIN) {
				prepStmt.setInt(5, 1);
            } else {
            	prepStmt.setInt(5, 0);
            }
			
			if (prepStmt.executeUpdate() <= 0) {
				log.severe("Update nicht erfolgreich, keine SQL-Exception");
				throw new NoSuchElementException("Update nicht erfolgreich");
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL-Fehler in UPDATE HighscoreEntry", e);

			for (; e != null; e = e.getNextException()) {
				log.severe("Message: " + e.getMessage());
				log.severe("SQL State: " + e.getSQLState());
				log.severe("Error Code: " + e.getErrorCode());
			}
		}
	}
	
	public void delete(Users entry) {
		try {
			PreparedStatement prepStmt = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE id = ?");

			prepStmt.setString(1, entry.getId());

			if (prepStmt.executeUpdate() == 0) {
				log.severe("zu l�schender Objekt nicht in DB vorhanden. Film ID: " + entry.getId());
				throw new NoSuchElementException(
						"zu l�schender Objekt nicht in DB vorhanden");
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL-Fehler in DELETE Users", e);

			for (; e != null; e = e.getNextException()) {
				log.severe("Message: " + e.getMessage());
				log.severe("SQL State: " + e.getSQLState());
				log.severe("Error Code: " + e.getErrorCode());
			}
		}
	}
	
	public Users getById(Users entry) {
		Users found = null;
		try {
			PreparedStatement prepStmt = con.prepareStatement("SELECT id, firstname, lastname, phone, role FROM " + TABLE_NAME + " WHERE id = ?");
			prepStmt.setString(1, entry.getId());

			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
                if (result.getInt("role") == 1) {
                    found = (new Users(result.getString("id"), result.getString("firstname"), result.getString("lastname"),
                            result.getString("phone"), UserRole.ADMIN));
                } else {
                    found = (new Users(result.getString("id"), result.getString("firstname"), result.getString("lastname"),
                            result.getString("phone"), UserRole.USER));
                }
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL-Fehler in getById HighscoreEntry", e);

			for (; e != null; e = e.getNextException()) {
				log.severe("Message: " + e.getMessage());
				log.severe("SQL State: " + e.getSQLState());
				log.severe("Error Code: " + e.getErrorCode());
			}
		}
		return found;
	}
	
}
