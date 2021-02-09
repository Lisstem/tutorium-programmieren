/**
* A tower in Towers of Hanoi
* @see Hanoi Towers of Hanoi
* @author lisstem
*/
public class Tower {
	private int[] disks;

	/**
	* Creates a new tower.
	* @param size the size of the tower
	* @param filled whether the tower should be filled with size disks
	* @throws IllegalArgumentException if size is less or equal to zero
	*/
	public Tower(int size, boolean filled) throws IllegalArgumentException {
		if (size <= 0) {
			throw new IllegalArgumentException("Size must be positive.");
		}

		disks = new int[size];
		for (int i = 0; i < size; i++) {
			if (filled) {
				disks[i] = i + 1;
			} else {
				disks[i] = 0;
			}
		}
	}

	/**
	* Creates a new tower.
	* @param size the size of the tower
	* @throws IllegalArgumentException if size is less or equal to zero
	*/
	public Tower(int size) throws IllegalArgumentException {
		this(size, false);
	}

	/**
	* Removes the top disk from the tower.
	* @return the top disk or zero if the tower is empty
	*/
	public int removeDisk() {
		for (int i = 0; i < disks.length; i++) {
			if (disks[i] != 0) {
				int disk = disks[i];
				disks[i] = 0;
				return disk;
			}
		}
		return 0;
	}

	/**
	* Adds a disk to the tower.
	* The disk will be rejected if it is less than 1 or greater than the size of the tower.
	* The disk will also be rejected if the tower is full or adding the disk will violate the rules of the game.
	* @param disk the size of the disk
	* @return wheather the disk was added
	*/
	public boolean addDisk(int disk) {
		if (disk <= 0 || disk > disks.length) {
			return false;
		}
		for (int i = disks.length - 1; i >= 0; i--) {
			if (disks[i] == 0) {
				disks[i] = disk;
				return true;
			} else if (disks[i] <= disk) {
				return false;
			}
		}
		return false;
	} 

	@Override
	public String toString() {
		// using a StringBuilder is more efficient than using String concatenation (the + operator)
		StringBuilder builder = new StringBuilder(disks.length * 4 + 3);
		for (int i = 0; i < disks.length; i++) {
			outputDisk(builder, disks[i]).append("\n");
		}
		builder.append("===");

		return builder.toString();
	}

	/**
	* Adds a disk as a String to a Stringbuilder.
	* @params builder the StringBuilder
	* @params disk the disk
	* @return the same StringBuilder for convenience
	*/
	private StringBuilder outputDisk(StringBuilder builder, int disk) {
		if (disk == 0) {
			builder.append(" | ");
		} else {
			if (disk < 100) {
				builder.append(' ');
			}
			builder.append(disk);
			if (disk < 10) {
				builder.append(' ');
			}
		}
		return builder;
	}

	/**
	* returns the size of the tower
	* @return the size of the tower
	*/
	public int getSize() {
		return disks.length; 
	}
}
