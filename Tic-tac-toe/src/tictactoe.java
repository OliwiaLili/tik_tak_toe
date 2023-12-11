
import java.util.Scanner; // importiert die Klasse Scanner


public class tictactoe {

	public static void main(String[] args) {
		


		// Variablen Deklarieren
		char[] fields = new char [9];
		int spieler = 1;
		boolean win = false;
		
		for (int i = 0; i < fields.length; i++) {
            fields[i] = (char)('1' + i); 
        }


		//Den "Scanner" erstellen (Das teil was die eingaben liest)
		Scanner sc = new Scanner(System.in);

		// Startet das Spiel
		while(win == false){
			//Spielfeld ausgeben
			SpielfeldAusgeben(fields [0], fields [1], fields [2], fields [3], fields [4], fields [5], fields [6], fields [7], fields [8]);

			if (spieler == 1){ // wenn spieler 1 dran ist
				System.out.println("Spieler 1 ist an der reihe.");
				System.out.println("Wo möchten Sie setzen");
				int eingabe = sc.nextInt(); //schaltet den Scanner für die nächste eingabe ein
				System.out.println("Sie habe Position " + eingabe + " gewählt!");

				if (eingabe < 10 & eingabe > 0) {

					int i = 0;
					i = eingabe - 1;

					if(fields[i] != 'X' | fields[i] != 'O') {

						fields[i] = 'O';
					} else
						System.out.println("Dieses Feld ist bereits belegt!");

				} else
					System.out.println("Eingabewert falsch");
			} else if (spieler == 2){   // Wenn Spieler 2 an der reihe ist
				System.out.println("Spieler 2 ist an der reihe.");
				System.out.println("Wo möchten Sie setzen");
				int eingabe = sc.nextInt(); //schaltet den Scanner für die nächste eingabe ein
				System.out.println("Sie habe Position " + eingabe + " gewählt!");

				
				if (eingabe < 10 & eingabe > 0) {
					
					int i = 0;
					i = eingabe - 1;
					
					if(fields[i] != 'X' | fields[i] != 'O') {
						
						fields[i] = 'X';
					} else
						System.out.println("Dieses Feld ist bereits belegt!");
					
				} else
					System.out.println("Eingabewert falsch");
			}

			
			// Überprüfung wurde gewonnen
			if (fields [0] == fields [1] && fields [1] == fields [2]){
				win = true;
			} else if (fields [3] == fields [4] && fields [4] == fields [5]) {
				win = true;
			} else if (fields [6] == fields [7] && fields [7] == fields [8]) {
				win = true;
			} else if (fields [0] == fields [3] && fields [3] == fields [6]) {
				win = true;
			} else if (fields [1] == fields [4] && fields [4] == fields [7]) {
				win = true;
			} else if (fields [2] == fields [5] && fields [5] == fields [8]) {
				win = true;
			} else if (fields [0] == fields [4] && fields [4] == fields [8]) {
				win = true;
			} else if (fields [2] == fields [4] && fields [4] == fields [6]) {
				win = true;
			} else {
			}

			//Ausgabe wenn gewonnen wurde
			if(win == true){
				SpielfeldAusgeben(fields [0], fields [1], fields [2], fields [3], fields [4], fields [5], fields [6], fields [7], fields [8]);
				System.out.println("Spieler " + spieler + " hat gewonnen");
			}
			
			
			//Spieler wechseln
			if (spieler == 1){
				spieler = 2;
			} else {
				spieler = 1;
			}

		}

	}



	public static void SpielfeldAusgeben(char f1, char f2, char f3 , char f4 , char f5 , char f6 , char f7 ,char f8 ,char f9){
		//Spielfeld ausgeben.
		System.out.println(f7 + " | " + f8 + " | " + f9);
		System.out.println("----------");
		System.out.println(f4 + " | " + f5 + " | " + f6);
		System.out.println("----------");
		System.out.println(f1 + " | " + f2 + " | " + f3);
	
	}


}

