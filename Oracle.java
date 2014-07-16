package calculatorix;

import java.util.Scanner;

/**
 * Runs the Calculatorix game.
 *
 * @author Jennifer Lam
 *
 */
public class Oracle{

    private static Scanner sc = new Scanner(System.in);
    public static final long WINNING_THRESHOLD = 1000L;

    public void play() throws InterruptedException {

        Oracle.displayGreeting();
        Oracle.displayInstructions();
        Oracle.displayStarter();

        BankAccount jackpot = new Jackpot();
        Wallet wallet = new Wallet();
        Calculator calculator = new Calculator(Oracle.sc);

        Monster leprechaun = new Leprechaun((Wallet) wallet, this);
        leprechaun.startRoaming();
        Monster sphinx = new Sphinx((Jackpot) jackpot, Oracle.sc, this);
        sphinx.startRoaming();

        while(jackpot.getAmount() < Oracle.WINNING_THRESHOLD) {
            synchronized(this) {
                System.out.println(calculator.problem());

                Thread t = new Thread(calculator);
                t.start();
                Thread.sleep(10*1000);
                if (t.isAlive()){
                    System.out.println("10 seconds has passed!");
                    t.interrupt();
                }

                if(calculator.answerMatches()){
                    System.out.println("Correct! " + calculator.getAnswer() +
                            " has been added to your wallet!");
                    wallet.increaseGold(calculator.getAnswer());
                } else{
                    System.out.println("Incorrect. You answered " +
                            calculator.getPlayerResponse() + " ." +
                            "The correct answer is " + calculator.getAnswer() +
                            ". " + calculator.getAnswer() + " will be deducted" +
                            " from your wallet.");
                    wallet.deduct(calculator.getAnswer());
                    }
                System.out.print("You now have " + wallet.getAmount() +
                        " gold in your wallet. Would you like to unload? " +
                        "Please type Y/N ");
                System.out.flush();

                if (sc.nextLine().equalsIgnoreCase("y")) {
                    jackpot.increaseGold(wallet.getAmount());
                    wallet.empty();
                    System.out.println("Your jackpot now has " + 
                            jackpot.getAmount());
                }

                System.out.println("Wallet: " + wallet.getAmount() +
                        ", Jackpot: " + jackpot.getAmount());
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println("Congratulations! You have one billion gold pieces!"
                + " You win!");

        if (Oracle.playAgain()) {
            this.play();
        } else {
            Oracle.displayFarewell();
            sc.close();
        }
    }

    private static void displayGreeting() {
        System.out.println("Welcom to Calculatorix!");
    }

    private static void displayInstructions() {
        System.out.println("To add to your gold, " +
                "solve the problem in 10 seconds! If you are correct, your " +
                "wallet amount is doubled, and the product is added to your " +
                "wallet. Leprechauns can steal your wallet at any time, so " +
                "be sure to unload your gold into your jackpot before he does! " +
                "Answer the sphinx's challenge to defend your jackpot!");
    }

    private static void displayStarter() {
        int pause = 1 * 1000;
        System.out.println("Get ready to start in");
        for (int i = 3; i > 0; i--) {
            System.out.println(i + "...");
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Solve!");
    }

    private static boolean playAgain() {
        System.out.println("Play again? Please type Y/N");
        if (sc.nextLine().equalsIgnoreCase("y"))
            return true;
        return false;
    }

    private static void displayFarewell() {
        System.out.println("So long!");
    }
}
