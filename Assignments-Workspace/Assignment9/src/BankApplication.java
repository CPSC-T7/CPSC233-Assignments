import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This class represents a the GUI for the bank application. It is meant to be
 * used in conjunction with the BankAcount class to tie people with their bank
 * accounts. <br>
 * When run, the user is presented with an interface showing the customer name,
 * ID number, and balance, as well as two text fields: One for an amount to
 * deposit, and one for an amount to withdraw. There is a button below this
 * which, when pressed, deposits and/or withdraws the specified amount(s) from
 * the attached savings account.
 * 
 * @version 1.2.1
 * @author T07
 * @see BankAccount
 */
public class BankApplication extends Application {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final int		VBOX_PADDING			= 10;
	private final int		HBOX_PADDING			= 10;
	
	private final String	DEPOSIT_DEFAULT_TEXT	= "Deposit";
	private final String	WITHDRAW_DEFAULT_TEXT	= "Withdraw";
	private final String	EXECUTE_BUTTON_TEXT		= "Execute";
	private final String	NAME_DEFAULT_TEXT		= "Name";
	private final String 	FINISH_DEFAULT_TEXT		= "Finish";
	
	private final String	INFO_FILE_NAME			= "BankAccountData.txt";
	
	private Customer		customer;
	private BankAccount		bankAccount;
	
	private boolean 		hasAccount			;
	
	private String 			askNameLabelText 		= "Please enter your name.";
	private String			askAccountTypeText		= "Which type of account would you like to open?";
	
	// GUI Elements
	
	//Existing Account
	private Label			customerNameLabel		= new Label("Empty");
	private Label			customerIDLabel			= new Label("Empty");
	private Label			balanceLabel			= new Label("$0.0");
	
	private TextField		depositTextField		= new TextField(DEPOSIT_DEFAULT_TEXT);
	private TextField		withdrawTextField		= new TextField(WITHDRAW_DEFAULT_TEXT);
	
	private Button			executeButton			= new Button(EXECUTE_BUTTON_TEXT);	
	
	//New Account
	private Label 			askForNameLabel			= new Label(askNameLabelText);
	private TextField		customerNameTextField	= new TextField(NAME_DEFAULT_TEXT);
	
	private Label 			askAccountTypeLabel	= new Label(askAccountTypeText);
	
	private Button			finishAccountButton			= new Button(FINISH_DEFAULT_TEXT);	
	
	/*
	 * 
	 * CONSTRUCTOR
	 * 
	 */
	
