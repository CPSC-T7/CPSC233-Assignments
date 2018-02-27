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

public class BankApplication extends Application {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private Customer		customer			= new Customer("Jeff Vader", 92687);
	private SavingsAccount	savingsAccount		= new SavingsAccount(customer, 150.0);
	
	private Label			customerNameLabel	= new Label("Customer Name: " + customer.getName());
	private Label			customerIDLabel		= new Label("Customer ID: " + customer.getID());
	private Label			balanceLabel		= new Label("Account Balance: " + savingsAccount.getBalance());
	private TextField		depositTextField	= new TextField("Deposit");
	private TextField		withdrawTextField	= new TextField("Withdraw");
	private Button			executeButton		= new Button("Execute Order 66");
	
	private int				vPadding			= 10;
	private int				hPadding			= 10;
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		VBox root = new VBox(vPadding);
		
		root.getChildren().add(customerNameLabel);
		root.getChildren().add(customerIDLabel);
		
		HBox textFields = new HBox(hPadding);
		textFields.getChildren().add(depositTextField);
		textFields.getChildren().add(withdrawTextField);
		root.getChildren().add(textFields);
		
		root.getChildren().add(executeButton);
		root.getChildren().add(balanceLabel);
		
		executeButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				double toDeposit = 0;
				double toWithdraw = 0;
				
				try {
					toDeposit = Double.parseDouble(depositTextField.getText());
				} catch (Exception e) {
					if (!depositTextField.getText().equals("Deposit")) {
						System.out.println("Invalid Deposit Value.");
					}
				}
				
				try {
					toWithdraw = Double.parseDouble(withdrawTextField.getText());
				} catch (Exception e) {
					if (!withdrawTextField.getText().equals("Withdraw")) {
						System.out.println("Invalid Withdraw Value.");
					}
				}
				
				savingsAccount.deposit(toDeposit);
				savingsAccount.withdraw(toWithdraw);
				
				balanceLabel.setText("Account Balance: " + savingsAccount.getBalance());
				
				depositTextField.setText("Deposit");
				withdrawTextField.setText("Withdraw");
				
			}
			
		});
		
		// Show the window
		Scene scene = new Scene(root, 350, 150);
		primaryStage.setTitle("Empire Banking Application");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
}
