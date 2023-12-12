
import java.util.Scanner; // importiert die Klasse Scanner


public class tictactoe {

	public static void main(String[] args) {


		// Variablen Deklarieren
		char[][] fields = new char [3][3];
		int spieler = 1;
		boolean win = false;
		boolean draw = false;
		
		int zahl = 1;
		for (int i = 0; i < 3; i++) {
			
			for(int j = 0; j<3; j++) {
				//Warum die 0?
				fields[i][j] = (char) (zahl + '0');
				zahl++;
			}
        }


		//Den "Scanner" erstellen (Das teil was die eingaben liest)
		Scanner sc = new Scanner(System.in);

		// Startet das Spiel
		while(!win && !draw){
			//Spielfeld ausgeben
			SpielfeldAusgeben(fields [0][0], fields [0][1], fields [0][2], fields [1][0], fields [1][1], fields [1][2], fields [2][0], fields [2][1], fields [2][2]);

			if(spieler == 1) {
				System.out.println("Spieler 1 ist an der Reihe");
			} else {
				System.out.println("Spieler 2 ist an der Reihe");
			}
			
			System.out.println("Wo möchtest du setzen?");
            int eingabe = sc.nextInt();
            
            if (eingabe < 10 & eingabe > 0) {
            	
            	int i = (eingabe - 1) / 3;
				int j = (eingabe -1) % 3;
				
				if (fields[i][j] != 'X' && fields[i][j] != 'O') {
					
					if (spieler == 1) {
                        fields[i][j] = 'O';
                    } else {
                        fields[i][j] = 'X';
                    }
				} else {
					System.out.println("Dieses Feld ist bereits belegt!");
					continue;
				}
            	
            }
			
			/*
			if (spieler == 1){ // wenn spieler 1 dran ist
				System.out.println("Spieler 1 ist an der Reihe.");
				System.out.println("Wo möchtest du setzen?");
				int eingabe = sc.nextInt(); //schaltet den Scanner für die nächste eingabe ein
				System.out.println("Du hast Position " + eingabe + " gewählt!");

				if (eingabe < 10 & eingabe > 0) {

					int i = (eingabe - 1) / 3;
					int j = (eingabe -1) % 3;
					
					if(fields[i][j] != 'X' | fields[i][j] != 'O') {

						fields[i][j] = 'O';
					} else
						System.out.println("Dieses Feld ist bereits belegt!");

				} else
					System.out.println("Ungültige Eingabe");
				
				
			} else if (spieler == 2){   // Wenn Spieler 2 an der reihe ist
				System.out.println("Spieler 2 ist an der reihe.");
				System.out.println("Wo möchtest du setzen?");
				int eingabe = sc.nextInt(); //schaltet den Scanner für die nächste eingabe ein
				System.out.println("Du hast Position " + eingabe + " gewählt!");

				
				if (eingabe < 10 & eingabe > 0) {
					
					int i = (eingabe - 1) / 3;
					int j = (eingabe -1) % 3;
					
					if(fields[i][j] != 'X' | fields[i][j] != 'O') {

						fields[i][j] = 'X';
					} else
						System.out.println("Dieses Feld ist bereits belegt!");
					
				} else
					System.out.println("Eingabewert falsch");
			}
			*/

			
			// Überprüfung Reihen und Diagonalen
			for( int i = 0; i<3; i++) {
				if((fields[i][0] == fields[i][1] & fields[i][1] == fields[i][2]) ||
				(fields[0][i] == fields[1][i] & fields[1][i] == fields[2][i])) {
					win = true;
					break;
				}
			}
			
			//Überprüfung Spalten
			if((fields[0][0] == fields[1][1] & fields[1][1] == fields[2][2]) ||
			(fields[0][2] == fields[1][1] & fields[1][1] == fields[2][0])) {
				win = true;
				break;
			}
			
			
			//Überprüfung ob unentschieden
			draw = true; // Annahme: Es ist unentschieden, bis ein leeres Feld gefunden wird
			for(int i = 0; i<3; i++) {
				
				for(int j = 0; j<3; j++) {
					
					if(fields[i][j] != 'X' && fields[i][j] != 'O') {
						
						draw = false;
						break;
					}	
				} 
				
				if (!draw) {
					break;
				}
			} 

			//Ausgabe wenn gewonnen wurde
			//eltl noch mit for schleife vereinfachen
			if(win == true){
				SpielfeldAusgeben(fields [0][0], fields [0][1], fields [0][2], fields [1][0], fields [1][1], fields [1][2], fields [2][0], fields [2][1], fields [2][2]);
				System.out.println("Spieler " + spieler + " hat gewonnen");
			} else if(draw == true) {
				SpielfeldAusgeben(fields [0][0], fields [0][1], fields [0][2], fields [1][0], fields [1][1], fields [1][2], fields [2][0], fields [2][1], fields [2][2]);
				System.out.println("Das Spiel ist unentschieden!");
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

