package POStagging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class sort {
	public Map<String, Float> sort(Map term_TFIDF)
	{
		
		Map<String,Float> sortedTerms=new HashMap<String, Float>();
		//String toptwenty = "";
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
        //System.out.println(entry.getKey());//+" ==== "+entry.getValue());
        sortedTerms.put(entry.getKey(),entry.getValue());
       // toptwenty=toptwenty+entry.getKey()+"::";
        	count++;
        	if(count>10)
        	{
        		break;
        	}
         }
     
		//System.out.println(sortedTerms);
	return sortedTerms;
	}
}
