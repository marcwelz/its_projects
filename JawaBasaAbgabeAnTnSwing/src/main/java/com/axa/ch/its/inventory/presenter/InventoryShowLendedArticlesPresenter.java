package com.axa.ch.its.inventory.presenter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.axa.ch.its.inventory.view.InventoryMainView;

public class InventoryShowLendedArticlesPresenter {
	
	InventoryMainView window;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Diese Klasse ist nicht fertig!
	
	public InventoryShowLendedArticlesPresenter() {
		window = new InventoryMainView();
		setActionEvents();
	}
	
	private void setActionEvents() {
		window.getBtnWarenkorb().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleWarenkorb();				
			}
		});
	}
	
	private void handleWarenkorb() {
		System.out.println("handleWarenkorb");
	
	}
	
}
