package com.axa.ch.its.inventory.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.naming.ServiceUnavailableException;

public class DaoManager {
	private static final DaoManager INSTANCE = new DaoManager();

    private static Logger log = Logger.getLogger(DaoManager.class.getName());

    private Connection con;

    private DaoManager() {

    }

    public static DaoManager getInstance() {
        return INSTANCE;
    }

    private Connection getConnection() throws SQLException, NamingException {
    	
    	
    	this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "");

        return this.con;
    }

    private void open() throws ServiceUnavailableException {
        try {
            if (this.con == null || this.con.isClosed()) {
                this.con = getConnection();
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Fehler beim �ffnen der DB-Connection", e);

            for (; e != null; e = e.getNextException()) {
                log.severe("Message: " + e.getMessage() + " / SQL State: " + e.getSQLState() + " / Error Code: "
                        + e.getErrorCode());
            }            
            throw new ServiceUnavailableException("Datenbank nicht verf�gbar");
        } catch (NamingException e) {            
            throw new ServiceUnavailableException("Datenbank nicht verf�gbar");
        }
    }
    
    public void close() {
        try {
            if (this.con != null) {
                this.con.close();
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Fehler beim Schliessen der DB-Connection", e);

            for (; e != null; e = e.getNextException()) {
                log.severe("Message: " + e.getMessage() + " / SQL State: " + e.getSQLState() + " / Error Code: "
                        + e.getErrorCode());
            }                     
        }
    }

    public DaoInventoryGeneric<?> getDao(DbTables table) throws ServiceUnavailableException {

    	DaoInventoryGeneric<?> dao = null;
        
        try {
            this.open();

            
            switch (table) {
                case USER:
                    dao = new DaoUser(this.con, "user");
                    break;    
                case ARTICLE:
                    dao = new DaoArticle(this.con, "article");
                    break;
                case LEND_ARTICLE:
                    dao = new DaoLendArticle(this.con, "lend_article");
                    break;
                default:
                    dao = null;
            }
        } catch (Exception e) {
            throw e;
        }

        return  dao;

    }
}
