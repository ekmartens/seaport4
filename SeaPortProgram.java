/*File: SeaPortProgram.java
* Author: Emily Martens
* Date: 5/21/19
* Purpose: Builds a GUI to explore the available SeaPorts fed into program from chosen text file
*/

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SeaPortProgram extends JFrame {

  private World world = null;
  private JButton chooseFile = new JButton("Choose File");
  private JLabel chooseFileLabel = new JLabel("No file chosen...");
  private JTextArea fullWorld = new JTextArea(10, 30);
  private JLabel findShipLabel = new JLabel("Enter Ship ID");
  private JTextField enterShipSearch = new JTextField(20);
  private JLabel shipLocationLabel = new JLabel("Ship Location");
  private JTextField shipLocation = new JTextField(25);
  private JButton searchLocation = new JButton("Search");
  private JComboBox<String> selectPort;
  private JLabel search1Label = new JLabel("Search for Ship's Dock");
  private JLabel search1Label1 = new JLabel("Enter Ship ID");
  private JLabel search1Label2 = new JLabel("Dock");
  private JLabel search1ByName = new JLabel("Enter Ship Name");
  private JTextField s1ShipId = new JTextField(20);
  private JTextField s1ShipName = new JTextField(20);
  private JTextArea s1Result = new JTextArea(5,20);
  private JButton s1ByName = new JButton("Search");
  private JButton s1 = new JButton("Search");
  private JLabel search2Label = new JLabel("Search for Ship's Details");
  private JLabel search2Label1 = new JLabel("Enter Ship ID");
  private JLabel search2Label2 = new JLabel("Ship Info");
  private JLabel search2ByName = new JLabel("Enter Ship Name");
  private JTextField s2ShipId = new JTextField(20);
  private JTextField s2ShipName = new JTextField(20);
  private JTextArea s2Result = new JTextArea(5,20);
  private JButton s2ShipNameButton = new JButton("Search");
  private JButton s2 = new JButton("Search");
  private JLabel search3Label = new JLabel("Search People");
  private JLabel search3Label1 = new JLabel("Enter Skill");
  private JLabel search3Label2 = new JLabel("People");
  private JLabel search3ByName = new JLabel("Enter Name");
  private JLabel search3ByIndex = new JLabel("Enter Index");
  private JTextField s3SkillSearch = new JTextField(20);
  private JTextField s3ByName = new JTextField(20);
  private JTextField s3ByIndex = new JTextField(20);
  private JTextArea s3Result = new JTextArea(5,20);
  private JButton s3ByNameButton = new JButton("Search");
  private JButton s3ByIndexButton = new JButton("Search");
  private JButton s3 = new JButton("Search");
  //Sorting elements
  //Sort All Ships - add to sortAllShipsPanel
  private JLabel sortAllLabel = new JLabel("Sort All Ships");
  private JRadioButton ascAll = new JRadioButton("Ascending");
  private JRadioButton descAll = new JRadioButton("Descending");
  private JButton sortAllBtn = new JButton("Sort Ships");
  private JTextArea sortAllResult = new JTextArea(10,40);
  //Sort By SeaPort - add to sortByNamePanel and sortQuePanel
  private JLabel sortNameL = new JLabel("Sort Personnel By Name");
  private JLabel sortQueL = new JLabel("Sort Que by Attributes");
  private JRadioButton ascPort = new JRadioButton("Ascending");
  private JRadioButton descPort = new JRadioButton("Descending");
  private JButton sortNameBtn = new JButton("Sort Persons");
  private JButton sortQueBtn = new JButton("Sort Que");
  private JTextArea sortNmRes = new JTextArea(10, 40);
  private JTextArea sortQRes = new JTextArea(10, 40);

  private JTree tree;

  public SeaPortProgram(String name){
    super(name);
    this.setSize(1200, 1000);
    setLayout(new BorderLayout());
    setLocationRelativeTo(null);
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

    //panels
    JPanel topPanel = new JPanel();
    topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JPanel displayPanel = new JPanel();
    displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JPanel bottomPanel = new JPanel();
    bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    bottomPanel.setLayout(new BorderLayout());
    //sub panels
    JPanel searches = new JPanel();
    searches.setLayout(new BorderLayout());
    searches.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JPanel search1 = new JPanel();
    search1.setLayout(new BoxLayout(search1, BoxLayout.PAGE_AXIS));
    search1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JPanel search2 = new JPanel();
    search2.setLayout(new BoxLayout(search2, BoxLayout.PAGE_AXIS));
    search2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JPanel search3 = new JPanel();
    search3.setLayout(new BoxLayout(search3, BoxLayout.PAGE_AXIS));
    search3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JPanel sortingPanel = new JPanel();
    sortingPanel.setLayout(new BorderLayout());
    sortingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JPanel sortAllShipsPanel = new JPanel();
    sortAllShipsPanel.setLayout(new BorderLayout());
    JPanel portSortPanel = new JPanel();
    portSortPanel.setLayout(new BorderLayout());
    JPanel sortByNamePanel = new JPanel();
    sortByNamePanel.setLayout(new BorderLayout());
    sortByNamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JPanel sortQuePanel = new JPanel();
    sortQuePanel.setLayout(new BorderLayout());
    sortQuePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    //build subpanels
    search1Label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search1.add(search1Label);
    search1Label1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search1.add(search1Label1);
    search1.add(s1ShipId);
    search1.add(s1);
    search1ByName.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search1.add(search1ByName);
    search1.add(s1ShipName);
    search1.add(s1ByName);
    search1.add(search1Label2);
    s1Result.setFont (new java.awt.Font ("Monospaced", 0, 12));
    s1Result.setBackground(Color.LIGHT_GRAY);
    search1.add(s1Result);
    search2Label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search2.add(search2Label);
    search1Label2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search2Label1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search2.add(search2Label1);
    search2.add(s2ShipId);
    search2.add(s2);
    search2ByName.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search2.add(search2ByName);
    search2.add(s2ShipName);
    search2.add(s2ShipNameButton);
    search2Label2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search2.add(search2Label2);
    s2Result.setFont (new java.awt.Font ("Monospaced", 0, 12));
    s2Result.setBackground(Color.LIGHT_GRAY);
    JScrollPane s2ResScroll = new JScrollPane(s2Result);
    search2.add(s2ResScroll);
    search3Label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search3.add(search3Label);
    search3Label1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search3.add(search3Label1);
    search3.add(s3SkillSearch);
    search3.add(s3);
    search3ByName.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search3.add(search3ByName);
    search3.add(s3ByName);
    search3.add(s3ByNameButton);
    search3ByIndex.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search3.add(search3ByIndex);
    search3.add(s3ByIndex);
    search3.add(s3ByIndexButton);
    search3Label2.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    search3.add(search3Label2);
    s3Result.setFont (new java.awt.Font ("Monospaced", 0, 12));
    s3Result.setBackground(Color.LIGHT_GRAY);
    JScrollPane s3ResScroll = new JScrollPane(s3Result);
    search3.add(s3ResScroll);
    selectPort = new JComboBox<String>(new String[] {"..."});
    selectPort.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //sort all ships by name
    sortAllShipsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    sortAllShipsPanel.add(sortAllLabel, BorderLayout.NORTH);
    ButtonGroup sortOrder1 = new ButtonGroup();
    sortOrder1.add(ascAll);
    sortOrder1.add(descAll);
    JPanel radios1 = new JPanel();
    radios1.add(ascAll);
    radios1.add(descAll);
    radios1.add(sortAllBtn);
    sortAllShipsPanel.add(radios1, BorderLayout.WEST);
    JScrollPane sortAllScroll = new JScrollPane(sortAllResult);
    sortAllShipsPanel.add(sortAllScroll, BorderLayout.SOUTH);
    //sortPortL sortNameL sortQueL ascPort descPort sortNameBtn sortQueBtn sortNmRes sortQRes

    ButtonGroup sortOrder2 = new ButtonGroup();
    sortOrder2.add(ascPort);
    sortOrder2.add(descPort);
    JPanel radios2 = new JPanel();
    radios2.add(ascPort);
    radios2.add(descPort);
    JPanel selectionsPanel = new JPanel();
    selectionsPanel.setLayout(new BorderLayout());
    selectionsPanel.setBorder(BorderFactory.createTitledBorder("Sort a Port"));
    selectionsPanel.add(selectPort, BorderLayout.NORTH);
    selectionsPanel.add(radios2, BorderLayout.WEST);
    sortNameL.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    sortByNamePanel.add(sortNameL, BorderLayout.NORTH);
    sortByNamePanel.add(sortNameBtn, BorderLayout.WEST);
    JScrollPane sortNmScroll = new JScrollPane(sortNmRes);
    sortByNamePanel.add(sortNmScroll, BorderLayout.SOUTH);
    sortQueL.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    sortQuePanel.add(sortQueL, BorderLayout.NORTH);
    sortQuePanel.add(sortQueBtn, BorderLayout.WEST);
    JScrollPane sortQScroll = new JScrollPane(sortQRes);
    sortQuePanel.add(sortQScroll, BorderLayout.SOUTH);
    JPanel psResultsPanel = new JPanel();
    psResultsPanel.add(sortByNamePanel);
    psResultsPanel.add(sortQuePanel);
    portSortPanel.add(selectionsPanel, BorderLayout.NORTH);
    portSortPanel.add(psResultsPanel, BorderLayout.WEST);

    //add sub-sub panels to subpanels
    searches.add(search1, BorderLayout.WEST);
    searches.add(search2, BorderLayout.CENTER);
    searches.add(search3, BorderLayout.EAST);
    sortingPanel.add(sortAllLabel, BorderLayout.NORTH);
    sortingPanel.add(sortAllShipsPanel, BorderLayout.CENTER);
    sortingPanel.add(portSortPanel, BorderLayout.SOUTH);

    //add components to panels
    topPanel.add(chooseFile);
    chooseFileLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    topPanel.add(chooseFileLabel);

    shipLocationLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    displayPanel.add(shipLocationLabel);
    displayPanel.add(findShipLabel);
    displayPanel.add(enterShipSearch);
    shipLocationLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    displayPanel.add(shipLocationLabel);
    shipLocation.setFont (new java.awt.Font ("Monospaced", 0, 12));
    shipLocation.setBackground(Color.LIGHT_GRAY);
    displayPanel.add(shipLocation);
    displayPanel.add(searchLocation);
    bottomPanel.setBorder(BorderFactory.createTitledBorder("Explore"));
    bottomPanel.add(searches, BorderLayout.NORTH);
    bottomPanel.add(sortingPanel, BorderLayout.CENTER);
    //Jobs panel
    JPanel left = new JPanel();
    left.setLayout(new BorderLayout());
    left.add(displayPanel, BorderLayout.NORTH);
    //Available Persons/Skills
    JPanel peoplePanel = new JPanel();
    peoplePanel.setLayout(new GridLayout(0, 7));
    peoplePanel.setBorder(BorderFactory.createTitledBorder("Persons"));
    left.add(peoplePanel, BorderLayout.EAST);
    //Run jobs button
    JPanel jbtn = new JPanel();
    JButton runJobs = new JButton("Run Jobs");
    jbtn.add(runJobs);
    left.add(jbtn, BorderLayout.SOUTH);
    JPanel jobsP = new JPanel();
    jobsP.setBorder(BorderFactory.createTitledBorder("Jobs"));
    jobsP.setLayout(new GridLayout(0, 10));

    //add panels to frame
    this.add(topPanel, BorderLayout.NORTH);
    this.add(left, BorderLayout.WEST);
    this.add(bottomPanel, BorderLayout.EAST);
    this.add(jobsP, BorderLayout.SOUTH);


    //button actions
    //get hashmaps ready for ActionListeners
    HashMap<Integer, SeaPort> worldPorts = new HashMap<Integer, SeaPort>();
    HashMap<Integer, Dock> worldDocks = new HashMap<Integer, Dock>();
    HashMap<Integer, Ship> worldShips = new HashMap<Integer, Ship>();
    HashMap<Integer, Person> worldPersons = new HashMap<Integer, Person>();
    HashMap<Integer, JLabel> personLabels = new HashMap<Integer, JLabel>();//tracks labels for Persons/Skills so color can be changed with status

    //action listener for file chooser
    chooseFile.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          JFrame frame = new JFrame("Select File");
          JFileChooser jfc = new JFileChooser(".");
          int returnVal = jfc.showOpenDialog(frame);
          if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            String f = file.getName();
            chooseFileLabel.setText(f);
            //set the scanner and use it to set up the world
            try {
              Scanner sc = new Scanner(file);
              setWorld("This World", sc);
              DefaultMutableTreeNode root = new DefaultMutableTreeNode(world.getName());
              for(SeaPort p : world.getPorts()){
                DefaultMutableTreeNode portNode = new DefaultMutableTreeNode(p.getName());
                for (Dock d : p.getDocks()){
                  DefaultMutableTreeNode dNode = new DefaultMutableTreeNode(d.getName());
                  if (d.getShip() != null){
                    dNode.add(new DefaultMutableTreeNode(d.getShip().getName()));
                  }
                  portNode.add(dNode);
                }
                for (Ship s : p.getQue()){
                  portNode.add(new DefaultMutableTreeNode("Queued: " + s.getName()));
                }
                root.add(portNode);
              }

              tree = new JTree(root);
              displayPanel.add(tree);

              //fullWorld.setText(world.toString());//add the file overview to the top text area
              //map ports and docks to make them searchable
              for (SeaPort port : world.getPorts()){
                worldPorts.put(port.getIndex(), port);
                for (Dock d : port.getDocks()){
                  worldDocks.put(d.getIndex(), d);
                }
                for (Ship s : port.getShips()){
                  worldShips.put(s.getIndex(), s);
                }
                for (Person p : port.getPersons()){
                  worldPersons.put(p.getIndex(), p);
                }
              }
              //set the drop down of available ports
              selectPort.removeAllItems();
              int numPorts = world.getPorts().size();
              for (int i = 0; i < numPorts; i++){
                String n = world.getPorts().get(i).getName();
                selectPort.insertItemAt(n, i);
              }
              selectPort.setSelectedIndex(0);

              //Add tasks

                for (Ship sh : worldShips.values()){
                  for (Job j : sh.getJobs()){
                    JPanel jp = j.getJobPanel();
                    jobsP.add(jp);
                  }
                }

                //add people
                for (Person p : worldPersons.values()){
                  JLabel l = new JLabel(p.getName() + ": " + p.getSkill());
                  l.setForeground(Color.blue);
                  l.setHorizontalAlignment(JLabel.CENTER);
                  personLabels.put(p.getIndex(), l);
                  peoplePanel.add(l);
                }

                //initialze skills


            } catch (FileNotFoundException fnfe){
              System.out.println("File Not Found");
            }
          } else {
            System.out.println("Action Cancelled by User");
            }
        }
      });//end action

      //ActionListener for run jobs
      runJobs.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          if (world != null){
            for (SeaPort sp : world.getPorts()){

              int size = sp.getDocks().size();
              if (size > 0){
                ExecutorService exec = Executors.newFixedThreadPool(size);
                System.out.println(size);
                //process already docked ships
                int max = sp.getQue().size();
                int counter = 0;
                Stack<Ship> st = new Stack<Ship>();
                for (Ship s : sp.getQue()){
                  st.push(s);
                }

                for (Dock d: sp.getDocks()){
                  if (d.getShip() != null){
                    //for each job on the ship, check to see if there are enough resources to complete the job
                    //if no, cancel job
                    for (Job j : d.getShip().getJobs()){
                      for (String s : j.getRequirements()){
                        int count = 0;
                        for (String str : j.getRequirements()){
                          if (Objects.equals(s, str)){
                            count += 1;
                          }
                        }
                        int res = 0;
                        for (Person p : sp.getPersons()){
                          if (Objects.equals(p.getSkill(), s)){
                            res += 1;
                          }
                        }
                        if (count > res){
                          j.cancel();
                        }
                      }
                    }
                    exec.execute(d.getShip());
                  }
                }

                for (Ship s : sp.getQue()){
                  for (Job j : s.getJobs()){
                    for (String string : j.getRequirements()){
                      int count = 0;
                      for (String str : j.getRequirements()){
                        if (Objects.equals(string, str)){
                          count += 1;
                        }
                      }
                      int res = 0;
                      for (Person p : sp.getPersons()){
                        if (Objects.equals(p.getSkill(), string)){
                          res += 1;
                        }
                      }
                      if (count > res){
                        j.cancel();
                      }
                    }
                  }
                  exec.execute(s);
                }
                exec.shutdown();
              }
            }
          }

        }
      });
      //end ActionListener

      //action listener for full world ship search
      searchLocation.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            if (world != null){
              try {
                int shipId = Integer.valueOf(enterShipSearch.getText());
                if (worldShips.containsKey(shipId)){
                  //lookup ship
                  Ship s = worldShips.get(shipId);
                  int parent = s.getParent();
                  //check to see if ship is in a que
                  if (worldPorts.containsKey(parent)){
                    String n = worldPorts.get(parent).getName();
                    shipLocation.setText(n);
                  } else {
                    //lookup ship's dock
                    if (worldDocks.containsKey(parent)){
                      int dp = worldDocks.get(parent).getParent();
                      String n = worldPorts.get(dp).getName();
                      shipLocation.setText(n);
                    } else {
                      shipLocation.setText("Ship not found");
                    }
                  }
                } else {
                  shipLocation.setText("Ship not found");
                }
              } catch (NumberFormatException nfe){
                shipLocation.setText("Please enter an integer value");
              }
            }
            else {
              shipLocation.setText("No World Found");
            }

          }
        });//end action

        //action listener for dock search
        s1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              //find ship index
              try{
                int findShip = Integer.valueOf(s1ShipId.getText());
                if (worldShips.containsKey(findShip)){
                  int par = worldShips.get(findShip).getParent();
                  String dockName = worldDocks.get(par).getName();
                  s1Result.setText(dockName);
                } else {
                  s1Result.setText("Ship not found");
                }
              } catch(NumberFormatException nfe){
                s1Result.setText("Please enter an integer value");
              }

            }
          });//end action

          //ActionListener for dock search by ship name
          s1ByName.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                int par = 0;
                String searchName = s1ShipName.getText();
                searchName = searchName.toLowerCase();
                for (Ship s : worldShips.values()){
                  if (Objects.equals(s.getName().toLowerCase(), searchName)){
                    par = s.getParent();
                  }
                }
                if (par > 0){
                  //find dock in docks HashMap
                  if (worldDocks.containsKey(par)){
                    s1Result.setText(worldDocks.get(par).getName());
                  } else {
                    //try to find matching seaport index to see if ship is in que
                    if(worldPorts.containsKey(par)){
                      s1Result.setText("Ship in que\nPort: " + worldPorts.get(par).getName());
                    } else {
                      s1Result.setText("Ship not found");
                    }
                  }

                } else {
                  s1Result.setText("Ship not found");
                }
              }
            });//end action

            //ActionListener for get ship details by index
            s2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  try {
                    int findShip = Integer.valueOf(s2ShipId.getText());
                    if (worldShips.containsKey(findShip)){
                      s2Result.setText(worldShips.get(findShip).toString());
                    } else {
                      s2Result.setText("Ship not found");
                    }
                  } catch (NumberFormatException nfe){
                    s2Result.setText("Please enter an integer value");
                  }
                }
              });//end action

              //ActionListener for search ship details by Name
              s2ShipNameButton.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                    String shipName = s2ShipName.getText();
                    shipName = shipName.toLowerCase();
                    Ship sDetail = null;
                    //search ships for name match
                    for (Ship s : worldShips.values()){
                      if (Objects.equals(s.getName().toLowerCase(), shipName)){
                        sDetail = s;
                      }
                    }
                    if (sDetail != null){
                      s2Result.setText(sDetail.toString());
                    } else {
                      s2Result.setText("Ship not found");
                    }
                  }
                });//end action

                //ActionListener for person search by skill
                s3.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                      String skill = s3SkillSearch.getText();
                      skill = skill.toLowerCase();
                      ArrayList<Person> people = new ArrayList<Person>();
                      //search people for skill
                      for (Person p : worldPersons.values()){
                        if (Objects.equals(p.getSkill().toLowerCase(), skill)){
                          people.add(p);
                        }
                      }
                      if (people.size() < 1){
                        s3Result.setText("No people with skill found");
                      } else {
                        String result = "";
                        for (Person p : people){
                          result += p.toString() + "\n\n";
                        }
                        s3Result.setText(result);
                      }
                    }
                  });//end action

                  //ActionListener for search people by name
                  s3ByNameButton.addActionListener(new ActionListener(){
                      public void actionPerformed(ActionEvent e){
                        String search = s3ByName.getText();
                        search = search.toLowerCase();
                        Person person = null;
                        for (Person p : worldPersons.values()){
                          if (Objects.equals(p.getName().toLowerCase(), search)){
                            person = p;
                          }
                        }

                        if (person != null){
                          s3Result.setText(person.toString());
                        } else {
                          s3Result.setText("Person not found");
                        }
                      }
                    });//end action

            //ActionListener for sort all ships by name
            sortAllBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  if (world != null){
                    if (ascAll.isSelected()){
                      String result = "";
                      //sort all world SeaPort ship lists
                      for (SeaPort sp : world.getPorts()){
                        result += sp.getName() + ":\n";
                        sp.sortByName(1);
                        for (Ship s : sp.getShips()){
                          result += s.getName() + "\n";
                        }
                        result += "\n";
                      }
                      sortAllResult.setText(result);
                    } else if (descAll.isSelected()){
                      String result = "";
                      //sort all world SeaPort ship lists
                      for (SeaPort sp : world.getPorts()){
                        result += sp.getName() + ":\n";
                        sp.sortByNameReverse(1);
                        for (Ship s : sp.getShips()){
                          result += s.getName() + "\n";
                        }
                        result += "\n";
                      }
                      sortAllResult.setText(result);
                    } else {
                      sortAllResult.setText("Please select a sort order");
                    }

                  } else {
                    sortAllResult.setText("No World set");
                  }
                }
            });//end action

            //ActionListener for sort persons by name
            sortNameBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  if (world != null){
                    String result = "";
                    //determine chosen SeaPort
                      SeaPort selection = null;
                      Object item = selectPort.getSelectedItem();
                      for (SeaPort sp : world.getPorts()){
                        if (sp.getName().equals(item)){
                          selection = sp;
                        }
                      }
                    if (ascPort.isSelected()){
                      //sort chosen SeaPort persons in ascending order, then add ordered names to result
                      selection.sortByName(4);
                      for (Person p : selection.getPersons()){
                        result += p.getName() + ": " + p.getSkill() + "\n";
                      }
                      sortNmRes.setText(result);
                    } else if (descPort.isSelected()){
                      //sort chosen SeaPort persons in descending order, then add ordered names to result
                      selection.sortByNameReverse(4);
                      for (Person p : selection.getPersons()){
                        result += p.getName() + ": " + p.getSkill() + "\n";
                      }
                    } else {
                      result += "Please select a sort order";
                    }
                    sortNmRes.setText(result);

                  } else {
                    sortNmRes.setText("No World set");
                  }
                }
            });//end action

            //ActionListener for que sort
            sortQueBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  if (world != null){
                    String result = "";
                    //determine chosen SeaPort
                      SeaPort selection = null;
                      Object item = selectPort.getSelectedItem();
                      for (SeaPort sp : world.getPorts()){
                        if (sp.getName().equals(item)){
                          selection = sp;
                        }
                      }
                    if (ascPort.isSelected()){
                      selection.sortQue(1);
                      for (Ship s : selection.getQue()){
                        result += s.getName() + ", " + s.getWeight() + ", " + s.getLength() + ", " + s.getWidth() + ", " + s.getDraft() + "\n";
                      }
                    } else if (descPort.isSelected()){
                      selection.sortQue(2);
                      for (Ship s : selection.getQue()){
                        result += s.getName() + ", " + s.getWeight() + ", " + s.getLength() + ", " + s.getWidth() + ", " + s.getDraft() + "\n";
                      }
                    } else {
                      result += "Please select a sort order";
                    }
                    sortQRes.setText(result);
                  } else {
                    sortQRes.setText("No World set");
                  }
                }
              });



    //pack and display the GUI
    pack();
    setVisible(true);
  }//end constructor

  //method to set the World
  public void setWorld(String name, Scanner s){
    this.world = new World(name, s);
  }


  public static void main(String[] args){
    SeaPortProgram build = new SeaPortProgram("Port Explorer");

/*
    Scanner si = new Scanner(System.in);
    System.out.println("Enter file name:");
    String f = si.nextLine();
    File file = new File(f);
    try {
      Scanner sc = new Scanner(file);
      World thisWorld = new World("This World", sc);
      System.out.println(thisWorld.toString());
    } catch (FileNotFoundException fnfe){
      System.out.println("File Not Found");
    }
    */

  }//end main
}//end class
