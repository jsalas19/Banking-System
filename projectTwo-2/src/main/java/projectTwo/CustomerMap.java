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
            //System.out.println(stringList);
            Customer customer = new Customer();

            customer.set_IdNo(customer.set_to_int(data.get("Identification Number").get(k)));
            customer.set_First_name(data.get("First Name").get(k));
            customer.set_Last_name(data.get("Last Name").get(k));
            customer.set_Address(data.get("Address").get(k));
            customer.set_Date_of_birth(data.get("Date of Birth").get(k));
            customer.set_Phone_no(data.get("Phone Number").get(k));
            customer.set_Checking(data.get("projectTwo.Checking Account Number").get(k),data.get("projectTwo.Checking Starting Balance").get(k));
            customer.set_Savings(data.get("projectTwo.Savings Account Number").get(k),data.get("projectTwo.Savings Starting Balance").get(k));
            customer.set_Credit(data.get("projectTwo.Credit Account Number").get(k),data.get("projectTwo.Credit Max").get(k),data.get("projectTwo.Credit Starting Balance").get(k));

            customerMap.put(stringList, customer);
            stringList = new ArrayList<>();
        }
    }

    public static account return_selected_accountType(Customer customer, String i){

        //Enter in Here if in Prompt Account your did not choose 4
        if(!Objects.equals(i,"4")) {


            //System.out.println("Enter the projectTwo.account holders First Name:  ");
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

    public static Map<List<String>, Customer> get_CustomerMap(){
        return customerMap;
    }

    public static void write_to_CSV() throws IOException {
        create_data.write_CSV_File(customerMap);
    }
}
