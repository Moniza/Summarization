package TFIDFCalculator;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class ValueAssignment {

	/*
	 * String readReview(File files) throws IOException {
	 * 
	 * FileInputStream fr = new FileInputStream(files.getAbsoluteFile());
	 * DataInputStream in = new DataInputStream(fr); BufferedReader br = new
	 * BufferedReader(new InputStreamReader(in)); String str; String text = "";
	 * while ((str = br.readLine()) != null) { text = text + str; } return text;
	 * }
	 */
	@SuppressWarnings("unchecked")
	public
	Map<String, Float> addScore(Map SortedTerms) throws IOException {
		/**
		 * Input :
		 * 
		 * Output:
		 */
		Map<String, Float> score = new HashMap<String, Float>();
		Set set = SortedTerms.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry me = (Entry) it.next();
			float n = (float) me.getValue() / 5;
			score.put((String) me.getKey(), n);

		}
		//System.out.print(score);
		return score;

	}

public	Map<String, Float> GenerateSummary(String text, Map<String, Float> score) {

		Map<String, Float> sen_score = new HashMap<String, Float>();
		String[] Sentence = text.split("\\::");
		String impTerms = "";
		Set set = score.entrySet(); // I need more than 15 terms here
		Iterator it = set.iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry) it.next();
			// System.out.println("key"+me.getKey());
			impTerms = impTerms + ":" + me.getKey();
			// System.out.println(">>"+me.getKey()+"<<");

		}
		// System.out.println(impTerms);
		String[] termsArray = impTerms.split(":");
		for (String str : Sentence) {

			for (int i = 1; i < termsArray.length; i++) {
				String a = termsArray[i];
				if (str.contains(a)) {

					Float count = score.get(a);
					if (sen_score.containsKey(a)) {
						Float c = sen_score.get(a);
						sen_score.put(str, count + c);
					} else
						sen_score.put(str, count);

				}
			}
		}
		//System.out.print(sen_score);
		return sen_score;
	}




public void ShowSummary(Map sen_score)
{
	String summery = "";
	//System.out.println("<<"+sen_score);
	Set set=sen_score.entrySet();
	Iterator it=set.iterator();
//	int i=1;
	while(it.hasNext())
	{
		
		Map.Entry me = (Map.Entry) it.next();
		summery=summery+"\n"+me.getKey();
		
		//System.out.println(">>"+me.getKey());
	}
	System.out.println(summery);
	//System.out.println("done");
	
	
	
}

}

/*
 * public static void main(String args[]) throws IOException {
 * 
 * Scanner in = new Scanner(System.in);
 * System.out.println("path of Review files:"); // String path = "D://Review/";
 * // String path = in.next(); ValueAssignment va = new ValueAssignment();
 * System.out.println("path of TfIdf:"); String tfidf = in.next(); // String
 * tfidf = "D://T/"; File f = new File(path); File[] files = f.listFiles(); File
 * f1 = new File(tfidf); File[] files2 = f1.listFiles(); try { for (int i = 0; i
 * < files.length; i++) {
 * 
 * va.parseFile(va.readReview(files[i]), va.readTdIdf(files2[i]));
 * 
 * }
 * 
 * } catch (Exception e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } }
 */

