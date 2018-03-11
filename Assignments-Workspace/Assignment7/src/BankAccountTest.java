import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class BankAccountTest
{
    public class BA extends BankAccount
    { 
		public BA(){};
		public BA(Customer c, double b){super(c,b);}
		double amount = 0.0;
        protected double getMonthlyFeesAndInterest() 
		{ 
			return amount;
		}
    }

	// Testing deposit
	@Test
    public void test_deposit() {
        BankAccount b = new BA();
        b.deposit(10.25);
        assertEquals("Deposited 10.25.", 10.25, b.getBalance(), 0.000001);
    }
    
	@Test
    public void test_deposit_negativeAmount() {
        BankAccount b = new BA();
        b.deposit(-10.25);
        assertEquals("Tried to deposit a negative amount", 0.00, b.getBalance(), 0.000001);
    }
        
    // testing withdraw
	@Test
    public void test_withdraw_sufficientBalance() {
        BankAccount b = new BA();
        b.deposit(500.00);
        b.withdraw(44.25);
        assertEquals("Withdrew 44.25 after depositing 500.00", 455.75, b.getBalance(), 0.000001);
    }
    
	@Test
    public void test_withdraw_insufficientBalance() {
        BankAccount b = new BA();
        b.deposit(5.00);
        b.withdraw(5.01);
        assertEquals("Withdrew 5.01 after depositing 5.00, nothing should have happened", 5.0, b.getBalance(), 0.000001);
    }
	
	@Test
	public void test_transfer_sufficientFunds() {
		BankAccount b = new BA(new Customer("temp",1),1000.0);
		BankAccount b2 = new BA(new Customer("temp2",2),500.0);
		b.transfer(1000,b2);
        assertEquals("Transfered 1000 from account with 1000 balance to account with 500 balance.  Testing from account", 0.0, b.getBalance(), 0.000001);
        assertEquals("Transfered 1000 from account with 1000 balance to account with 500 balance.  Testing to account", 1500.0, b2.getBalance(), 0.000001);
		
	}
	
	@Test
	public void test_transfer_insufficientFunds() {
		BankAccount b = new BA(new Customer("temp",1),1000.0);
		BankAccount b2 = new BA(new Customer("temp2",2),500.0);
		b.transfer(1001,b2);
        assertEquals("Transfered 1001 from account with 1000 balance to account with 500 balance.  Testing from account", 1000.0, b.getBalance(), 0.000001);
        assertEquals("Transfered 1001 from account with 1000 balance to account with 500 balance.  Testing to account", 500.0, b2.getBalance(), 0.000001);
		
	}
	
	@Test
	public void test_monthEndUpdate_ZeroFees(){
		BankAccount b = new BA();
		b.deposit(100.0);
		b.monthEndUpdate();
		assertEquals("Balance should be unchanged when fee and interest is 0.0", 100.0, b.getBalance(), 0.0000001);
	}

	@Test
	public void test_monthEndUpdate_PositiveFeesAndInterest(){
		BA b = new BA();
		b.amount = 10.50;
		b.deposit(100.0);
		b.monthEndUpdate();
		assertEquals("Balance should be increased when fee and interest is positive", 110.50, b.getBalance(), 0.0000001);
	}

	@Test
	public void test_monthEndUpdate_NegativeFeesAndInterest(){
		BA b = new BA();
		b.amount = -2.35;
		b.deposit(100.0);
		b.monthEndUpdate();
		assertEquals("Balance should be decreased when fee and interest is negative", 97.65, b.getBalance(), 0.0000001);
	}
}
