package com.axa.ch.its.inventory.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.naming.ServiceUnavailableException;

import com.axa.ch.its.inventory.dao.DaoLendArticle;
import com.axa.ch.its.inventory.dao.DaoManager;
import com.axa.ch.its.inventory.dao.DbTables;
import com.axa.ch.its.inventory.dao.Users;
import com.axa.ch.its.inventory.model.Article;
import com.axa.ch.its.inventory.model.LendArticle;
import com.axa.ch.its.inventory.model.LendState;
import com.axa.ch.its.inventory.view.InventoryLendSaveView;

public class InventoryLendSavePresenter {

	InventoryLendSaveView window;
	private ArrayList<Article> scannedArticles;
	private DialogStateWrapper dialogState;
	private DaoManager daoManager;
	private Users user;
	private DaoLendArticle LendArticleS;
	
	
	
	
	public InventoryLendSavePresenter(ArrayList<Article> scannedArticles, Users user, DialogStateWrapper dialogState) {		
		this.scannedArticles = scannedArticles;
		this.user = user;
		this.dialogState = dialogState;
	
		window = new InventoryLendSaveView();
		
		setActionEvents();
		
	}
	private void setActionEvents() {
		window.getBtnAbbrechen().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleCancel();				
				
			}
		});
		
		window.getBtnOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleOk();				
				
			}
		});
		
		
	}
	
	public void InventoryLendSaveController(ArrayList<Article> scannedArticles, Users user, DialogStateWrapper dialogState)  {
		
	}
	
	private void handleOk() {
		if(dialogState.getState() == DialogState.LEND) {	// Wenn der Artikel ausgeliehen wird
			saveLend();
		} else if (dialogState.getState() == DialogState.RETURN) {	//Wenn der Artikel zurückgebracht wird
			 try {
				for (Article a : scannedArticles) {
					 LendArticle ausleihe = new LendArticle(a, user, 0, LendState.LEND, window.getStartDate(),
				     window.getPlannedEndDate(), window.getPlannedEndDate(), user.getId());
				     daoManager = DaoManager.getInstance();
				     LendArticleS = (DaoLendArticle) daoManager.getDao(DbTables.LEND_ARTICLE);
				     System.out.println(ausleihe.getArticle().getId());
				     if (LendArticleS.isArticleAlreadyLended(ausleihe) == true) {
				    	 LendArticleS.updateReturn(ausleihe);
				    	 window.getFrame().dispose();	//Sobald die Artikel ausgeliehen wurden, wird dass Fenster geschlossen
				     } else {
				    	 System.out.println(ausleihe.getArticle().getId() + " dieser Artikel wurde noch nie ausgeliehen");
				     }
				 }
			} catch (ServiceUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void handleCancel() {
		window.getFrame().dispose();
	}
	
	
	
	private void saveLend() {
		try {
			DaoManager daoManager = DaoManager.getInstance();
			DaoLendArticle daoLendArticle = (DaoLendArticle) daoManager.getDao(DbTables.LEND_ARTICLE);
			for (Article article : scannedArticles) {
				daoLendArticle.save(new LendArticle(article, user, -1, LendState.LEND, window.getStartDate(), window.getPlannedEndDate(), null, null));
			}
			dialogState.setState(DialogState.LEND);
			window.getFrame().dispose();	//Sobald die Artikel ausgeliehen wurden, wird dass Fenster geschlossen
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	


}
