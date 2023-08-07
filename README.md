# FBLA-Coding-and-Programming
This program won the 1st place award at Iowa FBLA State Conference. Program to increase student participation in school events. Tracks each time an individual student comes to an event and awards the best attendance.
Hello and welcome to our program!

Overview:
  Essentially, our program is a functioning Event Attendance Tracker that the admins of a school can utilize to track students' attendance at school events
  Our program does this starting by having the admin user login into the program

Preset Admin/Student Logins:
  Currently, there is one admin login so that it is easy for people to test or startup. However, to create your own admin login, you can simply go to the "adminNames.txt" file and put in your desired username. Secondly, you must create a new .txt file that is titled with the username you just typed into the "adminNames.txt" file. The first line of this new file must say "true," the second line must have the username, and the third line must have the password (see "Admin.txt" file for an example).
  With that being said, the default login for an admin user is currently
  |
  |
  username: Admin
  password: admn5111111
  |
  |
  Along with the preset Admin login, we have 16 preset student logins (to show how the program would run as the program will error out if there aren't at least 4 students in each grade level), their usernames are their last and first names, and their password is their six-digit number given to them when they become a high schooler. In order to add more students, you must go to the "studentNames.txt" file and add the username of the student, which will be their last name and their first name conjoined in lowercase. Next, you must create a new .txt file titled with the username you just entered in the "studentNames.txt" file. The first line of this new file should be "false," the second line should be the student's username you just entered in the "studentNames.txt" file, the third line should be the student's school-designated six-digit code, the fourth line should be the student's grade (9-12), and the fifth line should be set to 0 at initial creation as it represents the student's current attendance at school events. Two of tje preset logins are as listed below.
  |
  |
  Student 1:
  username: tatejackie
  password: 100001
  |
  Student 2: 
  username: utreddanny
  password: 100002

Once the admin has logged in, they have the option to start a school event of their choice. The options for these events are listed below
  - PV Football Game 1 (interchangeable with respective school)
  - MAC XC Meet (interchangeable with respective conference)
  - Sound of Music (interchangeable with respective play)
  - School Movie Night
  - Golf Meet 1
  - Field Hockey Game 1
  - Winter Band Concert
  - PV Football Game 2 (interchangeable with respective school)
  - Autumn Choir Performance
  - Poetry Slam

After the admin selects one of these events, the program will begin asking for student logins. This is because before a student can enter the event,
they must login with the tracker so that they can be rewarded for their attendance. At the end of each quarter, the students with the 1st, 2nd, and 3rd highest
attendance scores in each grade will receieve a reward. They are listed below. These winners can be found when the admin hits the _end quarter_ button which will display the top 4 attendances in each grade and then reset everyone's attendance to 0 for the next quarter.
|
|
1st Place
 - Full School Mascot Uniform 
 - School Hat
2nd Place
  - School Backpack
   - Notebooks
   - Pencils
3rd Place
  - School Shirt
  - $10 Gift Card to desired store/restaurant
|
|

We hope that our program is put to use in your school. Thank you!

