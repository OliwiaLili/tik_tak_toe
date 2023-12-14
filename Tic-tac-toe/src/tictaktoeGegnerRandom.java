
import java.util.Random;
import java.util.Scanner;

public class tictaktoeGegnerRandom {

    public static void main(String[] args) {

        // Variablen und Spielfeldinitialisierung
        char[][] fields = new char[3][3];
        int spieler = 1;
        boolean win = false;
        boolean draw = false;

        int zahl = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = (char) (zahl + '0'); // Nummerierung des Spielfelds
                zahl++;
            }
        }

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        while (!win && !draw) {
            SpielfeldAusgeben(fields);

            if (spieler == 1) {
                SpielerZug(sc, fields, spieler); // Spielerzug
            } else {
                ComputerZug(fields); // Computergegner-Zug
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
			for(int i = 0; i<3; i++) 
			{

				for(int j = 0; j<3; j++) 
				{

					if(fields[i][j] != 'X' && fields[i][j] != 'O') 
					{

						draw = false;
						break;
					}	
				} 

			} 
			
			//Ausgabe wenn gewonnen wurde oder das Spiel unentschieden ist
			if(win == true)
			{
				SpielfeldAusgeben(fields);
				System.out.println("Spieler " + spieler + " hat gewonnen");
			} else if(draw == true) 
			{
				SpielfeldAusgeben(fields);
				System.out.println("Das Spiel ist unentschieden!");
			}

            if (spieler == 1) 
            {
                spieler = 2; // Spielerwechsel
            } 
            else 
            {
                spieler = 1; // Spielerwechsel
            }
        }
    }

    public static void SpielerZug(Scanner sc, char[][] fields, int spieler) {
        System.out.println("Spieler " + spieler + " (O) ist an der Reihe");
        System.out.println("Wo möchtest du setzen?");
        int eingabe = pruefeEingabe(sc);

        int i = (eingabe - 1) / 3;
        int j = (eingabe - 1) % 3;

        if (fields[i][j] != 'X' && fields[i][j] != 'O') {
            fields[i][j] = 'O';
        } else {
            System.out.println("Dieses Feld ist bereits belegt!");
            SpielerZug(sc, fields, spieler); // Erneute Eingabe des Spielers
        }
    }

    public static int pruefeEingabe(Scanner sc) {
        int eingabe;
        while (true) {
            if (sc.hasNextInt()) {
                eingabe = sc.nextInt();
                if (eingabe >= 1 && eingabe <= 9) {
                    break; // Gültige Eingabe
                } else {
                    System.out.println("Ungültiger Wert! Bitte eine Zahl zwischen 1 und 9 eingeben.");
                }
            } else {
                System.out.println("Ungültige Eingabe! Bitte eine Zahl zwischen 1 und 9 eingeben.");
                sc.next(); // Verwerfe die ungültige Eingabe
            }
        }
        return eingabe;
    }

    public static void ComputerZug(char[][] fields) {
        Random rand = new Random();

        System.out.println("Computer (X) ist an der Reihe");

        int i, j;
        do {
            i = rand.nextInt(3);
            j = rand.nextInt(3);
        } while (fields[i][j] == 'X' || fields[i][j] == 'O'); // Finden eines leeren Felds für den Zug des Computers

        fields[i][j] = 'X'; // Setzen des Zugs des Computers
    }

    public static void SpielfeldAusgeben(char[][] arr) {
        // Spielfeldausgabe
        System.out.println(arr[2][0] + " | " + arr[2][1] + " | " + arr[2][2]);
        System.out.println("----------");
        System.out.println(arr[1][0] + " | " + arr[1][1] + " | " + arr[1][2]);
        System.out.println("----------");
        System.out.println(arr[0][0] + " | " + arr[0][1] + " | " + arr[0][2]);
        System.out.println("");
    }
}
