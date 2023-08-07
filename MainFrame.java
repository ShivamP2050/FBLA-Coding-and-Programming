//importing of the necessary packages

import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

//"MainFrame" class header 
//this class extends JFrame so that we can utilize all JSwing things necessary in this class
public class MainFrame extends JFrame
{ 
    //creating all necessary JSwing objects for the user interface
    private JPanel inputPanel;
    private JTextField inputTextField;
    private JLabel inputMessageLabel;
    private JButton enterButton;
    private JButton eventEndButton;

    private JPanel adminLoginPanel;
    private JButton adminLoginButton;
    private JLabel loginPromptLabel;
    private JTextField usernameInputField;
    private JTextField passwordInputField;

    private JPanel adminChoicePanel;
    private JLabel adminActionPromptLabel;
    private JButton attendanceStatsButton;
    private JButton startEventButton;
    private JButton newQuarterButton;

    private JPanel attendanceDataPanel;
    private JTextArea attendanceData9;
    private JTextArea attendanceData10;
    private JTextArea attendanceData11;
    private JTextArea attendanceData12;
    private JButton attendanceDataBackButton;

    private JPanel eventIntiationPanel;
    private JLabel adminEventPrompt;
    private String[] eventList;
    private JComboBox eventsDropDownBox;
    private JButton selectEventButton;
    private JButton cancelEventIntitationButton;
    
    private JPanel adminVerificationPanel;
    private JButton adminVerificationButton;
    private JButton cancelVerificationButton;
    private JLabel verificationPromptLabel;
    private JTextField usernameVerificationField;
    private JTextField passwordVerificationField;

    private JPanel quarterResultsPanel;
    private JButton backFromQuarterResultsButton;
    private JTextArea quarterData9;
    private JTextArea quarterData10;
    private JTextArea quarterData11;
    private JTextArea quarterData12;
    private JLabel awards;

    //creating ArrayLists for students and admins
    ArrayList<String> studentNames = new ArrayList<String>();
    ArrayList<Academic> student = new ArrayList<Academic>();
    ArrayList<Integer> eventEntries = new ArrayList<Integer>();  

    ArrayList<String> adminNames = new ArrayList<String>();
    ArrayList<String> events = new ArrayList<String>();
    ArrayList<Academic> admins = new ArrayList<Academic>();
    String currentEvent = "";

  
   //"MainFrame" object constructor that takes in a String to determine the title of the object
   public MainFrame(String title) throws Exception
   {
      super(title);
     File UNText = new File("studentNames.txt");
    Scanner UNScanner = new Scanner(UNText);

    //while loop designed to read through the studentNames.txt file
    while(UNScanner.hasNextLine())
    {
       String username = UNScanner.nextLine();
       studentNames.add(username);
    }
    UNScanner.close();
    
    for(int i = 0; i < studentNames.size(); i++)
    {
       String fileName = studentNames.get(i) + ".txt";
       File newStudentFile = new File(fileName);
       Academic newStudent = new Academic(newStudentFile);
      
       student.add(newStudent);
    }

    //creating a file with all admin usernames
    File adminUsers = new File("adminNames.txt");
    Scanner adminUserScanner = new Scanner(adminUsers);

    //while loop designed to read through the entire adminNames.txt file
    while(adminUserScanner.hasNextLine())
      {
        String name = adminUserScanner.nextLine();
        adminNames.add(name);
        File adminFile = new File(name + ".txt");
        Academic newAdmin = new Academic(adminFile);
        admins.add(newAdmin);
      }
    adminUserScanner.close();
    //creating a file with all the schoolEvents available for the admin to start
     File schoolEventsFile = new File("schoolEvents.txt");
     Scanner eventFileScanner = new Scanner(schoolEventsFile);

     while(eventFileScanner.hasNextLine())
       {
         events.add(eventFileScanner.nextLine());
       }
     eventList = new String[events.size()];
     for(int i = 0; i < events.size(); i++)
       {
         eventList[i] = events.get(i);
       }
     eventFileScanner.close();
     
     this.setBackground(new Color(32, 42, 68));
     this.setSize(960, 540);
     this.setResizable(false);
     try
       {
        this.buildAdminLoginPanel();
       }
     catch(Exception e)
       {
         System.out.println(e);
       }
      this.add(adminLoginPanel);
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
   }

  

