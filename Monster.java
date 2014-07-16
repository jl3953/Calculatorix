package calculatorix;

/**
 * Template class for any type of Monster for the game.
 *
 * @author Jennifer Lam
 */
abstract class Monster implements Runnable{

    public void startRoaming() {

        Thread t = new Thread(this);
        t.start();
    }

    abstract void attack(BankAccount b);
}
