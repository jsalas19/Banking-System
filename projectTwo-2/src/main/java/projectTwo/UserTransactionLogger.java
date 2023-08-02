package projectTwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class UserTransactionLogger {
    private static PrintWriter logFile;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static LocalDateTime now = LocalDateTime.now();

    /**
     * Prints into the log file the action the user did on deposit on the user's file.
     * @param specificAccount specifying the account from the user (Savings, Checking, or Credit).
     * @param amount this is possible to input the right amount the user deposit into an account.
     */
    public static void deposit_UwU(account specificAccount, double amount){
        logFile.write(specificAccount.get_FullName() + " deposited $" + amount + " to " + check_Class(specificAccount) + "-"+ specificAccount.get_Account_Num() + ". ");
        new_balance(specificAccount);
        logFile.write("\n");
    }

    /**
     * Prints into the log file what the user did withdraw from its account, prints the action.
     * @param specificAccount specifying the account from the user (Savings, Checking, or Credit)
     * @param amount the amount the user withdraw from its account.
     */
    public static void withdraw_UwU(account specificAccount, double amount) {
        logFile.write(dtf.format(now) + "\tWithdrawal from: " + check_Class(specificAccount) + "-" + specificAccount.get_Account_Num() + "\t\t\t" + amount +"\n");
    }

    /**
     * This method prints into the log file the actions that the user did to make a payment to another account.
     * @param paying the name of the user that is going to pay the money.
     * @param recipient the name of the user that is going to receive the money.
     * @param amount the amount the user is going to pay that account.
     */
    public static void payment_UwU(account paying, account recipient, double amount){
        logFile.write(dtf.format(now) + "\tPayment to: " +  check_Class(recipient) + "-" + recipient.get_Account_Num() + "\t\t\t -" + amount + "\n");
    }

    /**
     * This method is to print into the log file the actions the user is going to do when trying to pay from to his/her account to his/her another account
     * @param transFrom account that is doing all the actions from
     * @param transTo account that is receiving all the user movements
     * @param amount total amount that the user is transfering.
     */
    public static void transfer_User(account transFrom, account transTo, double amount){
        logFile.write(dtf.format(now) + "\tTransfer to: " +  check_Class(transTo) + "-" + transTo.get_Account_Num() + " From: " + check_Class(transFrom) + "-" + transFrom.get_Account_Num()+ "\t\t\t -" + amount + "\n");
    }

    /**
     * Prints the new balance of the user after any action that was made to the account.
     * @param specificAccount any account from the user, can be Savings, Checking or Credit.
     */
    public static void new_balance(account specificAccount){
        logFile.write(specificAccount.get_FullName() + "'s New Balance for " + check_Class(specificAccount) + "-" + specificAccount.get_Account_Num() + ": " + specificAccount.balance() + ". ");
    }

    /**
            * This method prints into the log file if the user wants to inquire his/her balance, to see the account from user and the balance.
            * @param view the account from user.
     */
    public static void inquire_balance_UwU(account view){
        logFile.write(view.get_FullName() + " made a balance inquiry on " + check_Class(view) + "-" + view.get_Account_Num() + ". ");
        logFile.write(view.get_FullName() + "'s Balance for " + check_Class(view) + "-" + view.get_Account_Num() + ": " + view.balance());
        logFile.write("\n");
    }

    /**
     * This method identifies which account the user is logging in
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
     * This method allow us to create a new text file with the name of the user that is logging in.
     * @param file the name of the user, complete name.
     */
    public static void openLog(String file) {
        account user = null;
        File f = new File(file + ".txt");
        try {
            if (!f.isFile()) {
                logFile = new PrintWriter(f);
                add_user_information(user);
            }
        } catch (FileNotFoundException e) {
            System.out.println("LogFile could not be created after several attempts... Exiting System.");
            System.exit(0);
        }
    }
    
    public static void add_user_information(account user){
        
    }

    /**
     * This method let us close the file. End of the file.
     */
    public static void closeLog() {
        logFile.write("\n");
        logFile.write("Ending Balance for " + now + "\t\t\t\t");
        logFile.close();
    }



}