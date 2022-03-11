package Services;

import Models.bank.*;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.util.*;
import java.util.stream.Collectors;

public class ProductManagerService {
    // Products by cliente id
    private Map<String, List<BankAccount>> productsMap = new HashMap<>();

    public ProductManagerService(){}

    public Map<String, List<BankAccount>> getProductsMap() {
        return productsMap;
    }

    public void addProduct(Client client, BankAccount product){
        List<BankAccount> products = productsMap.get(client.getId());
        if (products == null){
            products = new ArrayList<>();
            productsMap.put(client.getId(), products);
        }
        if (product instanceof  InvestmentAccount) {
            boolean f = products.stream().anyMatch(CheckingAccount.class::isInstance);
            if (f) {
                System.out.println("YES");
                products.add(product);
            } else {
                System.out.println("NO");
            }
        }else{
            products.add(product);
        }
    }

    public boolean haveInvestmentAccount(List products){
        boolean haveCheckingAccount = products.stream().allMatch(CheckingAccount.class::isInstance);
        if (haveCheckingAccount) {
            System.out.println("Se agrego la cuenta de Inversion");
        } else {
            System.out.println("No se agrego la cuenta de Inversion, debes tener una cuneta de cheques primero");
            return false;
        }
        return true;
    }

    public List<BankAccount> productsEmpty(String customerId, List<BankAccount> products){
        products = new ArrayList<>();
        productsMap.put(customerId, products);
        return products;
    }

    public List<BankAccount> getProducts(String customerId) {
        List<BankAccount> products = productsMap.get(customerId);
        if (products == null)
            System.out.println("El cliente no tiene productos asignados");
        return products;
    }

    public BankAccount findBankAccountById(String clientId, String bankAccountId) {
        List<BankAccount> list = productsMap.get(clientId);
        return list.stream().
            filter(product -> product.getId().equals(bankAccountId)).
            findFirst().orElse(null);
    }

    public static void main(String[] args) {
        Client client = new Client("Mario", "23424", 24535);
        SavingsAccount savingsAccount = new SavingsAccount(3424,"42451");
        SavingsAccount savingsAccount1 = new SavingsAccount(90220,"13423");
        CheckingAccount checkingAccount = new CheckingAccount(9000, "67809");
        InvestmentAccount investmentAccount = new InvestmentAccount(2000.0, "12782", 0.05);

        ProductManagerService productManagerService = new ProductManagerService();
        productManagerService.addProduct(client, savingsAccount);
        productManagerService.addProduct(client, savingsAccount1);
        productManagerService.addProduct(client, checkingAccount);
        productManagerService.addProduct(client, investmentAccount);


        BankAccount bankAccount = productManagerService.findBankAccountById("23424", "12782");
        System.out.println(bankAccount);

        ((InvestmentAccount) bankAccount).withdrawal(600);
        ((InvestmentAccount) bankAccount).withdrawal(600);
        ((InvestmentAccount) bankAccount).applyCut();
        bankAccount.accountStatus();


    }
}
