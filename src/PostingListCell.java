import java.io.Serializable;

/**
 * 
 * @author H. Roy
 *
 */
public class PostingListCell{
	int docId;
    int stemFreq;

    public PostingListCell(int docId, int wordsFreq) {
        this.docId = docId;
        this.stemFreq = wordsFreq;
    }

    public int getDocId() {
        return docId;
    }


    public int getStemFreq() {
        return stemFreq;
    }
}
