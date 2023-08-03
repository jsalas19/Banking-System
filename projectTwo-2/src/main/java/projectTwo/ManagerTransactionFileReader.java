package projectTwo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.collections.map.TransformedMap;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerTransactionFileReader {
    private static Map<String, List<String>> TransCSV = new HashMap<>();

    public static void read_MT_File(String filePath) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] a;
        List<String> stringList = new ArrayList<>();
        String[] temp = reader.readNext();
        //j is the header
        for (String j : temp) {
            System.out.println(j);
            TransCSV.put(j, new ArrayList<>());
        }
        int k = 0;
        //transaction info
        while ((a = reader.readNext()) != null) {
            for (String i : a) {
                TransCSV.get(temp[k]).add(i);
                k++;
            }
            k = 0;
        }
        action_enabler();
    }

    public static void action_enabler(){
        for (int i = 0; i < TransCSV.size(); i++){
            action_switch(TransCSV.get("Action").get(i), i);
        }
    }

    public static void action_switch(String action, int i){
        try{
            switch (action){
                case "pays":
                    account fromAcct = customer_account_switch(CustomerFinder.find_account(TransCSV.get("From First Name").get(i),TransCSV.get("From Last Name").get(i)), TransCSV.get("From Where").get(i));
                    account toAcct = customer_account_switch(CustomerFinder.find_account(TransCSV.get("To First Name").get(i), TransCSV.get("To Last Name").get(i)), TransCSV.get("To Where").get(i));
                    assert fromAcct != null;
                    fromAcct.payment(toAcct, Integer.parseInt(TransCSV.get("Action Amount").get(i)));
                case "withdraws":
                    account with = customer_account_switch(CustomerFinder.find_account(TransCSV.get("From First Name").get(i),TransCSV.get("From Last Name").get(i)), TransCSV.get("From Where").get(i));
                    assert with != null;
                    with.withdraw(Integer.parseInt(TransCSV.get("Action Amount").get(i)));
                case "deposits":
                    account depo = customer_account_switch(CustomerFinder.find_account(TransCSV.get("From First Name").get(i),TransCSV.get("From Last Name").get(i)), TransCSV.get("From Where").get(i));
                    assert depo != null;
                    depo.deposit(Integer.parseInt(TransCSV.get("Action Amount").get(i)));
                case "transfers":
                    account fromSameAcct = customer_account_switch(CustomerFinder.find_account(TransCSV.get("From First Name").get(i),TransCSV.get("From Last Name").get(i)), TransCSV.get("From Where").get(i));
                    account toSameAcct = customer_account_switch(CustomerFinder.find_account(TransCSV.get("To First Name").get(i), TransCSV.get("To Last Name").get(i)), TransCSV.get("To Where").get(i));
                    assert fromSameAcct != null;
                    fromSameAcct.transfer(toSameAcct, Integer.parseInt(TransCSV.get("Action Amount").get(i)));
                case "inquires":
                    account inq = customer_account_switch(CustomerFinder.find_account(TransCSV.get("From First Name").get(i),TransCSV.get("From Last Name").get(i)), TransCSV.get("From Where").get(i));
                    assert inq != null;
                    inq.display_account_info();
            }
        }catch (NullPointerException e){
            System.out.println("This operation did not work");
        }
    }

    public static account customer_account_switch(Customer customer, String type){
        switch (type){
            case "Savings":
                return Prompts.return_selected_accountType(customer, "2");
            case "Checking":
                return Prompts.return_selected_accountType(customer, "1");
            case "Credit":
                return Prompts.return_selected_accountType(customer, "3");
        }
        return null;
    }


}



