package com.axa.ch.its.inventory.view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.axa.ch.its.inventory.model.Article;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InventoryMainView {

	private JFrame frame;
	private JTable checkoutTable;
	private DefaultTableModel tableModel;

	private JButton btnAbbrechen;
	private JButton btnConfirmation;
	private JButton btnAusleihen;
	private JButton btnSubmit;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1 ;
	private JButton btnRetour;
	private JButton btnWarenkorb;
	private JButton btnAdmin;

	/**
	 * Create the application.
	 */
	public InventoryMainView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 783, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 67, 479, 306);
		frame.getContentPane().add(scrollPane);

		checkoutTable = new JTable();
		checkoutTable.setRowHeight(50);
		checkoutTable.setEnabled(false);
		tableModel = addTableModel();
		checkoutTable.setModel(tableModel);
		checkoutTable.getColumnModel().getColumn(2).setCellRenderer(new ImageCellRenderer());

		scrollPane.setViewportView(checkoutTable);

		btnAbbrechen = new JButton("Abbrechen");

		btnAbbrechen.setBounds(179, 384, 110, 23);
		frame.getContentPane().add(btnAbbrechen);

		btnConfirmation = new JButton("Bestätigen");
		btnConfirmation.setBounds(37, 384, 126, 23);
		frame.getContentPane().add(btnConfirmation);

		btnAusleihen = new JButton("Ausleihen");
		btnAusleihen.setBounds(37, 29, 97, 25);
		frame.getContentPane().add(btnAusleihen);
		
		
		
		btnRetour = new JButton("Retour");
		btnRetour.setBounds(146, 29, 97, 25);
		frame.getContentPane().add(btnRetour);
		

		btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(525, 29, 97, 25);
		frame.getContentPane().add(btnAdmin);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(255, 33, 258, 16);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(528, 147, 212, 31);
		textField.setColumns(10);
		textField.setVisible(false);
		frame.getContentPane().add(textField);
		
		btnRetour.addActionListener(new ActionListener() {            
			public void actionPerformed(ActionEvent e) {
				btnSubmit.setVisible(true);
				textField.setVisible(true);
			}
		});
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(528, 191, 212, 25);
		btnSubmit.setVisible(false);
		frame.getContentPane().add(btnSubmit);
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(528, 249, 212, 77);
		lblNewLabel_1.setForeground(Color.red);
		frame.getContentPane().add(lblNewLabel_1);
		
		/*btnWarenkorb = new JButton("Warenkorb");
		btnWarenkorb.setBounds(525, 59, 97, 25);
		frame.getContentPane().add(btnWarenkorb);
		btnWarenkorb.setVisible(false);*/
		
		btnAusleihen.addActionListener(new ActionListener() {            
			public void actionPerformed(ActionEvent e) {
				btnSubmit.setVisible(true);
				textField.setVisible(true);
			}
		});
		
		
		
		frame.setVisible(true);
		
		
	}

	private DefaultTableModel addTableModel() {
		DefaultTableModel tableModel;

		ArrayList<String> colums = new ArrayList<String>();
		colums.add("Id");
		colums.add("Artikel");
		colums.add("Bild");

		tableModel = new DefaultTableModel(colums.toArray(), 0);

		return tableModel;
	}
	
	public void addArticle(Article article) {
		BufferedImage img = article.getProductImage();
		Vector<Object> data = new Vector<>();
		data.add(article.getId());
		data.add(article.getDescription());
		data.add(img);

		tableModel.addRow(data);

	}
	
	//Hier unten sind die Getters und die Setters
	
	public void removeArticle(int row) {
		tableModel.removeRow(row);
	}

	public JButton getBtnRetour() {
		return btnRetour;
	}
	
	public JButton getBtnAbbrechen() {
		return btnAbbrechen;
	}

	public JButton getBtnConfirmation() {
		return btnConfirmation;
	}

	public JButton getBtnAusleihen() {
		return btnAusleihen;
	}
	
	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public JTextField getTextfield() {
		return textField;
	}
	
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	
	public JLabel getLblNewLabelError() {
		return lblNewLabel_1;
	}

	public JButton getBtnWarenkorb() {
		return btnWarenkorb;
	}

	public void setBtnWarenkorb(JButton btnWarenkorb) {
		this.btnWarenkorb = btnWarenkorb;
	}

	public JButton getBtnAdmin() {
		return btnAdmin;
	}
	
	public void setBtnAdmin(JButton btnAdmin) {
		this.btnAdmin = btnAdmin;
	}
}
