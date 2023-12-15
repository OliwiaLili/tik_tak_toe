import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class tictactoe {
	
	// Farben für X und O
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";
    
    private static final String xT = GREEN + "X" + RESET;
    private static final String oT = RED + "O" + RESET;
   
	

    public static void main(String[] args) {
        // Variablen Deklarieren
        String[][] fields = new String[3][3];
        int spieler = 1;
        boolean win = false;
        boolean draw = false;
        /*String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        // Farben setzen für X und O
        String xT = GREEN + "X" + RESET;
        String oT = RED + "O" + RESET; */
       

        int zahl = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = Integer.toString(zahl);
                zahl++;
            }
        }

        // Den "Scanner" erstellen
        Scanner sc = new Scanner(System.in);

        // Abfrage, ob gegen Computer oder zweiten Nutzer spielen
        System.out.println("Möchten Sie gegen einen zweiten Spieler (1) oder gegen den Computer (2) spielen?");
        int spielModus = sc.nextInt();

        // Startet das Spiel
        while (!win && !draw) {
            // Spielfeld ausgeben
            SpielfeldAusgeben(fields);

            // Ausgabe, welcher Spieler an der Reihe ist
            if (spieler == 1) {
                System.out.println("Spieler 1 (O) ist an der Reihe");
                spielerZug(fields, oT);
            } else {
                if (spielModus == 1) {
                    System.out.println("Spieler 2 (X) ist an der Reihe");
                    spielerZug(fields, xT);
                } else {
                    System.out.println("Computer Spieler (X) ist an der Reihe");
                    computerZug(fields);
                }
            }

            // Überprüfung, ob jemand gewonnen hat
            // Überprüfung Reihen und Spalten
            for (int i = 0; i < 3; i++) {
                if ((fields[i][0].equals(fields[i][1]) && fields[i][1].equals(fields[i][2]))
                        || (fields[0][i].equals(fields[1][i]) && fields[1][i].equals(fields[2][i]))) {
                    win = true;
                    break;
                }
            }

            // Überprüfung Diagonalen
            if ((fields[0][0].equals(fields[1][1]) && fields[1][1].equals(fields[2][2]))
                    || (fields[0][2].equals(fields[1][1]) && fields[1][1].equals(fields[2][0]))) {
                win = true;
            }

            // Überprüfung ob unentschieden
            draw = true; // Annahme: Es ist unentschieden, bis ein leeres Feld gefunden wird
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!fields[i][j].equals(xT) && !fields[i][j].equals(oT)) {
                        draw = false;
                        break;
                    }
                }
            }

            // Ausgabe wenn gewonnen wurde oder das Spiel unentschieden ist
            if (win) {
                SpielfeldAusgeben(fields);
                if (spieler == 1) {
                    System.out.println("Spieler 1 hat gewonnen");
                } else {
                    if (spielModus == 1) {
                        System.out.println("Spieler 2 hat gewonnen");
                    } else {
                        System.out.println("Computer Spieler hat gewonnen");
                    }
                }
            } else if (draw) {
                SpielfeldAusgeben(fields);
                System.out.println("Das Spiel ist unentschieden!");
            }

            // Spieler wechseln
            if (spieler == 1) {
                spieler = 2;
            } else {
                spieler = 1;
            }
        }
    }

    // Funktion, die das Spielfeld mit den Werten im übergebenen Array ausgibt
    public static void SpielfeldAusgeben(String[][] arr) {
        // Spielfeld ausgeben.
        System.out.println(arr[2][0] + " | " + arr[2][1] + " | " + arr[2][2]);
        System.out.println("----------");
        System.out.println(arr[1][0] + " | " + arr[1][1] + " | " + arr[1][2]);
        System.out.println("----------");
        System.out.println(arr[0][0] + " | " + arr[0][1] + " | " + arr[0][2]);
        System.out.println("");
    }

    // Spieler macht einen Zug
    public static void spielerZug(String[][] arr, String symbol) {
        Scanner sc = new Scanner(System.in);
        try {
            int eingabe = sc.nextInt();

            // bei korrekter Eingabe wird durch Division und Modulo die richtige Position im Array ausgewählt
            if (eingabe < 10 && eingabe > 0) {
                int i = (eingabe - 1) / 3;
                int j = (eingabe - 1) % 3;

                // falls das Feld noch nicht belegt ist, wird das Symbol (X oder O) in das Feld geschrieben
                if (!arr[i][j].equals(xT) && !arr[i][j].equals(oT)) {
                    arr[i][j] = symbol;
                } else {
                    System.out.println("Dieses Feld ist bereits belegt!");
                    spielerZug(arr, symbol);
                }

            } else {
                System.out.println("Ungültiger Wert! Bitte eine Zahl zwischen 1 und 9 eingeben.");
                spielerZug(arr, symbol);
            }
        } catch (InputMismatchException e) {
            System.out.println("Ungültige Eingabe! Bitte eine Zahl zwischen 1 und 9 eingeben.");
            sc.nextLine(); // Verwerfe die ungültige Eingabe
            spielerZug(arr, symbol);
        }
    }

    // Computer Spieler (X) macht einen Zug
    public static void computerZug(String[][] arr) {
        int[] bestMove = minimax(arr, "X");
        arr[bestMove[0]][bestMove[1]] = "X"; // ACHTUNG !!! hier kommt die Endlose Schleife
    }

    // Minimax-Algorithmus für die Berechnung des besten Zugs
    public static int[] minimax(String[][] arr, String player) {
        int[] bestMove = new int[]{-1, -1};
        int bestScore = (player.equals("X")) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!arr[i][j].equals(xT) && !arr[i][j].equals(oT)) {
                    arr[i][j] = player;
                    int score = minimaxHelper(arr, 0, false);
                    arr[i][j] = Integer.toString(i * 3 + j + 1);

                    if ((player.equals("X") && score > bestScore) || (player.equals("O") && score < bestScore)) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }

    // Rekursive Hilfsmethode für den Minimax-Algorithmus
    private static int minimaxHelper(String[][] arr, int depth, boolean isMaximizing) {
        int score = bewerteSpielzustand(arr);

        if (score == 10 || score == -10) {
            return score;
        }

        if (istUnentschieden(arr)) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!arr[i][j].equals(xT) && !arr[i][j].equals(oT)) {
                        arr[i][j] = "X";
                        bestScore = Math.max(bestScore, minimaxHelper(arr, depth + 1, !isMaximizing));
                        arr[i][j] = Integer.toString(i * 3 + j + 1);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!arr[i][j].equals(xT) && !arr[i][j].equals(oT)) {
                        arr[i][j] = "O";
                        bestScore = Math.min(bestScore, minimaxHelper(arr, depth + 1, !isMaximizing));
                        arr[i][j] = Integer.toString(i * 3 + j + 1);
                    }
                }
            }
            return bestScore;
        }
    }

    // Funktion zur Bewertung des Spielzustands
    private static int bewerteSpielzustand(String[][] arr) {
        // Überprüfe Reihen
        for (int i = 0; i < 3; i++) {
            if (arr[i][0].equals(arr[i][1]) && arr[i][1].equals(arr[i][2])) {
                if (arr[i][0].equals("X")) {
                    return 10;
                } else if (arr[i][0].equals("O")) {
                    return -10;
                }
            }
        }

        // Überprüfe Spalten
        for (int i = 0; i < 3; i++) {
            if (arr[0][i].equals(arr[1][i]) && arr[1][i].equals(arr[2][i])) {
                if (arr[0][i].equals("X")) {
                    return 10;
                } else if (arr[0][i].equals("O")) {
                    return -10;
                }
            }
        }

        // Überprüfe Diagonalen
        if (arr[0][0].equals(arr[1][1]) && arr[1][1].equals(arr[2][2])) {
            if (arr[0][0].equals("X")) {
                return 10;
            } else if (arr[0][0].equals("O")) {
                return -10;
            }
        }

        if (arr[0][2].equals(arr[1][1]) && arr[1][1].equals(arr[2][0])) {
            if (arr[0][2].equals("X")) {
                return 10;
            } else if (arr[0][2].equals("O")) {
                return -10;
            }
        }

        // Kein Sieger
        return 0;
    }

    // Funktion zur Überprüfung, ob das Spiel unentschieden ist
    private static boolean istUnentschieden(String[][] arr) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!arr[i][j].equals(xT) && !arr[i][j].equals(oT)) {
                    return false;
                }
            }
        }
        return true;
    }
}
