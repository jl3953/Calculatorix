package calculatorix;

import java.util.Random;

/**
 * Implements a leprechaun that attacks every now and then.
 *
 * @author Jennifer Lam
 */
public class Leprechaun extends Monster {

    private Wallet wallet;
    private Random rand;
    private Oracle game;
    public static final int SLEEP_TIME = 2; //maximum time leprechaun sleeps

    public Leprechaun(Wallet wallet, Oracle game) {
        this.wallet = wallet;
        this.rand = new Random();
        this.game = game;
    }

    /**
     * Leprechaun sleeps for a random period of time and then attacks the 
     * player's wallet.
     */
    public void run(){
        while (true) {
            long sleep = (long) (rand.nextInt(Leprechaun.SLEEP_TIME)) + 1 * 60
                    * 1000;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            this.attack(this.wallet);
        }
    }

    /**
     * Leprechaun empties a player's wallet.
     */
    public void attack(BankAccount b) {
        synchronized(this.game) {
            System.out.println("Oh no! The leprechaun has stolen your gold! " +
                "Your wallet is now empty!");
            b.empty();
        }
    }
}
