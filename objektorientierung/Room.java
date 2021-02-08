public class Room extends Place {
  private double size;

  public double getSize() {
    return size;
  }
  
  public Room(String name, double size) {
    super(name);
    this.size = size;
  }
  
  public String toString() {
    return getName() + " (" + size + "m²)";
  }
}
