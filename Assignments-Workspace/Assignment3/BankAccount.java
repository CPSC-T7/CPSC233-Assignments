
/**
 * Defines a bank account from which you can get the balance, set the overdraft
 * amount, deposit, and withdraw. Get customer information related to an account.
 * 
 * @author T06-1
 * @version 2.0.0
 */
public class BankAccount {

//INSTANCE VARIABLES
	
	private double balance = 0.0, overdraftAmount = 100.0;
	private Customer customer;


//CONSTRUCTORS
	
	/**
	 * <Default Constructor>
	 * Initialize with default values
	 *
	 */
	BankAccount(){}//End of default constructor

	/**
	 * Set customer information for default bank account
	 *
	 * @param newCustomer
	 *			Source of customer information
	 */
	BankAccount(Customer newCustomer){

		this.customer = newCustomer;

	}//End of constructor

	/**
	 * Set customer information and account balance for a bank account
	 *
	 * @param newCustomer
	 *			Source of customer information for bank account
	 * @param newBalance
	 *			Total amount in bank account
	 */
	BankAccount(Customer newCustomer, double newBalance){

		this.customer = new Customer(newCustomer);
		this.balance = newBalance;

	}//End of constructor


//METHODS

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

	/**
	 * Returns a copy of customer information
	 *
	 * @return copy A copy customer information
	 */
	public Customer getCustomer(){
		
		//keep customer information secure
		Customer copy = new Customer(customer);

		return copy;

	}//End of getCustomer method

}// End of BankAccount class
