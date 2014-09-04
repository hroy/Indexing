import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author H. Roy
 *
 */
public class PostingList{

	public int frequency = 0;
	private List<PostingListCell> docsList;
	
	public PostingList()
	{
		this.docsList = new ArrayList<PostingListCell>();
		this.frequency = 0;
	}
	
	/**
	 * set the frequency of a word
	 * @param word
	 * @param frequency
	 */
	public void setFrequency(int frequency)
	{
		this.frequency = frequency;
	}
	
	/**
	 * get the frequency of a word
	 * @return
	 */
	public Integer getFrequency()
	{
		return this.frequency;
	}
		
	public int getNumberofDocs() 
	{
        return docsList.size();
    }

    public List<PostingListCell> getDocsList() 
    {
        return docsList;
    }

    public boolean add(PostingListCell cell) 
    {
    	frequency += cell.getStemFreq();
        return docsList.add(cell);
    }
    
    public int getPostingListLength() 
    {
        return 8 * docsList.size();
    }
}
