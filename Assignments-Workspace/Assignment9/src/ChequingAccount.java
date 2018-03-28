
public class ChequingAccount extends BankAccount {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */

	private double overdraftFee;
	private double overdraftAmount = 100.0;

	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */

	/**
	 * Creates a new chequing account with an overdraft fee.
	 * 
	 * @param overdraftFee
	 *            The amount charged to the account every time its balance passes
	 *            below 0.
	 */
	public ChequingAccount(double overdraftFee) {

		super();
		this.setOverdraftFee(overdraftFee);

	} // End of constructor

	/**
	 * Creates a new chequing account with an customer, starting balance, and
	 * overdraft fee.
	 * 
	 * @param customer
	 *            The customer to tie to the bank account.
	 * @param startingBalance
	 *            Initial amount in bank account.
	 * @param overdraftFee
	 *            The amount charged to the account every time its balance passes
	 *            below 0.
	 */
	public ChequingAccount(Customer customer, double startingBalance, double overdraftFee) {

		super(customer, startingBalance);
		this.setOverdraftFee(overdraftFee);

	} // End of constructor
	
	/**
	 * Creates a new chequing account with an customer.
	 * 
	 * @param customer
	 *            The customer to tie to the bank account.
	 */
	public ChequingAccount(Customer customer) {

		super(customer);

	} // End of constructor

	/*
	 * 
	 * METHODS
	 * 
	 */

	/**
	 * Sets the overdraft limit for the account.
	 * 
	 * @param amount
	 *            The amount to set the overdraft limit to.
	 */
	public void setOverdraftAmount(double amount) {

		// If the amount set will not mean the current balance is overdrafted...
		// (I know that isn't a word, but you get what I mean :P)
		if (this.getBalance() + amount >= 0) {

			// Set the amount
			this.overdraftAmount = amount;

		}

	} // End of setOverdraftAmount method

	/**
	 * Returns the overdraft amount.
	 * 
	 * @return The overdraft amount.
	 */
	public double getOverdraftAmount() {
		
		return this.overdraftAmount;
		
	} // End of getOverdraftAmount method

	/**
	 * Returns the overdraft fee.
	 * 
	 * @return The overdraft fee.
	 */
	public double getOverdraftFee() {
		
		return overdraftFee;
		
	} // End of getOverdraftFee method

	/**
	 * Sets the overdraft fee.
	 * 
	 * @param overdraftFee The new overdraft fee.
	 */
	public void setOverdraftFee(double overdraftFee) {
		
		// Make sure the fee is not negative
		if (overdraftFee >= 0) {
			this.overdraftFee = overdraftFee;
		}
		
	} // End of setOverdraftFee method

	/**
	 * Withdraws a specified amount from the account balance, but will not withdraw
	 * anything if the resulting balance would be below the set overdraft amount.
	 * 
	 * @param amount
	 *            Total amount to withdraw.
	 * @return
	 * 			  Any error that occurred during withdraw
	 */
	@Override
	public String withdraw(double amount) {

		// If the amount is positive...
		if (amount > 0) {

			// Calculate what the balance would be after the withdrawal
			double oldBalance = this.getBalance();
			double newBalance = this.getBalance() - amount;

			// If the resulting balance will be above or equal to the overdraft amount...
			if (newBalance >= (0 - this.overdraftAmount)) {

				// Charge the overdraft fee if the balance drops below 0
				if (oldBalance >= 0 & newBalance < 0) {
					newBalance -= this.overdraftFee;
				}

				// Withdraw the amount
				this.setBalance(newBalance);
				System.out.println("Your new balance is $" + Double.toString(newBalance));
				return("");

			} else {

				// Don't withdraw the amount
				System.out.println("Can't withdraw: Overdraft detected");
				
				// Return an overdraft error
				return("Overdraft");
			}

		}else if (amount < 0) {	// If the amount is negative...
			
			// Return a range error
			return("Negative");
			
		}else {
			return("");
		}

	} // End of withdraw method

	// JavaDoc set by parent.
	@Override
	protected double getMonthlyFeesAndInterest() {
		
		//If the balance is positive return 0
		if(this.getBalance() >= 0) {
			
			return 0;
			
		}
		
		//Otherwise return 20% of the negative balance
		else {
			
			return (0.20*this.getBalance());
			
		}
		
	}// End of monthEndUpdate method

}
