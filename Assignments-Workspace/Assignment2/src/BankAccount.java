/**
 * 
 * @author wylee
 *
 */
public class BankAccount {

	private double balance = 100;
	private double overdraftAmount = 100;
	
	/**
	 * Deposit money into the account.
	 *  
	 * @param amount Total amount to deposit
	 */
	public void deposit(double amount) {
		
		if (amount > 0) {
			balance += amount;
			System.out.println("Your new balance is $" + Double.toString(balance));
		}
		
	}//end of deposit
	
	/**
	 * Withdraw money from the account, as long as the amount being taken does not 
	 * bring the account balance below the overdraft limit.
	 *  
	 * @param amount Total amount to withdraw
	 */
	public void withdraw(double amount) {
		
		if (amount > 0) {
			double newBalance = balance - amount;
			
			if (newBalance >= (0 - overdraftAmount)) {
				balance = newBalance;
				System.out.println("Your new balance is $" + Double.toString(newBalance));
			}
			else {
				System.out.println("Can't withdraw: Overdraft detected");
			}
		}
		
	}//end of withdraw
	
	/**
	 * Returns the value of the balance variable.
	 * 
	 * @return balance Total balance of the account.
	 */
	public double getBalance() {
		
		return(balance);
		
	}//end of getBalance
	
	public void setOverdraftAmount(double amount) {
		
		if (amount > 0) {
			overdraftAmount = amount;
		}
		
		
	}//end of setOverdraftAmount
	
}//end of BankAccount class
