/* File: Person.java
* Author: Emily Martens
* Date: 5/21/19
* Purpose:
*/

import java.util.*;

public class Person extends Thing {
  private String skill = "";

  public Person(Scanner sc){
    super();
    setName(sc.next());
    setIndex(sc.nextInt());
    setParent(sc.nextInt());
    this.skill = sc.next();
  }

  public void setSkill(String skill){
    this.skill = skill;
  }

  public String getSkill(){
    return this.skill;
  }

  @Override
  public String toString() {
    String str = "Person: " + super.toString() + "\nSkill: " + this.skill;
    return str;
  }

/*
  public static void main(String[] args){
    Person test = new Person("John", 1, 2, "Bookkeeping");
    System.out.println(test.toString());
  }
**/

}// end class
