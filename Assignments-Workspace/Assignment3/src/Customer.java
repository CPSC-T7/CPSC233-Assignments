
/**
 * This class represents a customer with a name and an ID number. It is meant to
 * be used in conjunction with the BankAcount class to tie people with their
 * bank accounts.
 * 
 * @version 1.1.0
 * @author T07
 * @see BankAccount.class
 */
public class Customer {

	// INSTANCE VARIABLES

	private String name;
	private int id;

	// CONSTRUCTORS

	/**
	 * Initialize an empty customer object. (Not actually needed to be defined) <br>
	 * Caution: May or may not have a soul.
	 */
	Customer() {

		// this.soul = null;

	}// End of default constructor

	/**
	 * Initialize a customer with a name and id number.
	 * 
	 * @param name
	 *            Name of customer.
	 * @param id
	 *            Customer identification number.
	 */
	Customer(String name, int id) {

		// Initialize name and id as provided
		this.name = name;
		this.id = id;

	}// End of copy constructor

	/**
	 * Create a customer object based off of an existing customer object.
	 *
	 * @param customerToCopy
	 *            The customer to copy all of the information off of.
	 */
	Customer(Customer customerToCopy) {

		// Get information through methods to keep private info secure
		this.name = customerToCopy.getName();
		this.id = customerToCopy.getID();

	}// End of constructor

	// METHODS

	/**
	 * Returns the customer name.
	 * 
	 * @return The current customer name.
	 */
	public String getName() {

		return this.name;

	}// End of getName method

	/**
	 * Returns the customers ID number.
	 * 
	 * @return The current customer ID number.
	 */
	public int getID() {

		return this.id;

	}// End of getID method

	/**
	 * Set customer name.
	 * 
	 * @param newName
	 *            Name to replace current customer name with.
	 */
	public void setName(String newName) {

		this.name = newName;

	}// End of setName method

	/**
	 * Set customer ID.
	 * 
	 * @param newID
	 *            Customer ID number to replace current ID number with.
	 */
	public void setID(int newID) {

		this.id = newID;

	}// End of setID method

	/**
	 * Returns all customer information.
	 * 
	 * @return info all customer information
	 */
	public String toString() {

		// Format a string with the customer's name and ID.
		String info = "Customer Name: " + this.name + ", ID Number: " + this.id;
		return info;

	}// End of toString method

}// End of Customer Class