package com.axa.ch.its.inventory.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.ServiceUnavailableException;

import com.axa.ch.its.inventory.model.Article;
import com.axa.ch.its.inventory.model.InventoryDataNotFoundException;
import com.axa.ch.its.inventory.model.LendArticle;
import com.axa.ch.its.inventory.model.LendState;

public class DaoLendArticle implements DaoInventoryGeneric<LendArticle> {
	private String TABLE_NAME;
	private Connection con;
	private static Logger log = Logger.getLogger(DaoLendArticle.class.getName());
	private DaoManager daoManager;
	private DaoArticle daoArticle;
	private DaoUser daoUser;

	public DaoLendArticle(Connection con, String tableName) {
		this.con = con;
		this.TABLE_NAME = tableName;
		daoManager = DaoManager.getInstance();
		try {
			daoArticle = (DaoArticle) daoManager.getDao(DbTables.ARTICLE);
			daoUser = (DaoUser) daoManager.getDao(DbTables.USER);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(LendArticle entry) throws InventoryDataNotFoundException {
		saveAndReturnId(entry);
	}

	private int saveAndReturnId(LendArticle entry) {
		int id = -1;
		try {
			PreparedStatement prepStmt = con.prepareStatement("INSERT INTO " + TABLE_NAME
					+ " ( `fk_article_id`, `fk_user_id`, `state`, `lending_start_date`, `lending_planned_end_date`,"
					+ " `returned_date`, `fk_returned_user_id`) VALUES (?, ?, ?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			prepStmt.setString(1, entry.getArticle().getId());
			prepStmt.setString(2, entry.getUser().getId());
			switch (entry.getState()) {
			case LEND:
				prepStmt.setInt(3, 0);
				break;
			case RETURNED:
				prepStmt.setInt(3, 1);
				break;
			case LOST:
				prepStmt.setInt(3, 2);
				break;
			case REMINDED:
				prepStmt.setInt(3, 3);
				break;
			default:
				break;
			}
			
			prepStmt.setDate(4, Date.valueOf(entry.getLendingStartDate()));
			prepStmt.setDate(5, Date.valueOf(entry.getLendingPlannedEndDate()));
			prepStmt.setDate(6, null);
			prepStmt.setString(7, null);
			prepStmt.execute();

			ResultSet result = prepStmt.getGeneratedKeys();
			
			while(result.next()) {
				id = result.getInt(1);
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL-Fehler in SAVE HighscoreEntry", e);

			for (; e != null; e = e.getNextException()) {
				log.severe("Message: " + e.getMessage());
				log.severe("SQL State: " + e.getSQLState());
				log.severe("Error Code: " + e.getErrorCode());
			}
		}
		return id;
	}

	@Override
	public void update(LendArticle entry) throws InventoryDataNotFoundException {
		try {
			PreparedStatement prepStmt = con.prepareStatement("UPDATE " + TABLE_NAME
					+ " SET id = ?, fk_article_id = ?, `fk_user_id` = ?, `state` = ?, `lending_start_date` = ?, `lending_planned_end_date` = ?, `returned_date` = ?, `fk_returned_user_id` = ? WHERE id = ?");

			prepStmt.setString(1, entry.getArticle().getId());
			prepStmt.setString(2, entry.getUser().getId());
			switch (entry.getState()) {
			case LEND:
				prepStmt.setInt(3, 0);
				break;
			case RETURNED:
				prepStmt.setInt(3, 1);
				break;
			case LOST:
				prepStmt.setInt(3, 2);
				break;
			case REMINDED:
				prepStmt.setInt(3, 3);
				break;
			default:
				break;
			}
			prepStmt.setDate(4, Date.valueOf(entry.getLendingStartDate()));
			prepStmt.setDate(5, Date.valueOf(entry.getLendingPlannedEndDate()));
			prepStmt.setDate(6, null);
			prepStmt.setString(7, null);
			prepStmt.execute();

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
	
	public void updateReturn(LendArticle entry) {
        // System.out.println("Hier müssste die Datenbank aktualisiert werden.");
        // System.out.println(entry.getArticle());
        try {
            PreparedStatement prepStmt = con.prepareStatement("UPDATE " + TABLE_NAME
                    + " SET state = 99, returned_date = ?, fk_returned_user_id = ? WHERE fk_article_id = ? AND state = 0");

 

            prepStmt.setDate(1, Date.valueOf(entry.getReturnedData()));
            prepStmt.setString(2, entry.getReturnedByUserId());
            prepStmt.setString(3, entry.getArticle().getId());

 

            // Abfrage ob das Update gelungen ist.
            if (prepStmt.executeUpdate() == 0) {
                throw new NoSuchElementException("Update nicht erfolgreich");
            }

 

            prepStmt.close();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQL-Fehler in UPDATE movie", e);

 

            for (; e != null; e = e.getNextException()) {
                log.severe("Message: " + e.getMessage());
                log.severe("SQL State: " + e.getSQLState());
                log.severe("Error Code: " + e.getErrorCode());
            }
        }

 

    }

	@Override
	public void delete(LendArticle entry) throws InventoryDataNotFoundException {
		try {
			PreparedStatement prepStmt = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE id = ?");

			prepStmt.setInt(1, entry.getId());

			if (prepStmt.executeUpdate() == 0) {
				log.severe("zu l�schender Objekt nicht in DB vorhanden. ID: " + entry.getId());
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

	@Override
	public LendArticle getById(LendArticle entry) throws InventoryDataNotFoundException {
		LendArticle founded = null;
		try {
			PreparedStatement prepStmt = con.prepareStatement(
					"SELECT `id`, `fk_article_id`, `fk_user_id`, `state`, `lending_start_date`, `lending_planned_end_date`,"
							+ " `returned_date`, `fk_returned_user_id` FROM " + TABLE_NAME + " WHERE id = ?");
			prepStmt.setInt(1, entry.getId());

			ResultSet result = prepStmt.executeQuery();
			Article article = daoArticle.getById(new Article(result.getString("fk_article_id")));
			Users user = daoUser.getById(new Users(result.getString("fk_user_id")));
			LendState state = null;
			switch (result.getInt("state")) {
			case 0:
				state = LendState.LEND;
				break;
			case 1:
				state = LendState.RETURNED;
				break;
			case 2:
				state = LendState.LOST;
				break;
			case 3:
				state = LendState.REMINDED;
				break;
			default:
				break;
			}
			founded = new LendArticle(article, user, result.getInt("id"), state,
					result.getDate("lending_start_date").toLocalDate(),
					result.getDate("lending_planned_end_date").toLocalDate(),
					result.getDate("returned_date").toLocalDate(), 
					result.getString("fk_returned_user_id"));

		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL-Fehler in getById HighscoreEntry", e);

			for (; e != null; e = e.getNextException()) {
				log.severe("Message: " + e.getMessage());
				log.severe("SQL State: " + e.getSQLState());
				log.severe("Error Code: " + e.getErrorCode());
			}

		}
		return founded;
	}

	/*public boolean isArticleAlreadyLended(LendArticle entry) throws InventoryDataNotFoundException {
		PreparedStatement prepStmt;
		try {
			prepStmt = con.prepareStatement("SELECT `id` FROM " + TABLE_NAME + " WHERE fk_article_id = ? AND state = 0");
			prepStmt.setString(1, entry.getArticle().getId());
			ResultSet result = prepStmt.executeQuery();
			if(result.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}*/
	
	 public boolean isArticleAlreadyLended(LendArticle entry) {
	        if (entry != null) {
	            try {
	                PreparedStatement stmt = con
	                        .prepareStatement("Select id From " + TABLE_NAME + " Where fk_article_id = ? and state = 0");
	                stmt.setString(1, entry.getArticle().getId());
	                ResultSet result = stmt.executeQuery();
	                if (result.next()) {
	                    return true;
	                } else {
	                    return false;
	                }

	            	} catch (SQLException e) {
		                e.printStackTrace();
		                System.out.println("SQL-Fehler in SAVE " + e);
	            	}
	        }
	        return false;

	 

	    }
	
	public ArrayList<LendArticle> loadAllOverdue() {
		//Alle wo noch ausgelehnt sind
		ArrayList<LendArticle> LendArticle = null;
		try {
			PreparedStatement prepStmt = con.prepareStatement(
					"SELECT `id`, `fk_article_id`, `fk_user_id`, `state`, `lending_start_date`, `lending_planned_end_date`,"
							+ " `returned_date`, `fk_returned_user_id` FROM " + TABLE_NAME + " WHERE returned_date = null");

			ResultSet result = prepStmt.executeQuery();
			Article article = daoArticle.getById(new Article(result.getString("fk_article_id")));
			Users user = daoUser.getById(new Users(result.getString("fk_user_id")));
			LendState state = null;
			switch (result.getInt("state")) {
			case 0:
				state = LendState.LEND;
				break;
			case 1:
				state = LendState.RETURNED;
				break;
			case 2:
				state = LendState.LOST;
				break;
			case 3:
				state = LendState.REMINDED;
				break;
			default:
				break;
			}
			LendArticle.add(new LendArticle(article, user, result.getInt("id"), state,
					result.getDate("lending_start_date").toLocalDate(),
					result.getDate("lending_planned_end_date").toLocalDate(),
					result.getDate("returned_date").toLocalDate(), 
					result.getString("fk_returned_user_id")));

		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL-Fehler in getById HighscoreEntry", e);

			for (; e != null; e = e.getNextException()) {
				log.severe("Message: " + e.getMessage());
				log.severe("SQL State: " + e.getSQLState());
				log.severe("Error Code: " + e.getErrorCode());
			}

		}
		return LendArticle;
		
	}
	
	public ArrayList<LendArticle> loadAllByUser(LendArticle entry) {
        ArrayList<LendArticle> lendArticles = new ArrayList<LendArticle>();
        try {
            PreparedStatement stmt = con
                    .prepareStatement("Select id, " + "fk_article_id, fk_user_id, state, lending_start_date, "
                            + "lending_planned_end_date, returned_date, fk_returned_user_id From " + TABLE_NAME
                            + " Where fk_user_id = ?");
            stmt.setString(1, entry.getUser().getId());

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                LocalDate[] dates = new LocalDate[3];

                for (int i = 5; i <= 7; i++) {
                    if (result.getDate(i) != null) {
                        dates[i - 5] = result.getDate(i).toLocalDate();
                    }
                }
                LendState lendstate = null;
                switch (result.getInt(4)) {
                case 0:
                    lendstate = LendState.LEND;
                    break;
                case 1:
                    lendstate = LendState.RETURNED;
                    break;
                case 2:
                    lendstate = LendState.LOST;
                    break;
                case 3:
                    lendstate = LendState.REMINDED;
                    break;
                default:
                    log.warning("Statusnummer nicht vorhanden "+result.getInt(4));
                    break;
                }
                Article article = daoArticle.getById(new Article(result.getString(2)));
                Users user = daoUser.getById(new Users(result.getString(3)));
                lendArticles.add(new LendArticle(article, user, result.getInt(1), lendstate, dates[0], dates[1],
                        dates[2], result.getString(8)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, "SQL-Fehler in SAVE", e);
            for (; e != null; e = e.getNextException()) {
                log.severe("Message: " + e.getMessage());
                log.severe("SQL State: " + e.getSQLState());
                log.severe("Error Code: " + e.getErrorCode());
            }
 
        }
        return lendArticles;
    }
	
	public ArrayList<LendArticle> loadAllByUserAndByDate(LendArticle entry, LocalDate overdueDate) {
		ArrayList<LendArticle> lendArticles = new ArrayList<LendArticle>();
        try {
            PreparedStatement stmt = con
                    .prepareStatement("Select id, " + "fk_article_id, fk_user_id, state, lending_start_date, "
                            + "lending_planned_end_date, returned_date, fk_returned_user_id From " + TABLE_NAME
                            + " Where fk_user_id = ? AND lending_start_date= ? ");
            stmt.setString(1, entry.getUser().getId());
            stmt.setDate(1, Date.valueOf(entry.getLendingStartDate()));

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                LocalDate[] dates = new LocalDate[3];

               for (int i = 5; i <= 7; i++) {
                    if (result.getDate(i) != null) {
                        dates[i - 5] = result.getDate(i).toLocalDate(); //Wandelt alle Zeiten um
                    }
                }
                LendState lendstate = null;
                switch (result.getInt(4)) {
                case 0:
                    lendstate = LendState.LEND;
                    break;
                case 1:
                    lendstate = LendState.RETURNED;
                    break;
                case 2:
                    lendstate = LendState.LOST;
                    break;
                case 3:
                    lendstate = LendState.REMINDED;
                    break;
                default:
                    log.warning("Statusnummer nicht vorhanden "+result.getInt(4));
                    break;
                }
                Article article = daoArticle.getById(new Article(result.getString(2)));
                Users user = daoUser.getById(new Users(result.getString(3)));
                lendArticles.add(new LendArticle(article, user, result.getInt(1), lendstate,
                		dates[0], dates[1],
                        dates[2], result.getString(8)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, "SQL-Fehler in SAVE", e);
            for (; e != null; e = e.getNextException()) {
                log.severe("Message: " + e.getMessage());
                log.severe("SQL State: " + e.getSQLState());
                log.severe("Error Code: " + e.getErrorCode());
            }
 
        }
        return lendArticles;
	}
	
	
}
