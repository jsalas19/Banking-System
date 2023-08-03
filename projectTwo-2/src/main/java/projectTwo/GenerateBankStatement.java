package projectTwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenerateBankStatement {
    private static PrintWriter statementWriter;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static LocalDateTime now = LocalDateTime.now();

    /**
     * Prints into the bank statement file the action the user did on deposit on the user's file.
     * @param specificAccount specifying the account from the user (Savings, Checking, or Credit).
     * @param amount this is possible to input the right amount the user deposit into an account.
     */
    public static void deposit_UwU(account specificAccount, double amount){
        statementWriter.write(dtf.format(now) + "\tDeposit to: " + check_Class(specificAccount) + "-" + specificAccount.get_Account_Num() + "\t\t+" + amount +"\n");
    }

    /**
     * Prints into the bank statement file what the user did withdraw from its account, prints the action.
     * @param specificAccount specifying the account from the user (Savings, Checking, or Credit)
     * @param amount the amount the user withdraw from its account.
     */
    public static void withdraw_UwU(account specificAccount, double amount) {
        statementWriter.write(dtf.format(now) + "\tWithdrawal from: " + check_Class(specificAccount) + "-" + specificAccount.get_Account_Num() + "\t\t-" + amount +"\n");
    }

    /**
     * This method prints into the bank statement file the actions that the user did to make a payment to another account.
     * @param paying the name of the user that is going to pay the money.
     * @param recipient the name of the user that is going to receive the money.
     * @param amount the amount the user is going to pay that account.
     */
    public static void payment_UwU(account paying, account recipient, double amount){
        statementWriter.write(dtf.format(now) + "\tPayment to: " +  check_Class(recipient) + "-" + recipient.get_Account_Num() + " From: " + check_Class(paying) + "-" + paying.get_Account_Num()+  "\t\t -" + amount + "\n");
    }

    /**
     * This method is to print into the bank statement file the actions the user is going to do when trying to pay from to his/her account to his/her another account
     * @param transFrom account that is doing all the actions from
     * @param transTo account that is receiving all the user movements
     * @param amount total amount that the user is transfering.
     */
    public static void transfer_User(account transFrom, account transTo, double amount){
        statementWriter.write(dtf.format(now) + "\tTransfer to: " +  check_Class(transTo) + "-" + transTo.get_Account_Num() + " From: " + check_Class(transFrom) + "-" + transFrom.get_Account_Num()+ "\t\t -" + amount + "\n");
    }

    /**
     * Prints the new balance of the user after any action that was made to the account.
     * @param specificAccount any account from the user, can be Savings, Checking or Credit.
     */
    public static void new_balance(account specificAccount){
        statementWriter.write(dtf.format(now) + "\tBalance Iquiry for: " +  check_Class(specificAccount) + "-" + specificAccount.get_Account_Num() + "\t\t$"+ specificAccount.balance() + "\n");
    }

    /**
     * This method identifies which account the user is bank statementging in
     * @param accountType account type (Savings, Checking, Credit).
     * @return returns the type of account the user is modifying.
     */
    public static String check_Class(account accountType){
        switch (accountType.getClass().getTypeName()){
            case "projectTwo.Credit":
                return "Credit";
            case "projectTwo.Checking":
                return "Checking";
            case "projectTwo.Savings":
                return "Savings";
        }
        return " ";
    }

    /**
     * Writes to (user's fullname)_bank_statement.txt specifying which specific account we are altering.
     * @param account_UwU - account that is being accessed.
     */
    public static void specific_account_ID(account account_UwU){
        statementWriter.write("For account: " + check_Class(account_UwU) + "-" + account_UwU.get_Account_Num() + "\n\n\n");
    }

    /**
     * Creates a new bank statement .txt file if it does not already exist.
     * @param filepath - the name of the user allows the creation of a new bank statement file.
     * @param user - used to get Customer attributes
     */
    public static void create_Bank_Statement(String filepath, Customer user){
        File bs = new File(filepath + "_bank_statement.txt");
        try{
            if (!bs.isFile()){
                statementWriter = new PrintWriter(bs);
                add_user_information(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * With this method we write the account information of the user.
     * @param user - allows the use/access of Customer attributes
     */
    public static void add_user_information(Customer user){
        statementWriter.write("Account Name: " + user.get_First_name() + " " + user.get_Last_name() + "\n");
        statementWriter.write("Account ID Number: " + user.get_Id_No() + "\n");
        statementWriter.write("Date of Birth: " + user.get_Date_of_birth() + "\n");
        statementWriter.write("Address: " + user.get_Address() + "\n");
        statementWriter.write("\n");

    }

    public static void close_statement(account specificAccount){
        statementWriter.write("Ending Balance for " + dtf.format(now) + "\t\t\t" + specificAccount.balance());
        statementWriter.close();
    }
}
