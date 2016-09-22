import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * This class contains all the utility functions that deal with ArrayLists
 */

public class UtilityFunctions {
	
	/* getTreeMap(ArrayList<String>) returns a TreeMap that contains (Key, Value)
	 * pairs in the form of (word, frequency).
	 */
	public TreeMap<String, Integer> getTreeMap(ArrayList<String> inputArray){
		
		//Create a TreeMap
		TreeMap<String, Integer> newTreeMap = new TreeMap<>();

		// In our TreeMap, set the (Key, Value) as (Word, Frequency)
		// For example, ("Lincoln", 7) means "Lincoln" appeared 7 times
		for(String word: inputArray){
	
			// Collections.frequency(ArrayList, target word) returns and int
			newTreeMap.put(word, Collections.frequency(inputArray, word));
		}
		
		return newTreeMap;
	}
	
	
	
	
	/* removeNonLetters(ArrayList<String>) takes an ArrayList and crops out all non-letter characters.
	 * It then returns an ArrayList that contains lower cased words
	 */
	public ArrayList<String> removeNonLetters(ArrayList<String> inputArray){
		
		
		ArrayList<String> newWordsArray = new ArrayList<String>(); // create a new ArrayList<String>
		
		// Loop through each element in the inputArray
		for(String word: inputArray){
			
			// Check if a string contains 1 or more characters from a to z and A to Z
			if(word.matches("([a-zA-Z])+")){
				
				newWordsArray.add(word.toLowerCase()); // add to ArrayList
			}
			// Check if a string contains 1 or more characters from a to z and A to Z followed by a punctuation
			else if (word.matches("([a-zA-Z]+[,.:;'!?])")){
				
				// Removes all punctuation that is attached to each word by substituting with ""
				word = word.replaceAll("\\W", "");
			
				newWordsArray.add(word.toLowerCase()); // Add and make it lower-cased
			}
		}
		return newWordsArray;
	}
	
	
	
	/* loadTexts(String) checks if a text file exist
	 * If it exists, read every word into an ArrayList and return that ArrayList.
	 */
	public ArrayList<String> loadTexts(String fileName){
		
		ArrayList<String> arListWords = new ArrayList<String>(); // Create an ArrayList<String>
		
		boolean loop = true; // determines whether the do-while loop will loop again
		
		do{ // Beginning of do-while loop
			
		try{
		
		Scanner scFile = new Scanner(new File(fileName)); // Attempts to open file
		
		while(scFile.hasNext()){ // Read words from file
			arListWords.add(scFile.next());
		}
		
		scFile.close(); // Closes the file
		loop = false;
		
		// If the file is not found, break. This will return arListWords with size = 0
		} catch(FileNotFoundException e){
			break;}
		} while(loop); // End of do-while loop
		
		return arListWords;
		
	}

}
