import java.lang.reflect.Array;
import java.util.InputMismatchException;
import java.util.Scanner; // importiert die Klasse Scanner
//testcommit

public class tictactoe {

	//Farben setzen für X und O
	public static String GREEN = "\u001B[32m";
    public static String BLUE = "\u001B[36m";
    public static String RESET = "\u001B[0m";
	public static String xT = GREEN + "X" + RESET;
    public static String  oT = BLUE + "O" + RESET;
    public static int eingabe = 0;
  //Den "Scanner" erstellen
  	public static Scanner sc = new Scanner(System.in);
 // Variablen Deklarieren
 	public static String[][] fields = new String [3][3];
 	
 	public static boolean win = false;
	public static boolean draw = false;
	public static int spieler = 1;
	
	public static void main(String[] args) {

		int modus=0;
		boolean we = true;

		//erstellen von Spielfeld
		int zahl = 1;
		for (int i = 0; i < 3; i++) {

			for(int j = 0; j<3; j++) {
				fields[i][j] = Integer.toString(zahl);
				
				zahl++;
			}
		}

		System.out.println("Wollen Sie 2-Player-Modus(1) oder gegen Computer(2) spielen?");
		
		//Prüfen ob Eingabe vom "modus" richtig ist
 		while(we)
		{
			try
			{
				
				modus = sc.nextInt();
				if(modus <1 || modus >2)
				System.out.println("Falsche Eingabe, Bitte 1 oder 2");
				else if(modus == 1 || modus==2)
				{
					we = false;
				}
				
					
			}
		
			catch(Exception e)
			{
				System.out.println("Ungültige Eingabe! Bitte eine Zahl zwischen 1 und 2 eingeben.");
				we = true;
				sc.next();
				//continue;
				
			}
		

			
	}

			
			
			//2 Player Modus wird gespielt
			if(modus==1)
			{
				//Ausgabe, welcher Spieler an der Reihe ist
				
					
					while(!win && !draw)
					{
						SpielfeldAusgeben(fields);
						
						//Ausgabe, welcher Spieler an der Reihe ist
						if(spieler == 1) {
							System.out.println(BLUE +"Spieler 1 (O)" + RESET + " ist an der Reihe");
							System.out.println("Wo möchtest du setzen?");
								
						} else {
							System.out.println(GREEN +"Spieler 2 (X) "+RESET+" ist an der Reihe");
							System.out.println("Wo möchtest du setzen?");
							
						}
						
						
						try
						{
							//Eingabe User
							eingabe = sc.nextInt();
							
							//bei korrekter Eingabe wird durch Division und Modulo die richtige Position im Array ausgewählt
							if (eingabe < 10 & eingabe > 0) {
				
								int i = (eingabe - 1) / 3;
								int j = (eingabe -1) % 3;
								
								//falls das Feld noch nicht belegt ist, wird je nach spieler ein X oder ein O in das Feld geschrieben
								if (fields[i][j] != xT && fields[i][j] != oT) {
				
									if (spieler == 1) {
										fields[i][j] = oT;
										
									} else {
										fields[i][j] = xT;
										
										
									}
									//Fehlermeldung, falls das Feld bereits belegt ist
								} else {
									System.out.println("Dieses Feld ist bereits belegt!");
									continue;
									
								}
				
							} 
							else {
								System.out.println("Ungültiger Wert! Bitte eine Zahl zwischen 1 und 9 eingeben.");
								continue;
							}
						}
						//bei der Eingabe eines nicht-numerischen Wertes wird eine Fehlermeldung ausgegeben und die Eingabe wird verworfen
						//die Schleife wird erneut durchlaufen, odass der Spieler erneut ein Feld wählen kann
						catch (InputMismatchException e) {
							System.out.println("Ungültige Eingabe! Bitte eine Zahl zwischen 1 und 9 eingeben.");
							sc.nextLine(); // Verwerfe die ungültige Eingabe
							continue;
				
						}
						
						
						//Geprüft ob auf dem Spielfeld unenschieden oder "win" ist
						pruefeSpielstandWinOrDraw(fields);
						
					}
					
						
				
				
				
				
				
			}
			//Spiel wird gegen Computer gestartet
			else if(modus==2)
			{
				
                    
					if(spieler == 1) 
					{
						
						while(!win && !draw)
						{
							
							//Ausgabe, welcher Spieler an der Reihe ist
							if(spieler == 1) {
								SpielfeldAusgeben(fields);
								System.out.println(BLUE +"Spieler 1 (O)" + RESET + " ist an der Reihe");
								System.out.println("Wo möchtest du setzen?");
								
								try
								{
									
									eingabe = sc.nextInt();
									
									//bei korrekter Eingabe wird durch Division und Modulo die richtige Position im Array ausgewählt
									if (eingabe < 10 & eingabe > 0) {
						
										int i = (eingabe - 1) / 3;
										int j = (eingabe -1) % 3;
										
										//falls das Feld noch nicht belegt ist, wird je nach spieler ein X oder ein O in das Feld geschrieben
										if (fields[i][j] != xT && fields[i][j] != oT) {
						
											if (spieler == 1) {
												fields[i][j] = oT;
												
											} else {
												fields[i][j] = xT;
												
												
											}
											//Fehlermeldung, falls das Feld bereits belegt ist
										} else {
											System.out.println("Dieses Feld ist bereits belegt!");
											continue;
											
										}
						
									} 
									else {
										System.out.println("Ungültiger Wert! Bitte eine Zahl zwischen 1 und 9 eingeben.");
										continue;
									}
								}
								//bei der Eingabe eines nicht-numerischen Wertes wird eine Fehlermeldung ausgegeben und die Eingabe wird verworfen
								//die Schleife wird erneut durchlaufen, odass der Spieler erneut ein Feld wählen kann
								catch (InputMismatchException e) {
									System.out.println("Ungültige Eingabe! Bitte eine Zahl zwischen 1 und 9 eingeben.");
									sc.nextLine(); // Verwerfe die ungültige Eingabe
									continue;
						
								}
								
								pruefeSpielstandWinOrDraw(fields);
									
							} else {
								SpielfeldAusgeben(fields);
								System.out.println(GREEN +"Spieler 2 (Computer)(X) "+RESET+" ist an der Reihe");
			                    int input = getComputerMove(fields);
			                    placeToken(fields, input, xT);
			                    pruefeSpielstandWinOrDraw(fields);
								
							}
							
							
							
							
						}
                    
                    
				} 
			}
		}
	
	
	

