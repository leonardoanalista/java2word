package word.w2004.style;

import word.api.interfaces.ISuperStylin;

/**
 * @author everybody - there is no ownership!
 *
 * Use this class to apply style to the whole Heading1 element.
 * Default align is left
 *
 */
public class HeadingStyle extends AbstractStyle implements ISuperStylin{

    /**
     * Default align is left
     */
    private Align align = Align.LEFT;
    private boolean bold = false;
    private boolean italic = false;

    //we could abstract this or not... if we want to apply style to one word inside a the Heading, you can NOT apply align JUSTIFIED, for example.
    //For this reason I will leave this here for instance...
    public enum Align {
        CENTER("center"), LEFT("left"), RIGHT("right"), JUSTIFIED("both");

        private String value;

        Align(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    //This method holds the logic to replace all place holders for styling.
    //I also noticed if you don't replace the place holder, it doesn't cause any error!
    //But we should try to replace in order to keep the result xml clean.
    @Override
    public String getNewContentWithStyle(String txt) {
        String alignValue = "\n            	<w:jc w:val=\"" + align.getValue()+ "\" />";
        txt = txt.replace("{styleAlign}", alignValue);

        StringBuilder sbText = new StringBuilder("");

        applyBoldAndItalic(sbText);

        if(!"".equals(sbText.toString())) {
            sbText.insert(0, "\n	 <w:rPr>");
            sbText.append("\n	 </w:rPr>");
        }

        txt = txt.replace("{styleText}", sbText.toString());//Convention: apply styles
        txt = txt.replaceAll("[{]style(.*)[}]", ""); //Convention: remove unused styles after...

        return txt;
    }


    private void applyBoldAndItalic(StringBuilder sbText) {
        if(bold) {
            sbText.append("\n            	<w:b/><w:b-cs/>");
        }
        if(italic) {
            sbText.append("\n            	<w:i/>");
        }
    }


    //### Getters  setters... ###

    /**
     * Heading alignment
     * @param align
     * @return fluent @HeadingStyle
     */
    public HeadingStyle align(Align align) {
        this.align = align;
        return this;
    }

    /**
     * Set Heading font to bold
     * @return fluent @HeadingStyle
     */
    public HeadingStyle bold() {
        bold = true;
        return this;
    }

    /**
     * Set Heading font to italic
     * @return fluent @HeadingStyle
     */
    public HeadingStyle italic() {
        italic = true;
        return this;
    }


}
