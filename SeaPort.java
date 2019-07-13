/* File: SeaPort.java
* Author: Emily Martens
* Date: 5/21/19
* Purpose:
*/

import java.util.*;

public class SeaPort extends Thing {

  private ArrayList<Dock> docks;
  private ArrayList<Ship> que;
  private ArrayList<Ship> ships;
  private ArrayList<Person> persons;

  public SeaPort(Scanner sc){
    setName(sc.next());
    setIndex(sc.nextInt());
    setParent(sc.nextInt());
    this.docks = new ArrayList<Dock>();
    this.que = new ArrayList<Ship>();
    this.ships = new ArrayList<Ship>();
    this.persons = new ArrayList<Person>();
  }

  public void addDock(Dock dock){
    this.docks.add(dock);
  }

  public void addQue(Ship ship){
    this.que.add(ship);
  }

  public void addShip(Ship ship){
    this.ships.add(ship);
  }

  public void addPerson(Person person){
    this.persons.add(person);
  }

  public ArrayList<Ship> getShips(){
    return this.ships;
  }

  public ArrayList<Ship> getQue(){
    return this.que;
  }

  public ArrayList<Dock> getDocks(){
    return this.docks;
  }

  public ArrayList<Person> getPersons(){
    return this.persons;
  }

  public void sortByName(int choice){
    switch(choice){
      //Ships
      case 1: Ship temp;
              boolean isSorted = true;
              //continue process until sorting is complete
              while (isSorted){
                isSorted = false;
                //compare names of ships until all are in the correct position
                for (int i = 0; i < this.ships.size()-1; i++){
                  if (this.ships.get(i).getName().compareToIgnoreCase(this.ships.get(i+1).getName()) > 0){
                    //System.out.println("Swap: " + this.ships.get(i).getName() + " " + this.ships.get(i+1).getName());
                    temp = this.ships.get(i);
                    this.ships.set(i, this.ships.get(i+1));
                    this.ships.set(i+1, temp);
                    isSorted = true;
                  }
                }
              }
              for (Ship s : this.ships){
                System.out.println(s.getName());
              }
              break;
      //Docks
      case 2: Dock tempDock;
              isSorted = true;
              while (isSorted){
                isSorted = false;
                //compare names of Docks until all are in the correct position
                for (int i = 0; i < this.docks.size()-1; i++){
                  if (this.docks.get(i).getName().compareToIgnoreCase(this.docks.get(i+1).getName()) > 0){
                    tempDock = this.docks.get(i);
                    this.docks.set(i, this.docks.get(i+1));
                    this.docks.set(i+1, tempDock);
                    isSorted = true;
                  }
                }
                for (Dock d : this.docks){
                  System.out.println(d.getName());
                }
              }
              break;
      //Que Ships
      case 3: Ship tempQue;
              isSorted = true;
              while (isSorted){
                isSorted = false;
                //compare names of ships until all are in the correct position
                for (int i = 0; i < this.que.size()-1; i++){
                  if (this.que.get(i).getName().compareToIgnoreCase(this.que.get(i+1).getName()) > 0){
                    tempQue = this.que.get(i);
                    this.que.set(i, this.que.get(i+1));
                    this.que.set(i+1, tempQue);
                    isSorted = true;
                  }
                }
                for (Ship s : this.que){
                  System.out.println(s.getName());
                }
              }
              break;
      //Persons
      case 4: Person tempPerson;
          isSorted = true;
          while (isSorted){
            isSorted = false;
            //compare names of Persons until all are in the correct position
            for (int i = 0; i < this.persons.size()-1; i++){
              if (this.persons.get(i).getName().compareToIgnoreCase(this.persons.get(i+1).getName()) > 0){
                tempPerson = this.persons.get(i);
                this.persons.set(i, this.persons.get(i+1));
                this.persons.set(i+1, tempPerson);
                isSorted = true;
              }
            }
            for (Person p : this.persons){
              System.out.println(p.getName());
            }
          }
          break;
      default: System.out.println("Please enter valid choice.");
    }//end switch statement
  }//end sortByName

