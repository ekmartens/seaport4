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

  public synchronized void getResources(){

  }

  public void cancel(){
    isKill = true;
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
          }
        }
        if (progress.getValue() == 100){
          progress.setForeground(Color.green);
        }
        this.isDone = true;
  }
}//end class
