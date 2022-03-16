package Blockprüfung;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;

public class Niveau1 {

	public static void main(String[] args) {
		
		int i = 0;
		
		while(i == 0) {
			String vorname = null;
			try {
				vorname = JOptionPane.showInputDialog(null, "Bitte Geben Sie Ihren Vorname ein: ");
			} catch (HeadlessException e) {
				JOptionPane.showMessageDialog(null, "Bitte geben Sie Ihren Namen ein!");
				e.printStackTrace();
			}
			String nachname = null;
			try {
				nachname = JOptionPane.showInputDialog(null, "Bitte Geben Sie Ihren Nachnamen ein: ");
			} catch (HeadlessException e) {
				JOptionPane.showMessageDialog(null, "Bitte geben Sie Ihren Nachmamen ein!");
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Ihr Name lautet: " + vorname + " " + nachname);
			i++;
		}

	}

}
