package projectTwo;

import projectTwo.Checking;
import projectTwo.Credit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruth Cazares Bravo
 */
public class Customer extends Person {
    private int Id_No;
    private Checking checking = new Checking();
    private Savings savings = new Savings();
    private Credit credit = new Credit();
    //private projectTwo.Person person = new projectTwo.Customer();

    Customer(){}


    public void set_IdNo(int Id_No){
        this.Id_No = Id_No;
    }

    public int get_Id_No(){
        return Id_No;
    }
    
    public void set_Checking(String ch_account_number, String ch_balance){
        this.checking.set_Checking_account_no(set_to_int(ch_account_number));
        this.checking.set_Checking_start_bal(set_to_double(ch_balance));
        this.checking.set_FullName(get_First_name(), get_Last_name());
    }

    public void set_Savings(String s_account_number, String s_balance){
        this.savings.set_Saving_Num(set_to_int(s_account_number));
        this.savings.set_Saving_Start_Bal(set_to_double(s_balance));
        this.savings.set_FullName(get_First_name(), get_Last_name());
    }
    
    public void set_Credit(String cr_account_number, String cr_max, String cr_balance){
        this.credit.set_Credit_Acc_Num(set_to_int(cr_account_number));
        this.credit.set_Credit_Max(set_to_int(cr_max));
        this.credit.set_Credit_Start_Bal(set_to_double(cr_balance));
        this.credit.set_FullName(get_First_name(), get_Last_name());

    }

    public Checking get_Checking(){
        return this.checking;
    }

    public Savings get_Savings(){
        return this.savings;
    }

    public Credit get_Credit(){
        return this.credit;
    }

    public int set_to_int(String s){
        return (int) Double.parseDouble(s);
    }

    public Double set_to_double(String s){
        return Double.parseDouble(s);
    }

    public List<String> get_banking_information(){
        List<String> bank_info = new ArrayList<>();
        bank_info.add("\nprojectOne.projectTwo.Checking Account: " + String.valueOf(this.checking.get_Account_Num()));
        bank_info.add("\tbalance: $" + String.valueOf(this.checking.balance()));
        bank_info.add("\nprojectOne.projectTwo.Savings Account: " + String.valueOf(this.savings.get_Account_Num()));
        bank_info.add("\tbalance: $" + String.valueOf(this.savings.balance()));
        bank_info.add("\nprojectOne.projectTwo.Credit Account: " + String.valueOf(this.credit.get_Account_Num()));
        bank_info.add("\tcredit max: $" + String.valueOf(this.credit.get_Credit_Max()));
        bank_info.add("\tbalance: $" + String.valueOf(this.credit.balance()));

        return bank_info;
    }

} //end of the projectOne.projectTwo.Customer class