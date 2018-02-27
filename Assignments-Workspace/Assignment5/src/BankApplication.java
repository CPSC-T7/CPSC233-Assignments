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

public abstract class BankApplication extends Application{

	
	private Customer customer = new Customer("John Smith", 458796);
	private SavingsAccount savings = new SavingsAccount(customer, 150);
	
	String customerName = customer.getName();
	String customerNameLabelText = "Customer name: " + customerName;
	
	int customerID = customer.getID();
	String customerIDString = Integer.toString(customerID);
	String customerIDLabelText = "Account ID: " + customerIDString;
	
	double balance = savings.getBalance();
	String balanceString = Double.toString(balance);
	String balanceLabelText = "Current balance: $" + balanceString;
	
	
	Label customerNameLabel = new Label(customerNameLabelText);
	Label customerIDLabel = new Label(customerIDLabelText);
	Label balanceLabel = new Label(balanceLabelText);
	
	TextField depositTextField = new TextField("Amt to deposit");
	TextField withdrawTextField = new TextField("Amt to withdraw");
	
	Button executeButton = new Button("Execute");
	
	public void start(Stage primaryStage) throws Exception {
		
		VBox root = new VBox();
		
		root.getChildren().add(customerNameLabel);
		root.getChildren().add(customerIDLabel);
		
		HBox texts = new HBox();
		
		texts.getChildren().add(depositTextField);
		texts.getChildren().add(withdrawTextField);
		
		root.getChildren().add(texts);
		
		executeButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				try {
					String depositMoney = depositTextField.getText();
					String withdrawMoney = withdrawTextField.getText();
					
					if (!depositMoney.equals("Amt to deposit")) {
						double depositMoneyD = Double.parseDouble(depositMoney);
						savings.deposit(depositMoneyD);	
					}
					if (!withdrawMoney.equals("Amt to withdraw")) {
						double withdrawMoneyD = Double.parseDouble(withdrawMoney);
						savings.withdraw(withdrawMoneyD);
					}
					balance = savings.getBalance();
					balanceString = Double.toString(balance);
					balanceLabelText = "Current balance: $" + balanceString;
					balanceLabel.setText(balanceLabelText);
					
					
				}
				finally {
					depositTextField.setText("Amt to deposit");
					withdrawTextField.setText("Amt to withdraw");	
				}
		
				
			}
		});
		
		root.getChildren().add(executeButton);
		root.getChildren().add(balanceLabel);
		
		Scene scene = new Scene(root, 350, 300);
		primaryStage.setTitle("Bank application");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	
	
	
	
}
