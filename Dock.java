/* File: Dock.java
* Author: Emily Martens
* Date: 5/21/19
* Purpose: Details the Dock class which belongs to a SeaPort and may contain a Ship
*/

import java.util.*;

public class Dock extends Thing implements Runnable {
  private Ship ship = null;
  protected Boolean isFull = false;

  public Dock(Scanner sc){
    super();
    setName(sc.next());
    setIndex(sc.nextInt());
    setParent(sc.nextInt());
  }

  public void setShip(Ship ship){
    this.ship = ship;
  }

  public Ship getShip(){
    return this.ship;
  }

  public void run(){


    if (this.getShip() != null){
      for (Job j : this.getShip().getJobs()){
        j.toggleGo();
      }
      System.out.println("Jobs running");
    }

    
  }

/*
  public Boolean ready(){
    Boolean r = true;
    if (this.getShip() != null){
      r = this.getShip().areJobsDone();
    }
    return r;
  }

  public synchronized void processShip(){
    if (this.getShip() != null){
      Thread t = new Thread(this.getShip());
      t.start();
    } else {
      System.out.println("No ship set");
    }
  }
*/
  @Override
  public String toString() {
    String str = "Dock: " + super.toString();
    if (this.ship != null){
      str += "\nShip: " + this.ship.getName();
    } else {
      str += "\nShip: None";
    }
    return str;
  }

  /*

  public static void main(String[] args){
    Dock test = new Dock("Test", 1, 2);
    System.out.println(test.toString());
  }
  */
}//end class
