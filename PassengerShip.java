/* File: PassengerShip.java
* Author: Emily Martens
* Date: 5/21/19
* Purpose: Details the PassengerShip class which is a child of the Ship class
*/

import java.util.*;

public class PassengerShip extends Ship {
  private int numberOfOccupiedRooms;
  private int numberOfPassengers;
  private int numberOfRooms;

  public PassengerShip(Scanner sc){
    super(sc);
    if (sc.hasNextInt()){
      this.numberOfOccupiedRooms = sc.nextInt();
    }
    if (sc.hasNextInt()){
      this.numberOfPassengers = sc.nextInt();
    }
    if (sc.hasNextInt()){
      this.numberOfRooms = sc.nextInt();
    }
  }

  @Override
  public String toString() {
    String str = "Passenger Ship: " + super.toString() + "\nNumber of Occupied Rooms: " + this.numberOfOccupiedRooms + "\nNumber of Passengers: " + this.numberOfPassengers + "\nNumber of Rooms: " + this.numberOfRooms;
    return str;
  }

/*
  public static void main(String[] args){
    String line = "200.00 1200.50 10000.26 750.0 42 86 70";
    Scanner sc = new Scanner(line);
    PassengerShip test = new PassengerShip("Test", 1, 2, sc);
    System.out.println(test.toString());
  }
  */
}//end class
