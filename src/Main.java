import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author H. Roy
 *
 */
public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] terms = new String[]{"Reynolds", "NASA", "Prandtl", "flow", "pressure", "boundary", "shock"};
		String outComIndxFileName = "compressed-file.out";
		String outUncomIndxFileName = "uncompressed-file.out";
		String outDocsStatFileName = "doc-stats-file.out";
		
		String[] inFilesDr = {"",""};
		long startTime = System.currentTimeMillis();
				
		if(args.length == 2)
		{
//			System.out.println(args[0]+", " + args[1]);
			inFilesDr[0] = args[0];
			inFilesDr[1] = args[1];
		}
		else
		{
			System.err.println("Please provide the directory locations...");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while (true) {				
				try {					
					inFilesDr = br.readLine().split("\\s+");			
					if(inFilesDr.length == 2) 
						break;
					else 
						System.err.println("Sorry! Please enter again...");
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		}
		
		if(inFilesDr[0].equalsIgnoreCase("exit")) System.exit(1);
		
		if(inFilesDr.length==2)System.out.println("Given input directory: "+inFilesDr[0]+", "+inFilesDr[1]);
				
		Index obIndex = new Index();
		try {
			obIndex.indexFiles(inFilesDr);
		} catch (IOException e) {
			System.err.println("Exception in indexing file.");
			e.printStackTrace();
		}
		
		long indexingTime = System.currentTimeMillis() - startTime;
		
		System.out.println("");
        System.out.println("The elapsed time (wall-clock time) required to build the index: " + indexingTime + " msec");
        System.out.println("The size of the index uncompressed (in bytes): " + obIndex.getUncompressedIndexSize()+ " bytes");
        System.out.println("The size of the index compressed (in bytes): " + obIndex.getCompressedIndexSize()+ " bytes");
        System.out.println("The number of inverted lists in the index: " + obIndex.getIndexLength());        
        System.out.println("");
        System.out.println(String.format("%-15s | %-10s | %-5s | %-5s | %-40s | %-40s","Term", "Stem", "df", "tf", "Uncompressed-inverted-list-len(bytes)", "Compressed-inverted-list-len(bytes)"));

        for (String term : terms) {
            PostingList token = obIndex.getStemDetails(term);
            if (token == null) {
                System.out.println(String.format("%-15s | %-10s | %-5d | %-5d | %-40d | %-40d", term, "", 0, 0, 0, 0));
                continue;
            }
            System.out.println(String.format("%-15s | %-10s | %-5d | %-5d | %-40d | %-40d", term, obIndex.getStemmer(term),token.getNumberofDocs(),token.getFrequency(),obIndex.getUncompressedInvertedListLength(term),obIndex.getCompressedInvertedListLength(term)));
        } 
        
        System.out.println("");
        try {
        	obIndex.generateDocsStatOutFile(outDocsStatFileName);
        	obIndex.generateUncompressedIndexOutFile(outUncomIndxFileName);
            obIndex.generateCompressedIndexOutFile(outComIndxFileName);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
