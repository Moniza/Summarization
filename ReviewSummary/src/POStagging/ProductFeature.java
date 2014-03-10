package POStagging;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import TFIDFCalculator.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import TFIDFCalculator.*;

public class ProductFeature {

	/**
	 * @param args
	 */
		void summary(Map sen_score) throws ClassNotFoundException, IOException
		{
			String summary="";
			MaxentTagger tagger = new MaxentTagger("taggers/bidirectional-distsim-wsj-0-18.tagger");
			Set set=sen_score.entrySet();
			Iterator it=set.iterator();
			while(it.hasNext())
			{
				Map.Entry me=(Entry) it.next();
				String sentence=(String) me.getKey();
				String tagged = tagger.tagString(sentence);
				if(tagged.contains("JJ"))
				{
					summary=summary+sentence+"\r\n";
				}
			}
			System.out.println("*********************************");
			System.out.println(summary);
		}
		
		public static void main(String[] arg) throws IOException, ClassNotFoundException 
		{
		
			String Bname="da-vinci-code-Review.txt";
			TF3IDF1 ob1=new TF3IDF1();
			String bookReview=ob1.ExtractBookReview(Bname);
			Map sortedTerms=ob1.sort(ob1.ComputeTFIDF(ob1.ExtractReviewTermsFromIndex(ob1.RetriveBookId(Bname))));
			ValueAssignment va=new ValueAssignment();
			Map sen_score=va.GenerateSummary(bookReview, va.addScore(sortedTerms));
			
			va.ShowSummary(sen_score);
			
			ProductFeature prs=new ProductFeature();
			prs.summary(sen_score);
			
			
		}
		
			
	

}
