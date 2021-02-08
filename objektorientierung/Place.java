public abstract class Place {
  private String name;
  
  public String getName() {
    return name;
  }
  
  public abstract double getSize();
  
  public Place(String name) {
    this.name = name;
  }
}
