package word.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class ImageUtils {

	public static String getImageHexaBase64(BufferedImage bufferedImage, String imageformat) {
		//System.out.println("@@@@@@@ IMAGE - getImageHexaBase64 NEW Way @@@@@@");
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
		//"gif"  "png" "jpeg"
		try {
			ImageIO.write(bufferedImage, imageformat.toString() , baos);
			baos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro generating Base64 for the image", e);
		}
		byte[] resultImageAsRawBytes = baos.toByteArray();

		String encodedString = new Base64Encoder().encode(resultImageAsRawBytes);
		//String encodedString = Base64.encodeBytes(resultImageAsRawBytes);
		return encodedString;
	}


/*
	private final static String XML_DOC_HEADER = "<?xml version=\"1.0\" ?>\n";
	public static final String[] hexLookupTable = { "00", "01", "02", "03",
			"04", "05", "06", "07", "08", "09", "0a", "0b", "0c", "0d", "0e",
			"0f", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
			"1a", "1b", "1c", "1d", "1e", "1f", "20", "21", "22", "23", "24",
			"25", "26", "27", "28", "29", "2a", "2b", "2c", "2d", "2e", "2f",
			"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3a",
			"3b", "3c", "3d", "3e", "3f", "40", "41", "42", "43", "44", "45",
			"46", "47", "48", "49", "4a", "4b", "4c", "4d", "4e", "4f", "50",
			"51", "52", "53", "54", "55", "56", "57", "58", "59", "5a", "5b",
			"5c", "5d", "5e", "5f", "60", "61", "62", "63", "64", "65", "66",
			"67", "68", "69", "6a", "6b", "6c", "6d", "6e", "6f", "70", "71",
			"72", "73", "74", "75", "76", "77", "78", "79", "7a", "7b", "7c",
			"7d", "7e", "7f", "80", "81", "82", "83", "84", "85", "86", "87",
			"88", "89", "8a", "8b", "8c", "8d", "8e", "8f", "90", "91", "92",
			"93", "94", "95", "96", "97", "98", "99", "9a", "9b", "9c", "9d",
			"9e", "9f", "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8",
			"a9", "aa", "ab", "ac", "ad", "ae", "af", "b0", "b1", "b2", "b3",
			"b4", "b5", "b6", "b7", "b8", "b9", "ba", "bb", "bc", "bd", "be",
			"bf", "c0", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9",
			"ca", "cb", "cc", "cd", "ce", "cf", "d0", "d1", "d2", "d3", "d4",
			"d5", "d6", "d7", "d8", "d9", "da", "db", "dc", "dd", "de", "df",
			"e0", "e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "ea",
			"eb", "ec", "ed", "ee", "ef", "f0", "f1", "f2", "f3", "f4", "f5",
			"f6", "f7", "f8", "f9", "fa", "fb", "fc", "fd", "fe", "ff" };


	public void emptyDocTest() {
		Document2000 myDoc = new Document2000();
		// System.out.println(myDoc.getContent());

		try {
			// BufferedImage img = ImageIO.read(new File(new
			// URI("http://localhost:8080/app02/img/dtpick.gif") ));
			// OK!!! BufferedImage img = ImageIO.read(new
			// File("/Users/leonardo_correa/Desktop/icons_corrup/robber-thumb1893761.jpg"));
			// BufferedImage img = ImageIO.read(new
			// File("http://localhost:8080/app02/img/dtpick.gif"));

			URL url = new URL("http://localhost:8080/app02/img/dtpick.gif");
			BufferedImage img = ImageIO.read(url);
			System.out.println("H: " + img.getHeight());
			System.out.println("W: " + img.getWidth());
			System.out.println(img.toString());
			System.out.println("########################################");

			// createBase64XMLDoc("/Users/leonardo_correa/Desktop/icons_corrup/quote.gif",
			// "xxxx.txt");

			// ##### Goood code to get the hexa from a picture in the SAME
			// server ###
			File file = new File(
					"/Users/leonardo_correa/Desktop/icons_corrup/quote.gif");
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			int bytes = (int) file.length();
			byte[] buffer1 = new byte[bytes];
			int readBytes1 = bis.read(buffer1);
			bis.close();

			String encodedString = Base64.encode(buffer1);
			System.out.println(encodedString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String padHexString(String hexNum) {
		if (hexNum.length() < 2) {
			return "0" + hexNum;
		}
		return hexNum;
	}

	public void createBase64XMLDoc(String filename, String outFilename) {
		try {
			byte[] buffer = readFile(filename);
			int readBytes = buffer.length;

			long startTime = (new java.util.Date()).getTime();
			StringBuffer strXMLDoc = new StringBuffer(XML_DOC_HEADER);
			strXMLDoc.append("<image name=\"" + filename + "\">");
			String encodedString = Base64.encode(buffer);
			strXMLDoc.append(encodedString);
			strXMLDoc.append("</image>");
			long endTime = (new java.util.Date()).getTime();
			long diffTime = endTime - startTime;
			System.out.println("Total conversion time for " + readBytes
					+ " was " + diffTime + " msec or " + ((double) readBytes)
					/ ((double) diffTime) + " bytes/msec.");

			writeFile(outFilename, strXMLDoc.toString());
		} catch (IOException e) {
			System.out.println("Our exception is: " + e);
		}
	}

	public void createHexXMLDoc(String filename, String outFilename) {
		try {
			byte[] buffer = readFile(filename);
			long startTime = (new java.util.Date()).getTime();
			int readBytes = buffer.length;
			StringBuffer strXMLDoc = new StringBuffer(XML_DOC_HEADER);
			strXMLDoc.append("<image name=\"" + filename + "\">");
			for (int i = 0; i < readBytes; i++) {
				strXMLDoc.append(hexLookupTable[0xff & buffer[i]]);
			}
			strXMLDoc.append("</image>");
			long endTime = (new java.util.Date()).getTime();
			long diffTime = endTime - startTime;
			System.out.println("Total conversion time for " + readBytes
					+ " was " + diffTime + " msec or " + ((double) readBytes)
					/ ((double) diffTime) + " bytes/msec.");

			writeFile(outFilename, strXMLDoc.toString());
		} catch (IOException e) {
			System.out.println("Our exception is: " + e);
		}
	}

	public byte[] readFile(String filename) throws IOException {
		File file = new File(filename);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				file));
		int bytes = (int) file.length();
		byte[] buffer = new byte[bytes];
		int readBytes = bis.read(buffer);
		bis.close();
		System.out.println("Read " + readBytes + " and expected to read "
				+ bytes);
		return buffer;
	}

	public void writeFile(String filename, String data) throws IOException {
		File file = new File(filename);
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(file));
		bos.write(data.getBytes());
		bos.close();

		System.out.println("Wrote " + data.getBytes().length + " to file "
				+ filename);
		return;
	}

*/

}
