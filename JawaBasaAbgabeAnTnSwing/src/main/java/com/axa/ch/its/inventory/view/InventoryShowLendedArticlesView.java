package com.axa.ch.its.inventory.view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InventoryShowLendedArticlesView {
	private DefaultTableModel tableModel;
	
	private JTable checkoutTable;
	private JFrame frame;
	private JTable table;
	private JLabel Idlabel1;
	private JLabel IdEnter;
	private JLabel telefon;
	private JLabel TelefonEnter;
	private JLabel rechte;
	private JLabel rechteEnter;
	private JLabel User;
	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryShowLendedArticlesView window = new InventoryShowLendedArticlesView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InventoryShowLendedArticlesView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1039, 597);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 26, 1023, 0);
		frame.getContentPane().add(label);
		
		Idlabel1 = new JLabel("ID");
		Idlabel1.setBounds(42, 63, 51, 21);
		frame.getContentPane().add(Idlabel1);
		
		IdEnter = new JLabel("IdEnter");
		IdEnter.setBounds(103, 60, 141, 21);
		frame.getContentPane().add(IdEnter);
		
		telefon= new JLabel("Telefon");
		telefon.setBounds(42, 81, 51, 31);
		frame.getContentPane().add(telefon);
		
		rechte = new JLabel("Rechte");
		rechte.setBounds(42, 106, 51, 31);
		frame.getContentPane().add(rechte);
		
		TelefonEnter = new JLabel("TelefonEnter");
		TelefonEnter.setBounds(103, 83, 153, 27);
		frame.getContentPane().add(TelefonEnter);
		
		rechteEnter = new JLabel("RechteEnter");
		rechteEnter.setBounds(103, 106, 141, 31);
		frame.getContentPane().add(rechteEnter);
		
		User = new JLabel("Benutzer");
		User.setBounds(42, 11, 196, 44);
		frame.getContentPane().add(User);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 148, 931, 368);
		frame.getContentPane().add(scrollPane);
		
		checkoutTable = new JTable();
		checkoutTable.setRowHeight(50);
		checkoutTable.setEnabled(false);
		tableModel = addTableModel();
		checkoutTable.setModel(tableModel);
		checkoutTable.getColumnModel().getColumn(2).setCellRenderer(new ImageCellRenderer());
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
	}

	private DefaultTableModel addTableModel() {
		DefaultTableModel tableModel;

		ArrayList<String> colums = new ArrayList<String>();
		colums.add("Status");
		colums.add("Artikel ID");
		colums.add("Bezeichnung");
		colums.add("Bild");
		colums.add("Start");
		colums.add("Ende");
		colums.add("Überfällig?");

		tableModel = new DefaultTableModel(colums.toArray(), 0);

		return tableModel;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JLabel getIdlabel1() {
		return Idlabel1;
	}

	public void setIdlabel1(JLabel idlabel1) {
		Idlabel1 = idlabel1;
	}

	public JLabel getIdEnter() {
		return IdEnter;
	}

	public void setIdEnter(JLabel idEnter) {
		IdEnter = idEnter;
	}

	public JLabel getTelefon() {
		return telefon;
	}

	public void setTelefon(JLabel telefon) {
		this.telefon = telefon;
	}

	public JLabel getTelefonEnter() {
		return TelefonEnter;
	}

	public void setTelefonEnter(JLabel telefonEnter) {
		TelefonEnter = telefonEnter;
	}

	public JLabel getRechte() {
		return rechte;
	}

	public void setRechte(JLabel rechte) {
		this.rechte = rechte;
	}

	public JLabel getRechteEnter() {
		return rechteEnter;
	}

	public void setRechteEnter(JLabel rechteEnter) {
		this.rechteEnter = rechteEnter;
	}

	public JLabel getUser() {
		return User;
	}

	public void setUser(JLabel user) {
		User = user;
	}
}