	public BankApplication() {
		//If an account exists: read from the file.
		if (new File(INFO_FILE_NAME).isFile()) {
			hasAccount = true;
			getBankInfo();
			
			//Set the customer name/ID labels
			customerNameLabel.setText(customer.getName());
			customerIDLabel.setText(Integer.toString(customer.getID()));
			balanceLabel.setText("Current Balance: $" + Double.toString(bankAccount.getBalance()));
			
		}else { //If no accounts exist: create a new file
			hasAccount = false;
		}
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Launches the GUI Application.
	 * 
	 * @param args
	 *            Not explicitly used.
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	/**
	 * JavaFX start method.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/*
		 * GUI layout for a created account:
		 * 
		 * Name-Label ID-Label Deposit-TextField Withdraw-TextField Execute-Button
		 * Balance-Label
		 */
		
		VBox depositWithdrawVBox = new VBox(VBOX_PADDING);
		
		depositWithdrawVBox.getChildren().add(customerNameLabel);
		depositWithdrawVBox.getChildren().add(customerIDLabel);
		
		HBox depositWithdrawHBox = new HBox(HBOX_PADDING);
		
		depositWithdrawHBox.getChildren().add(depositTextField);
		depositWithdrawHBox.getChildren().add(withdrawTextField);
		
		depositWithdrawVBox.getChildren().add(depositWithdrawHBox);		
		
		depositWithdrawVBox.getChildren().add(executeButton);
		depositWithdrawVBox.getChildren().add(balanceLabel);
		
		//New scene for deposit withdraw
		Scene depositWithdrawScene = new Scene(depositWithdrawVBox, 350, 150);
		
		
		/*
		 * GUI layout for a new account:
		 * 
		 * Name-Label ID-Label Deposit-TextField Withdraw-TextField Execute-Button
		 * Balance-Label
		 */
		
		VBox newAccountVBox = new VBox(VBOX_PADDING);
		
		newAccountVBox.getChildren().add(askForNameLabel);
		
		HBox newCustomerNameHBox = new HBox(HBOX_PADDING);
		newCustomerNameHBox.getChildren().add(customerNameTextField);
		newAccountVBox.getChildren().add(newCustomerNameHBox);
		
		newAccountVBox.getChildren().add(askAccountTypeLabel);
		
		//Account Type Radio Buttons
		HBox newCustomerAccountTypeHBox = new HBox(HBOX_PADDING);
		
		final ToggleGroup radioButtonGroup = new ToggleGroup();
		
		RadioButton radioChequing = new RadioButton("Chequing");
		radioChequing.setUserData("Chequing");
		radioChequing.setToggleGroup(radioButtonGroup);
		radioChequing.setSelected(true);
		
		RadioButton radioSavings = new RadioButton("Savings");
		radioSavings.setUserData("Savings");
		radioSavings.setToggleGroup(radioButtonGroup);
		newCustomerAccountTypeHBox.getChildren().addAll(radioChequing, radioSavings);
		newAccountVBox.getChildren().add(newCustomerAccountTypeHBox);
		
		//Finish creating account button
		newAccountVBox.getChildren().add(finishAccountButton);
		
		
		
		//New scene for making a new account
		Scene newAccountScene = new Scene(newAccountVBox, 350, 150);
		
		
		// Draw the window
		primaryStage.setTitle("Bank Application");
		
		//Set the scene depending on whether there is an account or not
		if (hasAccount == true) {
			
			primaryStage.setScene(depositWithdrawScene);
			
		}else {
			
			primaryStage.setScene(newAccountScene);
		
		}
		primaryStage.show();
		
		// Set the button to withdraw and deposit the specified amounts
		finishAccountButton.setOnAction(new EventHandler<ActionEvent>() {
			
			/**
			 * Button handler. <br>
			 * Deposits and withdraws the amounts in depositTextField and withdrawTextField
			 * respectively to the linked savings account.
			 */
			@Override
			public void handle(ActionEvent event) {

				//Get the customer's name from the TextField
				String customerName 		= customerNameTextField.getText();
				
				//Set the customer's ID randomly
				int customerID 			= ThreadLocalRandom.current().nextInt(1000, 10000);
				
				//Create the customer object
				customer = new Customer(customerName, customerID);
				
				// Create the customers account based on the radio button selection
				String customerAccountType 	= radioButtonGroup.getSelectedToggle().getUserData().toString();
				
				if (customerAccountType == "Chequing") {
					bankAccount = new ChequingAccount(customer);
					
				}else {
					bankAccount = new SavingsAccount(customer);
					
				}
				
				//Save the users info to a file
				saveBankInfo();
				
				//Change the scene to deposit/withdraw from the account
				primaryStage.setScene(depositWithdrawScene);
				
				//Set the customer name/ID labels
				customerNameLabel.setText(customerName);
				customerIDLabel.setText(Integer.toString(customerID));
				
			}
			
		});
		
		// Set the button to finish creating a new account
		executeButton.setOnAction(new EventHandler<ActionEvent>() {
			
			/**
			 * Button handler.
			 * Creates a new account from the information given by
			 * the new customer.
			 */
			@Override
			public void handle(ActionEvent event) {
				
				double toDeposit = 0;
				double toWithdraw = 0;
				
				// Try to parse the value in depositTextField to a usable double
				try {
					
					toDeposit = Double.parseDouble(depositTextField.getText());
					
				} catch (NumberFormatException e) {// If the value isn't a usable double
					
					// Only tell the user about bad input if it isn't the default value
					if (!depositTextField.getText().equals(DEPOSIT_DEFAULT_TEXT)) {
						System.out.println("Invalid Deposit Value.");
					}
					
				}
				
				// Try to parse the value in withdrawTextField to a usable double
				try {
					
					toWithdraw = Double.parseDouble(withdrawTextField.getText());
					
				} catch (NumberFormatException e) { // If the value isn't a usable double
					
					// Only tell the user about bad input if it isn't the default value
					if (!withdrawTextField.getText().equals(WITHDRAW_DEFAULT_TEXT)) {
						System.out.println("Invalid Withdraw Value.");
					}
					
				}
				
				// Deposit and withdraw the amounts
				bankAccount.deposit(toDeposit);
				bankAccount.withdraw(toWithdraw);
				
				// Update the balance label
				balanceLabel.setText("Current Balance: $" + Double.toString(bankAccount.getBalance()));
				
				// Reset the text boxes
				depositTextField.setText(DEPOSIT_DEFAULT_TEXT);
				withdrawTextField.setText(WITHDRAW_DEFAULT_TEXT);
				
				//Save the changes to file
				saveBankInfo();
				
			}
			
		});
		
	}
	
