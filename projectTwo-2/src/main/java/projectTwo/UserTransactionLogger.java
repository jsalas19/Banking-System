package projectTwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class UserTransactionLogger {
    private static PrintWriter logFile;

    /**
     * Adds a System event to the Array List eventLog
     *
     * @param input The String of data to be added to eventLog
     */
    public static void add(String input) {
        logFile.write(input + "\n");
    }

    public static void openLog(String file) {

        try {
            logFile = new PrintWriter(new File(file + ".txt"));
            //logFile = new PrintWriter("/Users/mymac/Desktop/Advanced Obj. Oriented/projectTwo-2 4/projectTwo-2/src/main/java/projectTwo/UserTransactions.txt");
        } catch (FileNotFoundException e) {
            System.out.println("LogFile could not be created after several attempts... Exiting System.");
            System.exit(0);
        }
    }

    public static void closeLog() {
        logFile.close();
    }



}
