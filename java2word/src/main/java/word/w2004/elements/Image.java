package word.w2004.elements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import word.api.interfaces.IFluentElement;
import word.api.interfaces.IImage;
import word.utils.ImageUtils;

/**
 *
 * @author leonardo
 *
 * Use this class when you want to add images to your document.
 * You can insert images inside Paragraphs, Tables, Headings, Header, Footer and obviously, at the body of a Document.
 */
public class Image implements IImage, IFluentElement<Image> {

    private StringBuilder txt = new StringBuilder("");
    private boolean hasBeenCalledBefore = false;
    private String path = "";
    private String width = ""; // to be able to set this to override default
                                // size
    private String height = ""; // to be able to set this to override default
                                // size
    BufferedImage bufferedImage;
   
    private Image(String path, ImageLocation imageLocation) {
        this.path = path;
        try {
            if (imageLocation.equals(ImageLocation.FULL_LOCAL_PATH)) {
                bufferedImage = ImageIO.read(new File(path));
            }else if (imageLocation.equals(ImageLocation.WEB_URL)) {
                URL url = new URL(path);
                bufferedImage = ImageIO.read(url);
            }else if (imageLocation.equals(ImageLocation.CLASSPATH)) {
                InputStream is = getClass().getResourceAsStream(path);
                bufferedImage = ImageIO.read(is);
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't create ImageIO. Maybe the path is not valid. Path: \n" + path + "\nImageLocation: " + imageLocation.toString() , e);
        }
    }

    private Image(String filename, InputStream inputStream) {
    	  if (inputStream == null)
    		  throw new IllegalArgumentException("Can't create image - null input stream");
    	  if (filename == null || filename.length() < 3)
    		  throw new IllegalArgumentException("Can't create image - invalid filename");
    	  this.path = filename;
    	  try {
    		  this.bufferedImage = ImageIO.read(inputStream);
    	  } catch (IOException e) {
    	      throw new RuntimeException("Can't create ImageIO.", e);
    	  }
    }
    
    /**
     * It returns the original width and height of the image. The '#' is the separator.
     * */
    public String getOriginalWidthHeight() {
        String res = bufferedImage.getWidth() + "#" + bufferedImage.getHeight()
                + "";
        return res;
    }

    private void setUpSize() {
        if ("".equals(width) || "".equals(height)) {
            String[] wh = getOriginalWidthHeight().split("#");
            String ww = wh[0];
            String hh = wh[1];
            if ("".equals(width)) {
                width = ww;
            }
            if ("".equals(height)) {
                height = hh;
            }
        }

    }

    @Override
    public String getContent() {
        if (hasBeenCalledBefore) {
            return txt.toString();
        } else {
            hasBeenCalledBefore = true;
        }
        // Placeholders: internalFileName, fileName, binary, width and height

        String[] arr = path.split("/");
        String fileName = arr[arr.length - 1];

        String internalFileName = System.currentTimeMillis() + fileName;

        // String binary = ImageUtils.getImageHexaBase64(path);
        String imageformat = path.substring(path.lastIndexOf('.') + 1);
        String binary = ImageUtils.getImageHexaBase64(bufferedImage,
                imageformat);

        setUpSize();

        String res = img_template;
        res = res.replace("{fileName}", fileName);
        res = res.replace("{internalFileName}", internalFileName);
        res = res.replace("{binary}", binary);
        res = res.replace("{width}", width);
        res = res.replace("{height}", height);

        txt.append(res);
        return txt.toString();
    }

    @Override
    public Image setWidth(String value) {
        width = value;
        return this;
    }

    @Override
    public Image setHeight(String value) {
        height = value;
        return this;
    }

    private String img_template = "\n<w:pict>"
            + "\n	<v:shapetype id=\"_x0000_t75\" coordsize=\"21600,21600\" o:spt=\"75\" o:preferrelative=\"t\" path=\"m@4@5l@4@11@9@11@9@5xe\" filled=\"f\" stroked=\"f\">"
            + "		<v:stroke joinstyle=\"miter\"/>"
            + "		<v:formulas>"
            + "			<v:f eqn=\"if lineDrawn pixelLineWidth 0\"/>"
            + "			<v:f eqn=\"sum @0 1 0\"/><v:f eqn=\"sum 0 0 @1\"/>"
            + "			<v:f eqn=\"prod @2 1 2\"/>"
            + "			<v:f eqn=\"prod @3 21600 pixelWidth\"/>"
            + "			<v:f eqn=\"prod @3 21600 pixelHeight\"/>"
            + "			<v:f eqn=\"sum @0 0 1\"/>"
            + "			<v:f eqn=\"prod @6 1 2\"/>"
            + "			<v:f eqn=\"prod @7 21600 pixelWidth\"/>"
            + "			<v:f eqn=\"sum @8 21600 0\"/>"
            + "			<v:f eqn=\"prod @7 21600 pixelHeight\"/>"
            + "			<v:f eqn=\"sum @10 21600 0\"/>"
            + "		</v:formulas>"
            + "		<v:path o:extrusionok=\"f\" gradientshapeok=\"t\" o:connecttype=\"rect\"/>"
            + "		<o:lock v:ext=\"edit\" aspectratio=\"t\"/>"
            + "	</v:shapetype>"
            + "\n<w:binData w:name=\"wordml://{internalFileName}\" xml:space=\"preserve\">{binary}</w:binData>"
            + "\n	<v:shape id=\"_x0000_i1026\" type=\"#_x0000_t75\" style=\"width:{width}pt;height:{height}pt\"><v:imagedata src=\"wordml://{internalFileName}\" o:title=\"{fileName}\"/>"
            + "\n	</v:shape>" + "\n</w:pict>";


    /***
     * It creates an image from the Web.
     * @param path Image full path. To know if it will work, you should be able to see this image in your browser
     * @return
     */
    public static Image from_WEB_URL(String path) {
        return new Image(path,  ImageLocation.WEB_URL);
    }

    /***
     * It creates an image from your local machine.
     * @param path Image full path. To know if it will work, probably you should specify full path from the root of your system.
     * @return
     */
    public static Image from_FULL_LOCAL_PATHL(String path) {
        return new Image(path,  ImageLocation.FULL_LOCAL_PATH);
    }

    /***
     * It creates an image from your the Application Classpath
     * @param
     * @return
     */
    public static Image from_CLASSPATH(String path) {
        return new Image(path,  ImageLocation.CLASSPATH);
    }

    /**
     * Creates the Image from a InputStream. Useful when image comes from the database. Issue 85 by Trumbera
     *  
     * @param filename
     * @param inputStream
     * @return
     */
    public static Image from_STREAM(String filename, InputStream inputStream){
        return new Image(filename, inputStream);
    }
    
    @Override
    public Image create() {
        return this;
    }

    /*
     * private String imgTst = "\n<w:pict>" +
     * "\n	<v:shapetype id=\"_x0000_t75\" coordsize=\"21600,21600\" o:spt=\"75\" o:preferrelative=\"t\" path=\"m@4@5l@4@11@9@11@9@5xe\" filled=\"f\" stroked=\"f\"><v:stroke joinstyle=\"miter\"/><v:formulas><v:f eqn=\"if lineDrawn pixelLineWidth 0\"/><v:f eqn=\"sum @0 1 0\"/><v:f eqn=\"sum 0 0 @1\"/><v:f eqn=\"prod @2 1 2\"/><v:f eqn=\"prod @3 21600 pixelWidth\"/><v:f eqn=\"prod @3 21600 pixelHeight\"/><v:f eqn=\"sum @0 0 1\"/><v:f eqn=\"prod @6 1 2\"/><v:f eqn=\"prod @7 21600 pixelWidth\"/><v:f eqn=\"sum @8 21600 0\"/><v:f eqn=\"prod @7 21600 pixelHeight\"/><v:f eqn=\"sum @10 21600 0\"/></v:formulas><v:path o:extrusionok=\"f\" gradientshapeok=\"t\" o:connecttype=\"rect\"/><o:lock v:ext=\"edit\" aspectratio=\"t\"/></v:shapetype>"
     * +
     * "\n<w:binData w:name=\"wordml://01000002.gif\" xml:space=\"preserve\">R0lGODlhHwAaAMQAAMjIyOHh4cLCwufn56ioqKOjo+zs7L29va6urs3NzdLS0ri4uNfX19zc3LOz"
     * +
     * "s/Hx8Z6engAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAAA"
     * +
     * "AAAALAAAAAAfABoAAAWv4COOZDCQ6Gg0aRs40NGSQAGd82MIUH/PDYJPNgsgfL1FC4DsBVy2JoRF"
     * +
     * "OkghjtQgKhWQeNcCbgSTIqiixhUCMKDU3VQZSXimrM2EVkpwt7g9WSkJUmgoAVIKLQtNBDkMUn4o"
     * +
     * "R0heM0xNDpqalnybmnYPmGtYIqQ+aKNrgqc9qa2sra+nsaezpLWkt6umsiOqV7lru8G9tr+wxrrI"
     * + "tMrDIwqf0pwi09OhOdna29wpIQA7" + "</w:binData>" +
     * "\n	<v:shape id=\"_x0000_i1026\" type=\"#_x0000_t75\" style=\"width:31pt;height:26pt\"><v:imagedata src=\"wordml://01000002.gif\" o:title=\"quote.gif\"/>"
     * + "\n	</v:shape>" + "\n</w:pict>";
     *
     * private String img02 = "\n<w:pict> " +
     * "\n	<v:shapetype id=\"_x0000_t75\" coordsize=\"21600,21600\" o:spt=\"75\" o:preferrelative=\"t\" path=\"m@4@5l@4@11@9@11@9@5xe\" filled=\"f\" stroked=\"f\"> "
     * +
     * "\n		<v:stroke joinstyle=\"miter\"/><v:formulas><v:f eqn=\"if lineDrawn pixelLineWidth 0\"/> "
     * + "\n		<v:f eqn=\"sum @0 1 0\"/> " + "\n		<v:f eqn=\"sum 0 0 @1\"/> " +
     * "\n		<v:f eqn=\"prod @2 1 2\"/> " +
     * "\n		<v:f eqn=\"prod @3 21600 pixelWidth\"/> " +
     * "\n		<v:f eqn=\"prod @3 21600 pixelHeight\"/> " +
     * "\n		<v:f eqn=\"sum @0 0 1\"/> " + "\n		<v:f eqn=\"prod @6 1 2\"/> " +
     * "\n		<v:f eqn=\"prod @7 21600 pixelWidth\"/> " +
     * "\n		<v:f eqn=\"sum @8 21600 0\"/> " +
     * "\n		<v:f eqn=\"prod @7 21600 pixelHeight\"/> " +
     * "\n		<v:f eqn=\"sum @10 21600 0\"/> " + "\n	</v:formulas> " +
     * "\n		<v:path o:extrusionok=\"f\" gradientshapeok=\"t\" o:connecttype=\"rect\"/> "
     * + "\n		<o:lock v:ext=\"edit\" aspectratio=\"t\"/> " +
     * "\n	</v:shapetype> " +
     * "\n<w:binData w:name=\"wordml:\01000002.gif\" xml:space=\"preserve\">" +
     * "R0lGODlhHwAaAMQAAMjIyOHh4cLCwufn56ioqKOjo+zs7L29va6urs3NzdLS0ri4uNfX19zc3LOz"
     * +
     * "s/Hx8Z6engAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAAA"
     * +
     * "AAAALAAAAAAfABoAAAWv4COOZDCQ6Gg0aRs40NGSQAGd82MIUH/PDYJPNgsgfL1FC4DsBVy2JoRF"
     * +
     * "OkghjtQgKhWQeNcCbgSTIqiixhUCMKDU3VQZSXimrM2EVkpwt7g9WSkJUmgoAVIKLQtNBDkMUn4o"
     * +
     * "R0heM0xNDpqalnybmnYPmGtYIqQ+aKNrgqc9qa2sra+nsaezpLWkt6umsiOqV7lru8G9tr+wxrrI"
     * + "tMrDIwqf0pwi09OhOdna29wpIQA7" + "</w:binData>" +
     * "\n	<v:shape id=\"_x0000_i1026\" type=\"#_x0000_t75\" style=\"width:31pt;height:26pt\"><v:imagedata src=\"wordml:\01000002.gif\" o:title=\"quote.gif\"/>"
     * + "\n	</v:shape>" + "\n</w:pict>";
     */
}
