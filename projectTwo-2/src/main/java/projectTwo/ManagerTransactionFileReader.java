package projectTwo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerTransactionFileReader {
    private Map<String, List<String>> data = new HashMap<>();

    public void read_MT_File(String filePath) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] a;
        List<String> stringList = new ArrayList<>();
        String [] temp = reader.readNext();

        for (String j : temp){
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
}
