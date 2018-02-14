
/**
 * This class represents a savings account; a bank account that is meant for,
 * well, saving.
 * 
 * @version 1.0.1
 * @author T07
 * @see BankAccount
 */
public class SavingsAccount extends BankAccount {

	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */

	private double annualInterestRate; // Always positive. Stored as %, not decimal.

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

	/**
	 * Creates a savings account with a staring balance and an annual interest rate.
	 * 
	 * @param balance
	 *            Initial amount in bank account.
	 * @param annualInterestRate
	 *            The rate (In percentage, not decimal) that is added to the savings
	 *            account yearly.
	 */
	public SavingsAccount(double balance, double annualInterestRate) {

		super(balance);
		this.setAnnualInterestRate(annualInterestRate);

	}// End of constructor

	/*
	 * 
	 * METHODS
	 * 
	 */

	/**
	 * Deposits the ammount earned via interest from a monthly compound period. <br>
	 * As far as I can tell (Matt), this doesn't seem to be tested in
	 * SavingsAccountTest.java
	 */
	public void depositMonthlyInterest() {

		// Calculate the multiplier to get the earned amount:
		// First divide by 12 to get a _monthly_ interest rate,
		// then divide by 100 to get to a 0 - 1 range.
		double monthlyMultiplier = this.annualInterestRate / 12.0 / 100.0;

		// Deposit the earned interest
		this.deposit(this.getBalance() * monthlyMultiplier);

	}// End of depositMonthlyInterest

	/**
	 * Returns the annual interest rate.
	 * 
	 * @return The annual interest rate.
	 */
	public double getAnnualInterestRate() {

		return annualInterestRate;

	}// End of getAnnualInterestRate

	/**
	 * Sets the annual interest rate.
	 * 
	 * @param annualInterestRate The new annual interest rate.
	 */
	public void setAnnualInterestRate(double annualInterestRate) {

		// Make sure the rate is positive before setting th rate
		if (annualInterestRate >= 0) {
			this.annualInterestRate = annualInterestRate;
		}

	}// End of setAnnualInterestRate

}
