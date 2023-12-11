
import java.util.Scanner; // importiert die Klasse Scanner


public class tictactoe {

	public static void main(String[] args) {
		


		// Variablen Deklarieren
		char f1, f2, f3 ,f4 ,f5 ,f6 ,f7 ,f8 ,f9;
		int spieler;
		boolean win;

		//Variablen initialisieren
		f1 = f2 = f3 = f4 = f5 = f6 = f7 = f8 = f9 = '#';
		spieler = 1;
		win = false;

		//Den "Scanner" erstellen (Das teil was die eingaben liest)
		Scanner sc = new Scanner(System.in);

		// Startet das Spiel
		while(win == false){
			//Spielfeld ausgeben
			SpielfeldAusgeben(f1, f2, f3, f4, f5, f6, f7, f8, f9);

			if (spieler == 1){ // wenn spieler 1 dran ist
				System.out.println("Spieler 1 ist an der reihe.");
				System.out.println("Wo möchten Sie setzen");
				int eingabe = sc.nextInt(); //schaltet den Scanner für die nächste eingabe ein
				System.out.println("Sie habe Position " + eingabe + " gewählt!");

				//prüfen welches feld gewählt wurde
				//gewählte feld ändern

				switch (eingabe){
				case 1:
					f1 = 'X';
					break;

				case 2:
					f2 = 'X';
					break;

				case 3:
					f3 = 'X';
					break;

				case 4:
					f4 = 'X';
					break;

				case 5:
					f5='X';
					break;

				case 6:
					f6 ='X';
					break;

				case 7:
					f7 = 'X';
					break;

				case 8:
					f8 ='X';
					break;

				case 9:
					f9 ='X';
					break;
				}
			} else if (spieler == 2){   // Wenn Spieler 2 an der reihe ist
				System.out.println("Spieler 2 ist an der reihe.");
				System.out.println("Wo möchten Sie setzen");
				int eingabe = sc.nextInt(); //schaltet den Scanner für die nächste eingabe ein
				System.out.println("Sie habe Position " + eingabe + " gewählt!");

				//prüfen welches feld gewählt wurde
				//gewählte feld ändern

				switch (eingabe){
				case 1:
					f1 = 'O';
					break;

				case 2:
					f2 = 'O';
					break;

				case 3:
					f3 = 'O';
					break;

				case 4:
					f4 = 'O';
					break;

				case 5:
					f5='O';
					break;

				case 6:
					f6 ='O';
					break;

				case 7:
					f7 = 'O';
					break;

				case 8:
					f8 ='O';
					break;

				case 9:
					f9 ='O';
					break;
				}
			} else {
				System.out.println("Irgendwas mit der Spielerzählung hat nicht geklappt");
				win = true;
			}

			// Überprüfung wurde gewonnen
			if (f1 == f2 && f1 == f3 && f1 != '#'){
				win = true;
			} else if (f4 == f5 && f4 == f6 && f4 != '#') {
				win = true;
			} else if (f7 == f8 && f7 == f9 && f7 != '#') {
				win = true;
			} else if (f1 == f4 && f1 == f7 && f1 != '#') {
				win = true;
			} else if (f2 == f5 && f2 == f8 && f2 != '#') {
				win = true;
			} else if (f3 == f6 && f3 == f9 && f3 != '#') {
				win = true;
			} else if (f1 == f5 && f1 == f9 && f1 != '#') {
				win = true;
			} else if (f3 == f5 && f3 == f7 && f3 != '#') {
				win = true;
			} else {
			}

			//Ausgabe wenn gewonnen wurde
			if(win == true){
				SpielfeldAusgeben(f1, f2, f3, f4, f5, f6, f7, f8, f9);
				System.out.println("Spieler " + spieler + " hat gewonnen");
			}

			//Spieler wechseln
			if (spieler == 1){
				spieler++;
			} else {
				spieler--;
			}


		}

	}



	public static void SpielfeldAusgeben(char f1, char f2, char f3 , char f4 , char f5 , char f6 , char f7 ,char f8 ,char f9){
		//Spielfeld ausgeben.
		System.out.println(f7 + " | " + f8 + " | " + f9);
		System.out.println("----------");
		System.out.println(f4 + " | " + f5 + " | " + f6);
		System.out.println("----------");
		System.out.println(f1 + " | " + f2 + " | " + f3);
	
	}


}

