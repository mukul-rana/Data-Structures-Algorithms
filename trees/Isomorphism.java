public class practice
{
  public static String removeWord(final String sentence, final String word) 
	{
		String newSentence = sentence.replace(word, "");
		newSentence = newSentence.replaceAll("\\s+", " ");
		return newSentence;
  }
}