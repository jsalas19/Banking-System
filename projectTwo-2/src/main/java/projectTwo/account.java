package projectTwo;

/** @author deolivas4 , Josh salas, Ruth Cazares
 *This is an interface
 * The blueprint of Checkings, projectTwo.Savings and projectTwo.Credit Account
 */
interface account{



    /**
     * @param amount
     * This method allows deposits as method implies
     * every projectTwo.account can deposit
     */
    public boolean deposit(double amount) ;

    /**
     * @param amount
     * This method allows to withdraw as method implies
     * every projectTwo.account can withdraw
     */

    public void withdraw(double amount);


    /**
     * @param amount
     * This method allows Payments as method implies
     * every projectTwo.account can withdraw
     */
    public void payment(account recipient ,double amount);

    /**
     * @param amount
     * method allows transfers as method implies
     * every projectTwo.account can withdraw
     */

    public void transfer(account accountType, double amount);

    /**
     * @return a variable of type double which in our concrete methods in our other classes will be named "balance"
     */
    public double balance();

    /**
     * This method is to print a customers projectTwo.account balance and projectTwo.account number after each transaction
     * Our credit class takes this method a step foward and displays the coounts credit Max as well.
     */
    public void display_account_info();

    public String get_FullName();

    public int get_Account_Num();
}