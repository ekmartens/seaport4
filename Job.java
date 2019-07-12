/* File: Job.java
* Author: Emily Martens
* Date: 5/22/19
* Purpose:

Job extends Thing - optional till Projects 3 and 4
o duration: double
o requirements: ArrayList <String> // should be some of the skills of the persons
*/

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Job extends Thing implements Runnable {
  private double duration;
  private ArrayList<String> requirements = new ArrayList<String>();
  private JPanel jobP = new JPanel();
  private JProgressBar progress;
  private JButton pauseBtn = new JButton("Pause Job");
  private JButton killBtn = new JButton("Cancel Job");
  private Boolean isGo = false;
  private Boolean isDone = false;
  private Boolean isKill = false;
  private HashMap<Integer, JLabel> reqLabels = new HashMap<Integer, JLabel>();
  private ArrayList<Person> persons;

  public Job(Scanner sc){
    super();
    setName(sc.next());
    setIndex(sc.nextInt());
    setParent(sc.nextInt());//parent should be Ship
    this.duration = sc.nextDouble();
    while (sc.hasNext() == true){
      this.addReq(sc.next());
    }

    jobP.setLayout(new BorderLayout());
    jobP.setBorder(BorderFactory.createTitledBorder(this.getName()));
    progress = new JProgressBar();
    progress.setValue(0);
    progress.setStringPainted(true);
    JPanel buttons = new JPanel();
    buttons.add(pauseBtn);
    buttons.add(killBtn);
    JPanel req = new JPanel();
    req.setLayout(new BoxLayout(req, BoxLayout.PAGE_AXIS));

    for (int i = 0; i < this.requirements.size(); i++){
      JLabel jl = new JLabel(this.requirements.get(i));
      jl.setForeground(Color.red);
      reqLabels.put(i, jl);
      req.add(jl);
    }

    jobP.add(progress, BorderLayout.NORTH);
    jobP.add(buttons, BorderLayout.CENTER);
    jobP.add(req, BorderLayout.SOUTH);

    pauseBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          toggleGo();
        }
      });

      killBtn.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            isKill = true;
          }
        });


    Thread jobThread = new Thread(this);
    jobThread.start();
  }//end constructor

  public double getDuration(){
    return this.duration;
  }

  public void addReq(String r){
    this.requirements.add(r);
  }

  public ArrayList<String> getRequirements(){
    return this.requirements;
  }

  public Boolean done(){
    return this.isDone;
  }

  public JPanel getJobPanel(){
    return this.jobP;
  }

  public void toggleGo(){
    isGo = !isGo;
  }

  public HashMap<String, Integer> calculateResources(){
    HashMap<String, Integer> jobRes = new HashMap<String, Integer>();
    for (String s : this.requirements){
      if (!jobRes.containsKey(s)){
        jobRes.put(s, 1);
      } else {
        jobRes.put(s, jobRes.get(s) + 1);
      }
    }
    return jobRes;
  }

  public void setResources(ArrayList<Person> persons){
    this.persons = persons;
  }//end getResources

  public void cancel(){
    isKill = true;
  }

  public synchronized void gatherResources(){
    if (isKill == false){
      int resCount = 0;
      System.out.println("Req: " + this.requirements.size());
      while(resCount < this.requirements.size()){
      for (int i = 0; i < this.requirements.size(); i++){
        for (Person p : persons){
          if (Objects.equals(this.requirements.get(i), p.getSkill())){
            if (p.getStatus() == false){
              System.out.println(this.getName() + " " + this.requirements.get(i) + " in use");
              p.toggleWorking();
              reqLabels.get(i).setForeground(Color.blue);
              System.out.println("Setting label " + i + " to blue");
              resCount += 1;
              break;
            }
          }
        }
      }//end for
      }
      toggleGo();
    }//end if isKill
    else {
      toggleGo();
    }
  }

  public synchronized void releaseResources(){

    int resCount = this.requirements.size();
    while(resCount > 0){
      for (int i = 0; i < this.requirements.size(); i++){
        for (Person p : persons){
          if (Objects.equals(this.requirements.get(i), p.getSkill())){
            if (p.getStatus() == true){
              System.out.println(this.getName() + " " + this.requirements.get(i) + " finished");
              p.toggleWorking();
              reqLabels.get(i).setForeground(Color.red);
              System.out.println("Setting label " + i + " to red");
              resCount -= 1;
              break;
            }
          }
        }
      }//end for
    }
  }

  public void run(){

        int sleepInterval = (int)this.getDuration()/100;

        for (int i = 0; i <= 100; i++) {
          if (isKill == false){
            if(isGo == true){
              final int currentValue = i;
              try {
                  SwingUtilities.invokeLater(new Runnable() {
                      public void run() {
                          progress.setValue(currentValue);
                          progress.setForeground(Color.blue);
                      }
                  });
                  Thread.sleep(sleepInterval);
              } catch (InterruptedException e) {
              }
            } else {
              try {
                Thread.sleep(sleepInterval);
                progress.setForeground(Color.yellow);
                i--;
              } catch (InterruptedException ie){
              }
            }
          } else {
            //change bg color to red
            progress.setForeground(Color.red);
            this.isDone = true;
            releaseResources();
          }
        }
        if (progress.getValue() == 100){
          progress.setForeground(Color.green);
        }
        this.isDone = true;
        releaseResources();
  }
}//end class
