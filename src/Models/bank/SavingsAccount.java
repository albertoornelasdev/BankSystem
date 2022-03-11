package Models.bank;

public class SavingsAccount extends BankAccount{
    private static final double INTEREST_RATE = 0.16;

    public SavingsAccount(double balance, String id) {
        super(balance, id);
    }

    public void withdrawal(double amount) {
        super.withdrawal(amount);
    }

    public void deposit(double amount){
        super.deposit(amount);
    }
//    public void addInterest(double amout){
//        double interest = getBalance() * INTEREST_RATE / 100;
//
//        deposit(interest);
//    }

    @Override
    public void accountStatus() {
        System.out.println("Estado de Cuenta de Ahorro No. " + id);
        System.out.println("Balance: " + balance);
    }
}
