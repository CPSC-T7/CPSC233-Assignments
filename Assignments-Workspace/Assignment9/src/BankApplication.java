import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	
	private final String	INFO_FILE_NAME			= "BankAccountData.txt";
	
	private Customer		customer;
	private BankAccount		bankAccount;
	
	private String			nameLabelText			= "Customer Name: " + bankAccount.getCustomer().getName();
	private String			IDLabelText				= "Account ID: "
			+ Integer.toString(bankAccount.getCustomer().getID());
	private String			balanceLabelText		= "Current Balance: $" + Double.toString(bankAccount.getBalance());
	
	// GUI Elements
	
	private Label			customerNameLabel		= new Label(nameLabelText);
	private Label			customerIDLabel			= new Label(IDLabelText);
	private Label			balanceLabel			= new Label(balanceLabelText);
	
	private TextField		depositTextField		= new TextField(DEPOSIT_DEFAULT_TEXT);
	private TextField		withdrawTextField		= new TextField(WITHDRAW_DEFAULT_TEXT);
	
	private Button			executeButton			= new Button(EXECUTE_BUTTON_TEXT);
	
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
		 * GUI Layout:
		 * 
		 * Name-Label ID-Label Deposit-TextField Withdraw-TextField Execute-Button
		 * Balance-Label
		 */
		
		VBox root = new VBox(VBOX_PADDING);
		
		root.getChildren().add(customerNameLabel);
		root.getChildren().add(customerIDLabel);
		
		HBox textFileds = new HBox(HBOX_PADDING);
		
		textFileds.getChildren().add(depositTextField);
		textFileds.getChildren().add(withdrawTextField);
		
		root.getChildren().add(textFileds);
		
		// Set the button to withdraw and deposit the specified amounts
		executeButton.setOnAction(new EventHandler<ActionEvent>() {
			
			/**
			 * Button handler. <br>
			 * Deposits and withdraws the amounts in depositTextField and withdrawTextField
			 * respectively to the linked savings account.
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
				
			}
			
		});
		
		root.getChildren().add(executeButton);
		root.getChildren().add(balanceLabel);
		
		// Close event
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				
				// BankApplication.saveBankInfo();
				
			}
			
		});
		
		// Final touches, then draw the window
		Scene scene = new Scene(root, 350, 150);
		primaryStage.setTitle("Bank Application");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private void saveBankInfo() {
		
		try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(new File(INFO_FILE_NAME)))) {
			
			ArrayList<String> data = new ArrayList<String>();
			data.add(this.customer.getName());
			data.add(Integer.toString(this.customer.getID()));
			// data.add(this.bankAccount instanceof ChequingAccount ? "C" : "S");
			
			if (this.bankAccount instanceof ChequingAccount) {
				
				data.add("C");
				data.add(Double.toString(((ChequingAccount) this.bankAccount).getOverdraftAmount()));
				data.add(Double.toString(((ChequingAccount) this.bankAccount).getOverdraftFee()));
				
			} else if (this.bankAccount instanceof SavingsAccount) {
				
				data.add("S");
				data.add(Double.toString(((SavingsAccount) this.bankAccount).getAnnualInterestRate()));
				
			}
			
			bWriter.write(String.join(",", data));
			
		} catch (IOException e) {
			
			System.out.println("Error reading file [" + INFO_FILE_NAME + "]. I/O Exception! on write");
			e.printStackTrace();
			
		}
		
	}
	
	@SuppressWarnings("static-access")
	private void getBankInfo() {
		
		try (BufferedReader bReader = new BufferedReader(new FileReader(new File(INFO_FILE_NAME)))) {
			
			String[] data = bReader.readLine().split(",");
			
			String customerName = data[0];
			int customerID = Integer.parseInt(data[1]);
			
			if (data[2].equals("C")) {
				
				ChequingAccount account = new ChequingAccount(new Customer(customerName, customerID));
				account.setOverdraftAmount(Integer.parseInt(data[3]));
				account.setOverdraftFee(Integer.parseInt(data[4]));
				
			} else if (data[2].equals("S")) {
				
				SavingsAccount account = new SavingsAccount(new Customer(customerName, customerID));
				account.setAnnualInterestRate(Integer.parseInt(data[3]));
				
			}
			
		} catch (IOException e) {
			
			System.out.println("Error reading file [" + INFO_FILE_NAME + "]. I/O Exception on read!");
			e.printStackTrace();
			
		}
		
	}
	
}
