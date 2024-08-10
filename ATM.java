import java.util.*;

public class ATM 
{

    static Scanner sc = new Scanner(System.in);
    static double savbal = 2500.0;                              // Initial balance "savbal" is used for Saving Account Balance
    static double curbal = 5000.0;                              // Initial balance "curbal" is used for Current Account Balance
    static int pin = 5678;                                      // Default PIN 
    static String acctype = "Savings";                         // Default account type "acctype" is used for Account Type

    public static void main(String[]args) 
    {
        
        menu();                                   //for displaying all options of ATM
    }

    public static void menu() 
    {
        while (true) 
        {
            System.out.println("\nMenu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");

            System.out.println("\nPlease Enter your Choice : ");
            int choice = sc.nextInt();
           
            switch (choice) 
            {
                case 1:
                    user();
                    Acctype();      //Acctype method is called in all functions because of selection from savings / current account. 
                    withdraw();                                 
                    break;
                case 2:
                    user();
                    Acctype();
                    deposit();
                    break;
                case 3:
                    user();
                    Acctype();
                    checkbalance();
                    break;
                case 4:
                    user();
                    changepin();
                    break;
                case 5:
                    System.out.println("Thank you for using our ATM. Have a Nice Day!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void user()                       //PIN entry 
    {
        System.out.print("Enter your PIN: ");
        int inputpin = sc.nextInt();
        
        if (inputpin != pin) 
        {
            System.out.println("Incorrect PIN. Please try again.");
            user();
        }
    }

    public static void withdraw()                              //Withdrawal Function
    {
        System.out.print("Enter amount : ");
        double amount = sc.nextDouble();

        if(acctype.equals("Savings"))       
        { 
            if ( amount > 10000) 
            {
             System.out.println("Withdrawal limit exceeded for Savings account. Maximum allowed is 10000.");
            } 
            else if (amount > savbal) 
            {
             System.out.println("Insufficient balance.");
            } 
            else 
            {
             savbal -= amount;
             System.out.println("Withdrawal successfully. Remaining balance: " + savbal);
            }
        }
       
        else if(acctype.equals("Current"))
        {
            if (amount > curbal) 
            {
             System.out.println("Insufficient balance.");
            } 
            else 
            {
             curbal -= amount;
             System.out.println("Withdrawal successfully. Remaining balance: " + curbal);
            } 
        }
    }

    public static void deposit()                              //Deposit Function
    {
        System.out.print("Enter amount : ");
        double amount = sc.nextDouble();

        if (acctype.equals("Savings"))
        {
            if (amount > 15000) 
           {
              System.out.println("Deposit limit exceeded for Savings account. Maximum allowed is 15000.");
           } 
           else 
           {
             savbal += amount;
             System.out.println("Deposit successfully. New balance: " + savbal);
           }
        }
        
        else if(acctype.equals("Current"))
        {
            curbal += amount;
            System.out.println("Deposit successfully. New balance: " + curbal);
        }
    }

    public static void checkbalance()                         //Balance Check
    {
        if(acctype.equals("Savings"))
        {
         System.out.println("Your current balance in your savings account is: " + savbal);
        }
       
        else if(acctype.equals("Current"))
        {
            System.out.println("Your current balance in your current account is: " + curbal);  
        }   
    }

    public static void changepin()                            //PIN changing
    {
        System.out.print("Enter your new PIN: ");
        int newpin = sc.nextInt();
        pin = newpin;
        System.out.println("PIN changed successfully.");
    }

    public static void Acctype()                              //Select Account Type
    {
        System.out.println("Select Account Type:");
        System.out.println("1. Savings");
        System.out.println("2. Current");

        System.out.println("\nPlease enter your choice :");
        int choice = sc.nextInt();
        if (choice == 1) 
        {
            acctype = "Savings";
        } 
        else if (choice == 2) 
        {
            acctype = "Current";
        }
        System.out.println("You opted : " + acctype);
    }
}
