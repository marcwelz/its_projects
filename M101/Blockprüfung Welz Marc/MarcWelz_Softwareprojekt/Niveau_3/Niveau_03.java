package Blockprüfung;

import java.util.Scanner;

public class Niveau_03 {

	public static void main(String[] args) {
		String geschlechtText = "";
		int i = 0;
		Scanner sc = new Scanner(System.in);
		String vorname = null;
		String nachname = null;
		// String geschlecht = null;

		while (i < 5) {
			System.out.println("Geben Sie Ihren Vornamen ein: ");
			vorname = sc.nextLine();

			System.out.println("Geben Sie Ihren Nachnamen ein: ");
			nachname = sc.nextLine();

			System.out.println("Geben Sie Ihr Geschlecht ein: ");
			String geschlecht = sc.nextLine();

			if (geschlecht.equals("Männlich")) {
				geschlechtText = "Herr";
			}
			if (geschlecht.equals("Weiblich")) {
				geschlechtText = "Frau";
			}

			if (vorname.equals("") && nachname.equals("")) {
				System.out.println("Guten Tag Fremde/r");
			}
			if (vorname.equals("") && !nachname.equals("")) {
				System.out.println("Guten Tag " + geschlechtText + " " + nachname + ".");
			}
			if (!vorname.equals("") && nachname.equals("")) {
				System.out.println("Guten Tag " + vorname + ".");
			} 
			if (!vorname.equals("") && !nachname.equals("")) {
				System.out.println("Guten Tag " + vorname + " " + nachname + ".");
			}

			i++;
		}

	}

}
