package Models.commands.subcommands;

import Models.bank.BankAccount;
import Models.bank.CheckingAccount;
import Models.bank.InvestmentAccount;
import Models.bank.SavingsAccount;
import Models.commands.movements.Deposit;
import Models.commands.movements.Withdrawal;
import Services.ProductManagerService;

public class MovementsAccountsCmds {

    private ProductManagerService productManagerService;
    private Withdrawal withdrawal;
    private Deposit deposit;

    public MovementsAccountsCmds(ProductManagerService productManagerService) {
        this.productManagerService = productManagerService;
        withdrawal = new Withdrawal();
        deposit = new Deposit();
    }

    public void withdrawal(BankAccount bankAccount) {
        if(bankAccount instanceof InvestmentAccount)
            withdrawal.makeWithdrawalInvestment((InvestmentAccount) bankAccount);
        else if (bankAccount instanceof SavingsAccount)
            withdrawal.makeWithdrawalSaving((SavingsAccount) bankAccount);
        else if (bankAccount instanceof CheckingAccount)
            withdrawal.makeWithdrawalChecking((CheckingAccount) bankAccount);
    }

    public void deposit(BankAccount bankAccount) {
        if(bankAccount instanceof InvestmentAccount)
            deposit.makeDepositInvestment((InvestmentAccount) bankAccount);
        else if (bankAccount instanceof SavingsAccount)
            deposit.makeDepositSaving((SavingsAccount) bankAccount);
        else if (bankAccount instanceof CheckingAccount)
            deposit.makeDepositChecking((CheckingAccount) bankAccount);
    }
}
