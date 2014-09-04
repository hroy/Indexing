
import java.util.TreeSet;

/**
 * 
 * @author H. Roy
 *
 */
public interface Words {

	/**
	 * get total number of tokens
	 * @return
	 */
	int countTotalTokens();
	/**
	 * count the unique words
	 * @return
	 */
	int countUniqueWords();
	/**
	 * count the single occuring words
	 * @return
	 */
	int countSingleOccuringWords();
	/**
	 * get the given number of most frequent words
	 * @param number
	 * @return
	 */
	TreeSet<PostingList> getMostFrequentWordsWithFrequency(int number);
	/**
	 * get the average number of tokens
	 * @return
	 */
	double getNumberOfAverageTokens();
}
