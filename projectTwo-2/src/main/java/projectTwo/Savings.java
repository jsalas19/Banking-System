package projectTwo;

import projectTwo.BankLogger;
import projectTwo.account;

/**
 * @author Diana Olivas
 * This class purpose is to get the customers
 * savings projectTwo.account number and savings start balance.
 *
 */

public class Savings implements account{
    //unique savings projectTwo.account number
    private int Sav_Acc_Num;
    //unique savings balance
    protected double Savings_Balance;

    private String fullName;


    public void set_Saving_Num(int Sav_Acc_Num){
        this.Sav_Acc_Num = Sav_Acc_Num;
    }

    public void set_Saving_Start_Bal(double Savings_Balance){
        this.Savings_Balance = Savings_Balance;
    }

    public int get_Account_Num(){
        return Sav_Acc_Num;
    }


    public double balance(){
        return Savings_Balance;
    }


    /**
     * This method is for deposit money into either savings, checking or credit projectTwo.account
     * and updates the new balance after deposit money into the projectTwo.account.
     * @return void
     * @param amount the amount the userName is going to deposit
     */
    public boolean deposit(double amount){
        boolean depos = false;
        //the deposit has to be geater than 0.00
        if(amount > 0.00){
            Savings_Balance = Savings_Balance + amount;
            BankLogger.deposit_UwU(this, amount);
            UserTransactionLogger.deposit_UwU1(this, amount);
            depos = true;

        }
        else{
            System.out.println("You need more funds to complete a deposit.");
        }
        return depos;

    } //end of deposit method.
    /**
     * This method is for withdraw money from savings projectTwo.account and updates the current
     * balance of the savings projectTwo.account after withdraw money.
     * @param amount the amount the user wants to withdraw money from her/his savings projectTwo.account
     * @return String
     */
    public void withdraw(double amount){
        //if the amount is less than the savings balanace, proceed to withdraw money
        if(Savings_Balance >= amount){
            Savings_Balance = Savings_Balance - amount;
            BankLogger.withdraw_UwU(this, amount);
            UserTransactionLogger.withdraw_UwU(this, amount);
            System.out.println("You successfully widhtrawn $" + amount);
        }
        else{
            System.out.println("Not enough money in your projectTwo.account to complete transaction. Please enter a smaller amount.");
        }

    } //end of the withdrawMoney method.


    /**
     * This method is for payments, the user can make a payment for either their
     * checkings, savings or credit projectTwo.account.
     * @param amount the amount that is going to be paid from the user
     * @return
     */
    public void payment(account recipient, double amount){
        //the amount for the payment has to be less than the blance, if not,
        //not enough funds to pay someone else
        if (recipient.get_Account_Num() != Sav_Acc_Num && (recipient.get_Account_Num() % 1000) != (Sav_Acc_Num % 1000)){
            if(amount <= Savings_Balance){
                withdraw(amount);
                recipient.deposit(amount);
                BankLogger.payment_UwU(this, recipient, amount);
                UserTransactionLogger.payment_UwU(this, recipient, amount);
                System.out.println("You have successfully made a payment of $" + amount + ". To another projectTwo.account");
            }
        }

    } //end of the paySomeone method.

    /**
     * This method transfer money from savings projectTwo.account to another person's projectTwo.account
     * @param amount amount is going to transfer from the projectOne.projectTwo.Checking projectTwo.account to projectOne.projectTwo.Savings projectTwo.account
     * @return
     */
    public void transfer(account accoType, double amount){
        //the amount has to be less than the total balance in order to transfer the money
        //otherwise, not enough funds to transfer money.
        if (accoType.get_Account_Num() != Sav_Acc_Num && (accoType.get_Account_Num() % 100) == (Sav_Acc_Num % 100)){
            if(amount <= Savings_Balance){
                withdraw(amount);
                accoType.deposit(amount);
                BankLogger.transfer_UwU(this, accoType, amount);
                UserTransactionLogger.transfer_User(this, accoType, amount);
                System.out.println("You have successfully transfered $" + amount + " to this projectTwo.account.");
            }

            else{
                System.out.println("Transaction has failed. Enter a smaller amount");
            }
        }

    } //end of the transferMoney method.

    @Override
    public void display_account_info() {
        System.out.println("Account Number: "+ this.Sav_Acc_Num);
        System.out.println("\tBalance: "+this.Savings_Balance);
        BankLogger.inquire_balance_UwU(this);
        UserTransactionLogger.inquire_balance_UwU(this);
    }


    public void set_FullName(String firstName, String lastName) {
        this.fullName = firstName + " " + lastName;
    }

    public String get_FullName(){ //add this
        return fullName;
    }

} //end of the Savings class