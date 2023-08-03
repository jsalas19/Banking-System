package projectTwo;

import projectTwo.BankLogger;
import projectTwo.account;

/**
 * @author Joshua Salas
 * This class contains the user's checking information and allows the user to withdraw, deposit, pay another user's
 * projectTwo.account, and transfer between accounts of the same user.
 */
public class Checking implements account{
    /**
     * The attributes of this class are the following:
     * checking_account_no and checking_bal
     */
    private int checking_account_no;
    protected double checking_bal;
    private String fullName;

    /**
     * @param checking_account_no - projectTwo.account number of user.
     * This method is used to set the checking_account_no attribute.
     */
    public void set_Checking_account_no(int checking_account_no) {
        this.checking_account_no = checking_account_no;

    }

    public void set_FullName(String firstName, String lastName){ //add this
        this.fullName = firstName + " "+ lastName;
    }

    public String get_FullName(){ //add this
        return fullName;
    }

    /**
     * @param checking_start_bal - checking balance of user.
     * This method is used to set the checking_bal attribute.
     */
    public void set_Checking_start_bal(double checking_start_bal) {
        this.checking_bal = checking_start_bal;
    }

    /**
     * @return The value of 'checking_account_no'.
     * Gets the private attribute 'checking_account_no'.
     */
    public int get_Account_Num() {
        return checking_account_no;
    }

    /**
     *
     * @return The value of 'checking_bal'.
     * Gets the private attribute 'checking_bal'.
     */
    public double balance() {return checking_bal;}

    /**
     *
     * @param amount
     * method is to deposit funds into an projectTwo.account
     * amount > 0.00
     *
     * apply to checkings and  to savings
     */
    @Override
    public boolean deposit(double amount) {
        boolean depos = false;
        if (amount >= 0.0){
            this.checking_bal += amount;
            BankLogger.deposit_UwU(this, amount);
            UserTransactionLogger.deposit_UwU1(this, amount);
            GenerateBankStatement.deposit_UwU(this,amount);
            depos = true;
        }
        return depos;
    }

    /**
     *
     * @param amount
     * method is to withdraw fund from an projectTwo.account
     * amount > 0.00
     *
     * apply to checkings, saving ,credit
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= this.checking_bal){
            this.checking_bal -= amount;
            BankLogger.withdraw_UwU(this, amount);
            UserTransactionLogger.withdraw_UwU(this, amount);
            GenerateBankStatement.withdraw_UwU(this,amount);
        }
    }

    /**
     *
     * @param recipient
     * @param amount
     * method is for customer to pay another bank customer
     * using projectTwo.account of their choice
     *
     * apply to checkings, saving and credit
     */
    @Override
    public boolean payment(account recipient, double amount) {
        boolean depos = false;
        if (recipient.get_Account_Num() != checking_account_no && (recipient.get_Account_Num() % 1000) != (checking_account_no % 1000)){
            if (amount <= this.checking_bal && recipient.deposit(amount )){
                withdraw(amount);
                depos = true;
                BankLogger.payment_UwU(this, recipient, amount);
                UserTransactionLogger.payment_UwU(this, recipient, amount);
                GenerateBankStatement.payment_UwU(this,recipient, amount);
                System.out.println("Success! (payment)");
            }
            System.out.println("Failed! (payment)");
        }
        return depos;
    }

    /**
     *
     * @param amount
     * method is for customer to transfer money between
     * accounts
     *
     * apply to projectOne.checking, saving and credit
     */
    @Override
    public boolean transfer(account account_Type,double amount) {
        boolean depos = false;
        if (account_Type.get_Account_Num() != checking_account_no && (account_Type.get_Account_Num() % 1000) == (checking_account_no % 1000)) {
            if (amount <= this.checking_bal && account_Type.deposit(amount)); {
                withdraw(amount);
                depos = true;
                BankLogger.transfer_UwU(this, account_Type, amount);
                UserTransactionLogger.transfer_User(this, account_Type, amount);
                GenerateBankStatement.transfer_User(this, account_Type, amount);

                System.out.println("Success! (transfer)");
            }
            System.out.println("Failed! (transfer)");
        }
        return depos;
    }

    @Override
    public void display_account_info() {
        System.out.println("Account Number: "+ this.checking_account_no);
        System.out.println("\tBalance: "+this.checking_bal);
        BankLogger.inquire_balance_UwU(this);
        UserTransactionLogger.inquire_balance_UwU(this);
    }
}
