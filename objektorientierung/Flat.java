public class Flat extends Place {
  private Room entry;
  private Room[] rooms;
  
  public Flat(String name, Room entry) {
    super(name);
    this.entry = entry;
    rooms = new Room[1];
    rooms[0] = entry;
  }
  
  public void addRoom(Room room) {
    Room[] newArray = new Room[rooms.length + 1];
    for (int i = 0; i < rooms.length; i++) {
      newArray[i] = rooms[i];
    }
    newArray[newArray.length - 1] = room;
    rooms = newArray;
  }
 
  public double getSize() {
    double size = 0;
    for (int i = 0; i < rooms.length; i++) {
      size += rooms[i].getSize();
    }
    return size;
  }
  
  public String toString() {
    String out = getName() + " (" + getSize() + "m²):";
    for (int i = 0; i < rooms.length; i++) {
      out += "\n - " + rooms[i].toString();
    }
    return out;
  }

  public static void main(String[] args) {
    Room flur = new Room("Flur", 10.0);
    Flat flat = new Flat("1.OG rechts", flur);
    Room kueche = new Room("Küche", 12.0);
    Room bad = new Room("Bad", 8.0);
    flat.addRoom(kueche);
    flat.addRoom(bad);
    System.out.println(flat);
  }
}
