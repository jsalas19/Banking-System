package projectTwo;

import org.junit.Before;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import static org.testng.Assert.*;


public class CheckingTestSuite {
    Checking checkingAccount;

    @Before
    public void setUp(){
        checkingAccount = new Checking();
        checkingAccount.set_Checking_start_bal(100.00);
        checkingAccount.set_Checking_account_no(8008135);
        checkingAccount.set_FullName("Sponge", "Bobby");

    }
    //deposit test
    @Test
    public void test1() {
        checkingAccount.deposit(100);
        assertEquals(200.00, checkingAccount.balance());
    }


    //withdraw
    @Test
    public void test2() {
        checkingAccount.withdraw(50);
        assertEquals(50.00 ,checkingAccount.balance());
    }

    @Test
    public void withdraw_moreThanPossible() {
        checkingAccount.withdraw(101.00);
        //Here our expected balance should still be -10 since withdrawing 100 will set our balance to -110 and that is not OK
        assertEquals(100.00, checkingAccount.balance());

    }

    @Test
    public void makingAPayment(){
        Credit recipient = new Credit();
        recipient.set_Credit_Start_Bal(0);

        checkingAccount.payment(recipient, 10);

        assertEquals(0, recipient.balance());
        assertEquals(100.00, checkingAccount.balance());


    }

    @Test
    public void makeAPaymentToself(){
        Checking recipient = new Checking();
        recipient.set_Checking_account_no(8008135);


        assertFalse(checkingAccount.payment(recipient,20));

    }


    @Test
    public void makeTransfer(){
        Credit recipient = new Credit();
        recipient.set_Credit_Acc_Num(20999);
        recipient.set_Credit_Start_Bal(-23);
        assertFalse(checkingAccount.transfer(recipient, 23));


    }

    /**
     * Test to ensure the displayed info is correct to the one given in our setUp method
     */
    @Test
    public void showAccountinfo() {

        checkingAccount.display_account_info();
    }




}