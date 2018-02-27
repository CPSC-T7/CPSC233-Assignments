import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class SavingsAccountTest
{
	// test constructors
	@Test
    public void test_creation(){
        SavingsAccount s = new SavingsAccount();
        assertEquals("Expected initial balance to be 0.0", 0.0, s.getBalance(), 0.00001);
    }


    	@Test
	public void test_constructorWithInterestAndBalance()
	{
		SavingsAccount s = new SavingsAccount(50.0, 1.5);
		assertEquals("Unexpected balance", 50.0, s.getBalance(), 0.00001);
		assertEquals("Unexpected interest rate", 1.5, s.getAnnualInterestRate(), 0.00001);
		assertEquals("Unexpected customer", "", s.getCustomer().getName());
	}
	
	@Test
	public void test_constructorWithCustomerAndBalance()
	{
		Customer c = new Customer("John Doe", 321);
		SavingsAccount s = new SavingsAccount(c, 50.0);
		assertEquals("Unexpected balance",50.0,s.getBalance(), 0.00001);
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
        assertEquals("Withdrew 1.0 from account with zero balance and zero overdraftAmount", 0.0, s.getBalance(), 0.000001);
		
	}
	
	@Test
	public void test_setAnnualInterestRate_normalSet()
	{
		SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(5.8);
		assertEquals("Annual interest rate is 5.8", 5.8, s.getAnnualInterestRate(), 0.000001); 
	}

	@Test
	public void test_setAnnualInterestRate_negativeRate()
	{
		SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(5.8);
		s.setAnnualInterestRate(-10.0);
		assertEquals("Annual interest rate should not be negative", 5.8, s.getAnnualInterestRate(), 0.000001);
	}

	@Test
	public void test_depositMonthlyInterest()
	{
		SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(12.0);
		s.deposit(100);
		s.depositMonthlyInterest();
		assertEquals("Balance should have increased by interest rate/12 * balance", 101.0, s.getBalance(), 0.00001);
	}

	@Test
	public void test_depositMonthlyInterest_negativebalance()
	{
		SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(12.0);
		s.withdraw(100.0);
		s.depositMonthlyInterest();
		assertEquals("Balance should not have interest added to it", -100.0, s.getBalance(), 0.00001);	
	}

/******** CUT **********/

	@Test
	public void test_depositMonthlyInterest_switchbalance()
	{
		SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(12.0);
		s.withdraw(100.0);
		s.depositMonthlyInterest();
		assertEquals("Balance should not have interest added to it", -100.0, s.getBalance(), 0.00001);	
		s.deposit(200.0);
		s.depositMonthlyInterest();
		assertEquals("Balance should have increased by interest rate/12 * balance", 101.0, s.getBalance(), 0.00001);
		
	}

	@Test
	public void test_properVisibility()
	{
		Field fields[] = (new SavingsAccount()).getClass().getFields();
		assertEquals("Public fields in SavingsAccount", 0, fields.length );
	}

	@Test
	public void test_customerSecurityConstructor()
	{
		Customer puppet = new Customer("John Doe", 321);
		SavingsAccount s = new SavingsAccount(puppet, 0);
		puppet.setName( "Jane Roe" );

		assertEquals("Constructor is copy-by-reference", "John Doe", s.getCustomer().getName());
	}

/*	@Test
	public void test_customerSecuritySetter()
	{
		Customer puppet = new Customer("John Doe", 321);
		SavingsAccount s = new SavingsAccount();
		s.setCustomer( puppet );
		puppet.setName( "Jane Roe" );

		assertEquals("Setter is copy-by-reference", "John Doe", s.getCustomer().getName());
	}
*/
	@Test
	public void test_customerSecurityGetter()
	{
		Customer puppet = new Customer("John Doe", 321);
		SavingsAccount s = new SavingsAccount(puppet, 0);
		puppet = s.getCustomer();
		puppet.setName( "Jane Roe" );

		assertEquals("Getter is copy-by-reference", "John Doe", s.getCustomer().getName());
	}

        @Test
        public void test_customerBalanceConstructor()
        {
                Customer c = new Customer("John Doe", 321);
                SavingsAccount b = new SavingsAccount(c, 50.0);
                assertEquals("Customer's name differs",c.getName(),b.getCustomer().getName());
                assertEquals("Customer's ID differs",c.getID(),b.getCustomer().getID());
        }

        @Test
        public void test_large_withdraw_overdraft() {
                SavingsAccount b = new SavingsAccount();
                b.setOverdraftAmount(100.0);            // insurance
                b.deposit(500);
                b.withdraw(300);
                assertEquals("Didn't withdraw 300 after depositing 500", 200, b.getBalance(), 0.000001);
        }

        @Test
        public void test_multiple_withdraw_overdraft() {
                SavingsAccount b = new SavingsAccount();
                b.setOverdraftAmount(100.0);            // insurance
                b.withdraw(80);
                b.withdraw(80);
                assertEquals("Allowed two withdrawls of 80 with overdraft of 100", -80, b.getBalance(), 0.000001);
                }

        @Test
        public void test_negative_overdraft() {
                SavingsAccount b = new SavingsAccount();
                b.deposit(200);
                b.setOverdraftAmount(-100.0);           // this is legit!
                b.withdraw(150);
                assertEquals("Withdrew 150 after depositing 200 and setting overdraft to -100", 200, b.getBalance(), 0.000001);
                }

        @Test
        public void test_break_overdraft() {
                SavingsAccount b = new SavingsAccount();
                b.setOverdraftAmount(100.0);            // insurance
                b.withdraw(80);
                b.setOverdraftAmount(50.0);             // this should fail, leaving the overdraft at 100
                b.deposit(80);                          // top it back up
                b.withdraw(90);                         // this should work, as the overdraft is 100

                assertEquals("Balance shuffling revealed overdraft was blindly set", -90, b.getBalance(), 0.000001);
                }

        @Test
        public void test_null_interest() {
                SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(12.0);
		s.depositMonthlyInterest();

                assertEquals("Interest was non-zero with a zero balance", 0, s.getBalance(), 0.000001);
                }

	@Test
	public void test_setAnnualInterestRate_nancheck()
	{
		SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(5);
		s.setAnnualInterestRate(Double.NaN);
		assertEquals("NaN values allowed", 5, s.getAnnualInterestRate(), 0.00001);
	}

	@Test
	public void test_setAnnualInterestRate_infcheck()
	{
		SavingsAccount s = new SavingsAccount();
		s.setAnnualInterestRate(5);
		s.setAnnualInterestRate(Double.POSITIVE_INFINITY);
		assertEquals("Infinite values allowed", 5, s.getAnnualInterestRate(), 0.00001);
		s.setAnnualInterestRate(Double.NEGATIVE_INFINITY);
		assertEquals("Infinite values allowed", 5, s.getAnnualInterestRate(), 0.00001);
	}

	@Test
	public void test_setOverdraftAmount_nancheck()
	{
		SavingsAccount s = new SavingsAccount();
		s.setOverdraftAmount(5);
		s.setOverdraftAmount(Double.NaN);
		s.withdraw(5);
		assertEquals("NaN values allowed", -5, s.getBalance(), 0.00001);
	}

	@Test
	public void test_setOverdraftAmount_infcheck()
	{
		SavingsAccount s = new SavingsAccount();
		s.setOverdraftAmount(5);
		s.setOverdraftAmount(Double.POSITIVE_INFINITY);
		s.withdraw(5);
		assertEquals("Positive infinite values allowed", -5, s.getBalance(), 0.00001);
		s.deposit(5);
		s.setOverdraftAmount(Double.NEGATIVE_INFINITY);
		s.withdraw(5);
		assertEquals("Negative infinite values allowed", -5, s.getBalance(), 0.00001);
	}

	@Test
	public void test_deposit_nancheck()
	{
		SavingsAccount s = new SavingsAccount();
		s.deposit(5);
		s.deposit(Double.NaN);
		assertEquals("NaN values allowed", 5, s.getBalance(), 0.00001);
	}

	@Test
	public void test_deposit_infcheck()
	{
		SavingsAccount s = new SavingsAccount();
		s.deposit(5);
		s.deposit(Double.POSITIVE_INFINITY);
		assertEquals("Positive infinite values allowed", 5, s.getBalance(), 0.00001);
		s.deposit(Double.NEGATIVE_INFINITY);
		assertEquals("Negative infinite values allowed", 5, s.getBalance(), 0.00001);
	}

	@Test
	public void test_withdraw_nancheck()
	{
		SavingsAccount s = new SavingsAccount();
		s.deposit(5);
		s.withdraw(Double.NaN);
		assertEquals("NaN values allowed", 5, s.getBalance(), 0.00001);
	}

	@Test
	public void test_withdraw_infcheck()
	{
		SavingsAccount s = new SavingsAccount();
		s.deposit(5);
		s.withdraw(Double.POSITIVE_INFINITY);
		assertEquals("Positive infinite values allowed", 5, s.getBalance(), 0.00001);
		s.withdraw(Double.NEGATIVE_INFINITY);
		assertEquals("Negative infinite values allowed", 5, s.getBalance(), 0.00001);
	}

}
