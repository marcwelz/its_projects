package com.axa.ch.its.inventory.presenter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.naming.ServiceUnavailableException;

import com.axa.ch.its.inventory.dao.DaoArticle;
import com.axa.ch.its.inventory.dao.DaoLendArticle;
import com.axa.ch.its.inventory.dao.DaoManager;
import com.axa.ch.its.inventory.dao.DaoUser;
import com.axa.ch.its.inventory.dao.DbTables;
import com.axa.ch.its.inventory.dao.Users;
import com.axa.ch.its.inventory.model.Article;
import com.axa.ch.its.inventory.model.LendArticle;
import com.axa.ch.its.inventory.view.InventoryMainView;

public class InventoryMainPresenter {
	
	private DialogStateWrapper dialogState;
	private DaoManager daoManager;
	private DaoUser daoUser;
	private DaoArticle daoArticle;
	private Users scannedUsers;
	private DaoLendArticle daoLendArticle;
	
	private ArrayList<Article> scannedArticles;
	
	InventoryMainView window;
	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//InventoryMainPresenter presenter = new InventoryMainPresenter();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
						
	}
	
	private InventoryMainPresenter() {	
		scannedArticles = new ArrayList<Article>();
		dialogState = new DialogStateWrapper();
		window = new InventoryMainView();
		setActionEvents();
	}
	
	private void setActionEvents() {	//Hier die actionlistener für die Buttons
		window.getBtnAbbrechen().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleCancel();				
				
			}
		});
		
		
		window.getBtnConfirmation().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handelCheckout();				
				
			}
		});
		
		window.getBtnAusleihen().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				handleLend();
			}
		});
		
		window.getBtnSubmit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				handleSubmit();
			}
		});
		
		window.getBtnRetour().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				handleReturn();
			}
		});
		
		window.getBtnAdmin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				handleInventoryAdmin();
			}
		});
		
		/*window.getBtnWarenkorb().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleWarenkorb();				
			}
		});*/			//Dieser Code funkioniert noch nicht
		
	}
	
	private void handleLend() {
		dialogState.setState(DialogState.LEND);
	}
	
	private void handleSubmit() {
		String eingabe = window.getTextfield().getText();
		window.getTextfield().setText("");
		daoManager = DaoManager.getInstance();
		if(dialogState.getState() == DialogState.LEND) {	//Ob der Artikel ausgeliehen werden kann/soll
			if(eingabe.length() > 15) {
				try {
					//window.btnWarenkorb.setVisible(true);
					daoUser = (DaoUser) daoManager.getDao(DbTables.USER);
					Users user = daoUser.getById(new Users(eingabe));	//Gibt den User in die Variable anhand der ID
					if(user != null) {
						window.getLblNewLabelError().setText("");
						scannedUsers = user;
						window.getLblNewLabel().setText(user.getFirstname() + " " + user.getLastname());	//Gibt den User aus
					} else {
						window.getLblNewLabelError().setText("User nicht vorhanden!");
					}
				} catch (ServiceUnavailableException e) {
					e.printStackTrace();
				}
			} else {
				try {
					daoArticle = (DaoArticle) daoManager.getDao(DbTables.ARTICLE);
					daoLendArticle = (DaoLendArticle) daoManager.getDao(DbTables.LEND_ARTICLE);
					Article article = daoArticle.getById(new Article(eingabe));
					LendArticle lendArticle = new LendArticle(article);
					if(article != null) {
						if(daoLendArticle.isArticleAlreadyLended(lendArticle) == false) {
							window.getLblNewLabelError().setText("");
							if(scannedArticles.contains(article)) {	//Überprüft ob ein Artikel zweimal ausgewählt wurde, wenn ja dann werden sie gelöscht
								int i = 0;
								for (Article a : scannedArticles) {
									if(article.equals(a)) {
										window.removeArticle(i);
									}
									i++;
								}
								scannedArticles.remove(article);
							} else {
								scannedArticles.add(article);
								window.addArticle(article);
							}
						} else {
							window.getLblNewLabelError().setText("Artikel bereits ausgeliehen!");
						}	
					} else {
						window.getLblNewLabelError().setText("Artikel nicht vorhanden!");
					}
				} catch (ServiceUnavailableException e) {
					e.printStackTrace();
				}
			}
		}
	 else if (dialogState.getState() == DialogState.RETURN) {	//Wenn der Artikel zurückgegeben werden muss
         System.out.println("Article Return");
        
         if(eingabe.length() > 15) {	//schaut, ob es sich um ein Admin handelt
				try {
					//window.btnWarenkorb.setVisible(true);
					daoUser = (DaoUser) daoManager.getDao(DbTables.USER);
					Users user = daoUser.getById(new Users(eingabe));
					if(user != null) {	//Überprüft ob ein User eingeloggt ist
						window.getLblNewLabelError().setText("");
						scannedUsers = user;
						window.getLblNewLabel().setText(user.getFirstname() + " " + user.getLastname());	//Gibt den User ins GUI aus
					} else {
						window.getLblNewLabelError().setText("User nicht vorhanden!");
					}
				} catch (ServiceUnavailableException e) {
					e.printStackTrace();
				}
			}else {
             try {
            	 daoLendArticle = (DaoLendArticle) daoManager.getDao(DbTables.LEND_ARTICLE);
                 daoArticle = (DaoArticle) daoManager.getDao(DbTables.ARTICLE);
                 
                 Article article = daoArticle.getById(new Article(eingabe));
                 LendArticle lendArticle = new LendArticle(article);
                 if (article != null) {	//Überprüft ob der Artikel leer ist
                     if (daoLendArticle.isArticleAlreadyLended(lendArticle) == true) {

					     if (scannedArticles.contains(article)) {
					         int i = 0;
					         for (Article a : scannedArticles) {
					             if (article.equals(a)) {
					                 window.removeArticle(i);
					             }
					             i++;
					         }
					         scannedArticles.remove(article);
					     } else {
					         scannedArticles.add(article);
					         window.addArticle(article);
					     }
					 } else {
						 window.getLblNewLabelError().setText("Dieser Artikel wurde noch nicht ausgeliehen.");
					 }
                 } else {
                	 window.getLblNewLabelError().setText("Der Artikel wurde nicht gefunden.");
                 }
             } catch (ServiceUnavailableException e) {
                 e.printStackTrace();
             }
			}
	 	}
	}
	
	private void handleReturn()  {
		dialogState.setState(DialogState.RETURN);	//Setzt den Artikelstatus zu Return
	}
	
	private void handleInventoryAdmin() {
		window.getLblNewLabelError().setText("Admin wurde noch nocht eingefügt.");
	}
	
	private void handleCancel() {
		System.out.println("Button Abbrechen gedrückt.");
		System.exit(0);
	}
	
	private void handelCheckout() {
		if(scannedUsers != null && scannedArticles.size() > 0) {	//überprüft ob ein user und mind einen Artikel eingescannt wurde
			window.getLblNewLabelError().setText("");
			new InventoryLendSavePresenter(scannedArticles, scannedUsers, dialogState);	//schickt die Daten zu dem InventorylendSavePersenter
		} else {
			window.getLblNewLabelError().setText("Es ist nicht alles ausgefüllt worden.");
		}
	}
	
	/*private void handleWarenkorb() {
		new InventoryShowLendedArticlesPresenter();	//funktioniert noch nicht
	}*/

}
