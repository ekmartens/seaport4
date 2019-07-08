/* File: Thing.java
* Author: Emily Martens
* Date: 5/21/19
* Purpose: Provides a base class for objects which comprise a physical entity
*/

import java.util.*;

public class Thing implements Comparable<Thing> {

  private int index;
  private String name;
  private int parent;

  public Thing(){
  }

  public void setName(String n){
    this.name = n;
  }

  public void setIndex(int i){
    this.index = i;
  }

  public void setParent(int p){
    this.parent = p;
  }

  public int getParent(){
    return this.parent;
  }

  public int getIndex(){
    return this.index;
  }

  public String getName(){
    return this.name;
  }

  public int compareTo(Thing compare){
    return 1;
  }

  @Override
  public String toString() {
    String str = this.name + "\nIndex: " + this.index + "\nParent: " + this.parent;
    return str;
  }
}// end class