	/**
	 * Saves the current bank account and customer info to a text file.
	 */
	private void saveBankInfo() {
		
		// Open the file
		try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(new File(INFO_FILE_NAME)))) {
			
			//Data Holder
			ArrayList<String> data = new ArrayList<String>();
			
			// Grab the customer info
			data.add(this.customer.getName());
			data.add(Integer.toString(this.customer.getID()));
			
			// Grab the account info
			if (this.bankAccount instanceof ChequingAccount) {
				
				data.add("C");
				data.add(Double.toString(((ChequingAccount) this.bankAccount).getOverdraftAmount()));
				data.add(Double.toString(((ChequingAccount) this.bankAccount).getOverdraftFee()));
				data.add(Double.toString(((ChequingAccount) this.bankAccount).getBalance()));
				
			} else if (this.bankAccount instanceof SavingsAccount) {
				
				data.add("S");
				data.add(Double.toString(((SavingsAccount) this.bankAccount).getAnnualInterestRate()));
				data.add(Double.toString(((SavingsAccount) this.bankAccount).getBalance()));
				
			}
			
			// Write the data to the text file
			bWriter.write(String.join(",", data));
			
		} catch (IOException e) {
			
			System.out.println("Error reading file [" + INFO_FILE_NAME + "]. I/O Exception on write!");
			e.printStackTrace();
			
		}
		
	}
	
	/**
	 * Loads the current bank account and customer info from a text file.
	 */
	@SuppressWarnings("static-access")
	private void getBankInfo() {
		
		// Open the file
		try (BufferedReader bReader = new BufferedReader(new FileReader(new File(INFO_FILE_NAME)))) {
			
			//Data Holder
			String[] data = bReader.readLine().split(",");
			
			// Grab the customer info
			String customerName = data[0];
			int customerID = Integer.parseInt(data[1]);
			
			//Create the customer
			this.customer = new Customer(customerName, customerID);
			
			// Grab and set the account info
			if (data[2].equals("C")) {
				
				ChequingAccount account = new ChequingAccount(this.customer);
				account.setOverdraftAmount(Double.parseDouble(data[3]));
				account.setOverdraftFee(Double.parseDouble(data[4]));
				account.setBalance(Double.parseDouble(data[5]));
				this.bankAccount = account;
				
			} else if (data[2].equals("S")) {
				
				SavingsAccount account = new SavingsAccount(this.customer);
				account.setAnnualInterestRate(Double.parseDouble(data[3]));
				account.setBalance(Double.parseDouble(data[4]));
				this.bankAccount = account;
				
			}
			
		} catch (IOException e) {
			
			System.out.println("Error reading file [" + INFO_FILE_NAME + "]. I/O Exception on read!");
			e.printStackTrace();
			
		}
		
	}
	
}
