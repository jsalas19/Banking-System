package projectTwo;

import java.util.Objects;
import java.util.Scanner;

public class Prompts {
    public static account return_selected_accountType(Customer customer, String i){
        //Enter in Here if in Prompt Account your did not choose 4
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
                    break;
            }
        }
        return null;
    }

    public static String prompt_account_type() {
        Scanner nscnr = new Scanner(System.in);
        System.out.println("What is the account type? \n\t1. Checkings \n\t2. Savings\n\t3. Credit \n\t4. Exit");
        String str = nscnr.nextLine();
        return str;
    }
}
