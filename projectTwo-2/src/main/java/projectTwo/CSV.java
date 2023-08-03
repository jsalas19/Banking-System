package projectTwo;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSV {
    private Map<String, List<String>> data = new HashMap<>();

    CSV(){}
    CSV(String filePath) throws IOException, CsvValidationException {
            read_CSV_File(filePath);
    }

    public void read_CSV_File(String filePath) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] a;
        //List<String> stringList = new ArrayList<>();
        String [] temp = reader.readNext();

        for (String j : temp){
           // System.out.println(j);
            data.put(j, new ArrayList<>());
        }
        int k = 0;
        while ((a = reader.readNext()) != null) {
            for (String i : a) {
                data.get(temp[k]).add(i);
                k++;
            }
            k = 0;
        }

    }

    /**
     * @param customer_Map
     * @throws IOException
     *
     * Updates CSV with new information
     */

    public void write_CSV_File(Map<List<String>, Customer> customer_Map) throws IOException {
        String log = "testCSV.csv";
        String[] strings = {"Identification Number","First Name","Last Name","Date of Birth","Address","Phone Number","Checking Account Number","Checking Balance","Savings Account Number","Savings Balance","Credit Account Number","Credit Max","Credit Balance"};
        List<String[]> header = new ArrayList<>();
        header.add(strings);
        CSVWriter csv_Writer = new CSVWriter(new FileWriter(log));
        for (List<String> s : customer_Map.keySet()){
            header.add(customer_to_List(customer_Map.get(s)));
        }
        for (String[] strArray : header){
            csv_Writer.writeAll(Collections.singleton(strArray));
        }
        csv_Writer.close();
    }

    public String[] customer_to_List(Customer c){
        String[] stringList = new String[13];
        stringList[0] = (Integer.toString(c.get_Id_No()));
        stringList[1] = (c.get_First_name());
        stringList[2] = (c.get_Last_name());
        stringList[3] = (c.get_Date_of_birth());
        stringList[4] = (c.get_Address());
        stringList[5] = (c.get_Phone_no());
        stringList[6] = (Integer.toString(c.get_Checking().get_Account_Num()));
        stringList[7] = (Double.toString(c.get_Checking().balance()));
        stringList[8] = (Integer.toString(c.get_Savings().get_Account_Num()));
        stringList[9] = (Double.toString(c.get_Savings().balance()));
        stringList[10] = (Integer.toString(c.get_Credit().get_Account_Num()));
        stringList[11] = (Double.toString(c.get_Credit().get_Credit_Max()));
        stringList[12] = (Double.toString(c.get_Credit().balance()));

        return stringList;
    }

    public Map<String, List<String>> get_data(){
        return this.data;
    }
}
