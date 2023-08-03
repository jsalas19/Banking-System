package projectTwo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingTest {
    Checking checkingAccount;

    @BeforeEach
    void setUp(){
        checkingAccount = new Checking();
        checkingAccount.set_Checking_start_bal(100.00);
        checkingAccount.set_Checking_account_no(8008135);
        checkingAccount.set_FullName("Sponge", "Bobby");

    }

    void depositTest() {
        checkingAccount.deposit(100);
        assertEquals(200.00, checkingAccount.balance());
    }



    @Test
    void withdrawTest() {
        checkingAccount.withdraw(50);
        assertEquals(50.00 ,checkingAccount.balance());
    }

    @Test
    void withdraw_moreThanPossible() {
        checkingAccount.withdraw(101.00);
        //Here our expected balance should still be -10 since withdrawing 100 will set our balance to -110 and that is not OK
        assertEquals(100.00, checkingAccount.balance());

    }

    @Test
    void makingAPayment(){
        Credit recipient = new Credit();
        recipient.set_Credit_Start_Bal(0);

        checkingAccount.payment(recipient, 10);

        assertEquals(0, recipient.balance());
        assertEquals(100.00, checkingAccount.balance());


    }

    @Test
    void makeAPaymentToself(){
        Checking recipient = new Checking();
        recipient.set_Checking_account_no(8008135);


        assertFalse(checkingAccount.payment(recipient,20));

    }


    @Test
    void makeTransfer(){
        Credit recipient = new Credit();
        recipient.set_Credit_Acc_Num(20999);
        recipient.set_Credit_Start_Bal(-23);
        assertFalse(checkingAccount.transfer(recipient, 23));


    }

    /**
     * Test to ensure the displayed info is correct to the one given in our setUp method
     */
    @Test
    void showAccountinfo() {

        checkingAccount.display_account_info();
    }


}





