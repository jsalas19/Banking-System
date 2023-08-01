package projectTwo;

import projectTwo.BankLogger;
import projectTwo.account;

/**
 * @author deolivas4
 * This class is to handle any transaction inside credit projectTwo.account.
 * widthraw
 * deposit
 * make payment
 * and Transfer
 */
public class Credit implements account{
    //unique projectTwo.account number
    //change to protected,
    private int cred_Acc_Num;
    //unique balance
    protected double credit_Balance;
    //max credit
    protected double max_Credit;

    protected String fullName;

    /**
     * setter method for private values declare in class
     */
    public void set_Credit_Acc_Num(int cred_Acc_Num){
        this.cred_Acc_Num = cred_Acc_Num;
    }

    public void set_FullName(String firstName, String lastName){ //add this
        this.fullName = firstName +" " + lastName;
    }

    public String get_FullName(){ //add this
        return fullName;
    }

    public void set_Credit_Max(int max_Credit){
        this.max_Credit = max_Credit;
    }

    public void set_Credit_Start_Bal(double credit_Balance){
        this.credit_Balance  = credit_Balance;
    }

    /**
     * getter method for accountNumer
     * @return cred_Acc_Num
     */
    public int get_Account_Num(){
        return cred_Acc_Num;
    }
    /**
     * getter method for max_Credit
     * @return max_Credit;
     */
    public double get_Credit_Max(){
        return max_Credit;
    }
    /**
     * getter method for credit_Balance
     * @return credit_Balance
     */
    public double balance(){
        return credit_Balance;
    }
    /**
     * @param amount
     * this method is to deposit money into credit projectTwo.account
     * also adjusts accounts balance
     */
    public  void deposit(double amount) {
        //deposit needs to be greater than 0.00
        if(amount < 0.00) {
            credit_Balance = credit_Balance + amount;
            BankLogger.deposit_UwU(this, amount);
            System.out.println("You made a deposit of $" + amount);
        }else {
            System.out.println("you need more funds to complete a deposit");
        }
    }
    /**
     * @param amount
     * this method is used to withdraw money
     */
    public void withdraw(double amount) {
        if (Math.abs(credit_Balance) >= amount) {
            credit_Balance = credit_Balance + amount;
            BankLogger.withdraw_UwU(this, amount);
            System.out.println("you have successfully widthrawn $" + amount);
        }else{
            System.out.println("Not enough funds to complete transaction, Please enter a smaller amount");
        }
    }


    /**
     * @param amount
     * this method is for customers to send money to other bank costumers
     *
     * this method takes 2 arguments an projectTwo.account object and double type variable
     */
    public void payment(account recipient, double amount) {
        //amount must be greater than 0.00 (also no overdraft allowed)
        if (recipient.get_Account_Num() != cred_Acc_Num && (recipient.get_Account_Num() % 100) != (cred_Acc_Num % 100)){
            if (amount <= credit_Balance) {
                withdraw(amount);
                //accountType.deposit(amount);
                BankLogger.payment_UwU(this, recipient, amount);
                System.out.println("Success! (Payment)");
            }else{
                System.out.println("Insufficient funds to make payment! Try transferring money or make a deposit");
            }
        }else{
            System.out.println("Please enter the projectTwo.account number of the user you are making a payment to!");
        }

    }

    /**
     * @param amount
     * this method is to transfer money from X projectTwo.account to Y projectTwo.account
     * accountType is an object created in our main class that will hold the type of projectTwo.account we are modifying.
     */
    public void transfer(account account_Type,double amount) {
        //transfer needs to be greater than 0.00(no over draft allowed)
        if (account_Type.get_Account_Num() != cred_Acc_Num && (account_Type.get_Account_Num() % 100) == (cred_Acc_Num % 100)) {
            if (amount <= credit_Balance) {
                withdraw(amount);
                account_Type.deposit(amount);
                BankLogger.transfer_UwU(this, account_Type, amount);
                System.out.println("Success! (Transfer)");
            } else {
                System.out.println("Transaction has failed!");
            }
        }
    }

    @Override
    public void display_account_info() {
        System.out.println("Account Number: "+ this.cred_Acc_Num);
        System.out.println("\tprojectTwo.Credit Max: "+ this.max_Credit);
        System.out.println("\tBalance: " + this.credit_Balance);
        BankLogger.inquire_balance_UwU(this);
    }


}