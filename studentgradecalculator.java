import java.util.*;

public class studentgradecalculator 
{
    public static void main(String[]args) 
    {
        Scanner sc = new Scanner(System.in);
        String choice;

       do 
       {
           System.out.print("\nEnter the student's name: ");     //For student name entry
           String stdname = sc.nextLine();                                                        

           System.out.print("Enter the number of subjects: ");  //For marks entry
           int numbersub = sc.nextInt();
           sc.nextLine();                                        // For newline

           String[] sub = new String[numbersub];           //Array List for storing student details and marks
           int[] marks = new int[numbersub];
           float total = 0;

           for (int i = 0; i < numbersub; i++)                 //For input of subject with their marks
           {
              System.out.print("\nEnter name for subject " + (i + 1) + ": ");
              sub[i] = sc.nextLine();
            
              int mark;
               while (true) 
               {
                 System.out.print("\nEnter marks for " + sub[i] + "Out of 100 : ");
                 mark = sc.nextInt();
                   if (mark >= 0 && mark <= 100) 
                   {
                     break;
                   } 
                   else 
                   {
                     System.out.println("\nInvalid input,Please enter marks between 0 to 100.");
                   }
               }
                    marks[i] = mark;
                    total += mark;
                    sc.nextLine();                          // For newline
           }

                    // Calculation of Percentage, CGPA and GPA
                   float per = total / numbersub;
                   float cgpa = per / 10;               //CGPA : Cumulative Grade Point Average
                   float gpa =  per / 20;              //GPA : Grade Point Average 

                   //Grades On basis of Percentage
                   String grade;
                   if (per >= 90) 
                   {
                     grade = "O";             //"O" is used for Outstanding
                   } 
                   else if (per >= 80) 
                   {
                     grade = "A+";
                   } 
                   else if (per >= 70) 
                   {
                     grade = "A";
                   } 
                   else if (per >= 60) 
                   {
                     grade = "B+";
                   } 
                   else if (per >= 50) 
                   {
                     grade = "B";
                   } 
                   else if (per >= 40) 
                   {
                     grade = "C";
                   }
                   else 
                   {
                     grade = "D";           //"D" is used for Disgusting
                   }

                    // Output results
                    System.out.println("\nStudent's Report Card");
                    System.out.println("---------------------------");
                    System.out.println("Student Name: " +stdname);
                    System.out.println("Subjects: " + numbersub);
                    System.out.println("Marks Obtained by student in Each Subject:");

                    for (int i = 0; i < numbersub; i++) 
                    {
                     System.out.println(sub[i] + ": " + marks[i]);
                    }

                    System.out.println("\nTotal Marks: " + total + " out of " + (numbersub * 100));
                    System.out.println("Percentage % : " + per );
                    System.out.println("CGPA: " +cgpa);
                    System.out.println("GPA: " +gpa);
                    System.out.println("Grade: " + grade);

                    //Remarks according Student's Result Performance
       
                    if (per < 33) 
                    {
                      System.out.println("Status: Fail");
                      System.out.println("Remarks: Hard luck this time");
                    } 
                    else if(per >= 90)
                    {
                      System.out.println("Status: Pass with Outstanding Performance!");
                      System.out.println("Remarks: We wish you a bright future");
                    }
                    else if(per >= 80)
                    {
                     System.out.println("Status: Pass with Brilliant Performance!");
                     System.out.println("Remarks: We wish you a bright future"); 
                    }
                    else if(per >= 70)
                    {
                     System.out.println("Status: Pass with Honorable Performance!"); 
                     System.out.println("Remarks: We wish you a bright future");
                    }
                    else if(per >= 60)
                    {
                     System.out.println("Status: Pass with Good Performance!"); 
                     System.out.println("Remarks: We wish you a bright future");
                    }
                    else if(per >= 50)
                    {
                     System.out.println("Status: Pass with Average Performance!");
                     System.out.println("Remarks: We wish you a bright future"); 
                    }
                    else if(per >= 40)
                    {
                     System.out.println("Status: Pass with Below Average Performance!");
                     System.out.println("Remarks: We wish you a bright future"); 
                    }

                    System.out.println("Do you want to add more student's details ? (yes/no)");
                    choice = sc.nextLine();
       
        }while(choice.equalsIgnoreCase("yes"));

         System.out.println("Thanks For Using :)");     
         sc.close();
    }
}
