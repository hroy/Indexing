import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author H. Roy
 *
 */
public class IndexCompressed {

	private Map<String, PostingListCompressed> compressedTokens;

	long compressedSize = 0;
	
    public IndexCompressed() {
    	compressedTokens = new HashMap<String, PostingListCompressed>();
    }
    
    public PostingListCompressed put(String key, PostingListCompressed value) {
        return compressedTokens.put(key, value);
    }
    
    public void store(String file) throws FileNotFoundException, IOException{

        ObjectOutputStream obWrite = new ObjectOutputStream(new FileOutputStream(file));
        obWrite.writeObject(compressedTokens);
        obWrite.flush();
    }
    
    public Set<String> keySet() {
        return compressedTokens.keySet();
    }
    
    public PostingListCompressed get(String key) {
        return compressedTokens.get(key);
    }
    
    public long size()
    {
    	compressedSize = 0;
    	long stringSize = 0;
    	long pListSize = 0;
    	for(String stem:compressedTokens.keySet())
    	{
    		PostingListCompressed ptList = compressedTokens.get(stem);
    		Iterator docsIterator = ptList.getDocsList().iterator();
    		while(docsIterator.hasNext())
    		{
    			PostingListCellCompressed ptCell = (PostingListCellCompressed)docsIterator.next();
    			pListSize += ptCell.docId.length + ptCell.stemFreq.length;
    		}
    		stringSize += (2*stem.length());
    	}
    	
    	compressedSize = stringSize + pListSize;
//    	System.out.println("Compressed>>> String size: " + stringSize + " bytes, pList size only: "+ pListSize +" bytes");
    	return compressedSize;
    }
}
