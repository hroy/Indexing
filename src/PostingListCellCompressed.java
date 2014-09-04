import java.io.Serializable;
import java.util.BitSet;


/**
 * 
 * @author H. Roy
 *
 */
public class PostingListCellCompressed{

	byte[] docId;
	byte[] stemFreq;

    public PostingListCellCompressed(byte[] docId, byte[] stemFreq) {
        this.docId = docId;
        this.stemFreq = stemFreq;
    }

    public byte[] getDocId() {
        return docId;
    }

    public byte[] getStemFreq() {
        return stemFreq;
    }
}
