/* File: PortTime.java
* Author: Emily Martens
* Date: 5/21/19
*Purpose:

PortTime
o time: int
*/

public class PortTime {
  private int time;

  public PortTime(int time){
    this.time = time;
  }

  public void setTime(int time){
    this.time = time;
  }

  public int getTime(){
    return this.time;
  }

  @Override
  public String toString(){
    String str = "Time: " + this.time;
    return str;
  }
}//end class