   private void buildInputPanel() throws Exception
   {
      // Create a label to display instructions.
        
      inputMessageLabel = new JLabel("<html>Welcome to the School event! Thank you for supporting your school!<br>Please enter your six digit student ID number:</html>");
      inputMessageLabel.setForeground(new Color(255, 255, 255));
     
      // Create a text field 10 characters wide.
     
      inputTextField = new JTextField(10);
     
      enterButton = new JButton("ENTER");
      eventEndButton = new JButton("End Event");
     
      // Add an action listener to the button.
      enterButton.addActionListener(new EnterButtonListener());
      eventEndButton.addActionListener(new EventEndButtonListener());
     
      // Create a JPanel object and let the panel
      // field reference it.
      inputPanel = new JPanel();
   
      inputPanel.add(inputMessageLabel);
      inputPanel.add(inputTextField);
      inputPanel.add(enterButton);
      inputPanel.add(eventEndButton);
      inputPanel.setBackground(new Color(32, 42, 68));
   }

  //private class because it is only used within the MainFrame class
  private class EventEndButtonListener implements ActionListener 
   {
     public void actionPerformed(ActionEvent e)
     {
       try
         {
          buildAdminVerificationPanel();
         }
       catch(Exception ex)
         {
          System.out.println(ex);
         }
       inputPanel.setVisible(false);
       add(adminVerificationPanel);
     }
   }


  //private class because it is only used inside of the "MainFrame" class
  private class EnterButtonListener implements ActionListener 
   {
     public void actionPerformed(ActionEvent e)
     {
       boolean verified = false;
       String input; // To hold the user's input
       int number; // The number of miles
      
       // Get the text entered by the user into the
       // text field.
       input = inputTextField.getText();
      
       number = Integer.parseInt(input);
        int idIndex = -1;
        for(int i = 0; i < student.size(); i++)
        {
           if((student.get(i).getNum() == number) && !(eventEntries.contains(number)))
           {
              String userFile = studentNames.get(i) + ".txt";
              File newFile = new File(userFile);
              idIndex = i;
             //try catch statement that tries to update the user's file and add a number to the eventEntries ArrayList
             //if it can't do this, it prints out the error
             try
               {
                student.get(i).updateUser(newFile, 1);
                eventEntries.add(number);
                verified = true;
               }
             catch(Exception ex)
               {
                System.out.println(ex);
               }
           }
         }


       //set of logical statements to see if the user has successfully signed in for the event.
       //if they try to sign in more than once, it won't let them. also, if they enter the wrong number, it will give them an error messag and tell them to try again
       if(verified)
       {
          JOptionPane.showMessageDialog(null, "You have successfully signed in for the event!");
          
       }
       else if(eventEntries.contains(number))
       {
         JOptionPane.showMessageDialog(null, "You have ALREADY signed in ONCE before!");
       }
       else
       {
           JOptionPane.showMessageDialog(null, "INCORRECT NUMBER!");
       }

     }
   }

