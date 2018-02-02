
/**
 * This class defines a bank account from which you can get the balance, set the
 * overdraft amount, deposit, and withdraw. You can also tie a customer to an
 * account.
 * 
 * @author T7
 * @version 1.3.1
 */
public class BankAccount {

	// INSTANCE VARIABLES

	private double balance = 0.0, overdraftAmount = 100.0;
	private Customer customer;

	// CONSTRUCTORS

	/**
	 * Creates an empty bank account.
	 */
	public BankAccount() {
		
		// Empty

	}

	/**
	 * Creates a bank account with a customer and a balance.
	 * 
	 * @param customer The customer tied to the bank account.
	 * @param balance The balance to store in the bank account.
	 */
	public BankAccount(Customer customer, double balance) {

		this.setCustomer(customer);
		this.balance = balance;

	}

	// METHODS

	/**
	 * Deposits a specified amount into the account balance.
	 * 
	 * @param amount
	 *            Total amount to deposit.
	 */
	public void deposit(double amount) {

		// If the amount is positive...
		if (amount > 0) {

			// Deposit the amount into the account
			this.balance += amount;
			System.out.println("Your new balance is $" + Double.toString(this.balance));

		}

	}// End of deposit method

	/**
	 * Withdraws a specified amount from the account balance, but will not withdraw
	 * anything if the resulting balance would be below the set overdraft amount.
	 * 
	 * @param amount
	 *            Total amount to withdraw.
	 */
	public void withdraw(double amount) {

		// If the amount is positive...
		if (amount > 0) {

			// Calculate what the balance would be after the withdrawal
			double newBalance = this.balance - amount;

			// If the resulting balance will be above or equal to the set overdraft limit...
			if (newBalance >= (0 - this.overdraftAmount)) {

				// Withdraw the amount
				this.balance = newBalance;
				System.out.println("Your new balance is $" + Double.toString(newBalance));

			} else {

				// Don't withdraw the amount
				System.out.println("Can't withdraw: Overdraft detected");

			}

		}

	}// End of withdraw method

	/**
	 * Returns the value of the account balance.
	 * 
	 * @return balance Total balance of the account.
	 */
	public double getBalance() {

		// Return the balance
		return (this.balance);

	}// End of getBalance method

	/**
	 * Sets the overdraft limit for the account.
	 * 
	 * @param amount
	 *            The amount to set the overdraft limit to.
	 */
	public void setOverdraftAmount(double amount) {

		// If the amount is at least 0...
		if (amount >= 0) {

			// Set the amount
			this.overdraftAmount = amount;

		}

	}// End of setOverdraftAmount method

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}// End of BankAccount class
