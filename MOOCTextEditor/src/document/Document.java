package document;

/** 
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}

	// This is a helper function that returns the number of syllables
	// in a word.  You should write this and use it in your 
	// BasicDocument class.
	// You will probably NOT need to add a countWords or a countSentences method
	// here.  The reason we put countSyllables here because we'll use it again
	// next week when we implement the EfficientDocument class.
	protected int countSyllables(String word)
	{
		/**
		 * Get the number of syllables in the document.
		 * Words are defined as above.  Syllables are defined as:
		 * a contiguous sequence of vowels, except for a lone "e" at the 
		 * end of a word if the word has another set of contiguous vowels, 
		 * makes up one syllable.   y is considered a vowel.
		 */
		Pattern pattern = Pattern.compile("[AaEeIiOoUuYy]+");
		Matcher matcher = pattern.matcher(word);
		int numSylls = 0;
		boolean flagTest2ndLast = false;
		boolean flagDoubleVowel = false;
		boolean flagLast = false;
		int num;
		int num2 = 0;
		while (matcher.find())
		{
			numSylls = numSylls + 1;
		}
		char lastChar = word.charAt( word.length() - 1);
		boolean lastIsVowel = charIsVowel(lastChar);
		boolean nextLastIsVowel = false;
		//we've counted all the letters, including last one
		
		if (word.length() > 2) 
			// test the next to last
		{
			char nextLastChar = word.charAt( word.length() -2 );
			nextLastIsVowel = charIsVowel(nextLastChar);
			
		}

		if (lastChar == 'e')
		{

			numSylls = numSylls - 1;
			if (numSylls == 0) numSylls = 1;

			if (nextLastIsVowel == true) numSylls --;
			return numSylls;
		}
	
	
	    return numSylls;
	}


	// This is a helper function that returns the number of syllables
	// in a word.  You should write this and use it in your 
	// BasicDocument class.
	// You will probably NOT need to add a countWords or a countSentences method
	// here.  The reason we put countSyllables here because we'll use it again
	// next week when we implement the EfficientDocument class.
	protected int countSyllables_ORI(String word)
	{
		/**
		 * Get the number of syllables in the document.
		 * Words are defined as above.  Syllables are defined as:
		 * a contiguous sequence of vowels, except for a lone "e" at the 
		 * end of a word if the word has another set of contiguous vowels, 
		 * makes up one syllable.   y is considered a vowel.
		 */
		Pattern pattern = Pattern.compile("[AaEeIiOoUuYy]+");
		Matcher matcher = pattern.matcher(word);
		int numSylls = 0;
		boolean flagTest2ndLast = false;
		boolean flagDoubleVowel = false;
		boolean flagLast = false;
		int num;
		int num2 = 0;
		while (matcher.find())
		{
			numSylls = numSylls + 1;
		}
		char lastChar = word.charAt( word.length() - 1);
		boolean lastIsVowel = charIsVowel(lastChar);
		
		//we've counted all the letters, including last one
		
		if (word.length() > 2) 
			// test the next to last
		{
			char nextLastChar = word.charAt( word.length() -2 );
			boolean nextLastIsVowel = charIsVowel(nextLastChar);
			
		}
		boolean flagNextToLast = false;

		if (lastChar == 'e')
		{
			flagNextToLast = true;
			numSylls = numSylls - 1;
			if (numSylls == 0) numSylls = 1;
			num2 ++;
			if (flagDoubleVowel = true) numSylls ++;
			return numSylls;
		}
	
	
	    return numSylls;
	}

	
	/**
	 * @param nextLastChar
	 */
	private boolean charIsVowel(char testChar) {
		boolean flagVowel = false;
		String vowels = "AEIOUYaeioyu";
		if ( vowels.indexOf( Character.toString(testChar) ) > 0) 
		{
			// part of a double vowel ending
			flagVowel = true;
		};
		return flagVowel;
	}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
		double numWords = (double) this.getNumWords();
		double numSent = (double) this.getNumSentences();
		double numSyll = (double) this.getNumSyllables();
		
		double fleschScore = 206.835 - 1.015*(numWords/numSent ) - 84.6*(numSyll/numWords);
	    return fleschScore;
	}
	
	
	
}
