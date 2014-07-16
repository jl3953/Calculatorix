package calculatorix;

import java.util.Random;
import java.util.Scanner;
import java.lang.InterruptedException;

/**
 * Generates a math problem and matches the user's answer
 *
 * @author Jennifer Lam
 */
public class Calculator implements Runnable{
    
    public static final int UPPER_LIMIT = 10; //how large the multiplicands are
    private Random rand;
    private int playerResponse;
    private int answer; //correct answer to the problem
    private Scanner sc;

    public Calculator(Scanner sc){
        this.rand = new Random();
        this.playerResponse = 0;
        this.answer = 0;
        this.sc = sc;
    }

    /**
     * Generates a problem to be printed out and updates this.answer.
     */
    public String problem(){
        int num1 = rand.nextInt(Calculator.UPPER_LIMIT);
        int num2 = rand.nextInt(Calculator.UPPER_LIMIT);

        this.answer = num1 * num2;

        return num1 + " x " + num2;
    }

    /**
     * Accepts a player's answer.
     *
     * @param sc
     *          One universal scaner for all input
     */
    public void acceptAnswer(Scanner sc) throws InterruptedException{
        this.playerResponse = sc.nextInt();
        sc.nextLine();
        if (Thread.interrupted())
            throw new InterruptedException(); 
    }

    /**
     * Returns the correct answer.
     */
    public int getAnswer(){
        return this.answer;
    }

    /**
     * Returns true if the user's answer is correct.
     */
    public boolean answerMatches(){
        return (this.playerResponse == this.answer) ? true : false;
    }

    /**
     * Returns the answer the player gave, right or wrong.
     */
    public int getPlayerResponse(){
        return this.playerResponse;
    }

    public void run(){
        try{
            this.acceptAnswer(this.sc);
        } catch (InterruptedException e) {
            return;
        }
    }
}
