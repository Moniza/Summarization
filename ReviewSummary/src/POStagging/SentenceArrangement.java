package POStagging;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.crypto.Data;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

//import IndexCreation.Index;
public class SentenceArrangement {

	void Readfile(String filePath) throws IOException {
		// Index ic=new Index();
		File f = new File(filePath);
		File[] folder = f.listFiles();
		for (int i = 0; i < folder.length; i++) {
			System.out.println(folder[i].getName());
			FileInputStream fr = new FileInputStream(folder[i]);
			DataInputStream in = new DataInputStream(fr);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String str;
			String CompleteText = "";
			while ((str = br.readLine()) != null) {
				CompleteText = CompleteText + str;
			}
			String filename = folder[i].getName();
			WriteSentences(CompleteText, filename);
		}

	}

	void WriteSentences(String CompleteText, String filename)
			throws IOException {
		
		 InputStream is = new FileInputStream("D://summary/en-sent.bin");
		 SentenceModel model = new SentenceModel(is);
		 SentenceDetectorME sdetector = new SentenceDetectorME(model);
		 String sentences[] = sdetector.sentDetect(CompleteText);
		 FileWriter fw = new FileWriter("D://summary/sentenceArrangement/"+ filename);
		 BufferedWriter bw = new BufferedWriter(fw);
		 for (String s : sentences) {
			bw.write(s + "\r\n");

		}
		bw.close();
		fw.close();

	}

	public static void main(String[] arg) throws IOException {
		String filePath = "D://summary/Reviews/";
		SentenceArrangement sa = new SentenceArrangement();
		sa.Readfile(filePath);
		System.out.println("done");
				
	}

}
