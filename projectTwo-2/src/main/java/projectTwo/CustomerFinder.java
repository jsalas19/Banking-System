package projectTwo;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CustomerFinder {
    public static Customer find_account(String str1, String str2, String str3){
        for (List<String> j : CustomerMap.get_CustomerMap().keySet()){
            //System.out.println(j);
            if (j.contains(str1) && j.contains(str2) && j.contains(str3)){
                return CustomerMap.get_CustomerMap().get(j);
            }
        }
        return null;
    }

    public static Customer find_account_ID(String str1){
        for (List<String> j : CustomerMap.get_CustomerMap().keySet()){
            if (j.contains(str1)){
                return CustomerMap.get_CustomerMap().get(j);
            }
        }
        return null;
    }

    public static account find_specific_account(String i){
        Scanner scr = new Scanner(System.in);
        //System.out.println("What is the projectTwo.account number? \nEnter Account Number: ");
        if (!Objects.equals(i, "4")){
            System.out.println("What is the projectTwo.account number? \nEnter Account Number: ");
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
}
