import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quizgame
{
    private static int score = 0;
    private static int qindex = 0;
    private static int timeleft = 10;          // Time in seconds for each question
    private static int totalAttempts = 0;
    private static int correctAnswers = 0;
    private static int wrongAnswers = 0;
    private static int skippedQuestions = 0;

    private static Timer timer;

    private static JFrame frame;
    private static JLabel qlabel;
    private static JLabel tlabel;
    private static JButton[] ansbuttons;

    private static String[][] ques = 
    {
        {"1. What is the capital of INDIA?", "Kolkata", "Delhi", "Mumbai", "New Delhi", "New Delhi"},
        {"2. Which animal is known as the 'Ship of Desert'?", "Horse", "Camel", "Donkey", "Zebra", "Camel"},
        {"3. How many days are in a week?", "7", "8", "6", "10", "7"},
        {"4. Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Saturn", "Mars"},
        {"5. What is the time duration of India's National Anthem?", "54sec", "52sec", "60sec", "42sec", "52sec"},
        {"6. Who is the author of India's National Anthem?", "Rabindranath Tagore", "Bankim Chandra Chatterjee", "Lata Mangeshkar", "S.P.Balasubrahmanyam", "Rabindranath Tagore"},
        {"7. Who established Maratha Samrajya?", "Chhatrapati Shivaji Maharaj", "Tanhaji", "Shahaji", "Rajaram", "Chhatrapati Shivaji Maharaj"},
        {"8. Name the national animal of India?", "Lion", "Peacock", "Tiger", "Leopard", "Tiger"},
        {"9. How many continents in the world?", "12", "5", "10", "7", "7"},
        {"10. Festival of colours?", "Lohri", "Holi", "Shivratri", "Diwali", "Holi"},
    };

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(Quizgame::abc);
    }

    private static void abc()                   // FOR CREATE AND SHOWING GUI
    { 
        frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Set KBC-style background color
        frame.getContentPane().setBackground(Color.BLUE);

        qlabel = new JLabel("Question", JLabel.CENTER);
        qlabel.setFont(new Font("Arial", Font.BOLD, 16));
        qlabel.setForeground(Color.YELLOW);            // Set question text color to yellow
        frame.add(qlabel, BorderLayout.NORTH);

        tlabel = new JLabel("Time left: " + timeleft + " seconds", JLabel.CENTER);
        tlabel.setForeground(Color.CYAN);              // Set timer text color to cyan
        frame.add(tlabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));
        buttonPanel.setBackground(Color.BLACK);       // Set the panel background color

        ansbuttons = new JButton[4];
        for (int i = 0; i < 4; i++) 
        {
            ansbuttons[i] = new JButton();
            ansbuttons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            ansbuttons[i].setBackground(Color.BLACK);     // Button background color
            ansbuttons[i].setForeground(Color.WHITE);     // Button text color
            ansbuttons[i].addActionListener(new AnswerButtonListener());
            buttonPanel.add(ansbuttons[i]);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        pqr();
        xyz();

        frame.setVisible(true);
    }

    private static void pqr()                // FOR QUESTIONS LOADING
    { 
        if (qindex < ques.length) 
        {
            qlabel.setText(ques[qindex][0]);
            for (int i = 0; i < 4; i++) 
            {
                ansbuttons[i].setText(ques[qindex][i + 1]);
                ansbuttons[i].setBackground(Color.BLACK); // Reset button color
                ansbuttons[i].setEnabled(true);            // Enable buttons
            }

            timeleft = 10; // Reset time for the new question
            tlabel.setText("Time left: " + timeleft + " seconds");
        } 
        
        else 
        {
            Score();
        }
    }

    private static void xyz()               // FOR STARTING TIMER
    { 
        if (timer != null) 
        {
            timer.stop();
        }

        timer = new Timer(1000, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                timeleft--;
                tlabel.setText("Time left: " + timeleft + " seconds");
        
                if (timeleft <= 0) 
                {
                    skippedQuestions++;
                    qindex++;
                    pqr();
                }
            }
        });
        timer.start();
    }

    private static void Score()             // FOR SHOWING FINAL SCORE AND STATISTICS
    { 
        timer.stop();                      // Stop the timer when quiz ends

        String message = "Quiz completed! \nYour score is: " + score + "\n\n"
                         + "Total Attempts: " + totalAttempts + "\n"
                         + "Correct Answers: " + correctAnswers + "\n"
                         + "Wrong Answers: " + wrongAnswers + "\n"
                         + "Skipped Questions: " + skippedQuestions + "\n\n"
                         + "Do you want to play again?";

        int option = JOptionPane.showConfirmDialog(frame, message, "Quiz Completed", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) 
        {
            // Reset quiz
            score = 0;
            qindex = 0;
            totalAttempts = 0;
            correctAnswers = 0;
            wrongAnswers = 0;
            skippedQuestions = 0;
            pqr();
            xyz();
        } 
        
        else 
        {
            frame.dispose();
        }
    }

    private static void correctanswer()        // FOR DISPLAYING CORRECT ANSWER IF USER SELECTS A WRONG ANSWER
    {   
        String correctAnswer = ques[qindex][5];
        
        for (JButton button : ansbuttons) 
        {
            if (button.getText().equals(correctAnswer)) 
            {
                button.setBackground(Color.GREEN); // Mark correct answer in green
            }
            button.setEnabled(false);          // Disable buttons after showing the correct answer
        }
    }

    private static class AnswerButtonListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            JButton clickedButton = (JButton) e.getSource();
            String selectedAnswer = clickedButton.getText();
            String correctAnswer = ques[qindex][5];

            totalAttempts++; // Increment the total attempts counter

            if (selectedAnswer.equals(correctAnswer)) 
            {
                score++;
                correctAnswers++; // Increment the correct answers counter
                clickedButton.setBackground(Color.GREEN); // Mark correct answer in green
            } 
            
            else 
            {
                wrongAnswers++; // Increment the wrong answers counter
                clickedButton.setBackground(Color.RED);   // Mark wrong answer in red
                correctanswer();
            }

            timer.stop();
            Timer delay = new Timer(1500, new ActionListener()              // Delay before moving to the next question 
            { 
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    qindex++;
                    pqr();
                    xyz();
                }
            });

            delay.setRepeats(false);
            delay.start();
        }
    }
}
