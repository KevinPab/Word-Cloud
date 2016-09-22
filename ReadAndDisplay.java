import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReadAndDisplay extends Application{

	
	// Override the start() method
	@Override
	public void start(Stage primaryStage){
		
		final double MAX_FONT = 75.0; // Maximum font that a word can have
		
		Button btInput = new Button("Read File"); // Button
		
		TextField tfInput = new TextField(); // Text Field to type the file name
		
		FlowPane fpPane = new FlowPane(); // Pane to display text results
		fpPane.setHgap(7);
		
		HBox hbTop = new HBox(); // Stores the Label, Text Field, and Button horizontally
	
		hbTop.getChildren().addAll(new Label("Enter file name:"), tfInput, btInput); // Add children		
		hbTop.setAlignment(Pos.CENTER); // Center all the elements
		hbTop.setSpacing(10); // Make each element 10 units apart

		BorderPane bdPane = new BorderPane(); // Create a border pane to store the HBox
		bdPane.setTop(hbTop); // Add HBox to the top of border pane
		bdPane.setCenter(fpPane);
		
		// Perform the read and display processes when the "Read" button is clicked by the user
		btInput.setOnAction(e -> {
			
			String fileName = tfInput.getText(); // Initialize fileName to the string type in the text field
			
			// UtilityFunctions Class contains all utility methods that deal with editing strings in an ArrayList
			UtilityFunctions wordUtil = new UtilityFunctions();
			
			// Initialize the ArrayList or words by calling getWordsFromFile(String)
			ArrayList<String> alWords = wordUtil.getWordsFromFile(fileName);
			
			// Print "Invalid File" in the text field if file is not found
			if(alWords.size() <= 0){
				tfInput.setText("Invalid file!");
			}
			
			// Remove all non-letter characters from the strings
			alWords = wordUtil.removeNonLetters(alWords);
			
			Collections.sort(alWords); // Sort in ascending order
			
			// Create a TreeMap with (Key, Value) where Key = Word and Value = frequency
			Map<String, Integer> treeMap = new TreeMap<>(wordUtil.getSortedMap(alWords));
			
			double maxFrequency = 0; // Max frequency of a word. Will be used to determine max font printed
			
			// Determine the max frequency 
			for(int i: treeMap.values()){
				maxFrequency = (maxFrequency <= i)?i:maxFrequency;
			}

			// Get an entry set to be able to access the Key and Value in our TreeMap
			// **Reference to page 815 code line 29
			Set<Map.Entry<String, Integer>> entrySet = treeMap.entrySet();
			
			double fontSize = 0; // Will be used to set the font size of each word
			
			// A for loop that adjusts the font and cumulatively adds them to the FlowPane
			for(Map.Entry<String, Integer> word: entrySet){
				
				Text text = new Text(); // Create a Text 
				text.setText(word.getKey()); // Set the text
				
				// Calculate font size by MAX_FONT * (ratio of each frequency to Max frequency) 
				fontSize = MAX_FONT * (word.getValue())/maxFrequency;

				// Font size below 12 is not readable, so set it to 12 by default
				fontSize = (fontSize < 11)? 11: fontSize; 
				
				text.setFont(new Font(fontSize)); // Set the font size
				
				text.setFill(Color.color(Math.random(), Math.random(), Math.random())); // Set a random color
				
				fpPane.getChildren().add(text);
			}
			
	
		});
		
		Scene scene = new Scene(bdPane, 700, 700);
		
		primaryStage.setTitle("Word Cloud");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	} // End of start() method
	
	
	// Main function to launch the application
	public static void main(String[] args) {
		
		Application.launch();
		
	}

}
