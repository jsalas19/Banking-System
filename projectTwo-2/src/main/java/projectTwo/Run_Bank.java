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
     * @throws CsvValidationException - required exception throw to ensure the file is a CSV file.
     * This is our main method that drives the functionality of the system.
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
                                    left = CustomerFinder.find_account(strList.get(0), strList.get(1), strList.get(2));
                                    if (left == null) {
                                        System.out.println("Sorry, account was not found \nEnter a proper name!");
                                    } else {
                                        account specifiedA = Prompts.return_selected_accountType(left, Prompts.prompt_account_type());
                                        if (specifiedA != null) {
                                            available_actions_specific(specifiedA);
                                        }
                                    }
                                }
                                UserTransactionLogger.closeLog();
                            }
                            break;

                        case "2":
                            account specified = CustomerFinder.find_specific_account(Prompts.prompt_account_type());
                            assert specified != null;
                            specified.display_account_info();
                            available_actions_specific(specified);
                            break;
                    }

                    //access = false;
                } else if (str.equals("1")) {
                    do {
                        System.out.println("1. Inquire Account By Customer Name.\n2. Inquire Account By Account Id\n3.End Session");
                        str = scnr.nextLine();
                        if (str.equals("1")) {
                            System.out.println("Whose account would you like to inquire about?: ");
                            str = scnr.nextLine();
                            List<String> strList = new ArrayList<>(List.of(str.split(" ")));
                            Customer left = CustomerFinder.find_account(strList.get(0), strList.get(1), strList.get(2));
                            if (left == null) {
                                System.out.print("Customer does not exist");
                            } else {
                                System.out.println("Displaying " + str + "'s Account Information");
                                display_account_information(left);
                            }

                        } else if (str.equals("2")) {
                            System.out.println("Enter Customers ID Number");
                            str = scnr.nextLine();
                            Customer customer = CustomerFinder.find_account_ID(str);
                            if (customer == null) {
                                System.out.println("Customer does not Exist");
                            } else {
                                display_account_information(customer);
                            }
                        }
                    } while (!str.equals("3"));

                    System.out.println("GoodBYE!");
                    //access = false;
                }
            } //end of the case(access).

            CustomerMap.write_to_CSV();


        } catch (IOException e) {
            System.out.println("OwO, something went wrong");
        }
        BankLogger.closeLog();

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

        //String str = null; not being used
        /*do while starts here, Allows the program to display our Menu until user decides to quit.*/
        //do {
            switch (accountType.getClass().getTypeName()) {
                //Inside Checking Account
                case "projectTwo.Checking" -> {
                    BankOperationCalls.switch_for_Sav_and_Check(accountType);
                }
                //Inside Savings Account
                case "projectTwo.Savings" -> {
                    BankOperationCalls.switch_for_Sav_and_Check(accountType);
                }
                    //Inside Credit Account
                case "projectTwo.Credit" -> {
                    BankOperationCalls.credit_switch_for_Sav_and_Check(accountType);
                }
            }
        //}while (!str.equals("6"));

    }
}