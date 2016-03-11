package document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 
 * A naive implementation of the Document abstract class. 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document 
{
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text)
	{
		super(text);
	}
	
	
	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		//TODO: Implement this method.  See the Module 1 support videos 
	    // if you need help.
		List<String> tokens = new ArrayList<String>();
		tokens = this.getTokens("[A-Za-z]+");
		tokens.removeAll(Collections.singleton(null));
		int numWords = tokens.size();
	    return numWords;
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * @return the last white-space delimited word in the document
	 */
	public String getLastWord()
	{
		List<String> theseWords = new ArrayList<String>();
		theseWords = this.getTokens("[A-Z,a-z]+");
		String lastWordTrimmed = theseWords.get(theseWords.size() - 1);
		int indexLast = this.getText().lastIndexOf(lastWordTrimmed);
		String lastWord = this.getText().substring(indexLast);
		//String lastWord = theseWords.get(theseWords.size() - 1);
		return lastWord;
		
				
	}
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
	    //TODO: Implement this method.  See the Module 1 support videos 
        // if you need help.
		List<String> tokens = new ArrayList<String>();
		tokens = this.getTokens("[A-Za-z]+");
		//tokens.removeAll(Collections.singleton(null));
		while(tokens.remove(null));
		int numWords = tokens.size();
		
		//System.out.println(Integer.toString(numWords));
		List<String> tokensEOL = new ArrayList<String>();
		tokensEOL = this.getTokens("[!?.]+");
		
		// grab last token from all words
		//String lastToken = tokens.get(numWords-1);
		//List<String> lastWordToken = new ArrayList<String>();
		
		boolean flagLastWordSentance = false;
		
		if (tokens.size() > 0)
		{
		    String lastWord =this.getLastWord();
			if ( lastWord.indexOf('!') > 0 ) flagLastWordSentance = true;
			if ( lastWord.indexOf('?') > 0 ) flagLastWordSentance = true;
			if ( lastWord.indexOf('.') > 0 ) flagLastWordSentance = true;
			
			if ( flagLastWordSentance ) return tokensEOL.size();
			else return (tokensEOL.size() + 1);
		}
		else return 0;


	}
	
	/**
	 * Get the number of syllables in the document.
	 * Words are defined as above.  Syllables are defined as:
	 * a contiguous sequence of vowels, except for a lone "e" at the 
	 * end of a word if the word has another set of contiguous vowels, 
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{

		List<String> tokens = new ArrayList<String>();
		List<String> syllTokens = new ArrayList<String>();
		syllTokens = this.getTokens("[AaEeIiOoUuYy]+");
		
		tokens = this.getTokens("[A-Za-z]+");
		
		tokens.removeAll(Collections.singleton(null));
		syllTokens.removeAll(Collections.singleton(null));
		int numSylls = 0;
		int deadCount = 0;
		for (String word : tokens)
		{
			int localCount = 0;
			localCount = this.countSyllables(word);
			numSylls = numSylls + localCount;
			deadCount ++;
		}
		
	    return numSylls;
	}
	
	
	/* The main method for testing this class. 
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		/*testCase(new BasicDocument("This is a test.  How many???  "
		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
		        + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new BasicDocument("Segue"), 2, 1, 1);
		*/testCase(new BasicDocument("Sentence"), 2, 1, 1);
		testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
		
		
	}
	
}