  //method to build the panel that pops up when the program begins that asks the admin user to login
  private void buildAdminLoginPanel()
  {
    adminLoginPanel = new JPanel();
    adminLoginPanel.setLayout(null);
    
    loginPromptLabel = new JLabel("Admin, please login to initiate a school event");
    loginPromptLabel.setForeground(new Color(255, 255, 255));
    loginPromptLabel.setLocation(260, 0);
    loginPromptLabel.setSize(500, 25);
    loginPromptLabel.setFont(new Font("SansSerif", Font.ITALIC, 20));
    
    adminLoginButton = new JButton("LOGIN");
    adminLoginButton.setBounds(430, 125, 100, 40);
    adminLoginButton.setBackground(Color.lightGray);
    adminLoginButton.setBorder(BorderFactory.createBevelBorder(0));
    
    usernameInputField = new JTextField(10);
    usernameInputField.setLocation(405, 50);
    usernameInputField.setSize(150, 25);
    usernameInputField.setText("Username");
    
    passwordInputField = new JTextField(10);
    passwordInputField.setLocation(405, 75);
    passwordInputField.setSize(150, 25);
    passwordInputField.setText("Password");
    
    
    adminLoginButton.addActionListener(new AdminLoginButtonListener());

    adminLoginPanel.add(loginPromptLabel);
    adminLoginPanel.add(usernameInputField);
    adminLoginPanel.add(passwordInputField);
    adminLoginPanel.add(adminLoginButton);
    adminLoginPanel.setBackground(new Color(32, 42, 68));
    
  }
  //Another private class that is only ever used inside of the "MainFrame" class
  private class AdminLoginButtonListener implements ActionListener 
   {
     public void actionPerformed(ActionEvent e)
     {
       boolean verified = false;
       String username; 
       String password;
      
       // Get the text entered by the user into the
       // text field.
       username = usernameInputField.getText();
       password = passwordInputField.getText();
       
        for(int i = 0; i < adminNames.size(); i++)
        {
           if((admins.get(i).getName().equals(username)) && (admins.get(i).getPassword().equals(password)))
           {
              verified = true;
           }
         }
      
       if(verified)
       {
          JOptionPane.showMessageDialog(null, "You have succefully logged in!");
          try
           {
             buildAdminChoicePanel();
             adminLoginPanel.setVisible(false);
             add(adminChoicePanel);
           }
         catch(Exception ex)
           {
             System.out.println(ex);
           }
       }
       else
       {
           JOptionPane.showMessageDialog(null, "INCORRECT LOGIN!");
       }

     }
   }
  //private method that is only called in the class. this method builds the panel that popus up when an admin user goes to select an event
  private void buildAdminChoicePanel()
  {
    adminActionPromptLabel = new JLabel("What would you like to do?");
    adminActionPromptLabel.setForeground(new Color(255, 255, 255));
    attendanceStatsButton = new JButton("View Current Attendance Data");
    newQuarterButton = new JButton("End Quarter");
    startEventButton = new JButton("Start Event");
    adminChoicePanel = new JPanel();
    
    startEventButton.addActionListener(new StartEventButtonListener());
    attendanceStatsButton.addActionListener(new AttendanceStatsButtonListener());
    newQuarterButton.addActionListener(new NewQuarterButtonListener());

    adminChoicePanel.add(adminActionPromptLabel);
    adminChoicePanel.add(attendanceStatsButton);
    adminChoicePanel.add(newQuarterButton);
    adminChoicePanel.add(startEventButton);
    adminChoicePanel.setBackground(new Color(32, 42, 68));
  }

