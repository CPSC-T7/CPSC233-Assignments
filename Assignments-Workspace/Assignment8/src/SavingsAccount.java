
/**
 * This class represents a savings account; a bank account that is meant for,
 * well, saving.
 * 
 * @version 1.2
 * @author T07
 * @see BankAccount
 */
public class SavingsAccount extends BankAccount {
	
	/*
	 * 
	 * STATIC VARIABLES
	 * 
	 */
	
	private static double annualInterestRate; // Always positive. Stored as %, not decimal.
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Initialize an empty savings account. (Not actually needed to be defined)
	 */
	SavingsAccount() {
		
		// Empty
		
	}// End of default constructor
	
	/**
	 * Creates a savings account with a customer tied to it.
	 *
	 * @param customer
	 *            The customer to tie to the bank account.
	 */
	SavingsAccount(Customer customer) {
		
		super(customer);
		
	}// End of constructor
	
	/**
	 * Creates a savings account with a customer tied to it, and a starting balance.
	 *
	 * @param customer
	 *            The customer to tie to the bank account.
	 * @param balance
	 *            Initial amount in bank account.
	 */
	SavingsAccount(Customer customer, double balance) {
		
		super(customer, balance);
		
	}// End of constructor
	
	/**
	 * Creates a savings account with only a starting balance.
	 * 
	 * @param initialBalance
	 *            Initial amount in bank account.
	 */
	SavingsAccount(double initialBalance) {
		
		super(initialBalance);
		
	}// End of constructor
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Deposits the amount earned via interest from a monthly compound period. <br>
	 * As far as I can tell (Matt), this doesn't seem to be tested in
	 * SavingsAccountTest.java
	 */
	public void depositMonthlyInterest() {
		
		// Calculate the multiplier to get the earned amount:
		// First divide by 12 to get a _monthly_ interest rate,
		// then divide by 100 to get to a 0 - 1 range.
		double monthlyMultiplier = SavingsAccount.annualInterestRate / 12.0 / 100.0;
		
		// Deposit the earned interest
		this.deposit(this.getBalance() * monthlyMultiplier);
		
	}// End of depositMonthlyInterest
	
	/**
	 * Returns the annual interest rate.
	 * 
	 * @return The annual interest rate.
	 */
	public double getAnnualInterestRate() {
		
		return SavingsAccount.annualInterestRate;
		
	}// End of getAnnualInterestRate
	
	/**
	 * Sets the annual interest rate.
	 * 
	 * @param annualInterestRate
	 *            The new annual interest rate.
	 */
	public static void setAnnualInterestRate(double annualInterestRate) {
		
		// If the interest rate is a reasonable number...
		if (annualInterestRate >= 0 && annualInterestRate != Double.POSITIVE_INFINITY) {
			SavingsAccount.annualInterestRate = annualInterestRate;
		}
		
	}// End of setAnnualInterestRate
	
	// JavaDoc set by parent.
	@Override
	protected double getMonthlyFeesAndInterest() {
		
		// Initialize the monthly fee to 0
		double fee = 0;
		
		// If the balance is less than 1000, change the fee to 5 dollars
		if (this.getBalance() < 1000) {
			
			fee = 5;
		}
		
		// Return the 1/12 of annual interest rate times the balance and subtract the
		// fees
		return (1.0 / 12.0) * (this.getAnnualInterestRate() / 100) * this.getBalance() - fee;
		
	}// End of getMonthlyFeesAndInterest method
	
}