public class TicTacToe {
  public static void main(String[] args) {
		char[][] field = createField();
		printField(field);
		boolean playerOne = getFirstPlayer();
		System.out.println(playerOne);
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
  	System.out.println("  0 1 2");
    for (int x = 0; x < field.length; x++) {
    	System.out.println("--------");
      System.out.print(x + "|");
    	for (int y = 0; y < field[x].length; y++) {
    	  System.out.print(field[x][y] + "|");
    	}
    	System.out.println("");
    }
    System.out.println("--------");
  }
  
  public static boolean getFirstPlayer() {
    double random =  Math.random();
  	if (random < 0.5) {
  		return true;
  	} else {
  	  return false;
  	}
  }
}
