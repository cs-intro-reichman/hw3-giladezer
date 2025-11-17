/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1);
		str2 = preProcess(str2);

		str1 = preProcess(str1).replace(" ", "");
		str2 = preProcess(str2).replace(" ", "");

		if (str1.length() != str2.length()) {
			return false;
		}

		for (char c : str1.toCharArray()) {
			int index = str2.indexOf(c);
			if (index == -1) {
				return false;
			}
			str2 = str2.substring(0, index) + str2.substring(index + 1);
		}

		return str2.isEmpty();
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		StringBuilder finalStr = new StringBuilder();
		for (char c : str.toCharArray()) {
			if (Character.isLetter(c)) {
				finalStr.append(Character.toLowerCase(c));
			} else if (c == ' ') {
				finalStr.append(c);  // preserve spaces
			}
		}
		return finalStr.toString();
	}
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		char[] chars = new char[str.length()];
		boolean[] used = new boolean[str.length()];
		String anagram = "";
		for (int i = 0; i < str.length(); i++) {
			int index = (int)(Math.random() * str.length());
			while (used[index]) {
				index = (int)(Math.random() * str.length());
			}
			chars[i] = str.charAt(index);
			used[index] = true;
		}	
		for (char c : chars) {
			anagram += c;
		}
		return anagram;
		
	}
}
