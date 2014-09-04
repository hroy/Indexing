/**
 * 
 * @author H. Roy
 *
 */
public class Doc {
	private int docId;
	private String docName;
    private int totalTerms;
    private int maxFreq;
    private String mostFreqTerm;

    public Doc(int docId, String docName, int totalTerms, int maxFreq, String mostFreqTerm) {
        this.docId = docId;
        this.docName = docName;
        this.totalTerms = totalTerms;
        this.maxFreq = maxFreq;
        this.mostFreqTerm = mostFreqTerm;
    }

    public int getDocumentId() {
        return docId;
    }
    
    public String getDocumentName() {
        return docName;
    }

    public int getTotalTerms() {
        return totalTerms;
    }

    public int getMaxTermFrequency() {
        return maxFreq;
    }

    public String getMaxOccurringStem() {
        return mostFreqTerm;
    }
}
