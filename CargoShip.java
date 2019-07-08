/* File: CargoShip.java
* Author: Emily Martens
* Date: 5/21/19
* Purpose: Details CargoShip class, a child of the Ship class
*/

import java.util.*;

public class CargoShip extends Ship {
  private double cargoValue;
  private double cargoVolume;
  private double cargoWeight;

  public CargoShip(Scanner sc){
    super(sc);
    if(sc.hasNextDouble()){
      this.cargoValue = sc.nextDouble();
    }
    if (sc.hasNextDouble()){
      this.cargoVolume = sc.nextDouble();
    }
    if (sc.hasNextDouble()){
      this.cargoWeight = sc.nextDouble();
    }
  }

  @Override
  public String toString() {
    String str = "Cargo Ship: " + super.toString() + "\nCargo Value: " + this.cargoValue + "\nCargo Volume: " + this.cargoVolume + "\nCargo Weight: " + this.cargoWeight;
    return str;
  }

  /*
  public static void main(String[] args){
    String line = "200.00 1200.50 10000.26 750.0 2000000.99 600.00 1000.00";
    Scanner sc = new Scanner(line);
    CargoShip test = new CargoShip("Test", 1, 2, sc);
    System.out.println(test.toString());
  }
  */
}
