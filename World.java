/* File: World.java
* Author: Emily Martens
* Date: 5/21/19

ports: ArrayList <SeaPort>
o time: PortTime
* Purpose:
*/

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class World extends Thing {
  private PortTime time;
  private ArrayList<SeaPort> ports;

  public World(String name, Scanner sc){
    super();
    setName(name);
    setIndex(0);
    setParent(-1);
    this.ports = new ArrayList<SeaPort>();
    //initilize the world
    initWorld(sc);
  }

  //method to read the submitted file and build the world
  public void initWorld(Scanner sc){
    //New Code: use HashMaps to find correct parent rather than search entire data structure
    //New Code: Add the completed SeaPorts to the SeaPort ArrayList from the HashMap
    HashMap<Integer, SeaPort> portsMap = new HashMap<Integer, SeaPort>();
    HashMap<Integer, Dock> docksMap = new HashMap<Integer, Dock>();
    HashMap<Integer, Ship> dockedShip = new HashMap<Integer, Ship>();
    HashMap<Integer, Ship> queuedShip = new HashMap<Integer, Ship>();
    //while there is a next line to read continue
    while (sc.hasNextLine()){
      String nl = sc.nextLine();
      System.out.println("Current Line: " + nl);
      if (!Objects.equals(nl, "//")){
        try {
          Scanner scanPass = new Scanner(nl);
          String type = scanPass.next();

          switch(type){
            case "port": SeaPort port = new SeaPort(scanPass);
                         //add to SeaPorts HashMap
                         portsMap.put(port.getIndex(), port);
                         break;
            case "dock": Dock dock = new Dock(scanPass);
                  //add to Docks HashMap
                  docksMap.put(dock.getIndex(), dock);
                  break;
            case "ship": Ship ship = new Ship(scanPass);
                  dockedShip.put(ship.getIndex(), ship);
                  //look up parent dock and setShip
                  //docksMap.get(ship.getParent()).setShip(ship);
                  break;
            case "cship": CargoShip cship = new CargoShip(scanPass);
                          dockedShip.put(cship.getIndex(), cship);
                  //docksMap.get(cship.getParent()).setShip(cship);
                 break;
           case "pship": PassengerShip pship = new PassengerShip(scanPass);
                         dockedShip.put(pship.getIndex(), pship);
                 //Add to Ships HashMap
                 //docksMap.get(pship.getParent()).setShip(pship);
                break;
            case "que": Ship queShip;
                        String typeShip = scanPass.next();
                        switch(typeShip){
                          //for each case add the correctly typed ship to the corresponding que and seaport ship list
                          case "cship": queShip = new CargoShip(scanPass);
                                queuedShip.put(queShip.getIndex(), queShip);
                                //portsMap.get(queShip.getParent()).addQue(queShip);
                              //  portsMap.get(queShip.getParent()).addShip(queShip);
                                break;
                          case "pship": queShip = new PassengerShip(scanPass);
                                  queuedShip.put(queShip.getIndex(), queShip);
                              //  portsMap.get(queShip.getParent()).addQue(queShip);
                              //  portsMap.get(queShip.getParent()).addShip(queShip);
                                break;
                          default: queShip = new Ship(scanPass);
                              queuedShip.put(queShip.getIndex(), queShip);
                              //  portsMap.get(queShip.getParent()).addQue(queShip);
                              //  portsMap.get(queShip.getParent()).addShip(queShip);
                        }
                        break;
              case "person": Person person = new Person(scanPass);
                             //add person to seaport
                             portsMap.get(person.getParent()).addPerson(person);
                             break;
              case "job": Job job = new Job(scanPass);
                          //look up parent index and add job to parent ship
                          int par = job.getParent();
                          if (dockedShip.containsKey(par)){
                            dockedShip.get(par).addJob(job);
                          } else if (queuedShip.containsKey(par)){
                            queuedShip.get(par).addJob(job);
                          } else {
                            System.out.println("No parent");
                          }
                          break;
              default: continue;
          }//end switch statement
        } catch (NoSuchElementException nsee){
          System.out.println("");
        }
      }
    }
    //add Ships to correct docks
    for (Ship sh : dockedShip.values()){
      docksMap.get(sh.getParent()).setShip(sh);
    }
    //add queued ships to correct SeaPort (both lists)
    for (Ship qsh : queuedShip.values()){
      portsMap.get(qsh.getParent()).addQue(qsh);
      portsMap.get(qsh.getParent()).addShip(qsh);
    }
    //add docks to correct SeaPort
    for (Dock d : docksMap.values()){
      portsMap.get(d.getParent()).addDock(d);
      //add dock ship to list of ships at seaport
      Ship s = d.getShip();
      portsMap.get(d.getParent()).addShip(s);
    }
    //add completed SeaPorts to World
    for (SeaPort port : portsMap.values()){
      this.addPort(port);
    }
  }

  public PortTime getWorldTime(){
    return this.time;
  }

  public void setWorldTime(int time){
    PortTime newTime = new PortTime(time);
    this.time = newTime;
  }

  public void addPort(SeaPort port){
    this.ports.add(port);
  }

  public ArrayList<SeaPort> getPorts(){
    return this.ports;
  }

  @Override
  public String toString(){
    String str = "World: " + this.getName() + "\n\n";
    for (SeaPort sp : this.ports){
      str += sp.toString();
      str += "\n\n=========================\n\n";
    }
    return str;
  }
  /*
  public static void main(String[] args){
    File f = new File("sort.txt");
    try {
      Scanner sc = new Scanner(f);
      World test = new World("World", sc);
      //test.sortPorts(1);
      //test.sortPorts(4);
      test.getPorts().get(1).sortQue(2);
    } catch(FileNotFoundException fnfe){
      System.out.println("File not found");
    }
  }
  */
}//end class
