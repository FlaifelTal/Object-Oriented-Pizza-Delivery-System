package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.Region;

public class Main extends Application {
	Scene scene, scene1, scene2;

	// Variables to store customer name, order type, pizza size, and toppings
	private String customerName = "";
	private String orderType = "ToGo";
	private int pizzaSize = 1;
	private String[] toppings = { "Onions", "Olives", "Green Peppers" };
	private boolean[] selectedToppings = { false, false, false };
	private double toppingPrice = 10;
	private double tripRate;
	private int zone;
	private double serviceCharge;
	private int numberOfPeople;

	//private TextField tripRateInput;
	//private TextField zonein;
	//private TextField numberOfPeoplein;
	//private TextField serviceChargelabelin;

	@Override
	public void start(Stage primaryStage) {
		ArrayList<PizzaOrder> orders = new ArrayList<PizzaOrder>(); // declaring an array list to store the orders

		primaryStage.getIcons().add(new Image("pizza.png"));

		primaryStage.setTitle("Pizza Order");
		primaryStage.setResizable(true);

		// Scene 1
		Label label1 = new Label("Click to order");
		Button button1 = new Button("");

		button1.setOnAction(e -> primaryStage.setScene(scene));
		VBox layout1 = new VBox(20);
		layout1.setAlignment(Pos.CENTER);
		// layout1.setAlignment(Pos.BOTTOM_RIGHT);

		layout1.getChildren().addAll(label1, button1);
		scene1 = new Scene(layout1, 600, 600);

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		grid.setAlignment(Pos.CENTER);

		// Customer name
		Label nameLabel = new Label("Customer Name:");
		GridPane.setConstraints(nameLabel, 0, 0);

		TextField nameInput = new TextField();
		nameInput.setPromptText("Enter your name");
		GridPane.setConstraints(nameInput, 1, 0);

		// Order type
		Label typeLabel = new Label("Order Type:");
		GridPane.setConstraints(typeLabel, 0, 1);

		ToggleGroup typeToggleGroup = new ToggleGroup();

		RadioButton toGoRadioButton = new RadioButton("ToGo");
		toGoRadioButton.setToggleGroup(typeToggleGroup);
		toGoRadioButton.setSelected(true);
		GridPane.setConstraints(toGoRadioButton, 1, 1);

		RadioButton deliveryRadioButton = new RadioButton("Delivery");
		deliveryRadioButton.setToggleGroup(typeToggleGroup);
		GridPane.setConstraints(deliveryRadioButton, 2, 1);

		RadioButton seatedRadioButton = new RadioButton("Seated");
		seatedRadioButton.setToggleGroup(typeToggleGroup);
		GridPane.setConstraints(seatedRadioButton, 3, 1);

		typeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == deliveryRadioButton) {
				Label tripRateLabel = new Label("Trip Rate:");
				GridPane.setConstraints(tripRateLabel, 2, 2);
				grid.getChildren().add(tripRateLabel);

				TextField tripRateInput = new TextField();
				tripRateInput.setPromptText("Enter the trip rate");
				GridPane.setConstraints(tripRateInput, 3, 2);
				grid.getChildren().add(tripRateInput);

				Label zoneLabel = new Label("Zone:");
				GridPane.setConstraints(zoneLabel, 2, 3);
				grid.getChildren().add(zoneLabel);

				TextField zoneInput = new TextField();
				zoneInput.setPromptText("Enter the Zone:");
				GridPane.setConstraints(zoneInput, 3, 3);
				grid.getChildren().add(zoneInput);
				
				
				
				   double tripRate = Double.parseDouble(tripRateInput.getText());
				   int zone = Integer.parseInt(zoneInput.getText());
				   
			} else {
				grid.getChildren()
						.removeIf(node -> node instanceof Label && ((Label) node).getText().equals("Trip Rate:"));
				grid.getChildren().removeIf(node -> node instanceof TextField
						&& ((TextField) node).getPromptText().equals("Enter the trip rate"));

				grid.getChildren().removeIf(node -> node instanceof Label && ((Label) node).getText().equals("Zone:"));
				grid.getChildren().removeIf(node -> node instanceof TextField
						&& ((TextField) node).getPromptText().equals("Enter the Zone:"));
			}

