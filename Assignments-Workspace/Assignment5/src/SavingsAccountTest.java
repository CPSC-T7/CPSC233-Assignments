import static org.junit.Assert.*;

import org.junit.Test;

public class SavingsAccountTest {
	// test constructors
	@Test
	public void test_creation() {
		SavingsAccount s = new SavingsAccount();
		assertEquals("Expected initial balance to be 0.0", 0.0, s.getBalance(), 0.00001);
	}

	@Test
	public void test_constructorWithInterestAndBalance() {
		SavingsAccount s = new SavingsAccount(50.0, 1.5);
		assertEquals("Unexpected balance", 50.0, s.getBalance(), 0.00001);
		assertEquals("Unexpected interest rate", 1.5, s.getAnnualInterestRate(), 0.00001);
		assertEquals("Unexpected customer", "", s.getCustomer().getName());
	}

	@Test
	public void test_constructorWithCustomerAndBalance() {
		Customer c = new Customer("John Doe", 321);
		SavingsAccount s = new SavingsAccount(c, 50.0);
		assertEquals("Unexpected balance", 50.0, s.getBalance(), 0.00001);
	}

	// Testing deposit

	@Test
	public void test_deposit() {
		SavingsAccount s = new SavingsAccount();
		s.deposit(10.25);
		assertEquals("Deposited 10.25.", 10.25, s.getBalance(), 0.000001);
	}

	@Test
	public void test_deposit_negativeAmount() {
		SavingsAccount s = new SavingsAccount();
		s.deposit(-10.25);
		assertEquals("Tried to deposit a negative amount", 0.00, s.getBalance(), 0.000001);
	}

	// testing withdraw
	@Test
	public void test_withdraw_sufficientBalance() {
		SavingsAccount s = new SavingsAccount();
		s.deposit(500.00);
		s.withdraw(44.25);
		assertEquals("Withdrew 44.25 after depositing 500.00", 455.75, s.getBalance(), 0.000001);
	}

	@Test
	public void test_withdraw_overdraft() {
		SavingsAccount s = new SavingsAccount();
		s.deposit(5.00);
		s.withdraw(5.01);
		assertEquals("Withdrew 5.01 after depositing 5.00", -0.01, s.getBalance(), 0.000001);
	}

	@Test
	public void test_setOverdraftAmount_setToZero() {
		SavingsAccount s = new SavingsAccount();
		s.setOverdraftAmount(0.0);
		s.withdraw(1.0);
		assertEquals("Withdrew 1.0 from account with zero balance and zero overdraftAmount", 0.0, s.getBalance(),
				0.000001);

	}

	@Test
	public void test_setAnnualInterestRate_normalSet() {
		SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(5.8);
		assertEquals("Annual interest rate is 5.8", 5.8, s.getAnnualInterestRate(), 0.000001);
	}

	public void test_setAnnualInterestRate_negativeRate() {
		SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(5.8);
		s.setAnnualInterestRate(-10.0);
		assertEquals("Annual interest rate should not be negative", 5.8, s.getAnnualInterestRate(), 0.000001);
	}

	public void test_depositMonthlyInterest() {
		SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(12.0);
		s.deposit(100);
		s.depositMonthlyInterest();
		assertEquals("Balance should have increased by interest rate/12 * balance", 101.0, s.getBalance(), 0.00001);
	}

	public void test_depositMonthlyInterest_negativebalance() {
		SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(12.0);
		s.withdraw(100.0);
		s.depositMonthlyInterest();
		assertEquals("Balance should not have interest added to it", -100.0, s.getBalance(), 0.00001);
	}

}
