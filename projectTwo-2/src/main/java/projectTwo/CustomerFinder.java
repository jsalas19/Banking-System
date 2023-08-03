package projectTwo;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CustomerFinder {
    /**
     * Method that finds Customer object via first and last name.
     * @param str1 - parameter for first name.
     * @param str2 - parameter for last name.
     * @return Customer object
     */
    public static Customer find_account(String str1, String str2){
        for (List<String> j : CustomerMap.get_CustomerMap().keySet()){
            //System.out.println(j);
            if (j.contains(str1) && j.contains(str2)){
                return CustomerMap.get_CustomerMap().get(j);
            }
        }
        return null;
    }

    /**
     * Method that finds Customer object via identification number.
     * @param str1 - parameter for identification number.
     * @return Customer object
     */
    public static Customer find_account_ID(String str1){
        for (List<String> j : CustomerMap.get_CustomerMap().keySet()){
            if (j.contains(str1)){
                return CustomerMap.get_CustomerMap().get(j);
            }
        }
        return null;
    }

    /**
     * Method that returns the specific Checking, Savings, or Credit via specific account ID.
     * @param i - parameter for specific account identification number.
     * @return account object
     */
    public static account find_specific_account(String i){
        Scanner scr = new Scanner(System.in);
        //System.out.println("What is the account number? \nEnter Account Number: ");
        if (!Objects.equals(i, "4")){
            System.out.println("What is the account number? \nEnter Account Number: ");
            switch (i) {
                case "1":
                    i = scr.nextLine();
                    for (List<String> s : CustomerMap.get_CustomerMap().keySet()) {
                        //System.out.println(s);
                        if (String.valueOf(CustomerMap.get_CustomerMap().get(s).get_Checking().get_Account_Num()).equals(i)) {
                            return CustomerMap.get_CustomerMap().get(s).get_Checking();
                        }
                    }
                    break;
                case "2":
                    i = scr.nextLine();
                    for (List<String> s : CustomerMap.get_CustomerMap().keySet()) {
                        if (String.valueOf(CustomerMap.get_CustomerMap().get(s).get_Savings().get_Account_Num()).equals(i)) {
                            return CustomerMap.get_CustomerMap().get(s).get_Savings();
                        }
                    }
                    break;
                case "3":
                    i = scr.nextLine();
                    for (List<String> s : CustomerMap.get_CustomerMap().keySet()) {
                        if (String.valueOf(CustomerMap.get_CustomerMap().get(s).get_Credit().get_Account_Num()).equals(i)) {
                            return CustomerMap.get_CustomerMap().get(s).get_Credit();
                        }
                    }
                    break;
            }
        }
        return null;
    }

    public static Customer find_specific_account_Customer(String i){
        Scanner scr = new Scanner(System.in);
        //System.out.println("What is the account number? \nEnter Account Number: ");
        int j = Integer.parseInt(i)/1000;
        switch (j) {
            case 1:
                for (List<String> s : CustomerMap.get_CustomerMap().keySet()) {
                    //System.out.println(s);
                    if (String.valueOf(CustomerMap.get_CustomerMap().get(s).get_Checking().get_Account_Num()).equals(i)) {
                        return CustomerMap.get_CustomerMap().get(s);
                    }
                }
                break;
            case 2:
                for (List<String> s : CustomerMap.get_CustomerMap().keySet()) {
                    if (String.valueOf(CustomerMap.get_CustomerMap().get(s).get_Savings().get_Account_Num()).equals(i)) {
                        return CustomerMap.get_CustomerMap().get(s);
                    }
                }
                break;
            case 3:
                for (List<String> s : CustomerMap.get_CustomerMap().keySet()) {
                    if (String.valueOf(CustomerMap.get_CustomerMap().get(s).get_Credit().get_Account_Num()).equals(i)) {
                        return CustomerMap.get_CustomerMap().get(s);
                    }
                }
                break;
        }
        return null;
    }
}
