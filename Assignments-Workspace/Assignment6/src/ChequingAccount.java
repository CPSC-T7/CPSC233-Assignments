
public class ChequingAccount extends BankAccount{
	
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
	
	public ChequingAccount(double overdraftFee) {
		
		super();
		this.setOverdraftFee(overdraftFee);
		
	}
	
	public ChequingAccount(Customer customer, double startingBalance, double overdraftFee) {
		
		super(customer, startingBalance);
		this.setOverdraftFee(overdraftFee);
		
	}
	
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
		if (this.balance + amount >= 0) {

			// Set the amount
			this.overdraftAmount = amount;

		}

	}// End of setOverdraftAmount method
	
	public double getOverdraftAmount() {
		return this.overdraftAmount;
	}

	public double getOverdraftFee() {
		return overdraftFee;
	}

	public void setOverdraftFee(double overdraftFee) {
		this.overdraftFee = overdraftFee;
	}
	
	@Override
	public void withdraw(double amount) {

		// If the amount is positive...
		if (amount > 0) {

			// Calculate what the balance would be after the withdrawal
			double oldBalance = this.balance;
			double newBalance = this.balance - amount;

			// If the resulting balance will be above or equal to the overdraft amount...
			if (newBalance >= (0 - this.overdraftAmount)) {
				
				if (oldBalance >= 0 & newBalance < 0) {
					newBalance -= this.overdraftFee;
				}

				// Withdraw the amount
				this.balance = newBalance;
				System.out.println("Your new balance is $" + Double.toString(newBalance));

			} else {

				// Don't withdraw the amount
				System.out.println("Can't withdraw: Overdraft detected");

			}

		}

	}// End of withdraw method

}
