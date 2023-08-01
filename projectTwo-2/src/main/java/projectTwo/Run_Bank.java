package projectTwo;

import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;
import java.util.List;

/**
 * @author Joshua Salas, Diana Olivas, Ruth Cazares-Bravo
 * @Version openjdk-20.0.1
 */

/**
 * This is our teams driver class, which allows us to create and manipulate the objects we have designed for
 * the project. This class also allows us to read and create Excel file (.xlsx) as well as log any interaction of the
 * user with the system.
 */
public class Run_Bank {


    /**
     * @param args - default main method parameter.
     * @throws CsvValidationException - required exception throw to ensure the file is a projectTwo.CSV file.
     *                                This is our main method that drives the functionality of the system.
     */
    public static void main(String[] args) throws CsvValidationException {

        BankLogger.openLog();

        try {
            Scanner scnr = new Scanner(System.in);
            //PATH MIGHT BE WRONG
            CustomerMap.read_data_UwU("projectTwo-2/src/main/java/projectTwo/CS 3331 - Bank Users (2).csv");

            //Map<List<String>, Customer> customerMap = create_customerMap(create_data.get_data());
            boolean access = true;
            while (access) {
                String str;
                String idString;
                System.out.println("1. Access as Manager \n2. Access as Customer");
                str = scnr.nextLine();
                if (str.equals("EXIT")) {
                    break;
                } else if (str.equals("2")) {

                    System.out.println("1. Inquire by account name. \n2. Inquire by account type/number \n3. Exit");
                    str = scnr.nextLine();
                    switch (str) {
                        case "1":
                            Customer left = null;
                            while (left == null) {
                                System.out.println("Whose account would you like to inquire about? \nEnter Full Name of account (Ex: Joshua Salas or Joshua S):");
                                str = scnr.nextLine();
                                UserTransactionLogger.openLog(str);
                                System.out.println("Enter your Identification Number:");
                                idString = scnr.nextLine();
                                List<String> strList = new ArrayList<>(List.of(str.split(" ")));
                                strList.add(idString);
                                System.out.println(strList.size());
                                if (strList.size() == 3) {
                                    left = CustomerMap.find_account(strList.get(0), strList.get(1), strList.get(2));
                                    if (left == null) {
                                        System.out.println("Sorry, account was not found \nEnter a proper name!");
                                    } else {
                                        account specifiedA = CustomerMap.return_selected_accountType(left, prompt_account_type());
                                        if (specifiedA != null) {
                                            available_actions_specific(specifiedA);
                                        }
                                    }
                                }
                                UserTransactionLogger.closeLog();
                            }
                            break;

                        case "2":
                            account specified = CustomerMap.find_specific_account(prompt_account_type());
                            assert specified != null;
                            specified.display_account_info();
                            available_actions_specific(specified);
                            break;
                    }

                    access = false;
                } else if (str.equals("1")) {
                    do {
                        System.out.println("1. Inquire Account By Customer Name.\n2. Inquire Account By Account Id\n3.End Session");
                        str = scnr.nextLine();
                        if (str.equals("1")) {
                            System.out.println("Whose account would you like to inquire about?: ");
                            str = scnr.nextLine();
                            List<String> strList = new ArrayList<>(List.of(str.split(" ")));

                            Customer left = CustomerMap.find_account(strList.get(0), strList.get(1), strList.get(2));
                            if (left == null) {
                                System.out.print("Customer does not exist");
                            } else {
                                System.out.println("Displaying " + str + "'s Account Information");
                                display_account_information(left);
                            }

                        } else if (str.equals("2")) {
                            System.out.println("Enter Customers ID Number");
                            str = scnr.nextLine();

                            Customer customer = CustomerMap.find_account_ID(str);
                            if (customer == null) {
                                System.out.println("Customer does not Exist");
                            } else {
                                display_account_information(customer);
                            }
                        }
                    } while (!str.equals("3"));

                    System.out.println("GoodBYE!");
                    access = false;
                }
            }

            CustomerMap.write_to_CSV();


        } catch (IOException e) {
            System.out.println("OwO, something went wrong");
        }
        BankLogger.closeLog();

    }

