import java.util.Scanner;
import java.util.InputMismatchException;

public class TicTacToe {
  public static void main(String[] args) {
		char[][] field = createField();
		boolean playerOne = getFirstPlayer();
		Scanner scanner = new Scanner(System.in);
		int xCoordinate, yCoordinate;
		
		printField(field);
		
		while (hasEmptyField(field) && !hasPlayerWon(field)) {
      yCoordinate = getCoordinate(scanner);
		  xCoordinate = getCoordinate(scanner);
      // richtige Eingabe erzwingen
      while (!isValid(xCoordinate, yCoordinate, field)) {
        System.out.println("Das Feld ist außerhalb des Spielfelds oder bereits belegt.");
        yCoordinate = getCoordinate(scanner);
		    xCoordinate = getCoordinate(scanner);
      } 
      
      if (playerOne) {
        field[xCoordinate][yCoordinate] = 'X';
        playerOne = false;
      } else {
      	field[xCoordinate][yCoordinate] = 'O';
        playerOne = true;
    	}
    	printField(field);
		}
  }
  
  public static char[][] createField() {
    char[][] field = new char[3][3];
    for (int x = 0; x < field.length; x++) {
    	for (int y = 0; y < field[x].length; y++) {
    	  field[x][y] = ' ';
    	}
    }
    return field;
  }
  
  public static void printField(char[][] field) {
    String strich = "--"; // horizontaler Strich
    
    // Ausgabe der Zahlen für die Spalten
    System.out.print(" ");
    for (int y = 0; y < field[0].length; y++) {
    	strich += "--"; // Länge des honrizontalen Strichs bestimmen
    	System.out.print(" " + y);
  	}
    System.out.println("");
    
    for (int x = 0; x < field.length; x++) {
    	System.out.println(strich);
      System.out.print(x + "|");
    	for (int y = 0; y < field[x].length; y++) {
    	  System.out.print(field[x][y] + "|");
    	}
    	System.out.println("");
    }
    System.out.println(strich);
  }
  
  public static boolean getFirstPlayer() {
    double random =  Math.random();
  	if (random < 0.5) {
  		return true;
  	} else {
  	  return false;
  	}
  }
  
  public static boolean isValid(int x, int y, char[][] field) {
    return x >= 0 && x < field.length && y >= 0 && y < field[x].length
        && field[x][y] == ' ';
  }
  
  public static boolean hasEmptyField(char[][] field) {
    for (int x = 0; x < field.length; x++) {
      for (int y = 0; y < field[x].length; y++) {
        if (field[x][y] == ' ') {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean hasPlayerWon(char[][] field) {
    for (int x = 0; x < field.length; x++) {
      if (field[x][0] != ' ' && field[x][0] == field[x][1] && field[x][1] == field[x][2]) {
        return true;
      }
    }
    for (int y = 0; y < field[0].length; y++) {
      if (field[0][y] != ' ' && field[0][y] == field[1][y] && field[1][y] == field[2][y]) {
        return true;
      }
    }
    if (field[0][0] != ' ' && field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
     return true;
    }
    if (field[0][2] != ' ' && field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
     return true;
    }
    return false;
  }
  
  public static int getCoordinate(Scanner scanner) {
    int coordinate;
    try {
      coordinate = scanner.nextInt();
    } catch (InputMismatchException ex) {
      System.out.println("Bitte gebe eine Zahl ein.");
      // token aus scanner entfernen
      scanner.next();
      coordinate = getCoordinate(scanner);    
    }
    return coordinate;
  }
}
