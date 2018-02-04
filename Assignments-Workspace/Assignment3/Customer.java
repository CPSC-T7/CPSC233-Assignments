/**
 * Defines customer information, allows user to retrieve or update that information as required.
 * 
 * @author Cass
 * @version 1.0.0
 */
public class Customer{
	
// INSTANCE VARIABLES
	private String name;
	private int customerID;


//CONSTRUCTORS
	
	/**
	 * <Default Constructor>
	 * Initialize with default values
	 *
	 */
	Customer(){}

	/**
	 * Set customer information as provided.
	 * 
	 * @param name
	 *			Name of customer
	 * @param id
	 *			Customer identification number
	 */
	Customer(String name, int id){

		//initialize name and id as provided
		this.name = name;
		this.customerID = id;
	}


	/**
	 * <Copy Constructor>
	 * Set customer information from copy
	 *
	 * @param toCopy
	 *			Source of information to copy from
	 */
	Customer(Customer toCopy){
	
		//get information through methods to keep private info secure
		this.name = toCopy.getName();
		this.customerID = toCopy.getID();
	}


//METHODS
	
	/**
	 * Returns the customer name.
	 * 
	 * @return name current customer name 
	 */
	public String getName(){return this.name;}//End of getName method


	/**
	 * Returns the customers ID number.
	 * 
	 * @return current customers ID number 
	 */
	public int getID(){return this.customerID;}//End of getID method


	/**
	 * Set customer name.
	 * 
	 * @param newName
	 * 			Name to replace current customer name
	 */
	public void setName(String newName){this.name = newName;}//End of setName method


	/**
	 * Set customer ID.
	 * 
	 * @param newID 
	 *			Customer ID number to replace current ID number
	 */
	public void setID(int newID){this.customerID = newID;}//End of setID method


	/**
	 * Returns all customer information.
	 * 
	 * @return info all customer information
	 */
	public String toString(){

		//format required information
		String info = "Customer Name: " + this.name + ", ID Number: " + this.customerID;

		return info;
	}//End of toString method

}// End of Customer Class