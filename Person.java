/* File: Person.java
* Author: Emily Martens
* Date: 5/21/19
* Purpose:
*/

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Person extends Thing {
  private String skill = "";
  private boolean isBusy = false;
  private JLabel pLabel;

  public Person(Scanner sc){
    super();
    setName(sc.next());
    setIndex(sc.nextInt());
    setParent(sc.nextInt());
    this.skill = sc.next();

    this.pLabel = new JLabel(this.getName() + ": " + this.skill);
    pLabel.setForeground(Color.blue);
    pLabel.setHorizontalAlignment(JLabel.CENTER);
  }

  public JLabel getLabel(){
    return this.pLabel;
  }

  public void setSkill(String skill){
    this.skill = skill;
  }

  public String getSkill(){
    return this.skill;
  }

  public void toggleWorking(){
    if(isBusy == false){
      isBusy = true;
      this.pLabel.setForeground(Color.red);
    } else {
      isBusy = false;
      this.pLabel.setForeground(Color.blue);
    }
  }

  public Boolean getStatus(){
    return this.isBusy;
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
