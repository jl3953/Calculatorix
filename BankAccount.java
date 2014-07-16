package calculatorix;

/**
 * Provides a template for a class that stores money
 *
 * @author Jennifer Lam
 */
abstract class BankAccount {

    protected long balance; //how much is in the bank account

    public BankAccount(){
        this.balance = 0L;
    }

    /**
     * Returns how much the BankAccount instance has
     */
    public long getAmount(){
        return this.balance;
    }

    /**
     * Defines how the gold in BankAccount increases
     *
     * @param gold
     *          how much gold is being added
     */
    public void increaseGold(long gold){
        this.balance += gold;
    }

    /**
     * Empties the BankAccount of all gold
     */
    public void empty(){
        this.balance = 0L;
    }

}
