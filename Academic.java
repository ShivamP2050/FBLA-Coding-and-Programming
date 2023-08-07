//importing of necessary packages
import java.util.*;
import java.io.*;

//"Academic" class header
public class Academic
{
   private boolean isAdmin;
   private String name;
   private String password;
  
   private int num;
   private int grade;
   private int attendance;
   File tempFile;

   //"Academic" object constructor that takes in a file as a parameter
   public Academic(File svFile) throws Exception
   {
      Scanner svScanner = new Scanner(svFile);
      
      isAdmin = svScanner.nextBoolean();

     //logic statements to see if the object is an admin user or not
     if(isAdmin)
     {
        svScanner.nextLine();
        name = svScanner.nextLine();
        password = svScanner.nextLine();
     }
     else
     {
        svScanner.nextLine();
        name = svScanner.nextLine();
        num = svScanner.nextInt();
        grade = svScanner.nextInt();
        attendance = svScanner.nextInt();
     }
      svScanner.close();

   }

  //method to update the user's file information
   public void updateUser(File student, int attendancePoints) throws Exception
   {
      if(attendancePoints == 0)
      {
        attendance = 0;
      }
      else
      {
        attendance++;
      }
      System.out.print(attendance);
      tempFile = student;
      PrintWriter fw = new PrintWriter(student);
      //writing all necessary things to the user's file
      fw.println(isAdmin);
      fw.println(name);
      fw.println(num);
      fw.println(grade);
      fw.println(attendance);
      fw.close();
      
   }
  
  //getter method for the "password" instance variable
  public String getPassword() {
    return password;
  }

  //getter method for the "grade" instance variable
  public int getGrade() {
    return grade;
  }

  //getter method for the "num" instance variable
  public int getNum() {
    return num;
  }

  //getter method for the "name" instance variable
  public String getName() {
    return name;
  }

  //getter method for the "attendance" instance variable
  public int getAttendance() {
    return attendance;
  }
   
}

