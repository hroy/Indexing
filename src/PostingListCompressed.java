import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author H. Roy
 *
 */
public class PostingListCompressed{
    private List<PostingListCellCompressed> docsList;

    public PostingListCompressed(List<PostingListCellCompressed> docsList) {
        this.docsList = docsList;
    }

    public int getNumberofDocs() {
        return docsList.size();
    }

    public List<PostingListCellCompressed> getDocsList()
    {
    	return this.docsList;
    }
    
    public boolean add(PostingListCellCompressed wcCompressed) {
        return docsList.add(wcCompressed);
    }
}
