package POStagging;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class tf1idf1_adjective {

 public	void scoreCalculation(Map adj_score,Map sen_score,int avg)
 {
	 Map<String,Float> adj=adj_score;
	 Map<String,Float> SummarySentence=new HashMap<String, Float>();
	 Set set=adj.entrySet();
	 Iterator it=set.iterator();
	 Set set1=sen_score.entrySet();
	 Iterator it1=set1.iterator();
	 while(it.hasNext())
	 {
		 Map.Entry me=(Entry) it.next();
		 String sen_adj=(String) me.getKey();
		 
		 while(it1.hasNext())
		 {
			 Map.Entry me1=(Entry) it1.next();
			 String sen_tfidf=(String) me1.getKey();
			 if(sen_adj.equals(sen_tfidf))
					 {
				 		float adj_count=(float) ((Map) me).get(sen_adj);
				 		float tfidf_count=(float) ((Map) me1).get(sen_tfidf);
				 		((Map) me).put((String)sen_adj,adj_count*tfidf_count);
					 }
		 
					 
		 }
		 
	 }
	 
	 Set s=adj.entrySet();
	Iterator i=s.iterator();
	while(i.hasNext())
	{
		Map.Entry m=(Entry) i.next();
		int score=(int) m.getValue();
		if(score>=avg)
		{
			SummarySentence.put((String)m.getKey(),(float)m.getValue());
					
		}
	}
	System.out.println(SummarySentence);
 }
	
	
}
