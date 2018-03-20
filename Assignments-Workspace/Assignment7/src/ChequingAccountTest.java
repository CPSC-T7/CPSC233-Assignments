import static org.junit.Assert.*;

import org.junit.Test;

public class ChequingAccountTest
{
    @Test
    public void test_constr1()
    {
        ChequingAccount b = new ChequingAccount(1.0);
        assertEquals(0.0, b.getBalance(), 0.00001);
    }
    
    @Test
    public void test_constr2()
    {
        Customer c = new Customer("John Doe", 321);
        ChequingAccount b = new ChequingAccount(c, 50.0, 1.3);
        assertEquals(50.0, b.getBalance(), 0.00001);
        assertEquals(1.3, b.getOverdraftFee(), 0.00001);
    }
    
    @Test
    public void test_deposit1() 
    {
        ChequingAccount b = new ChequingAccount(1.3);
        b.deposit(11.3);
        assertEquals(11.3, b.getBalance(), 0.000001);
    }
    
    @Test
    public void test_deposit2() 
    {
        ChequingAccount b = new ChequingAccount(13.0);
        b.deposit(11.3);
        assertEquals(11.3, b.getBalance(), 0.000001);
    }
    
    @Test
    public void test_withdraw1() 
    {
        Customer c = new Customer("John Doe", 321);
        ChequingAccount b = new ChequingAccount(c, 100, 10);
        b.withdraw(10);
        assertEquals(90, b.getBalance(), 0.000001);
    }
    
    @Test
    public void test_withdraw2() 
    {
        Customer c = new Customer("John Doe", 321);
        ChequingAccount b = new ChequingAccount(c, 1000, 13);
        b.setOverdraftAmount(50);
        b.withdraw(1030);
        assertEquals(-43.0, b.getBalance(), 0.000001);
    }
    
    @Test
    public void test_withdraw3() 
    {
        Customer c = new Customer("John Doe", 321);
        ChequingAccount b = new ChequingAccount(c, 1000, 13);
        b.setOverdraftAmount(50);
        b.withdraw(1050);
        assertEquals(-63.0, b.getBalance(), 0.000001);
    }
    
    @Test
    public void test_getMonthlyFeesAndInterest_OverdraftBalance() 
    {
        Customer c = new Customer("John Doe", 321);
        ChequingAccount b = new ChequingAccount(c, 1000, 13);
        b.setOverdraftAmount(50);
        b.withdraw(1040);
        assertEquals("Expected fee due to overdraft", -10.60000, b.getMonthlyFeesAndInterest(), 0.00001);
        assertEquals("Account balance should not have changed due to calling getMonthlyFeesAndInterest", -53.0, b.getBalance(), 0.000001);
		b.monthEndUpdate();
		assertEquals("Balance should have changed after calling monthEndUpdate", -63.60, b.getBalance(),0.00001);
    }
    
    @Test
    public void test_getMonthlyFeesAndInterest2() 
    {
        Customer c = new Customer("John Doe", 321);
        ChequingAccount b = new ChequingAccount(c, 1000, 13);
        b.setOverdraftAmount(50);
        b.withdraw(800);
        assertEquals(0.0, b.getMonthlyFeesAndInterest(), 0.00001);
        assertEquals("Account balance should not have changed due to calling getMonthlyFeesAndInterest", 200.0, b.getBalance(), 0.000001);
		b.monthEndUpdate();
		assertEquals("Balance should not changed after calling monthEndUpdate since fee was 0.0", 200.0, b.getBalance(),0.00001);
    }

}