			if (newValue == seatedRadioButton) {
				Label serviceChargeLabel = new Label("Service Charge:");
				GridPane.setConstraints(serviceChargeLabel, 2, 2);
				grid.getChildren().add(serviceChargeLabel);

				TextField serviceChargeInput = new TextField();
				serviceChargeInput.setPromptText("Enter Service Charge:");
				GridPane.setConstraints(serviceChargeInput, 3, 2);
				grid.getChildren().add(serviceChargeInput);

				Label numberOfPeopleLabel = new Label("Number Of People:");
				GridPane.setConstraints(numberOfPeopleLabel, 2, 3);
				grid.getChildren().add(numberOfPeopleLabel);

				TextField numberOfPeopleInput = new TextField();
				numberOfPeopleInput.setPromptText("Enter The number of people:");
				GridPane.setConstraints(numberOfPeopleInput, 3, 3);
				grid.getChildren().add(numberOfPeopleInput);
				
				double serviceCharge = Double.parseDouble(serviceChargeInput.getText());
				int numberOfPeople = Integer.parseInt(numberOfPeopleInput.getText());


			} else {
				grid.getChildren()
						.removeIf(node -> node instanceof Label && ((Label) node).getText().equals("Service Charge:"));
				grid.getChildren().removeIf(node -> node instanceof TextField
						&& ((TextField) node).getPromptText().equals("Enter Service Charge:"));

				grid.getChildren().removeIf(
						node -> node instanceof Label && ((Label) node).getText().equals("Number Of People:"));
				grid.getChildren().removeIf(node -> node instanceof TextField
						&& ((TextField) node).getPromptText().equals("Enter The number of people:"));
			}

		});

		// Pizza size
		Label sizeLabel = new Label("Pizza Size:");
		GridPane.setConstraints(sizeLabel, 0, 2);

		ComboBox<String> sizeComboBox = new ComboBox<>();
		sizeComboBox.getItems().addAll("Small", "Medium", "Large");
		sizeComboBox.setValue("Small");
		GridPane.setConstraints(sizeComboBox, 1, 2);

		// Toppings

		Label toppingsLabel = new Label("Toppings:");
		GridPane.setConstraints(toppingsLabel, 0, 3);

		CheckBox onionCheckBox = new CheckBox("Onions");
		GridPane.setConstraints(onionCheckBox, 1, 3);

		CheckBox oliveCheckBox = new CheckBox("Olives");
		GridPane.setConstraints(oliveCheckBox, 1, 4);

		CheckBox greenPepperCheckBox = new CheckBox("Green Peppers");
		GridPane.setConstraints(greenPepperCheckBox, 1, 5);

		// Order price
		Label priceLabel = new Label("Order Price:");
		GridPane.setConstraints(priceLabel, 0, 6);

		// Text priceText = new Text();
		Label priceValue = new Label("0.0");
		GridPane.setConstraints(priceValue, 1, 6);

		// ProcessOrder button
		Button processOrder = new Button("Process Order");
		processOrder.setOnAction(e -> {
		    // Get the data from the text fields
		  String  customerName = nameInput.getText();
		   int pizzaSize = sizeComboBox.getSelectionModel().getSelectedIndex() + 1;
		//   double tripRate = Double.parseDouble(tripRateInput.getText());
		  // int zone = Integer.parseInt(zonein.getText());
		   //double serviceCharge = Double.parseDouble(serviceChargelabelin.getText());
		   //int numberOfPeople = Integer.parseInt(numberOfPeoplein.getText());

		    // Check which type of order the user selected
		    if (deliveryRadioButton.isSelected()) {
		        // Create a new DeliveryOrder object with the data
		        Delivery order = new Delivery(customerName, pizzaSize, 3, toppingPrice, tripRate, zone);
		        orders.add(order);
		        priceValue.setText(String.valueOf(order.calculateOrderPrice()));
		    } else if (toGoRadioButton.isSelected()) {
		        // Create a new ToGoOrder object with the data
		        ToGo order = new ToGo(customerName, pizzaSize, 3, toppingPrice);
		        orders.add(order);
		        priceValue.setText(String.valueOf(order.calculateOrderPrice()));
		    } else if (seatedRadioButton.isSelected()) {
		        // Create a new SeatedOrder object with the data
		        Seated order = new Seated(customerName, pizzaSize, 3, toppingPrice, serviceCharge, numberOfPeople);
		        orders.add(order);
		        priceValue.setText(String.valueOf(order.calculateOrderPrice()));
		    }
		});

		GridPane.setConstraints(processOrder, 1, 7);

		// PrintOrders button
		Button goback = new Button("GO back to order");
		GridPane grid1 = new GridPane();

		Button PrintOrders = new Button("Print Orders");
		PrintOrders.setOnAction(e -> {
			primaryStage.setScene(scene2);

		//	GridPane grid1 = new GridPane();
			grid1.setVgap(10);
			grid1.setHgap(10);
			grid1.setPadding(new Insets(10, 10, 10, 10));

			// Sort the orders by price
			Collections.sort(orders, new Comparator<PizzaOrder>() {
				@Override
				public int compare(PizzaOrder o1, PizzaOrder o2) {
					return Double.compare(o1.calculateOrderPrice(), o2.calculateOrderPrice());
				}
			});

			// Display the customer names and order prices
			int rowIndex = 0;
			for (PizzaOrder order : orders) {
				Label nameLabel1 = new Label(order.getCustomerName());
				Label priceLabel1 = new Label(String.valueOf(order.calculateOrderPrice()));
				grid1.add(nameLabel1, 0, rowIndex);
				grid1.add(priceLabel1, 1, rowIndex);
				rowIndex++;
			}
			
			// Set the scene to display the GridPane
			scene2 = new Scene(grid1, 600, 600);
			primaryStage.setScene(scene2);
			
			
			
		});
		
		goback.setOnAction(e -> primaryStage.setScene(scene));
		goback.setAlignment(Pos.BOTTOM_RIGHT);
		grid1.add(goback,3,8);

		GridPane.setConstraints(PrintOrders, 2, 7);

		// Reset button
		Button Reset = new Button("Reset");
		Reset.setOnAction(e -> {
			// Reset the items and fields to their default values

			nameInput.setText("");
			onionCheckBox.setSelected(false);
			oliveCheckBox.setSelected(false);
			greenPepperCheckBox.setSelected(false);
			toGoRadioButton.setSelected(true);
			sizeComboBox.setValue("Small");
			priceValue.setText("");

			// Remove all the orders from the ArrayList
			orders.clear();

		});
		GridPane.setConstraints(Reset, 3, 7);

		grid.getChildren().addAll(nameLabel, nameInput, typeLabel, toGoRadioButton, deliveryRadioButton,
				seatedRadioButton, sizeLabel, sizeComboBox, toppingsLabel, onionCheckBox, oliveCheckBox,
				greenPepperCheckBox, priceLabel, priceValue, processOrder, PrintOrders, Reset);

		scene = new Scene(grid, 600, 600);

		primaryStage.setScene(scene1);
		primaryStage.show();

		// background
		Image img = new Image("pizza.png");
		BackgroundImage pic = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround = new Background(pic);
		// bGround.getOutsets();
		layout1.setBackground(bGround);

		Image img1 = new Image("pizzaa.png");
		BackgroundImage pic1 = new BackgroundImage(img1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround1 = new Background(pic1);
		// bGround.getOutsets();
		grid.setBackground(bGround1);

	}

	

	public static void sortOrders(ArrayList<PizzaOrder> orders) { // sorting the orders based on total price using
		// colllection.sort method (with compareto used in
		// pizzaOrder class)
		Collections.sort(orders);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
