/**
* A game of Towers of Hanoi
* @see <a href="https://en.wikipedia.org/wiki/Tower_of_Hanoi">Wikipedia</a>
* @author lisstem
*/

public class Hanoi {
  private Tower leftTower;
  private Tower middleTower;
  private Tower rightTower;

  /**
  * Creates a new game of Towers of Hanoi with a given number of disks
  * @param size the number of disks
  * @throws IllegalArgumentException if size is less or equal to zero
  */
  public Hanoi(int size) throws IllegalArgumentException {
    if (size <= 0) {
      throw new IllegalArgumentException("Size must be positive.");
    }
    leftTower = new Tower(size, true);
    middleTower = new Tower(size);
    rightTower = new Tower(size);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(leftTower.getSize() * 14 + 14);
    String[][] towers = new String[3][];
    towers[0] = leftTower.toString().split("\n");
    towers[1] = middleTower.toString().split("\n");
    towers[2] = rightTower.toString().split("\n");

    for (int i = 0; i <= leftTower.getSize(); i++) {
      for (int t = 0; t < 3; t++) {
        builder.append(towers[t][i]).append("  ");
      }
      builder.delete(builder.length() - 2, builder.length());
      builder.append("\n");
    }
    builder.deleteCharAt(builder.length() - 1);

    return builder.toString();
  }

  /**
  * Solves the game of hanoi.
  * Works only on a fresh game.
  * @param stepByStep whether a step by step solution should be output
  * @return whether the game could be solved
  */
  public boolean solve(boolean stepByStep) {
   return moveDisks(leftTower, rightTower, middleTower, leftTower.getSize(), stepByStep);
  }

  /**
  * Moves the top disks from one tower to another.
  * @param source the tower to take the disks from
  * @param target the tower to move the disks to
  * @param other the other tower, used as a intermediate tower
  * @param number the number of disks to move
  * @param stepByStep whether a step by step solution should be output
  * @return whether the move was successful
  */
  private boolean moveDisks(Tower source, Tower target, Tower other,
    int number, boolean stepByStep) {
    if (number <= 0) {
      throw new IllegalArgumentException("Number of disks must be positive.");
    }
    if (number > 1 && !moveDisks(source, other, target, number - 1, stepByStep)) {
      return false;
    }
    if (!moveDisk(source, target, stepByStep)) {
      // reverse changes if move fails
      if (number > 1) {
        moveDisks(other, source, target, number - 1, false);
      }
      return false;
    }
    if (number > 1 && !moveDisks(other, target, source, number - 1, stepByStep)) {
      // reverse changes if move fails
      moveDisk(target, source, false);
      moveDisks(other, source, target, number - 1, false);
      return false;
    }
    return true;
  }

  /**
  * Moves the top disk from one tower to another
  * @param source the tower to take the disk from
  * @param target the tower to move the disk to
  * @param output outputs the game state after the move if true
  * @return whether the move was successful
  */
  private boolean moveDisk(Tower source, Tower target, boolean output) {
    int disk = source.removeDisk();
    if (!target.addDisk(disk)) {
      // reverse changes if move fails
      source.addDisk(disk);
      return false;
    }
    if (output) {
      System.out.println(this);
    }
    return true;
  }


  /**
  * Starts a new game of hanoi with a given size and solves it
  * @param args args[0] must 
  */
  public static void main(String[] args) {
    String helpMessage = "Usage: java Hanoi <SIZE>\nStarts a new game of Towers of Hanoi with SIZE disks and solves it.";
    if (args.length == 0) {
      System.out.println("Error: No SIZE specified.");
      System.out.println(helpMessage);
      System.exit(-1);
    }
    int size = 0;
    switch (args[0].toLowerCase().trim()) {
      case "--help":
      case "help":
      case "-h":
        System.out.println(helpMessage);
      return;
      default:
        try {
          size = Integer.parseInt(args[0]);
          break;
        } catch (NumberFormatException ex) {
          System.out.println("Error: \"" + args[0] + "\" is not an integer.");
          System.out.println(helpMessage);
          System.exit(-1);
        }
    }
    if (size <= 0) {
      System.out.println("Error: SIZE must be positive.");
      System.out.println(helpMessage);
      System.exit(-1);
    }
    Hanoi hanoi = new Hanoi(size);
    System.out.println(hanoi);
    hanoi.solve(true);
  }
}
