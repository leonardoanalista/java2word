package word.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtils {

	public static int regexCount(String text, String regex){
		if(text == null || regex == null){
			throw new IllegalArgumentException("Can't be null.");
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		
		int total = 0;
		while (matcher.find()) {
			total ++;
        }
		
		return total;
	}
	
	public static void createLocalDoc(String myDoc) {
	    createLocalDocument(myDoc, "Java2word_allInOne.doc");
	}
	
	public static void createLocalDoc(String myDoc, String fileName) {
	    if("".equals(fileName) || fileName == null) {
	        fileName = "Java2word_allInOne.doc";
	    }
	    createLocalDocument(myDoc, fileName);
	}
	
    public static void createLocalDocument(String myDoc, String fileName) {
        //Property prop = new Property("");
        Properties prop = new Properties();
        String tmpDocs = "";
        try {            
            prop.load(new FileInputStream("build.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tmpDocs = (String) prop.get("tmp.docs.dir");
        
        //System.out.println(tmpDocs);
        //"/home/leonardo/Desktop/Java2word_allInOne.doc"
        
        File fileObj = new File(tmpDocs + "/" + fileName);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String myWord = myDoc;

        writer.println(myWord);
        writer.close();
    }
	
}
