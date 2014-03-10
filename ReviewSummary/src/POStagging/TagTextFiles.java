package POStagging;

import TFIDFCalculator.*;
import IndexCreation.Index;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.HTML.Tag;
import javax.xml.crypto.Data;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class TagTextFiles {
	static int avg = 0;

	Map<String, Float> taggingSentences() throws ClassNotFoundException,
			IOException {
		Map<String, Float> adj_score = new HashMap<String, Float>();
		Map<String, Float> toptweentyTerms = new HashMap<String, Float>();
		MaxentTagger tagger = new MaxentTagger(
				"taggers/bidirectional-distsim-wsj-0-18.tagger");
		FileInputStream fr = new FileInputStream(
				"D://summary/sentenceArrangement/best-kept-secret-Review.txt");
		DataInputStream in = new DataInputStream(fr);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		String adj_words = "";
		String adj = "JJ";
		Pattern pt = Pattern.compile("/JJ");
		//String adj = "VBP";
	//	Pattern pt = Pattern.compile("/VBP");
		while ((str = br.readLine()) != null) {

			String tagged = tagger.tagString(str);
			if (tagged.contains(adj)) {
				float count = 0;
				//System.out.println("<<"+tagged);
				adj_words = tagged;
				Matcher match = pt.matcher(adj_words);
				while (match.find()) {
					count++;
				}
				//System.out.println(str);
				adj_score.put(str, count);

			}
		}

		return adj_score;
	}

	void showResult(String[] result) {
		if (result != null){
			//System.out.println("************************");
			for (String s : result) {
				String[] sentence = s.split("\\.");
				for (String sen : sentence) {
					if (sen.isEmpty())
						continue;
					else if (sen.length() > 150)
						continue;
					else
						System.out.println(sen);

				}

			}
		}
		else
			System.out.print("no matching sentence");
	}

	// **********************************************************************//

	public static void main(String arg[]) throws IOException,
			ClassNotFoundException {

		TagTextFiles ttf = new TagTextFiles();
		// ttf.scoreCalculation(adj_score, sen_score);
		Map adj_score = ttf.taggingSentences();
		// System.out.print("done");
		sort s = new sort();
		Map topTwentyTerms = s.sort(adj_score);
		// System.out.println(topTwentyTerms);
		String Bname = "best-kept-secret-Review.txt";
		TF1IDF1 ob1 = new TF1IDF1();
		String bookReview = ob1.ExtractBookReview(Bname);
		Map tfidf = ob1.ComputeTFIDF(ob1.ExtractReviewTermsFromIndex(ob1
				.RetriveBookId(Bname)));
		Map sortedTerms = ob1.sort(tfidf);
		ValueAssignment va = new ValueAssignment();
		Map sen_score = va
				.GenerateSummary(bookReview, va.addScore(sortedTerms));
		va.ShowSummary(topTwentyTerms);
		
		//System.out.println(sen_score);
		Intersection in = new Intersection();
		String[] result = in.intersect(topTwentyTerms, sen_score);
		ttf.showResult(result);
		// String
		// s1=" Maybe the three (or more?) part publication is good for marketing, but how I wish we ";
		// System.out.println(s1.length());

	}

}
