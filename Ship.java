/* File: Ship.java
* Author: Emily Martens
* Date: 5/21/19
* Purpose:

Ship extends Thing
o arrivalTime, dockTime: PortTime
o draft, length, weight, width: double
o jobs: ArrayList <Job>
*/

import java.util.*;

public class Ship extends Thing implements Runnable {
  private PortTime arrivalTime, dockTime;
  private double draft, length, weight, width;
  private ArrayList<Job> jobs;
  //Lock dockLock = new ReentrantLock();

  public Ship(Scanner sc){
    super();
    setName(sc.next());
    setIndex(sc.nextInt());
    setParent(sc.nextInt());
    if (sc.hasNextDouble()){
      this.draft = sc.nextDouble();
    }
    if (sc.hasNextDouble()){
      this.length = sc.nextDouble();
    }
    if (sc.hasNextDouble()){
      this.weight = sc.nextDouble();
    }
    if (sc.hasNextDouble()){
      this.width = sc.nextDouble();
    }
    this.jobs = new ArrayList<Job>();
  }

  public void setArrivalTime(int time){
    PortTime newArrival = new PortTime(time);
    this.arrivalTime = newArrival;
  }

  public void setDockTime(int time){
    PortTime newDockTime = new PortTime(time);
    this.dockTime = newDockTime;
  }

  public PortTime getArrivalTime(){
    return this.arrivalTime;
  }

  public void addJob(Job job){
    this.jobs.add(job);
  }

  public ArrayList<Job> getJobs(){
    return this.jobs;
  }

  public PortTime getDockTime(){
    return this.dockTime;
  }

  public double getDraft(){
    return this.draft;
  }

  public double getLength(){
    return this.length;
  }

  public double getWeight(){
    return this.weight;
  }

  public double getWidth(){
    return this.width;
  }

  public Boolean areJobsDone(){
    Boolean isDone;
    int count = 0;
    for (Job j : this.getJobs()){
      if (j.done() != true){
        count +=1;
      }
    }
    if (count > 0){
      isDone = false;
    } else {
      isDone = true;
    }
    return isDone;
  }

  public void run(){
    boolean isDone = false;
    for (Job j : this.getJobs()){
      j.toggleGo();
    }
    while (isDone == false){
      isDone = areJobsDone();
      try {
        Thread.sleep(100);
      } catch (InterruptedException ie){}
    }
  }

   public int compareTo(Ship compare){
     //sort priority weight, length, width, draft
     int result = Double.compare(this.weight, compare.getWeight());
     if (result == 0){
       result = Double.compare(this.length, compare.getLength());
     }
     if (result == 0){
       result = Double.compare(this.width, compare.getWidth());
     }
     if (result == 0){
       result = Double.compare(this.draft, compare.getDraft());
     }
     return result;
   }

  @Override
  public String toString() {
    String str = super.toString() + "\nDraft: " + this.draft + "\nLength: " + this.length + "\nWeight: " + this.weight + "\nWidth: " + this.width;
    return str;
  }

/*
  public static void main(String[] args){
    String line = "200.00 1200.50 10000.26 750.0";
    Scanner sc = new Scanner(line);
    Ship test = new Ship("Test", 1, 2, sc);
    System.out.println(test.toString());
  }
  */
}//end class
