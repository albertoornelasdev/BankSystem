import Models.BasicAccount;

public class Main {
    public static void main(String[] args) {
        BasicAccount basicAccount = new BasicAccount(1300);
        basicAccount.deposit(300);
        basicAccount.withdraw(1650);
        System.out.println(basicAccount.getBalance());
    }
}
