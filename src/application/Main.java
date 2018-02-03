package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Dodanie obiektu klasy z metodami koduj i dekoduj
			RLE codingMachine = new RLE();
			//Stworzenie lewego pojemnika typu VBox
			VBox leftContainer = new VBox();
			//Dodanie etykiety dla tekstu źródłowego
			Label sourceLabel = new Label("źródło");
			//Dodanie pola dla tekstu źródłowego
			TextField sourceText = new TextField();
			//Dodanie przycisku "kopiuj"
			Button copyButton = new Button("kopiuj");
			//Dodanie etykiety do lewego pojemnika
			leftContainer.getChildren().add(sourceLabel);
			//Dodanie pola tekstu źródłowego do lewego pojemnika
			leftContainer.getChildren().add(sourceText);
			//Dodanie przycisku "kopiuj" do lewego pojemnika
			leftContainer.getChildren().add(copyButton);	
			leftContainer.setId("left-container");
			
			//Dodanie etykiety dla tekstu przetworzonego
			Label resultLabel = new Label("wynik");			
			//Dodanie pola dla tekstu przetworzonego
			TextField resultText = new TextField();
			//Dodanie przycisku "wykonaj"
			Button executeButton = new Button("wykonaj");

			
			//Dodanie etykiety do lewego pojemnika
			leftContainer.getChildren().add(resultLabel);
			//Dodanie pola tekstu przetworzonego do lewego pojemnika
			leftContainer.getChildren().add(resultText);
			//Dodanie przycisku "wykonaj" do lewego pojemnika
			leftContainer.getChildren().add(executeButton);
			
			//Stworzenie prawego pojemnika typu VBox
			VBox rightContainer = new VBox();
			//Stworzenie przycisków radiowych
			RadioButton codingButton = new RadioButton("kodowanie");
			RadioButton decodingButton = new RadioButton("dekodowanie");
			//Stworzenie grupy dla przycisków radiowych
			final ToggleGroup codingGroup = new ToggleGroup();
			//Ustawienie grupy dla przycisków radiowych
			codingButton.setToggleGroup(codingGroup);
			decodingButton.setToggleGroup(codingGroup);
			//Dodanie przycisków radiowych do prawego pojemnika
			rightContainer.getChildren().add(codingButton);
			rightContainer.getChildren().add(decodingButton);
			rightContainer.setId("right-container");
			
			//Dodanie zdarzenia do przycisku "wykonaj"
			executeButton.setOnAction(
					event->{
						if(codingButton.isSelected())
							resultText.setText(codingMachine.encode(sourceText.getText()));
						else if(decodingButton.isSelected())
							resultText.setText(codingMachine.decode(sourceText.getText()));
					}
				);
			
			//Dodanie zdarzenia do przycisku "kopiuj"
			copyButton.setOnAction(
					event->{
						sourceText.setText(resultText.getText());
						resultText.setText("");
					}
				);
			
			
			//Dodanie pojemników do okna
			root.setLeft(leftContainer);
			root.setRight(rightContainer);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
