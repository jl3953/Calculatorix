package calculatorix;

import java.util.Random;
import java.util.Scanner;

public class Sphinx extends Monster {

    private Jackpot jackpot;
    private Random rand;
    private Calculator challenge;
    private Scanner sc;
    private Oracle game;
    private static final int SLEEP_TIME = 5;

    public Sphinx(Jackpot jackpot, Scanner sc, Oracle game) {
        this.jackpot = jackpot;
        this.rand = new Random();
        this.sc = sc;
        this.challenge = new Calculator(this.sc);
        this.game = game;
    }

    public void run(){
        while (true) {
            long sleep = ((long) rand.nextInt(Sphinx.SLEEP_TIME) + 2) * 60 * 1000;
            try{
                Thread.sleep(sleep);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            this.attack(this.jackpot);
        }
    }

    public void attack(BankAccount b){
        synchronized(this.game) {
            System.out.println("The sphinx has challenged you! Solve " +
                    this.challenge.problem());
            Thread t = new Thread(this.challenge);
            t.start();
            if(this.challenge.answerMatches()){
                System.out.println("You've answered correctly! " + 
                        this.challenge.getAnswer() + "has been added to " +
                        "your jackpot!");
                this.jackpot.increaseGold(this.challenge.getAnswer());
            } else {
                System.out.println("Incorrect. Your answer was " +
                        this.challenge.getPlayerResponse() + " . " +
                        "The correct answer is " + this.challenge.getAnswer() +
                        ". Your jackpot is now empty.");
                this.jackpot.empty();
            }
        }
    }
}
