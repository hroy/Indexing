import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 
 * @author H. Roy
 *
 */
public class IndexUncompressed {
	private Map<String, PostingList> uncompressedTokens;

	long uncompressedSize = 0;
	
    public IndexUncompressed() {
    	uncompressedTokens = new HashMap<String, PostingList>();
    }
    
    public void put(String token, int docId, int freq) {

        if (!uncompressedTokens.containsKey(token)) {
        	PostingList pList = new PostingList();
            pList.add(new PostingListCell(docId, freq));
            uncompressedTokens.put(token, pList);
        } 
        else {
        	PostingList pList = uncompressedTokens.get(token);
            pList.add(new PostingListCell(docId, freq));
        }
    }
    
    public void store(String file) throws FileNotFoundException, IOException{

        ObjectOutputStream obWrite = new ObjectOutputStream(new FileOutputStream(file));
        obWrite.writeObject(uncompressedTokens);
        obWrite.flush();
    }
    
    public Set<String> keySet() {
        return uncompressedTokens.keySet();
    }
    
    public PostingList get(String key) {
        return uncompressedTokens.get(key);
    }
    
    public long size()
    {
    	uncompressedSize = 0;
    	long stringSize = 0;
    	long pListSize = 0;
    	for(String stem:uncompressedTokens.keySet())
    	{
    		PostingList ptList = uncompressedTokens.get(stem);
    		uncompressedSize += (2*stem.length()) + 4 + ptList.getPostingListLength();
    		stringSize += (2*stem.length());
    		pListSize += ptList.getPostingListLength();
    	}
//    	System.out.println("Uncompressed>>> String size: " + stringSize +" bytes, total freq size: " + 4*uncompressedTokens.size()+" bytes, pList size only: "+ pListSize + " bytes");
    	return uncompressedSize;
    }
}
