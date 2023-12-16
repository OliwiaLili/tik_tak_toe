

import java.util.Scanner;

public class tictactoe {

	public static String GREEN = "\u001B[32m";
    public static String RED = "\u001B[31m";
    public static String RESET = "\u001B[0m";
	public static String xToken = GREEN + "X" + RESET;
    public static String  oToken = RED + "O" + RESET;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

                // Variablen Deklarieren
                String[][] fields = new String[3][3];
                int currentPlayer = 1;
                boolean win = false;
                boolean draw = false;
                
                

                initializeBoard(fields); // auf Methode wird zurückgegriffen

                //Den "Scanner" erstellen
                Scanner sc = new Scanner(System.in);

                // Startet das Spiel
                while (!win && !draw) {
                        
                        //Spielfeld ausgeben
                        SpielfeldAusgeben(fields);

                        //Ausgabe, ob Spieler oder Computer an der Reihe ist
                        if (currentPlayer == 1) {
                                System.out.println("Spieler 1 (O) ist an der Reihe");
                                System.out.println("Wo möchtest du setzen?");
                                int input = getValidInput(sc, fields);
                                placeToken(fields, input, oToken);
                        } else {
                                System.out.println("Spieler 2 (Computer) ist an der Reihe");
                                int input = getComputerMove(fields);
                                placeToken(fields, input, xToken);
                        }

                        // Gewinner oder Unentschieden überprüfen
            win = checkForWinner(fields); // Überprüfe auf Gewinner
            draw = checkForDraw(fields); // Überprüfe auf Unentschieden

            //Ausgabe beim Spielende
                        if (win || draw) {
                                SpielfeldAusgeben(fields);
                                if (win) {
                                        System.out.println("Spieler " + currentPlayer + " hat gewonnen");
                                } else {
                                        System.out.println("Das Spiel ist unentschieden!");
                                }
                        }

                        //Spielerwechsel
                        currentPlayer = (currentPlayer == 1) ? 2 : 1;
                }
        }
        //hier wird das Spielfeld initialisiert, also die Zahlen (1-9) werden in das Speilfeld eingetragen
        private static void initializeBoard(String[][] arr) { 
                int number = 1;
                for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                                arr[i][j] = Integer.toString(number);
                                number++;
                        }
                }
        }

        // Funktion um das Speilfeld auszugeben
        private static void SpielfeldAusgeben(String[][] arr) {
                System.out.println(arr[2][0] + " | " + arr[2][1] + " | " + arr[2][2]);
                System.out.println("----------");
                System.out.println(arr[1][0] + " | " + arr[1][1] + " | " + arr[1][2]);
                System.out.println("----------");
                System.out.println(arr[0][0] + " | " + arr[0][1] + " | " + arr[0][2]);
                System.out.println("");
        }

        //prüft die Eingabe des Benutzers
        private static int getValidInput(Scanner sc, String[][] arr) {
                int input = -1;
                boolean validInput = false;
                while (validInput == false) {
                        try {
                                input = sc.nextInt();
                                if (input >= 1 || input <= 9) {
                                        int i = (input - 1) / 3;
                                        int j = (input - 1) % 3;
                                        if (!arr[i][j].equals("X") || !arr[i][j].equals("O")) {
                                                validInput = true;
                                        } else {
                                                System.out.println("Dieses Feld ist bereits belegt!");
                                        }
                                
                                } 
                        } catch (Exception e) {
                                System.out.println("Ungültige Eingabe! Bitte eine Zahl zwischen 1 und 9 eingeben.");
                                sc.nextLine(); // Verwerfe die ungültige Eingabe
                        }
                }
                return input;
        }
        // Prüfen ob der Platz schon belegt ist
        private static void placeToken(String[][] arr, int input, String token) {
                int i = (input - 1) / 3;
                int j = (input - 1) % 3;
                arr[i][j] = token;
        }

        //Überprüfung ob jemand gewonnen hat
        private static boolean checkForWinner(String[][] arr) {
                for (int i = 0; i < 3; i++) {
                        if ((arr[i][0].equals(arr[i][1]) && arr[i][1].equals(arr[i][2])) ||
                                        (arr[0][i].equals(arr[1][i]) && arr[1][i].equals(arr[2][i]))) {
                                return true;
                        }
                }

                return (arr[0][0].equals(arr[1][1]) && arr[1][1].equals(arr[2][2])) ||
                                (arr[0][2].equals(arr[1][1]) && arr[1][1].equals(arr[2][0]));
        }

        //Überprüfung auf unetschieden
        private static boolean checkForDraw(String[][] arr) {
                for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                                if (!arr[i][j].equals("X") && !arr[i][j].equals("O")) {
                                        return false;
                                }
                        }
                }
                return true;
        }
        //Funktion für den Zug des Computers
        private static int getComputerMove(String[][] arr) {
                int computerMove = -1; // Standardinitialisierung außerhalb der Schleife;
                boolean validInput = false;
                int i =0;
                int j =0;
                while (validInput == false) {
                        computerMove = (int) (Math.random() * 9 + 1);
                        i = (computerMove - 1) / 3;
                        j = (computerMove - 1) % 3;

                        if (arr[i][j].equals(xToken) || arr[i][j].equals(oToken)) {
                        	validInput = false;
                        	
                                
                        }
                        else if(!arr[i][j].equals(xToken) || !arr[i][j].equals(oToken))
                        {
                        		break;
                        }
                        
                }
                return computerMove;
        }
}
