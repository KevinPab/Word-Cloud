import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ReadAndDisplay {

	public static void main(String[] args) {
		
		System.out.print("Enter file name(no spaces): ");

		// String fileName = new Scanner(System.in).next();
		
		ArrayList<String> alWords = getWordsFromFile("speech");
		
		alWords = getCroppedWords(alWords);
		
		Collections.sort(alWords);
		
		System.out.print(alWords);
		
	}
	
	/* A function that takes an ArrayList and crops out all the punctuation.
	 * It then returns an ArrayList that contains lower case words with no punctuation
	 */
	public static ArrayList<String> getCroppedWords(ArrayList<String> inputArray){
		
		
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
	public static ArrayList<String> getWordsFromFile(String fileName){
		
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
			
			System.out.print(fileName + " is not found\nPlease re-enter file name: ");
			fileName = (new Scanner(System.in).next());
		}
		} while(loop); // End of do-while loop
		
		return arListWords;
		
	}
}
