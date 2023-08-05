package projectTwo;


import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.Test;

public class CreditTestSuite {
    Credit creditAccount;


    @Before
    public void setUp() {
        creditAccount = new Credit();
        creditAccount.set_FullName("Daniel", "A");
        creditAccount.set_Credit_Max(7985);
        creditAccount.set_Credit_Start_Bal(-786.93);
        creditAccount.set_Credit_Acc_Num(3078);

    }

    @Test
    public void depositTest() {
        creditAccount.deposit(6.93);
        assertEquals(-780.00, creditAccount.balance());
    }

    @Test
    public void depositMoreThanOwed() {
        creditAccount.deposit(788.00);

        assertEquals(-786.93, creditAccount.balance());
    }


    @Test
    public void withdrawTest() {
        creditAccount.withdraw(50);
        assertEquals(-836.93 ,creditAccount.balance());
    }

    @Test
    public void withdraw_thatSetsBalAboveMax() {
        creditAccount.withdraw(8000);
        //Here our expected balance should still be -10 since withdrawing 100 will set our balance to -110 and that is not OK
        assertEquals(-786.93, creditAccount.balance());
    }


    @Test
    public void creditPayment() {
        //creating recieving customer
        //Assumption we are making a payment to another credit account
        Credit recipient = new Credit();
        recipient.set_Credit_Start_Bal(0);

        creditAccount.payment(recipient, 10);

        assertEquals(0, recipient.balance());
        assertEquals(-786.93, creditAccount.balance());

    }

    /**
     * Test to ensure the displayed info is correct to the one given in our setUp method
     */
    @Test
    public void showAccountinfo() {
        creditAccount.display_account_info();
    }


}