	//Funktion, die das Spielfeld mit den Werten im übergebenen Array ausgibt
	public static void SpielfeldAusgeben(String[][] arr){
		//Spielfeld ausgeben.
		System.out.println(arr[2][0] + " | " + arr[2][1] + " | " + arr[2][2]);
		System.out.println("----------");
		System.out.println(arr[1][0] + " | " + arr[1][1] + " | " + arr[1][2]);
		System.out.println("----------");
		System.out.println(arr[0][0] + " | " + arr[0][1] + " | " + arr[0][2]);
		System.out.println("");

	}
	
	
	//Methode um azf "draw" und "win" zu prüfen
	public static void pruefeSpielstandWinOrDraw(String[][] fields)
	{
		
		//Überprüfung, ob jemand gewonnen hat
		// Überprüfung Reihen und Spalten
		for( int i = 0; i<3; i++) {
			if((fields[i][0] == fields[i][1] & fields[i][1] == fields[i][2]) ||
					(fields[0][i] == fields[1][i] & fields[1][i] == fields[2][i])) {
				SpielfeldAusgeben(fields);
				
				System.out.println("Spieler " + spieler + " hat gewonnen");
				win = true;
				
			}
		}

		//Überprüfung Diagonalen
		if((fields[0][0] == fields[1][1] & fields[1][1] == fields[2][2]) ||
				(fields[0][2] == fields[1][1] & fields[1][1] == fields[2][0])) {
			SpielfeldAusgeben(fields);
			System.out.println("Spieler " + spieler + " hat gewonnen");
			win = true;
		}


		//Überprüfung ob unentschieden
		draw = true; // Annahme: Es ist unentschieden, bis ein leeres Feld gefunden wird
		for(int i = 0; i<3; i++) {

			for(int j = 0; j<3; j++) {

				if(fields[i][j] != xT && fields[i][j] != oT ) {
					
					draw = false;
					break;
					
				}	
				
				
			} 

		} 
		
		
		if(draw == true)
		{
			SpielfeldAusgeben(fields);
			System.out.println("Das Spiel ist unentschieden!");
			
		}
		if (spieler == 1){
			spieler = 2;
		} else {
			spieler = 1;
		}
		
		
	}
	
	
	
	// Prüfen ob der Platz schon belegt ist
    private static void placeToken(String[][] arr, int input, String tSymbol) {
            int i = (input - 1) / 3;
            int j = (input - 1) % 3;
            arr[i][j] = tSymbol;
    }
	
	private static int getComputerMove(String[][] arr) {
        int computerMove = -1; // Standardinitialisierung außerhalb der Schleife;
        boolean validInput = false;
        int i =0;
        int j =0;
        while (validInput == false) {
                computerMove = (int) (Math.random() * 9 + 1);
                i = (computerMove - 1) / 3;
                j = (computerMove - 1) % 3;

                if (arr[i][j].equals(xT) || arr[i][j].equals(oT)) {
                	validInput = false;
                	
                        
                }
                else if(!arr[i][j].equals(xT) || !arr[i][j].equals(oT))
                {
                		break;
                }
                
        }
        
        return computerMove;
}

}