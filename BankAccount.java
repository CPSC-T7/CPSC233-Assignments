/**
 *Cassie Platel
 *January 27, 2018
 *version 1
 *
 *Manage bank account transactions, check for valid withdrawals, and update allowable overdraft.
 *
 *@param balance
 *@param overdraftAmount
 */

public class BankAccount {

	double balance = 0.00;
	double overdraftAmount = 100.00;


	/**
	 *deposit(inAmount) returns void
	 *Update balance with amount in
	 *
	 *@param inAmount
	 */
	
	public void deposit(double inAmount) {

		this.balance += inAmount;
	} // End of deposit


	/**
	 *withdraw(outAmount) returns void
	 *Check if overdraft exceeded and update balance with amount out if appropriate
	 *
	 *@param outAmount
	 */

	public void withdraw(double outAmount) {

		if ((this.balance - outAmount) < overdraftAmount) {

			System.out.println("Overdraft exceeded, transaction aborted.");
		}
		else {
			this.balance -= outAmount;
		}
	} // End of withdraw


	/**
	 *balance() returns double
	 *Get current balance
	 */

	public double balance() {

		return this.balance;
	} // End of balance


	/**
	 *overdraftAmount(value) returns void
	 *Set allowable overdraft amount
	 *
	 *@param value
	 */

	public void overdraftAmount(double value) {

		this.overdraftAmount = value;
	} // End of overdraftAmount



	/** This is testing code, comment out before finalizing version */
	public static void main(String[] args) {

	BankAccount b1 = new BankAccount();
	b1.deposit(100.0);

	double b1Value = b1.balance();
	System.out.println(b1Value);

	BankAccount b2 = new BankAccount();
	b2.deposit(200.0);

	double b2Value = b2.balance();
	System.out.println(b2Value);

	BankAccount b3 = b2;
	b3.withdraw(400.0);

	double b3Value = b3.balance();
	System.out.println(b3Value);


	} // end of main test

}