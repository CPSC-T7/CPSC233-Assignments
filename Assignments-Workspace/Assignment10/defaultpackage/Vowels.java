
public class Vowels {

	public static int numOfVowels(String string) {
		string = string.toUpperCase();
		if(string.length() == 1) {
			if("AEIOU".contains(string)) {
				return 1;
			}
			else return 0;
		}
		else {
			int mid = string.length()/2;
			return numOfVowels(string.substring(0, mid)) + numOfVowels(string.substring(mid));
		}
	}

}
