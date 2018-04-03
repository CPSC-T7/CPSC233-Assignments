/**
 * 
 * Assignment 10: Recursive
 * Contains static method numOfVowels done recursively
 * @author colinauyeung
 *
 */
public class Vowels {

	/**
	 * Counts number of vowels recursively
	 * @param string
	 * 		String to count vowels in 
	 * @return int - number of vowels in string
	 */
	public static int numOfVowels(String string) {
		
		//Ensures that the the string in a consistent case
		string = string.toUpperCase();
		
		//Base case: if the string is empty, return 0
		if(string.length() == 0) return 0;
		
		//Base case: if the string is a single character...
		if(string.length() == 1) {
			
			//if the string is a vowel return 1
			if("AEIOU".contains(string)) {
				return 1;
			}
			
			//else return 0
			else return 0;
		}
		
		//Recursive step
		else {
			
			//split the string in half and call numOfVowels on both halves then add the results
			int mid = string.length()/2;
			return numOfVowels(string.substring(0, mid)) + numOfVowels(string.substring(mid));
			
		}
		
	}//end of numOfVowels

}//end of Vowels
