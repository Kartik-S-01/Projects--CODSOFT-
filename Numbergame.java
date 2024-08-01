import java.util.*;

public class Numbergame 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();
        int score = 0;
        boolean game = true;

        while (game) 
        {
            int numbertoguess = ran.nextInt(100) + 2;
            int numberoftries = 0;
            int guess = 0;
            boolean Won = false;
            int maxattempts = 5; // Maximum number of attempts are allowed

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("Guess a number between 1 and 100:");
            
            while (numberoftries < maxattempts && !Won) 
            {
                guess = sc.nextInt();
                numberoftries++;
                
                if (guess < numbertoguess) 
                {
                    System.out.println("Too low, Please try again:");
                } 
                else if (guess > numbertoguess) 
                {
                    System.out.println("Too high, Please try again:");
                } 
                else 
                {
                    Won = true;
                }
            }

            if (Won) 
            {
                System.out.println("Congratulations, You guessed the number in " + numberoftries + " tries.");
                score += (maxattempts - numberoftries) + 1; 
            } 
            else 
            {
                System.out.println("Sorry, you have used all your attempts. The number was: " + numbertoguess);
            }

            System.out.println("Your current score is: " + score);
            System.out.println("Do you want to play again ? (yes/no)");
            String response = sc.next();
            game = response.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing, Your final score is: " + score);
        sc.close();
    }
}
