package com.axa.ch.its.inventory.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.axa.ch.its.inventory.model.Article;
import com.itextpdf.io.source.ByteArrayOutputStream;

public class DaoArticle implements DaoInventoryGeneric<Article> {
	private String TABLE_NAME;
	private Connection con;
	private static Logger log = Logger.getLogger(DaoUser.class.getName());

	public DaoArticle(Connection con, String tableName) {
		this.con = con;
		this.TABLE_NAME = tableName;
	}

	public void save(Article entry) {

		try {
			PreparedStatement prepStmt = con.prepareStatement(
					"INSERT INTO " + TABLE_NAME + " (id, description, image) VALUES (?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			ByteArrayOutputStream outClean = new ByteArrayOutputStream();
			ImageIO.write(entry.getProductImage(), "jpg", outClean);
			byte[] bufClean = outClean.toByteArray();
			ByteArrayInputStream streamClean = new ByteArrayInputStream(bufClean);

			prepStmt.setString(1, entry.getId());
			prepStmt.setString(2, entry.getDescription());
			prepStmt.setBlob(3, streamClean, streamClean.available());
			prepStmt.executeUpdate();

		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL-Fehler in SAVE HighscoreEntry", e);

			for (; e != null; e = e.getNextException()) {
				log.severe("Message: " + e.getMessage());
				log.severe("SQL State: " + e.getSQLState());
				log.severe("Error Code: " + e.getErrorCode());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(Article entry) {

		try {
			PreparedStatement prepStmt = con
					.prepareStatement("UPDATE " + TABLE_NAME + " SET id = ?, description = ?, image = ? WHERE id = ?");

			prepStmt.setString(1, entry.getId());
			prepStmt.setString(2, entry.getDescription());

			ByteArrayOutputStream outClean = new ByteArrayOutputStream();
			ImageIO.write(entry.getProductImage(), "jpg", outClean);
			byte[] bufClean = outClean.toByteArray();
			ByteArrayInputStream streamClean = new ByteArrayInputStream(bufClean);

			prepStmt.setBlob(3, streamClean, streamClean.available());

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(Article entry) {
		try {
			PreparedStatement prepStmt = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE id = ?");

			prepStmt.setString(1, entry.getId());

			if (prepStmt.executeUpdate() == 0) {
				log.severe("zu l�schender Objekt nicht in DB vorhanden. Film ID: " + entry.getId());
				throw new NoSuchElementException("zu l�schender Objekt nicht in DB vorhanden");
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

	public Article getById(Article entry) {
		Article founded = null;
		try {
			PreparedStatement prepStmt = con
					.prepareStatement("SELECT id, description, image FROM " + TABLE_NAME + " WHERE id = ?");
			prepStmt.setString(1, entry.getId());

			ResultSet result = prepStmt.executeQuery();

			BufferedImage img;
			InputStream is;
			while (result.next()) {
				is = result.getBlob("image").getBinaryStream();
				img = ImageIO.read(is);
				founded = new Article(result.getString("id"), result.getString("description"), img);
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL-Fehler in getById HighscoreEntry", e);

			for (; e != null; e = e.getNextException()) {
				log.severe("Message: " + e.getMessage());
				log.severe("SQL State: " + e.getSQLState());
				log.severe("Error Code: " + e.getErrorCode());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return founded;
	}
}
