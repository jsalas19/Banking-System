package projectTwo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BankLogger {

    private static PrintWriter logFile;

    public static void deposit_UwU(account specificAccount, double amount){
        logFile.write(specificAccount.get_FullName() + " deposited " + amount + " to " + check_Class(specificAccount) + "-"+ specificAccount.get_Account_Num() + ". ");
        new_balance(specificAccount);
        logFile.write("\n");
    }

    public static void withdraw_UwU(account specificAccount, double amount) {
        logFile.write(specificAccount.get_FullName() + " withdrew $" + amount + " in cash from " + check_Class(specificAccount) + "-"+ specificAccount.get_Account_Num() + ". ");
        new_balance(specificAccount);
        logFile.write("\n");
    }

    public static void payment_UwU(account paying, account recipient, double amount){
        logFile.write(paying.get_FullName() + " paid " + recipient.get_FullName() + " $" + amount + "from " + check_Class(paying) +"-" + paying.get_Account_Num() + ". ");
        new_balance(paying);
        logFile.write(recipient.get_FullName() + " received $" + amount + " from " + paying.get_FullName() + ". ");
        new_balance(recipient);
        logFile.write("\n");
    }

    public static void transfer_UwU(account transFrom, account transTo, double amount){
        logFile.write(transFrom.get_FullName() + " transferred $" + amount + " from " + check_Class(transFrom) + "-" + transFrom.get_Account_Num() + " to " + check_Class(transTo) + "-" + transTo.get_Account_Num() + ". ");
        new_balance(transFrom);
        new_balance(transTo);
        logFile.write("\n");
    }

    public static void inquire_balance_UwU(account view){
        logFile.write(view.get_FullName() + " made a balance inquiry on " + check_Class(view) + "-" + view.get_Account_Num() + ". ");
        logFile.write(view.get_FullName() + "'s Balance for " + check_Class(view) + "-" + view.get_Account_Num() + ": " + view.balance());
        logFile.write("\n");
    }

    public static void new_balance(account specificAccount){
        logFile.write(specificAccount.get_FullName() + "'s New Balance for " + check_Class(specificAccount) + "-" + specificAccount.get_Account_Num() + ": " + specificAccount.balance() + ". ");
    }

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

    public static void openLog(){
        try {
            logFile = new PrintWriter("projectTwo-2/src/main/java/projectTwo/logFile.txt");
        } catch (FileNotFoundException e) {
            System.out.println("LogFile could not be created :( ... Exiting System.");
            System.exit(0);
        }

    }

    public static void closeLog(){
        logFile.close();
    }

}
