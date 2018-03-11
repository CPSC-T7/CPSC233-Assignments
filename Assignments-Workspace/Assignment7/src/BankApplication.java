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
	
	private Customer		customer				= new Customer("John Smith", 458796);
	private SavingsAccount	savingsAccount			= new SavingsAccount(customer, 150);
	
	private String			nameLabelText			= "Customer Name: " + savingsAccount.getCustomer().getName();
	private String			IDLabelText				= "Account ID: " + Integer.toString(savingsAccount.getCustomer().getID());
	private String			balanceLabelText		= "Current Balance: $" + Double.toString(savingsAccount.getBalance());
	
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
				savingsAccount.deposit(toDeposit);
				savingsAccount.withdraw(toWithdraw);
				
				// Update the balance label
				balanceLabel.setText("Current Balance: $" + Double.toString(savingsAccount.getBalance()));
				
				// Reset the text boxes
				depositTextField.setText(DEPOSIT_DEFAULT_TEXT);
				withdrawTextField.setText(WITHDRAW_DEFAULT_TEXT);
				
			}
			
		});
		
		root.getChildren().add(executeButton);
		root.getChildren().add(balanceLabel);
		
		// Final touches, then draw the window
		Scene scene = new Scene(root, 350, 150);
		primaryStage.setTitle("Bank Application");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
}
