
import java.util.InputMismatchException;
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

		//Den "Scanner" erstellen
		Scanner sc = new Scanner(System.in);

		// Startet das Spiel
		while(!win && !draw){
			//Spielfeld ausgeben
			SpielfeldAusgeben(fields);

			//Ausgabe, welcher Spieler an der Reihe ist
			if(spieler == 1) {
				System.out.println("Spieler 1 (O) ist an der Reihe");
			} else {
				System.out.println("Spieler 2 (X) ist an der Reihe");
			}

			System.out.println("Wo möchtest du setzen?");
			try {
				int eingabe = sc.nextInt();

				//bei korrekter Eingabe wird durch Division und Modulo die richtige Position im Array ausgewählt
				if (eingabe < 10 & eingabe > 0) {

					int i = (eingabe - 1) / 3;
					int j = (eingabe -1) % 3;
					
					//falls das Feld noch nicht belegt ist, wird je nach spieler ein X oder ein O in das Feld geschrieben
					if (fields[i][j] != 'X' && fields[i][j] != 'O') {

						if (spieler == 1) {
							fields[i][j] = 'O';
						} else {
							fields[i][j] = 'X';
						}
						//Fehlermeldung, falls das Feld bereits belegt ist
					} else {
						System.out.println("Dieses Feld ist bereits belegt!");
						continue;
					}

				}
			}
			//bei der Eingabe eines nicht-numerischen Wertes wird eine Fehlermeldung ausgegeben und die Eingabe wird verworfen
			//die Schleife wird erneut durchlaufen, odass der Spieler erneut ein Feld wählen kann
			catch (InputMismatchException e) {
				System.out.println("Ungültige Eingabe! Bitte eine Zahl zwischen 1 und 9 eingeben.");
				sc.nextLine(); // Verwerfe die ungültige Eingabe
				continue;

			}

			//Überprüfung, ob jemand gewonnen hat
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

			} 

			//Ausgabe wenn gewonnen wurde oder das Spiel unentschieden ist
			if(win == true){
				SpielfeldAusgeben(fields);
				System.out.println("Spieler " + spieler + " hat gewonnen");
			} else if(draw == true) {
				SpielfeldAusgeben(fields);
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

	//Funktion, die das Spielfeld mit den Werten im übergebenen Array ausgibt
	public static void SpielfeldAusgeben(char[][] arr){
		//Spielfeld ausgeben.
		System.out.println(arr[2][0] + " | " + arr[2][1] + " | " + arr[2][2]);
		System.out.println("----------");
		System.out.println(arr[1][0] + " | " + arr[1][1] + " | " + arr[1][2]);
		System.out.println("----------");
		System.out.println(arr[0][0] + " | " + arr[0][1] + " | " + arr[0][2]);
		System.out.println("");

	}

}