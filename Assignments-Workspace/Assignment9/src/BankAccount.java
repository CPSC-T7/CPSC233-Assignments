
/**
 * This class defines a bank account from which you can get the balance,
 * deposit, and withdraw, as well as tie a Customer object to it.
 * 
 * @version 2.4
 * @author T07
 */
abstract class BankAccount {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private double		balance		= 0.0;
	private Customer	customer	= new Customer();
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Initialize an empty bank account.
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
	 * ABSTRACT METHODS
	 * 
	 */
	
	/**
	 * Returns the monthly change in balance. Negative if the balance should go down
	 * and positive if the balance should increase.
	 * 
	 * @return the amount that the balance should change by
	 */
	protected abstract double getMonthlyFeesAndInterest();
	
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
	 * @return
	 * 			  Any error that occurred during withdraw.        
	 */
	public String deposit(double amount) {
		
		// If the amount is a reasonable number...
		if (amount > 0 && amount != Double.POSITIVE_INFINITY) {
			
			// Deposit the amount into the account
			this.balance += amount;
			System.out.println("Your new balance is $" + Double.toString(this.balance));
			return("");
			
		}else if (amount < 0){		// If the amount is below 0...
			
			// Return a range error
			return("Negative");
			
		}else if(amount == Double.POSITIVE_INFINITY) {  // If the amount is infinite...
			
			// Return a range error
			return("Infinite");
			
		}else {
			return("");
		}
		
	}// End of deposit method
	
	/**
	 * Withdraws a specified amount from the account balance, but will not withdraw
	 * anything if the resulting balance would be below 0.
	 * 
	 * @param amount
	 *            Total amount to withdraw.
	 * @return
	 * 			  Any error that occurred during withdraw.
	 */
	public String withdraw(double amount) {
		
		// If the amount is positive...
		if (amount > 0) {
			
			// Calculate what the balance would be after the withdrawal
			double balance = this.balance - amount;
			
			// If the resulting balance will be above or equal to 0...
			if (balance >= 0) {
				
				// Withdraw the amount
				this.balance = balance;
				System.out.println("Your new balance is $" + Double.toString(balance));
				return("");
				
			} else {
				
				// Don't withdraw the amount
				System.out.println("Can't withdraw: Overdraft detected");
				
				// Return an overdraft error
				return("Overdraft");
				
			}
			
		}else if(amount < 0) {		// If the amount is negative...
			
			// Return a range error
			return("Negative");
			
		}else {
			
			return("");
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
		
	}// End of setCustomer method
	
	/**
	 * Transfers a set amount from this account to another account.
	 * 
	 * @param amount
	 *            The amount to transfer.
	 * @param receivingAccount
	 *            The account to transfer the money to.
	 */
	public void transfer(double amount, BankAccount receivingAccount) {
		
		// Record the existing balance
		double oldBalance = this.balance;
		
		// Try to withdraw the amount
		withdraw(amount);
		
		// If the amount was withdrawn...
		if (this.balance != oldBalance) {
			
			// Transfer the money
			receivingAccount.deposit(amount);
			
		}
		
	}// End of transfer method
	
	/**
	 * Updates the balance of the account with any increases due to interest or
	 * decreases due to fees.
	 */
	public void monthEndUpdate() {
		
		this.setBalance(this.getBalance() + this.getMonthlyFeesAndInterest());
		
	}// End of monthEndUpdate method
	
	/**
	 * Sets the balance of the account.
	 * 
	 * @param amount
	 *            The new account balance.
	 */
	protected void setBalance(double amount) {
		
		this.balance = amount;
		
	}// End of setBalance method
	
}// End of BankAccount class