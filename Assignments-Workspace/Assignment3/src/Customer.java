
/**
 * This class represents a customer with a name and an ID number. It is tied
 * with the BankAcount class to tie people with their bank accounts.
 * 
 * @see BankAccount.class
 * @author Matthew Allwright
 * @version 1.1.0
 *
 */
public class Customer {

	// INSTANCE VARIABLES

	private String name;
	private int ID;

	// CONSTRUCTORS

	/**
	 * Creates an empty customer. May or may not have a soul.
	 */
	public Customer() {

		// this.soul = null;

	}

	/**
	 * Creates a customer with a name and customer ID number.
	 * 
	 * @param name
	 *            Name of the customer.
	 * @param customerID
	 *            Customer ID number of the customer.
	 */
	public Customer(String name, int customerID) {

		this.name = name;
		this.ID = customerID;

	}

	/**
	 * Creates a customer based off of another customer.
	 * 
	 * @param customer
	 *            The customer to clone the data from.
	 */
	public Customer(Customer customer) {

		this.name = customer.getName();
		this.ID = customer.getID();

	}

	// METHODS

	/**
	 * Returns a string representing the customer instance.
	 */
	public String toString() {

		return "Customer ID# : " + Integer.toString(this.ID) + " - " + this.name;

	}

	/**
	 * Gets the name of the customer.
	 * 
	 * @return Name of the customer.
	 */
	public String getName() {

		return this.name;

	}

	/**
	 * Sets the name of the customer.
	 * 
	 * @param name
	 *            Name of the customer.
	 */
	public void setName(String name) {

		this.name = name;

	}

	/**
	 * Gets the ID of the customer.
	 * 
	 * @return ID of the customer.
	 */
	public int getID() {

		return this.ID;

	}

	/**
	 * Sets the ID of the customer.
	 * 
	 * @param customerID
	 *            ID of the customer.
	 */
	public void setID(int customerID) {

		this.ID = customerID;

	}

}
