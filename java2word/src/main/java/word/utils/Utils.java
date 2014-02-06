package word.utils;

import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import word.api.interfaces.IDocument;

//import javax.xml.transform.Source;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.stream.StreamResult;
//import javax.xml.transform.stream.StreamSource;

//import org.dom4j.DocumentException;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Node;

public class Utils {

	
	/**
	 * @return
	 * 
	 *         The root of the web app as String.
	 */
	public static String getAppRoot() {
		File file = new File(".");
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Can't get app root directory", e);
		}
	}

	/**
	 * 
	 * @param file
	 *            : It is the full path to the file
	 * 
	 * @return String with the content of the file
	 */
	public static String readFile(String file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Can't find the file", e);
		}
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			return stringBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			reader = null;
		}
	}

	

	public static String replaceSpecialCharacters(IDocument myDoc) {
//		String cont = myDoc.getContent();
//		cont = cont.replace("ì", "&#236;");
//		cont = cont.replace("í", "&#237;");
//		cont = cont.replace("î", "&#238;");
//		cont = cont.replace("ï", "&#239;");
		
		System.out.println("#### FFF 02 ####");
		System.out.println(myDoc.getContent());
		return myDoc.getContent().replace("í", "&#237;");
	}
	
	
	/**
	 * Full list: http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references
	 * @param original: the original string that may contain special characters  
	 * @return a new string that all specials have been replaced with Unicode Code Point(Decimal)
	 */
	public static String replaceSpecialCharacters(String original) {
		Map<String, String> specials = new HashMap<String, String>();

		//from 192 to 255
		specials.put("À", "&#192;");
		specials.put("Á", "&#193;");
		specials.put("Â", "&#194;");
		specials.put("Ã", "&#195;");
		specials.put("Ä", "&#196;");
		specials.put("Ä", "&#196;");
		specials.put("Å", "&#197;");
		specials.put("Æ", "&#198;");
		specials.put("Ç", "&#199;");
		specials.put("È", "&#200;");
		specials.put("É", "&#201;");
		specials.put("Ê", "&#202;");
		specials.put("Ë", "&#203;");
		specials.put("Ì", "&#204;");
		specials.put("Í", "&#205;");
		specials.put("Î", "&#206;");
		specials.put("Ï", "&#207;");
		specials.put("Ð", "&#208;");
		specials.put("Ñ", "&#209;");
		specials.put("Ò", "&#210;");
		specials.put("Ó", "&#211;");
		specials.put("Ô", "&#212;");
		specials.put("Õ", "&#213;");
		specials.put("Ö", "&#214;");
		specials.put("×", "&#215;");
		specials.put("Ø", "&#216;");
		specials.put("Ù", "&#217;");
		specials.put("Ú", "&#218;");
		specials.put("Û", "&#219;");
		specials.put("Ü", "&#220;");
		specials.put("Ý", "&#221;");
		specials.put("Þ", "&#222;");
		specials.put("ß", "&#223;");
		specials.put("à", "&#224;");
		specials.put("á", "&#225;");
		specials.put("â", "&#226;");
		specials.put("ã", "&#227;");
		specials.put("ä", "&#228;");
		specials.put("å", "&#229;");
		specials.put("æ", "&#230;");
		specials.put("ç", "&#231;");
		specials.put("è", "&#232;");
		specials.put("é", "&#233;");
		specials.put("ê", "&#234;");
		specials.put("ë", "&#235;");
		specials.put("ì", "&#236;");
		specials.put("í", "&#237;");
		specials.put("î", "&#238;");
		specials.put("ï", "&#239;");
		specials.put("ð", "&#240;");
		specials.put("ñ", "&#241;");
		specials.put("ò", "&#242;");
		specials.put("ó", "&#243;");
		specials.put("ô", "&#244;");
		specials.put("õ", "&#245;");
		specials.put("ö", "&#246;");
		specials.put("÷", "&#247;");
		specials.put("ø", "&#248;");
		specials.put("ù", "&#249;");
		specials.put("ú", "&#250;");
		specials.put("û", "&#251;");
		specials.put("ü", "&#252;");
		specials.put("ý", "&#253;");
		specials.put("þ", "&#254;");
		specials.put("ÿ", "&#255;");

		//from xx to xx, if we need more

		for (String key : specials.keySet()) {
			original = original.replace(key, specials.get(key));
		}
		return original;
	}

	/**
	 * 
	 * @param xml
	 *            xml to be pretifized
	 * 
	 * @return pretifized xml
	 */
	/*
	 * public static String pretty(String xml) { try { org.dom4j.Document
	 * document = DocumentHelper.parseText(xml) .getDocument();
	 * 
	 * String res = formatXml(document, false); return res; } catch
	 * (DocumentException e) { e.printStackTrace(); throw new
	 * RuntimeException("Can't parse xml", e); } }
	 */

	/*
	 * public static String formatXml(Node node, boolean oneLine) {
	 * 
	 * try { TransformerFactory tf = TransformerFactory.newInstance();
	 * Transformer transformer = tf.newTransformer();
	 * 
	 * transformer.setOutputProperty("omit-xml-declaration", "yes");
	 * transformer.setOutputProperty("method", "xml");
	 * transformer.setOutputProperty("encoding", "ISO-8859-1");
	 * transformer.setOutputProperty(
	 * "{http://xml.apache.org/xslt}indent-amount", "4");
	 * transformer.setOutputProperty("indent", "yes"); java.io.StringWriter sw =
	 * new java.io.StringWriter(); StreamResult sr = new StreamResult(sw); //
	 * Must strip out new lines and whitespace, because formatter thinks // this
	 * is content that should be preserved in pretty String xml =
	 * node.asXML().replaceAll(">\\s*(\\r|\\n)\\s*", ">")
	 * .replaceAll("\\s*(\\r|\\n)\\s*<", "<");
	 * 
	 * // String xml = node.asXML(); ByteArrayInputStream inputStream = new
	 * ByteArrayInputStream(xml .getBytes("UTF-8")); Source domSource = new
	 * StreamSource(inputStream); transformer.transform(domSource, sr);
	 * 
	 * String prettyXml = sw.toString();
	 * 
	 * // Although this looks like the same thing as above, it actually // isn't
	 * // if (oneLine) { // prettyXml =
	 * prettyXml.replaceAll(">\\s*(\\r|\\n)\\s*<", "><"); // }
	 * 
	 * return prettyXml; } catch (Exception e) { throw new RuntimeException(e);
	 * } }
	 */

}
