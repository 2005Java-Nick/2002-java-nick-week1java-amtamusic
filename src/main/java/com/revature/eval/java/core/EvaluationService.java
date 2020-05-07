package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		//creates array of characters equal to the length of the input string
		char[] reversed = new char[string.length()];
		//fills array with characters in reverse order
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		//returns reversed string
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		//Expression filters out all words and puts them in array
		String[] arr = phrase.split("[^a-zA-Z]+");
		String result="";
		//Takes the first letter of the first word capitalizes it and adds it to the string to form acronym
		for(int i=0;i<arr.length;i++) {
			if(Character.toString(arr[i].charAt(0)).toUpperCase().compareTo(Character.toString(arr[i].charAt(0)).toUpperCase())==0)
			result=result+arr[i].charAt(0);			
		}
		return result.toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			//Validates all sides are equal
			if (this.sideOne==this.sideTwo && this.sideTwo==this.sideThree)
				return true;
			else
				return false;
		}

		public boolean isIsosceles() {
			//if not equilateral or scalene then isosceles.	
			if (!isEquilateral()&&!isScalene())
				return true;
			else
				return false;
		}

		public boolean isScalene() {
			//Verifies that all sides are different sizes
			if(this.sideOne!=this.sideTwo && this.sideTwo!=this.sideThree && this.sideThree!=this.sideOne)
				return true;
			else
				return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {

		int points=0;
		String phrase=string.toUpperCase();
		//Create point system for game.
		String[] onePoint= {"A","E","I","O","U","L","N","R","S","T"};
		String[] twoPoints= {"D","G"};
		String[] threePoints= {"B","C","M","P"};
		String[] fourPoints= {"F","H","V","W","Y"};
		String[] fivePoints= {"K"};
		String[] eightPoints= {"J","X"};
		String[] tenPoints= {"Q","Z"};
		String[][] pointSystem= {{""},onePoint,twoPoints,threePoints,fourPoints,fivePoints,{""},{""},eightPoints,{""},tenPoints};
		
		//Iterates through every letter of the array.
		for(int i=0;i<phrase.length();i++)
		{
			//Iterates through point system to find appropriate value for the given letter.
			for (int j=0;j<pointSystem.length;j++)
			{	
				for(int k=0;k<pointSystem[j].length;k++)
				{
					if(Character.toString(phrase.charAt(i)).equalsIgnoreCase(pointSystem[j][k])) {
						points+=j;
					}
				}
			}
		}
		
		return points;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		//Verifies all character are valid phone number characters.
		if(string.replaceAll("[0-9\\-\\.\\(\\)]+","").trim().length()>1)
		{
			throw new IllegalArgumentException();
		}
		//Removes all spaces and non numbers.
		string=string.replaceAll("[^0-9]+","");
		//validates length of phone number.
		if(string.length()>11)
		{
			throw new IllegalArgumentException();
		}
		
		return string;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		//Creates a map to store each unique word.
		Map<String,Integer> wordCount = new HashMap<String, Integer>();
		//Creates a list of all words.
		String[] words= string.split("[^\\w]+");
		//iterates through the words and checks if word is unique if so increments counter else initializes at 1.
		for (String word : words)
	    {
	        if(wordCount.containsKey(word))
	        {
	            wordCount.put(word, wordCount.get(word)+1);
	        }
	        else
	        {
	            wordCount.put(word, 1);
	        }

	    }
	    return wordCount;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>> {
		private List<T> sortedList;


		public int indexOf(T t) {
			//Can easily solve by using the next three lines of code
			/*T[] elements=(T[]) sortedList.toArray();
			//runs a binary search assuming sortedList is actually sorted.
			//return Arrays.binarySearch(elements, t);
			*/
			
			int indexStart = 0;
			int indexEnd= sortedList.size()-1;
			//Cycles through each subset of the array verifying if the middle element is equal to the given element
			while(indexStart<=indexEnd)
			{
				int middle=((indexEnd-indexStart)/2)+1;
				//If equal return result 
				if(sortedList.get(indexStart+middle).compareTo(t)==0)
				{
					return indexStart+middle;
				//If element is greater increment the start index
				}else if(sortedList.get(indexStart+middle).compareTo(t)<0)
				{
					indexStart+=middle;
				//If element is lower then decrease the end index
				}else
				{
					indexEnd-=middle;
				}
			}
			return 0;
			
		}
		

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {

		//Creates a list of all words.
		String[] words = string.split("[^a-zA-Z]+");
		//Vowel array
		char [] vowels= {'a','e','i','o','u'};
		String result="";
		//Cycles through all the words in the sentence.
		for(String word : words)
		{
			string = word;
			String ending="ay";
			int index=0;
			boolean isvowel=false;
			String shiftString="";
			//cycles through string until a vowel is found.
			outer:
			while (!isvowel && index<string.length())
			{
				for(int i=0;i<vowels.length;i++)
				{
					if(string.substring(index, index+1).equalsIgnoreCase(vowels[i]+""))
					{
						//Validates special case words starting with qu.
						if(index==1 && string.substring(0, index+1).equalsIgnoreCase("qu"))
						{
							shiftString+=string.substring(index, index+1);
							index++;
						}
						isvowel=true;
						break outer;
					}
				}
			//adds letters to result that need to be shifted to the end of the word.
			if(!isvowel)
			{
				shiftString+=string.substring(index, index+1);
			}
			index++;
			}
		//Rebuilds string and adds the ending of the word.
		result+=(string.substring(index)+shiftString+ending+" ");
		}
		return result.trim();
		
		
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		char [] numbersChars=(input+"").toCharArray();
		double [] numbers=new double[numbersChars.length];
		double sum=0;
		//Sums up all the digits to a power equal to the length of the input.
		for(int i=0;i<numbers.length;i++)
		{
			numbers[i]= Double.parseDouble(Character.toString(numbersChars[i]));
			sum+=Math.pow(numbers[i], numbers.length);
		}
		//Validates if result is a strong number.
		if(Double.parseDouble(input+"")==sum)
			return true;
		else
			return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> results =new ArrayList<Long>();
		Long counter=2L;
		//Cycles through each number starting at 2 and determines whether that number is a factor if the input if the counter is prime.
		while(counter<=l)
		{
			if(l%counter==0){
				//If number is a factor adds it to the array.
				if(isPrime(counter))
				{
					results.add(counter);
					l/=counter;
				}
			}else
			{
				counter++;
			}
		}
		return results;
	}
	//Verifies if number is prime.
	public boolean isPrime(long number)
	{
		int counter=0;
		for(int i=1;i<=number;i++)
		{
			if(number%i==0)
			{
				counter++;
			}
		}
		if(counter==2)
		{
			return true;
		}
		return false;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			
			String alphabet="abcdefghijklmnopqrstuvwxyz";
			String part1=alphabet.substring(key);
			String part2=alphabet.substring(0,key);
			String resultShift=part1+part2;
			//Manipulates strings to obtain the cipher from the alphabet.
			char[] oldAlphabet= alphabet.toCharArray();
			char[] cipher = resultShift.toCharArray();
			String result="";
			//Cycles through the string letter by letter swapping characters.
			for(int i=0;i<string.length();i++)
			{
				//verifies if character is in the alphabet if not adds it to result.
				if(alphabet.contains(string.substring(i, i+1).toLowerCase()))
				{
					//Replaces character with respective cipher character.
					for(int j=0;j<oldAlphabet.length;j++)
					{
						//Validates Character Casing.
						if(string.substring(i, i+1).equals(Character.toString(oldAlphabet[j])))
						{
							result=result+cipher[j];
						}
						else if(string.substring(i, i+1).equals(Character.toString(oldAlphabet[j]).toUpperCase()))
						{
							result=result+Character.toString(cipher[j]).toUpperCase();
						}
						
					}
				}else
				{
					result=result+string.substring(i, i+1);
				}
			}
			return result;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) throws IllegalArgumentException {
		int counter=0;
		int index=2;
		//Cycles through number verifying if they are prime and increments index.
		if(i!=0)
		{
			while (counter<i)
			{
				if(isPrime(index))
					counter++;
				if(counter!=i)
					index++;
			}
		}else
		{
			throw new IllegalArgumentException();
		}
		return index;
	}
	//Verifies if number is prime.
	public boolean isPrime(int number)
	{
		int counter=0;
		for(int i=1;i<=number;i++)
		{
			if(number%i==0)
			{
				counter++;
			}
		}
		if(counter==2)
		{
			return true;
		}
		return false;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		static char[] cipherCharacters=("zyxwvutsrqponmlkjihgfedcba").toCharArray();
		static char[] cipherOriginal=("abcdefghijklmnopqrstuvwxyz").toCharArray();
		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			//filters string and removes punctuation
			string =string.replaceAll("[^\\w']+","");
			String result="";
			//Cycles through string and divides result by presenting it 5 characters at a time.
			for(int i=0;i<string.length();i++)
			{
				//Adds space every 5 characters 
				if(i%5==0 && i!=0)
				{
					result+=" ";
				}
				//Encode message logic : iterates through string and replaces characters accordingly
				boolean found=false;
				for(int j=0;j<cipherOriginal.length;j++)
				{
					if(string.substring(i, i+1).toLowerCase().equals(Character.toString(cipherOriginal[j])))
					{
						result+=Character.toString(cipherCharacters[j]);
						found=true;
						break;
					}
				}
				if(!found)
				{
					result+=string.substring(i, i+1);
				}
			}
			return result;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			String result="";
			//Filters unwanted characters
			string=string.replaceAll("[^a-zA-Z0-9]", "");
			//Decode message logic : iterates through string and replaces characters accordingly
			for(int i=0;i<string.length();i++)
			{
				boolean found=false;
				for(int j=0;j<cipherCharacters.length;j++)
				{
					if(string.substring(i, i+1).toLowerCase().equals(Character.toString(cipherCharacters[j])))
					{
						result+=Character.toString(cipherOriginal[j]);
						found=true;
						break;
					}
				}
				if(!found)
				{
					result+=string.substring(i, i+1);
				}
			}
			return result;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		String isbn=string;
		//Filters all valid characters
		isbn=isbn.replaceAll("[^0-9xX]+", "");
		//Verifies length of ISBN
		if(isbn.length()!=10)
		{
			return false;
		}
		char[]numbers=isbn.toCharArray();
		int multiplier=10;
		int sum=0;
		//Cycles through digits and multiplies them by the according multiplier
		for(char number:numbers)
		{
			if(!Character.toString(number).equalsIgnoreCase("x"))
			{
				int num=Integer.parseInt(Character.toString(number));
				sum+=num*multiplier;
				multiplier--;
			}else
			{
				int num=10;
				sum+=num*multiplier;
				multiplier--;
			}
		}
		//Finds modulus of sum divided by 11
		if(sum%11==0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {


		String alphabet="abcdefghijklmnopqrstuvwxyz";
		char[] letters= string.toCharArray();
		//cycles through the string and removes the letters found from the alphabet array
		for(char c: letters)
		{
			alphabet=alphabet.replaceAll(Character.toString(c), "");
		}
		if(alphabet.length()==0)
			return true;
		return false;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	
	public Temporal getGigasecondDate(Temporal given) {

		//Validates the type of time instance that was passed in and casts it to the appropriate type.
		if (given instanceof LocalDate)
		{
			LocalDate date = (LocalDate)given;
			LocalDateTime dateTime = date.atStartOfDay();
			//Adds seconds.
			dateTime=dateTime.plus(1000000000,ChronoUnit.SECONDS);
			return dateTime;
		}else
		{
			LocalDateTime date = (LocalDateTime)given;
			date=date.plus(1000000000,ChronoUnit.SECONDS);
			//Adds seconds.
			return date;
		}
		
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		
		int result=0;
		ArrayList<Integer> uniqueMultiples=new ArrayList<Integer>();
		//Cycles through multiples and calls sumSet() method.
		for(int base : set)
		{
			uniqueMultiples=sumSet(i, base,uniqueMultiples);
		}
		//Sums up all the unique multiples.
		for(int j : uniqueMultiples)
		{
			result+=j;
		}
		return result;
	}
	
	//Adds all unique multiples to arrayList that are unique building upon previous multiples.
	public ArrayList<Integer> sumSet(int i, int base,ArrayList<Integer> uniqueMultiples)
	{
		int baseTemp=base;
		//Validates all multiples that come before the given parameter and verifies if they are unique to the array.
		while(base<i)
		{
			boolean found=false;
			for(int j=0;j<uniqueMultiples.size();j++)
			{
				if (uniqueMultiples.get(j)==base)
				{
					found=true;
					break;
				}
			}
			if(!found)
			{
				uniqueMultiples.add(base);
			}
			base+=baseTemp;
		}
		return uniqueMultiples;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		//Verifies existence of invalid characters.
		if(string.trim().split("[a-zA-Z]|[.,!?:;'\\\"-]+").length!=1)
		{
			return false;
		}
		//Filters string and creates an ArrayList of all numbers.
		String[] numFilter = string.split("\\D");
		ArrayList<Integer> numbers= new ArrayList<Integer>();
		for(int i=0;i<numFilter.length;i++) {
			if(numFilter[i].length()!=0)
			{
				for(char c : numFilter[i].toCharArray())
				numbers.add(0,Integer.parseInt(Character.toString(c)));
			}
		}
		//Verifies size of input.
		if (numbers.size()<=1)
		{
			return false;
		}
		int sum=0;
		//Sums up every other character.
		for(int i=0;i<numbers.size();i++)
		{
			
			if(i%2!=0)
				{
				int doubleNum = numbers.get(i)*2;
				if(doubleNum>9)
				{
					doubleNum-=9;
				}
				sum+=doubleNum;
			}
			else
			{
				sum+=numbers.get(i);
			}
		}
		//Validates modulus.
		if(sum%10==0)
		{
			return true;
		}else
		{
			return false;
		}
		
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		
		int selectOperator=0;
		//Establishes what operation will be run.
		if(string.contains("plus"))
		{
			selectOperator=0;
			string = string.replaceAll("plus", "_");
		}
		if(string.contains("minus"))
		{
			selectOperator=1;
			string = string.replaceAll("minus", "_");
		}
		if(string.contains("multiplied"))
		{
			selectOperator=2;
			string = string.replaceAll("multiplied", "_");
		}
		if(string.contains("divided"))
		{
			selectOperator=3;
			string = string.replaceAll("divided", "_");
		}
		//Filters string to get numbers for the operation.
		String numString=string.replaceAll("[^0-9^\\+\\-\\_]", "");
		String[] numbers=numString.split("_");
		int result=0;
		//Evaluates operation based on input.
		if (selectOperator==0)
		{
			result=Integer.parseInt(numbers[0])+Integer.parseInt(numbers[1]);
		}
		if (selectOperator==1)
		{
			result=Integer.parseInt(numbers[0])-Integer.parseInt(numbers[1]);
		}
		if (selectOperator==2)
		{
			result=Integer.parseInt(numbers[0])*Integer.parseInt(numbers[1]);
		}
		if (selectOperator==3)
		{
			result=Integer.parseInt(numbers[0])/Integer.parseInt(numbers[1]);
		}
		
		return result;
	}

}
