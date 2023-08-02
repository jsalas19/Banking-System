package projectTwo;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.*;

public class CustomerMap {
    protected static Map<List<String>, Customer> customerMap = new HashMap<>();
    private static CSV create_data;


    public static void read_data_UwU(String filePath) throws CsvValidationException, IOException {
        create_data = new CSV(filePath);
        create_customerMap(create_data.get_data());
        IdSets.create_sets(create_data.get_data());
    }
    /**
     * This Method creates a Hashmap of customers that we can use to find a customer.
     * @param data - Information that has been read from provided csv file.
     */
    public static void create_customerMap(Map<String, List<String>> data){
        List<String> stringList = new ArrayList<>();
        for (int k = 0; k < data.get("Identification Number").size(); k++){

            stringList.add(data.get("Identification Number").get(k));
            stringList.add(data.get("First Name").get(k));
            stringList.add(data.get("Last Name").get(k));

            Customer customer = new Customer();

            customer.set_IdNo(customer.set_to_int(data.get("Identification Number").get(k)));
            customer.set_First_name(data.get("First Name").get(k));
            customer.set_Last_name(data.get("Last Name").get(k));
            customer.set_Address(data.get("Address").get(k));
            customer.set_Date_of_birth(data.get("Date of Birth").get(k));
            customer.set_Phone_no(data.get("Phone Number").get(k));
            customer.set_Checking(data.get("Checking Account Number").get(k),data.get("Checking Starting Balance").get(k));
            customer.set_Savings(data.get("Savings Account Number").get(k),data.get("Savings Starting Balance").get(k));
            customer.set_Credit(data.get("Credit Account Number").get(k),data.get("Credit Max").get(k),data.get("Credit Starting Balance").get(k));

            customerMap.put(stringList, customer);
            stringList = new ArrayList<>();
        }
    }

    public static Map<List<String>, Customer> get_CustomerMap(){
        return customerMap;
    }

    public static void write_to_CSV() throws IOException {
        create_data.write_CSV_File(customerMap);
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
        int r = 0;

        if(t < 580){
            r = rdm.nextInt(100,699);
        }else if (t > 581 && t < 669){
            r = rdm.nextInt(700,4999);
        }else if (t > 670 && t < 739){
            r = rdm.nextInt(5000,7499);
        }else if (t > 740 && t < 799){
            r = rdm.nextInt(7500,15999);
        }else if (t > 800){
            r = rdm.nextInt(16000,25000);
        }

        int set_acc_num = IdSets.Acc_Id_list.get(0)+1;
        int set_id = IdSets.Id_list.get(0)+1;
        while(IdSets.Id_set.contains(set_id)){
            set_id += 1;
        }
        while(IdSets.Acc_Id_set.contains(set_acc_num)){
            set_acc_num += 1;
        }
        new_Customer.set_IdNo(set_id);
        new_Customer.set_Checking("1"+String.valueOf(set_acc_num),"0.00");
        new_Customer.set_Savings("2"+String.valueOf(set_acc_num), "0.00");
        new_Customer.set_Credit("3"+String.valueOf(set_acc_num), String.valueOf(r), "0.00");

        List<String> keys = new ArrayList<String>(Arrays.asList(String.valueOf(set_id), strName[0], strName[1]));

        data.put(keys, new_Customer);
    }
}
