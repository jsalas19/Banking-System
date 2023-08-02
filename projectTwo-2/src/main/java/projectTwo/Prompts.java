package projectTwo;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Prompts {
    public static account return_selected_accountType(Customer customer, String i){
        if(!Objects.equals(i,"4")) {
            switch (i) {
                case "1":
                    return customer.get_Checking();
                case "2":
                    return customer.get_Savings();
                case "3":
                    return customer.get_Credit();
                case "4":
                    System.out.print("Terminating Session");
                    break;
                default:
                    System.out.println("Please enter a valid number.");
                    break;
            }
        }
        return null;
    }

    public static String prompt_account_type() {
        try {
            Scanner nscnr = new Scanner(System.in);
            System.out.println("What type of account do you want to access? \n\t1. Checkings \n\t2. Savings\n\t3. Credit \n\t4. Exit");
            return nscnr.nextLine();
        }catch (NoSuchElementException e){
            return "4";
        }
    }

    public static String make_selection_prompt(){
        Scanner scr = new Scanner(System.in);
            System.out.println("Make A selection:");
            System.out.println("1. Deposit \n2. Withdraw \n3. Payment \n4. Transfer \n5. View Balance \n6. Exit");
            return scr.nextLine();
    }

    public static Double enter_strInt_prompt(String prompt){
        Scanner scnr = new Scanner(System.in);
        System.out.println(prompt);
        return Double.parseDouble(scnr.nextLine());
    }

    public static String make_selection_prompt_credit(){
        Scanner scr = new Scanner(System.in);
        System.out.println("Make A selection:");
        System.out.println("1. Pay Balance \n2. Withdraw \n3. Payment \n4. Transfer \n5. View Balance \n6. Exit");
        return scr.nextLine();
    }
}
