
/**
 * This class defines a bank account from which you can get the balance, set the
 * overdraft amount, deposit, and withdraw, as well as tie a Customer object to
 * it.
 * 
 * @version 2.3.0
 * @author T07
 */
public class BankAccount {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */

	private double balance = 0.0, overdraftAmount = 100.0;
	private Customer customer = new Customer();

	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */

	/**
	 * Initialize an empty bank account. (Not actually needed to be defined)
	 */
	BankAccount() {

		// Empty

	}// End of default constructor

	/**
	 * Creates a bank account with a customer tied to it.
	 *
	 * @param customer
	 *            The customer to tie to the bank account.
	 */
	BankAccount(Customer customer) {

		this.customer = new Customer(customer);

	}// End of constructor

	/**
	 * Creates a bank account with a customer tied to it, and a starting balance.
	 *
	 * @param customer
	 *            The customer to tie to the bank account.
	 * @param balance
	 *            Initial amount in bank account.
	 */
	BankAccount(Customer customer, double balance) {

		this.customer = new Customer(customer);
		this.balance = balance;

	}// End of constructor

	/**
	 * Creates a bank account with only a starting balance.
	 * 
	 * @param initialBalance
	 *            Initial amount in bank account.
	 */
	BankAccount(double initialBalance) {

		this.balance = initialBalance;

	}// End of constructor

	/*
	 * 
	 * METHODS
	 * 
	 */

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
			double balance = this.balance - amount;

			// If the resulting balance will be above or equal to the set overdraft limit...
			if (balance >= (0 - this.overdraftAmount)) {

				// Withdraw the amount
				this.balance = balance;
				System.out.println("Your new balance is $" + Double.toString(balance));

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

		// If the amount is at least 0 and isn't smaller than a negative balance...
		if (amount >= 0 && this.balance + amount >= 0) {

			// Set the amount
			this.overdraftAmount = amount;

		}

	}// End of setOverdraftAmount method

	/**
	 * Returns a copy of customer information.
	 *
	 * @return A copy customer information.
	 */
	public Customer getCustomer() {

		// Keep customer information secure by returning a copy of the private customer
		// object.
		Customer copy = new Customer(customer);
		return copy;

	}// End of getCustomer method

	/**
	 * Ties a customer object to the bank account.
	 * 
	 * @param customer
	 *            The customer object to tie to the bank account.
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}// End of BankAccount class
