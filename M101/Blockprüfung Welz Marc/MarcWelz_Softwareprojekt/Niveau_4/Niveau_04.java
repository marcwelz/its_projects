package Blockprüfung;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Niveau_04 {

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

			FileWriter fw = null;
			try {
				fw = new FileWriter("C:\\Users\\C315184\\OneDrive - AXA\\Desktop\\Blockprüfung Welz Marc\\MarcWelz_Softwareprojekt\\Niveau_4\\Text2.txt", true);
			} catch (IOException e) {
				System.out.println("Fehler beim schreiben");
			}
			BufferedWriter schreibe2 = new BufferedWriter(fw);
			PrintWriter schreiben = new PrintWriter(schreibe2);
			
			
			
			if (vorname.equals("") && nachname.equals("")) {
				
					
			}
			if (vorname.equals("") && !nachname.equals("")) {
				System.out.println(geschlechtText + nachname );
				
					schreiben.write(geschlechtText + " " + nachname);
					schreiben.println();
					
				
			}
			if (!vorname.equals("") && nachname.equals("")) {
				System.out.println(vorname);
				
					schreiben.write( vorname );
					schreiben.println();
					
				
				
			}
			 
			if (!vorname.equals("") && !nachname.equals("")) {
				System.out.println(vorname + " " + nachname);
				
					schreiben.write(vorname + " " + nachname);
					schreiben.println();
			}
			
			
			schreiben.close();
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			i++;
		}
		
	
		}
		
	}

}
