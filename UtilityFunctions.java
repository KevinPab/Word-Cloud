import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * Contains all the utility functions that deal with ArrayList of strings
 */

public class UtilityFunctions {
	
	public Map<String, Integer> getSortedMap(ArrayList<String> inputArray){
		
		Map<String, Integer> treeMap = new TreeMap<>();

		for(String word: inputArray){
			treeMap.put(word, Collections.frequency(inputArray, word));
		}
		
		
		return treeMap;
	}
	
	/* A function that takes an ArrayList and crops out all the punctuation.
	 * It then returns an ArrayList that contains lower case words with no punctuation
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
			
				newWordsArray.add(word.toLowerCase());
			}
		}
		return newWordsArray;
	}
	
	
	/* A function that checks if a text file exist
	 * If it exists, read every word into an ArrayList and return that ArrayList.
	 * Else, prompt for a new file name from the user.
	 */
	public ArrayList<String> getWordsFromFile(String fileName){
		
		ArrayList<String> arListWords = new ArrayList<String>(); // Create an ArrayList<String>
		
		boolean loop = true; // determines whether the do-while loop will loop again
		
		do{ // Beginning of do-while loop
		// Try to open a file
		try{
		
		Scanner scFile = new Scanner(new File(fileName)); // Attempts to open file
		
		while(scFile.hasNext()){ // Read words from file
			arListWords.add(scFile.next());
		}
		
		scFile.close(); // Closes the file
		loop = false;
		
		// If the file is not found, prompt for a new file name
		} catch(FileNotFoundException e){
			break;
		}
		} while(loop); // End of do-while loop
		
		return arListWords;
		
	}

}
