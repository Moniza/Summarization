package TFIDFCalculator;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TF2IDF2 {

	
	int collectionSize=0;

	String RetriveBookId(String Bname) throws IOException
	{
		FileInputStream fr=new FileInputStream("D:\\DocToDocID.txt");
		DataInputStream in=new DataInputStream(fr);
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String str;
		while((str=br.readLine())!=null)
		{
			String[] BookID=str.split(":");
			if(BookID[0].equals(Bname))
			{
				return BookID[1];
			}
			
		}
		return null;
		
	/*	while((str=br.readLine())!=null)
		{
			String lowercase=str.toLowerCase();
			String[] BookId=str.split(":");
			if(BookId[0].contains(Bname))
			{
				return BookId[1];
			}
						
			
		}
		
		return null;
	*/
		
		
	
	}
	
	String ExtractBookReview(String Bname) throws IOException
	{
		File f=new File("D://Reviews/");
		File[] folder=f.listFiles();
		String BookReview="";
		collectionSize=folder.length;
				
		for(int i=0;i<folder.length;i++)
		{
			if(folder[i].getName().equals(Bname))
			{
				FileInputStream fr=new FileInputStream(folder[i].getAbsolutePath());
				DataInputStream in=new DataInputStream(fr);
				BufferedReader br=new  BufferedReader(new InputStreamReader(in));
				String str;
				while((str=br.readLine())!=null)
				{
					BookReview=BookReview+str;
				}
			}
		}
		
		return BookReview;
	}
	
	Map<String, String> ExtractReviewTermsFromIndex(String BookID) throws IOException
	{
		Map<String,String> map_Term_DF_TF=new HashMap<>();
		FileInputStream fr=new FileInputStream("D://Index/Index.txt");
		DataInputStream in=new DataInputStream(fr);
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String str;
		while((str=br.readLine())!=null)
		{
			String[] term_DTf=str.split(":");
			
			if(term_DTf[1].contains(BookID))
			{
				//System.out.println(term_DTf[1]);
				String[] a=term_DTf[1].split("D");
				//System.out.println(a.length);
				int DF=a.length-1;
				Pattern pt=Pattern.compile(BookID+"[T][0-9]");
				Matcher match=pt.matcher(term_DTf[1]);
				while(match.find())
				{
					
					String newString=term_DTf[1].substring(match.start(),match.end());
					//System.out.println(newString);
					String[] TF=newString.split(BookID+"T");
					map_Term_DF_TF.put(term_DTf[0],DF+":"+TF[1]);
				}
			}
			
		}
	//	System.out.println(map_Term_DF_TF);
		return map_Term_DF_TF;
		
		
	}
	
	
	Map<String, Float> ComputeTFIDF(Map  map_Term_DF_TF)
	{
		Map<String, Float> Term_TFIDF=new HashMap<String,Float>();
		
		Set set = map_Term_DF_TF.entrySet(); // I need more than 15 terms here
		Iterator it = set.iterator();
		
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();
			String DFTF=(String) me.getValue();
			String[] DF0TF1=DFTF.split(":");
			//System.out.println("<<"+DF0TF1[0]);
			float DF=Float.parseFloat(DF0TF1[0]);
			//System.out.println(">>"+DF0TF1[1]);
			float temp=Float.parseFloat(DF0TF1[1]);
			float TF=(float) Math.log(temp);
			float IDF=(float) (Math.log((collectionSize-DF)/DF));
			float tf_idf=TF*IDF;
			Term_TFIDF.put((String) me.getKey(),tf_idf);
		
		}
		
		return Term_TFIDF;
	}
	
	
	Map<String, Float> sort(Map term_TFIDF)
	{
		
		Map<String,Float> sortedTerms=new HashMap<String,Float>();
		Set<Entry<String, Float>> set = term_TFIDF.entrySet();
        List<Entry<String, Float>> list = new ArrayList<Entry<String, Float>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Float>>()
        {
            public int compare( Map.Entry<String, Float> o1, Map.Entry<String, Float> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
         
        int count=0;
		for(Map.Entry<String, Float> entry:list){
        System.out.println(entry.getKey());//+" ==== "+entry.getValue());
        sortedTerms.put(entry.getKey(),entry.getValue());
        
        	count++;
        	if(count>10)
        	{
        		break;
        	}
         }
     
	return sortedTerms;
	}
		
		
	
	
	
	
	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner(System.in);
		System.out.println("Book name:");//name of book whose review user want to get.
		String Bname="da-vinci-code-Review.txt";
		TF2IDF1 ob1=new TF2IDF1();
		String bookReview=ob1.ExtractBookReview(Bname);
		Map sortedTerms=ob1.sort(ob1.ComputeTFIDF(ob1.ExtractReviewTermsFromIndex(ob1.RetriveBookId(Bname))));
		ValueAssignment va=new ValueAssignment();
		va.ShowSummary(va.GenerateSummary(bookReview, va.addScore(sortedTerms)));
		
		//String Pname=in.next();


	}
	
	
	
}