    public static void add_new_user(Map<List<String>, Customer> data) {
        Random rdm = new Random();
        Scanner scr = new Scanner(System.in);
        Customer new_Customer = new Customer();
        String placeholder;
        String[] strName;
        do {
            System.out.println("Enter First and Last Name (i.e. Joshua Salas, Joshua S):");
            placeholder = scr.nextLine();
            strName = placeholder.split(" ");
            if (strName.length < 2) {
                System.out.println("Please enter only your first and last name!");
            }
        } while (strName.length < 2);
        new_Customer.set_First_name(strName[0]);
        new_Customer.set_Last_name(strName[1]);

        System.out.println("Enter your Date of Birth (i.e. 1-Jan-00):");
        placeholder = scr.nextLine();
        new_Customer.set_Date_of_birth(placeholder);

        System.out.println("Enter your Address, City, State, Zip (ex. 500 W. University Ave, El Paso, TX 79968)");
        placeholder = scr.nextLine();
        new_Customer.set_Address(placeholder);

        do {
            System.out.println("Enter your Phone Number (ex: (999)999-999)");
            placeholder = scr.nextLine();
            if (placeholder.length() != 12) {
                System.out.println("Please enter a phone number in the example format!");
            }
        } while (placeholder.length() != 12);

        new_Customer.set_Phone_no(placeholder);
        do {
            System.out.println("What is your credit score? (must be between 300 and 850");
            placeholder = scr.nextLine();
            if (Integer.parseInt(placeholder) < 300 || Integer.parseInt(placeholder) > 850) {
                System.out.println("Please enter a credit score in range!");
            }
        } while (Integer.parseInt(placeholder) < 300 || Integer.parseInt(placeholder) > 850);

        int t = Integer.parseInt(placeholder);

        if(t < 580){
            new_Customer.get_Credit().set_Credit_Max(rdm.nextInt(100,699));
        }else if (t > 581 && t < 669){
            new_Customer.get_Credit().set_Credit_Max(rdm.nextInt(700,4999));
        }else if (t > 670 && t < 739){
            new_Customer.get_Credit().set_Credit_Max(rdm.nextInt(5000,7499));
        }else if (t > 740 && t < 799){
            new_Customer.get_Credit().set_Credit_Max(rdm.nextInt(7500,15999));
        }else if (t > 800){
            new_Customer.get_Credit().set_Credit_Max(rdm.nextInt(16000,25000));
        }

        new_Customer.get_Credit().set_Credit_Start_Bal(0.00);

    }

    /**
     * This method prompts the user to choose the type of account they want to interact with.
     *
     * @return String of integer option (account type option).
     */
    public static String prompt_account_type() {
        Scanner nscnr = new Scanner(System.in);
        System.out.println("What is the account type? \n\t1. Checkings \n\t2. Savings\n\t3. Credit \n\t4. Exit");
        String str = nscnr.nextLine();
        return str;
    }

    /**
     * This method displays the ALL account information
     *
     * @param customer
     */
    public static void display_account_information(Customer customer) {
        System.out.print("Account Name: " + customer.get_First_name() + " ");
        System.out.println(customer.get_Last_name());
        System.out.println("Account ID Number: " + customer.get_Id_No());
        System.out.println("Date of Birth: " + customer.get_Date_of_birth());
        System.out.println("Address: " + customer.get_Address());
        List<String> temp = customer.get_banking_information();
        for (String i : temp) {
            System.out.println(i);
        }
    }

