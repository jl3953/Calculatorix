package calculatorix;

public class Wallet extends BankAccount{

    public Wallet(){
        super();
    }

    public void increaseGold(long gold){
        this.balance *= 2;
        this.balance += gold;
    }

    public void deduct(int gold){
        if ((this.balance -= gold) < 0) {
            this.balance = 0;
        }
    }
}