  public void sortByNameReverse(int choice){
    switch(choice){
      //Ships
      case 1: Ship temp;
              boolean isSorted = true;
              //continue process until sorting is complete
              while (isSorted){
                isSorted = false;
                //compare names of ships until all are in the correct position
                for (int i = 0; i < this.ships.size()-1; i++){
                  if (this.ships.get(i).getName().compareToIgnoreCase(this.ships.get(i+1).getName()) < 0){
                    //System.out.println("Swap: " + this.ships.get(i).getName() + " " + this.ships.get(i+1).getName());
                    temp = this.ships.get(i);
                    this.ships.set(i, this.ships.get(i+1));
                    this.ships.set(i+1, temp);
                    isSorted = true;
                  }
                }
              }
              for (Ship s : this.ships){
                System.out.println(s.getName());
              }
              break;
      //Docks
      case 2: Dock tempDock;
              isSorted = true;
              while (isSorted){
                isSorted = false;
                //compare names of Docks until all are in the correct position
                for (int i = 0; i < this.docks.size()-1; i++){
                  if (this.docks.get(i).getName().compareToIgnoreCase(this.docks.get(i+1).getName()) < 0){
                    tempDock = this.docks.get(i);
                    this.docks.set(i, this.docks.get(i+1));
                    this.docks.set(i+1, tempDock);
                    isSorted = true;
                  }
                }
                for (Dock d : this.docks){
                  System.out.println(d.getName());
                }
              }
              break;
      //Que Ships
      case 3: Ship tempQue;
              isSorted = true;
              while (isSorted){
                isSorted = false;
                //compare names of ships until all are in the correct position
                for (int i = 0; i < this.que.size()-1; i++){
                  if (this.que.get(i).getName().compareToIgnoreCase(this.que.get(i+1).getName()) < 0){
                    tempQue = this.que.get(i);
                    this.que.set(i, this.que.get(i+1));
                    this.que.set(i+1, tempQue);
                    isSorted = true;
                  }
                }
                for (Ship s : this.que){
                  System.out.println(s.getName());
                }
              }
              break;
      //Persons
      case 4: Person tempPerson;
          isSorted = true;
          while (isSorted){
            isSorted = false;
            //compare names of Persons until all are in the correct position
            for (int i = 0; i < this.persons.size()-1; i++){
              if (this.persons.get(i).getName().compareToIgnoreCase(this.persons.get(i+1).getName()) < 0){
                tempPerson = this.persons.get(i);
                this.persons.set(i, this.persons.get(i+1));
                this.persons.set(i+1, tempPerson);
                isSorted = true;
              }
            }
            for (Person p : this.persons){
              System.out.println(p.getName());
            }
          }
          break;
      default: System.out.println("Please enter valid choice.");
    }//end switch statement
  }//end sortByNameReverse

  //Sort qued ships by attribute
  public void sortQue(int choice){
    Boolean isSorted = true;
    Ship temp;

    while(isSorted){
      isSorted = false;
      for (int i = 0; i < this.que.size()-1; i++){
        //ascending
        if (choice == 1){
          if (this.que.get(i).compareTo(this.que.get(i+1)) > 0){
            temp = this.que.get(i);
            this.que.set(i, this.que.get(i+1));
            this.que.set(i+1, temp);
            isSorted = true;
          }
        } else {
          //descending
          if (this.que.get(i).compareTo(this.que.get(i+1)) < 0){
            temp = this.que.get(i);
            this.que.set(i, this.que.get(i+1));
            this.que.set(i+1, temp);
            isSorted = true;
          }
        }
      }
    }
    /*
    for (Ship s : this.que){
      //weight, length, width, draft
      System.out.print(s.getName() + ", " + s.getWeight() + ", " + s.getLength() + ", " + s.getWidth() + ", " + s.getDraft() + "\n");
    }
    */
  }//end sortQue

  @Override
  public String toString() {
    String str = "Port: " + super.toString() + "\n\n>>>ALL DOCKS:\n\n";
    for (Dock d : this.docks){
      str += d.toString() + "\n\n";
    }
    str += "\n>>>SHIPS IN QUE:\n\n";
    for (Ship s : this.que){
      str += s.getName() + "\n\n";
    }
    str += "\n\n>>>ALL SHIPS:\n\n";
    for (Ship s : this.ships){
      str += s.getName() + ", Index: " + s.getIndex() + "\n\n";
    }
    str += "\n>>>ALL PERSONS:\n\n";
    for (Person p : this.persons){
      str += p.getName() + "\nSkill: " + p.getSkill() + "\n";
    }
    return str;
  }
/*
  public static void main(String[] args){
    SeaPort test = new SeaPort("Test", 1, 2);
    String line = "200.00 1200.50 10000.26 750.0 8000000.00 450.78 897.23";
    Scanner sc = new Scanner(line);
    CargoShip ship = new CargoShip("Sanctimony", 2, 4, sc);
    test.addShip(ship);
    //test.addQue(ship);
    Dock dock = new Dock("Dock 1", 3, 1);
    Dock dock2 = new Dock("Dock2", 4, 1);
    dock2.setShip(ship);
    test.addDock(dock);
    test.addDock(dock2);
    System.out.println(test.toString());
  }
  */
}// end class;