    /**
     * This method contains all actions that the user is able to take.
     *
     * @param accountType - Could be any of the account sub-classes (Credit, Savings, Checkings).
     * parameter accountType is of type Account is casted to become of type Credit to access Credits, Credit Max
     */
    public static void available_actions_specific(account accountType) {
        //System.out.println("II am inside available actions specific");
        Scanner scr = new Scanner(System.in);
        //Initializing our String Variable her because we want our do while loop to have acess the account.
        String str = null;


        /*do while starts here, Allows the program to display our Menu until user decides to quit.*/
        do {
            switch (accountType.getClass().getTypeName()) {


                //Inside projectTwo.Checking Account
                case "projectTwo.Checking" -> {
                    System.out.println("Make A selection:");
                    System.out.println("1. Deposit \n2. Withdraw \n3. Payment \n4. Transfer \n5. View Balance \n6. Exit");
                    str = scr.nextLine();
                    Double amount;
                    switch (str) {
                        //Under Deposit option
                        case "1":
                            System.out.print("Enter Deposit Amount: $");
                            str = scr.nextLine();
                            System.out.println();
                            amount = Double.parseDouble(str);
                            accountType.deposit(amount);
                            //accountType.display_account_info();
                            break;
                        //Under widthdraw Option
                        case "2":
                            System.out.print("Enter Withdraw Amount: $");
                            str = scr.nextLine();
                            //System.out.println();
                            amount = Double.parseDouble(str);
                            accountType.withdraw(amount);
                            //accountType.display_account_info();
                            //projectTwo.BankLogger.withdraw_UwU(accountType, amount);
                            break;
                        //Under Payment Option
                        case "3":
                            System.out.print("Enter Payment Amount: $");
                            str = scr.nextLine();
                            amount = Double.parseDouble(str);
                            while (amount > accountType.balance()) {
                                System.out.println("INSUFFICIENT FUNDS!! Try entering a new Payment amount:");
                                str = scr.nextLine();
                                amount = Double.parseDouble(str);
                            }
                            account pay_acc = CustomerMap.find_specific_account(prompt_account_type());
                            if (pay_acc != null) {
                                accountType.payment(pay_acc, amount);
                                //accountType.display_account_info();
                                //pay_acc.display_account_info();
                            }
                            //projectTwo.BankLogger.add("Account Payment: " + amount + " amount.");
                            break;
                        //Under Transfer Option
                        case "4":
                            System.out.println("Enter the amount you wish to Transfer:");
                            str = scr.nextLine();
                            amount = Double.parseDouble(str);
                            System.out.println();
                            account transferAccount = CustomerMap.find_specific_account(prompt_account_type());
                            if (transferAccount != null) {
                                accountType.transfer(transferAccount, amount);
                                //accountType.display_account_info();
                                //transferAccount.display_account_info();
                            }
                            //projectTwo.BankLogger.add("Account Transfer: " + amount + " amount.");
                            break;
                        //Under view Balance Option
                        case "5":
                            accountType.display_account_info();
                            //projectTwo.BankLogger.add("Account inquire: " + accountType.balance() + " amount.");
                            break;
                        case "6":
                            System.out.println("Thank You for banking with UTEP\n Goodbye!");
                            break;
                    }
                }
                //Inside Savings Account
                case "projectTwo.Savings" -> {
                    System.out.println("Make A selection:");
                    System.out.println("1. Deposit \n2. Withdraw \n3. Payment \n4. Transfer \n5. View Balance \n6. Exit");
                    str = scr.nextLine();
                    Double amount;

                    switch (str) {
                        //Under Deposit option
                        case "1":
                            //do {
                            System.out.print("Enter Deposit Amount: $");
                            str = scr.nextLine();
                            System.out.println();
                            amount = Double.parseDouble(str);
                            accountType.deposit(amount);
                            accountType.display_account_info();
                            BankLogger.deposit_UwU(accountType, amount);
                            break;
                        //Under widthdraw Option
                        case "2":
                            System.out.print("Enter Withdraw Amount: $");
                            str = scr.nextLine();
                            //System.out.println();
                            amount = Double.parseDouble(str);
                            accountType.withdraw(amount);
                            accountType.display_account_info();
                            BankLogger.withdraw_UwU(accountType, amount);
                            break;
                        //Under Payment Option
                        case "3":
                            System.out.print("Enter Payment Amount: $");
                            str = scr.nextLine();
                            amount = Double.parseDouble(str);
                            while (amount > accountType.balance()) {
                                System.out.println("INSUFFICIENT FUNDS!! Try entering a new Payment amount:");
                                str = scr.nextLine();
                                amount = Double.parseDouble(str);
                            }

                            // System.out.println("What kind of account are we making a payment towards?");
                            account pay_acc = CustomerMap.find_specific_account(prompt_account_type());
                            if (pay_acc != null) {
                                accountType.payment(pay_acc, amount);
                                //projectTwo.BankLogger.add("OwO");
                                accountType.display_account_info();
                                pay_acc.display_account_info();
                            }
                            //projectTwo.BankLogger.add("Account Payment: " + amount + " amount.");
                            break;
                        //Under Transfer Option
                        case "4":
                            System.out.println("Enter the amount you wish to Transfer:");
                            str = scr.nextLine();
                            amount = Double.parseDouble(str);

                            while (amount > accountType.balance()) {
                                System.out.println("INSUFFICIENT FUNDS!! Try Entering a Different Amount");
                                str = scr.nextLine();
                                amount = Double.parseDouble(str);
                            }
                            //System.out.println();
                            System.out.println("Choose recipient AccountType to Transfer Funds.");
                            account transferAccount = CustomerMap.find_specific_account(prompt_account_type());
                            if (transferAccount != null) {
                                accountType.transfer(transferAccount, amount);
                                accountType.display_account_info();
                                transferAccount.display_account_info();
                            }
                            //projectTwo.BankLogger.add("Account Transfer: " + amount + " amount.");
                            break;
                        //Under view Balance Option
                        case "5":
                            accountType.display_account_info();
                            //projectTwo.BankLogger.add("Account inquire: " + accountType.balance() + " amount.");
                            break;
                        case "6":
                            System.out.println("Thank You for banking with UTEP\n Goodbye!");
                            break;
                    }
                }
                //Inside Credit Account
                case "projectTwo.Credit" -> {
                    //CreditAccount maxCredit = (CreditAccount) accountType;
                    System.out.println("Make A selection:");
                    System.out.println("1. Pay Balance \n2. Withdraw \n3. Payment \n4. Transfer \n5. View Balance \n6. Exit");
                    str = scr.nextLine();
                    Double amount;

                    switch (str) {
                        //Under Deposit option
                        case "1":

                            System.out.print("Enter Deposit Amount: $");
                            str = scr.nextLine();
                            amount = Double.parseDouble(str);
                            if (accountType.balance() + amount > ((Credit) accountType).get_Credit_Max()) {
                                System.out.println("Deposit DENIED. Account balance must remain below or euqal to your credit max of: " + ((Credit) accountType).get_Credit_Max());
                                break;
                            } else {
                                accountType.deposit(amount);
                                accountType.display_account_info();
                            }
                            break;
                        //Under widthdraw Option
                        case "2":
                            System.out.print("Enter Withdraw Amount: $");
                            str = scr.nextLine();
                            amount = Double.parseDouble(str);

                            if (accountType.balance() < 0) {
                                System.out.print("Your Accounts balance is in the Negatives You can not Withdraw at this Time\n");

                            } else {
                                accountType.withdraw(amount);
                                accountType.display_account_info();
                            }
                            BankLogger.withdraw_UwU(accountType, amount);
                            break;
                        //Under Payment Option
                        case "3":
                            System.out.print("Enter Payment Amount: $");
                            str = scr.nextLine();
                            amount = Double.parseDouble(str);
                            if (amount > accountType.balance() || amount > ((Credit) accountType).balance()) {
                                System.out.println("INSUFFICIENT FUNDS!!:");
                            } else {
                                System.out.println("What kind of account are we making a payment towards?");
                                account pay_acc = CustomerMap.find_specific_account(prompt_account_type());
                                if (pay_acc != null) {
                                    accountType.payment(pay_acc, amount);
                                    accountType.display_account_info();
                                    pay_acc.display_account_info();
                                }
                            }
                            break;
                        //Under Transfer Option
                        case "4":
                            System.out.println("Enter the amount you wish to Transfer:");
                            str = scr.nextLine();
                            amount = Double.parseDouble(str);

                            while (amount > accountType.balance() || amount > ((Credit) accountType).balance()) {
                                System.out.println("INSUFFICIENT FUNDS!! Try Entering a Different Amount");
                                str = scr.nextLine();
                                amount = Double.parseDouble(str);
                            }

                            System.out.println("Choose recipient AccountType to Transfer Funds.");
                            account transferAccount = CustomerMap.find_specific_account(prompt_account_type());
                            if (transferAccount != null) {
                                accountType.transfer(transferAccount, amount);
                                accountType.display_account_info();
                                transferAccount.display_account_info();
                            }
                            //projectTwo.BankLogger.add("Account Transfer: " + amount + " amount.");
                            break;
                        //Under view Balance Option
                        case "5":
                            accountType.display_account_info();
                            //projectTwo.BankLogger.add("Account inquire: " + accountType.balance() + " amount.");
                            break;
                        case "6":
                            System.out.println("Thank You for banking with UTEP\n Goodbye!");
                            break;
                    }
                }
            }
        } while (!str.equals("6"));

    }
}