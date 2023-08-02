package projectTwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GenerateBankStatement {
    private static PrintWriter statementWriter;

    public void bank_statement(){

    }
    public void create_Bank_Statement(String filepath){
        File bs = new File(filepath + "_bank_statement.txt");
        try{
            if (!bs.isFile()){
                statementWriter = new PrintWriter(bs);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