  private class NewQuarterButtonListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
        try{
            buildQuarterResultsPanel();
            for(int i = 0; i < student.size(); i++)
            {
              File tempFile = new File(studentNames.get(i) + ".txt");
              student.get(i).updateUser(tempFile, 0);
            }
         }
         catch(Exception ex)
         {
            System.out.println(ex);
         }
         adminChoicePanel.setVisible(false);
         add(quarterResultsPanel);
      }
    }

  private class AttendanceStatsButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      try{
          buildAttendanceDataPanel();
       }
       catch(Exception ex)
       {
          System.out.println(ex);
       }
       adminChoicePanel.setVisible(false);
       add(attendanceDataPanel);
    }
  }
  
   private class StartEventButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try{
            buildEventIntiationPanel();
         }
         catch(Exception ex)
         {
            System.out.println(ex);
         }
         adminChoicePanel.setVisible(false);
         add(eventIntiationPanel);
      }
   }


  //another private method used only in the MainFrame class
  //similar to the other build panel methods, this one builds the panel for when the admin user clicks on the "View Attendance Data" button
  private void buildAttendanceDataPanel()
  {
    String ninthGrade = gradeSortedAttendance(9, false);
    String tenthGrade = gradeSortedAttendance(10, false);
    String eleventhGrade = gradeSortedAttendance(11, false);
    String twelfthGrade = gradeSortedAttendance(12, false);
    attendanceData9 = new JTextArea(ninthGrade);
    attendanceData10 = new JTextArea(tenthGrade);
    attendanceData12 = new JTextArea(twelfthGrade);
    attendanceData11 = new JTextArea(eleventhGrade);
    attendanceData9.setEditable(false);
    attendanceData10.setEditable(false);
    attendanceData11.setEditable(false);
    attendanceData12.setEditable(false);
    attendanceDataBackButton = new JButton("Back");
    attendanceDataPanel = new JPanel();
    
    attendanceDataBackButton.addActionListener(new AttendanceDataBackButtonListener());

    attendanceDataPanel.add(attendanceData9);
    attendanceDataPanel.add(attendanceData10);
    attendanceDataPanel.add(attendanceData11);
    attendanceDataPanel.add(attendanceData12);
    attendanceDataPanel.add(attendanceDataBackButton);
    attendanceDataPanel.setBackground(new Color(32, 42, 68));
  }

  private class AttendanceDataBackButtonListener implements ActionListener 
   {
     public void actionPerformed(ActionEvent e)
     {
       attendanceDataPanel.setVisible(false);
       adminChoicePanel.setVisible(true);

     }
   }

  private void buildQuarterResultsPanel()
  {
    String ninthGrade = gradeSortedAttendance(9, true);
    String tenthGrade = gradeSortedAttendance(10, true);
    String eleventhGrade = gradeSortedAttendance(11, true);
    String twelfthGrade = gradeSortedAttendance(12, true);
    quarterData9 = new JTextArea(ninthGrade);
    quarterData10 = new JTextArea(tenthGrade);
    quarterData11 = new JTextArea(eleventhGrade);
    quarterData12 = new JTextArea(twelfthGrade);
    quarterData9.setEditable(false);
    quarterData10.setEditable(false);
    quarterData11.setEditable(false);
    quarterData12.setEditable(false);
    backFromQuarterResultsButton = new JButton("Back");
    awards = new JLabel("<html>Awards are as follows:<br>1st place - School Mascot Uniform and School Hat<br>2nd place - School Themed Backpack with supplies<br>3rd place - School shirt with a $10 Gift Card</html>");
    quarterResultsPanel = new JPanel();

    backFromQuarterResultsButton.addActionListener(new BackFromQuarterResultsButtonListener());

    quarterResultsPanel.add(quarterData9);
    quarterResultsPanel.add(quarterData10);
    quarterResultsPanel.add(quarterData11);
    quarterResultsPanel.add(quarterData12);
    quarterResultsPanel.add(backFromQuarterResultsButton);
    quarterResultsPanel.add(awards);
    quarterResultsPanel.setBackground(new Color(32, 42, 68));
  }

  private class BackFromQuarterResultsButtonListener implements ActionListener 
   {
     public void actionPerformed(ActionEvent e)
     {
       quarterResultsPanel.setVisible(false);
       adminChoicePanel.setVisible(true);
     }
   }

  //method that takes in a grade and sorts a group of students into their respective grade. it also modifies the ArrayList so that the student with the highest attendance score will not have to sit in the back 
  public String gradeSortedAttendance(int grade, boolean isQuarterEnd) {

    ArrayList<Academic> gradeSeperatedStudents = new ArrayList<Academic>();
    ArrayList<Academic> sortedGrade = new ArrayList<Academic>();
    String orderedString = "";
    String desiredGrade = "Grade: " + grade + " \n";
    
    for (int i = 0; i < student.size(); i++) {
      if (student.get(i).getGrade() == grade) {
        gradeSeperatedStudents.add(student.get(i));
      }
    }
    if(gradeSeperatedStudents.size() == 0)
    {
      return desiredGrade + "N/A ";
    }

   
    sortedGrade.add(gradeSeperatedStudents.get(0));
    for (int j = 1; j < gradeSeperatedStudents.size(); j++) {
      Academic temp = gradeSeperatedStudents.get(j);
      for(int k = 0; k < sortedGrade.size(); k++)
        {
          if(sortedGrade.get(k).getAttendance() < temp.getAttendance()) {
            sortedGrade.add(k, temp);
            break;
          }
          if((k == sortedGrade.size() - 1) && !(sortedGrade.get(k).getAttendance() < temp.getAttendance()))
          {
            sortedGrade.add(temp);
            break;
          }
      }
    }
    orderedString = desiredGrade + displayFormattedString(sortedGrade, isQuarterEnd);
    return orderedString;
  }

  //method to return a formatted string
  public String displayFormattedString(ArrayList<Academic> sortedStudents, boolean quarterEnd)
  {
    ArrayList<Academic> sorted = sortedStudents;
    String formattedString = "";
    if(!quarterEnd)
    {
      for(int i = 0; i < sortedStudents.size(); i++)
        {
          formattedString += sortedStudents.get(i).getName() + "   Attendance: " + sortedStudents.get(i).getAttendance() + "\n";
        }
    }
    else
    {
      for(int i = 0; i < 3; i++)
        {
          formattedString += sorted.get(i).getName() + "   Attendance: " + sorted.get(i).getAttendance() + "\n";
        }
    }
    return formattedString;
  }

  private void buildEventIntiationPanel()
  {
    adminEventPrompt = new JLabel("Choose the event:");
    adminEventPrompt.setForeground(new Color(255, 255, 255));
    eventsDropDownBox = new JComboBox(eventList);
    selectEventButton = new JButton("Start Event");
    cancelEventIntitationButton = new JButton("Back");
    eventIntiationPanel = new JPanel();
    
    selectEventButton.addActionListener(new SelectEventButtonListener());
    cancelEventIntitationButton.addActionListener(new CancelEventIntitationButtonListener());

    eventIntiationPanel.add(adminEventPrompt);
    eventIntiationPanel.add(eventsDropDownBox);
    eventIntiationPanel.add(selectEventButton);
    eventIntiationPanel.add(cancelEventIntitationButton);
    eventIntiationPanel.setBackground(new Color(32, 42, 68));
  }

  private class SelectEventButtonListener implements ActionListener 
   {
     public void actionPerformed(ActionEvent e)
     {
       String selectedEvent; 
      
       // Get the text entered by the user into the
       // text field.
       selectedEvent = (String) eventsDropDownBox.getSelectedItem();
       
       currentEvent = selectedEvent;
       try
       {
        buildInputPanel();
       }
     catch(Exception ex)
       {
         System.out.println(ex);
       }
       eventIntiationPanel.setVisible(false);
       add(inputPanel);

     }
   }
   
   private class CancelEventIntitationButtonListener implements ActionListener 
   {
     public void actionPerformed(ActionEvent e)
     {
       eventIntiationPanel.setVisible(false);
       adminChoicePanel.setVisible(true);
     }
   }
       
    private void buildAdminVerificationPanel()
    {
       verificationPromptLabel = new JLabel("Admin, please verify your credentials in order to end the event");
       verificationPromptLabel.setForeground(new Color(255, 255, 255));
       adminVerificationButton = new JButton("Verify");
       usernameVerificationField = new JTextField(10);
       usernameVerificationField.setText("Username");
       passwordVerificationField = new JTextField(10);
       passwordVerificationField.setText("Password");
       cancelVerificationButton = new JButton("Back");
       adminVerificationPanel = new JPanel();
       
       adminVerificationButton.addActionListener(new AdminVerificationButtonListener());
       cancelVerificationButton.addActionListener(new CancelVerificationButtonListener());
   
       adminVerificationPanel.add(verificationPromptLabel);
       adminVerificationPanel.add(usernameVerificationField);
       adminVerificationPanel.add(passwordVerificationField);
       adminVerificationPanel.add(adminVerificationButton);
       adminVerificationPanel.add(cancelVerificationButton);
       adminVerificationPanel.setBackground(new Color(32, 42, 68));
    }
    
    private class CancelVerificationButtonListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
         adminVerificationPanel.setVisible(false);
         inputPanel.setVisible(true);
      }
    }

    private class AdminVerificationButtonListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
          boolean verified = false;
          String username; 
          String password;
         
          // Get the text entered by the user into the
          // text field.
          username = usernameVerificationField.getText();
          password = passwordVerificationField.getText();
          
           for(int i = 0; i < adminNames.size(); i++)
           {
              if((admins.get(i).getName().equals(username)) && (admins.get(i).getPassword().equals(password)))
              {
                 verified = true;
              }
            }
         
          if(verified)
          {
             JOptionPane.showMessageDialog(null, "You have succefully ended the event!");
             try
              {
                buildAdminChoicePanel();
                eventEntries = new ArrayList<Integer>();
                adminVerificationPanel.setVisible(false);
                add(adminChoicePanel);
                adminChoicePanel.setVisible(true);
              }
            catch(Exception ex)
              {
                System.out.println(ex);
              }
          }
          else
          {
              JOptionPane.showMessageDialog(null, "INCORRECT CREDENTIALS! Try Again!");
          }
      }
    }

   
}