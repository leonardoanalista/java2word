package word.w2004.style;

import word.api.interfaces.ISuperStylin;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.Font;

/**
 * @author anyone
 *
 *         Use this class in order to apply specifics style to paragraph. Eg.:
 *         one word in bold, other in italic.
 *
 */
public class ParagraphPieceStyle extends AbstractStyle implements ISuperStylin {

    private boolean bold = false;
    private boolean italic = false;
    private boolean underline = false;
    private String textColor = "";
    private Color color;
    public Font font;
    private String fontSize = "";
    private String bgColor = "";

    private boolean caps = false;
    private boolean doubleStrike = false;
    private boolean emboss = false;
    private boolean imprint = false;
    private boolean outline = false;
    private boolean shadow = false;
    private boolean smallCaps = false;
    private boolean strike = false;
    private boolean subscript = false;
    private boolean superscript = false;
    private boolean vanish = false;
    private String bidi = "";


    @Override
    public String getNewContentWithStyle(String txt) {
        StringBuilder style = new StringBuilder("");

        // 'doStyleFont' has to be before 'doStyleBold' and 'doStyleItalic'
        // because of the 'smart bold/italic' based on font type.
        doStyleFont(style);
        doStyleBold(style);
        doStyleItalic(style);
        doStyleUnderline(style);
        doStyleTextColorHexa(style);
        doStyleColorEnum(style);
        doStyleFontSize(style);
        doStyleBgColor(style);

        doStyleSubscript(style);
        doStyleSuperscript(style);
        doStyleStrike(style);
        doStyleCaps(style);
        doStyleSmallCaps(style);
        doStyleDoubleStrike(style);
        doStyleEmboss(style);
        doStyleImprint(style);
        doStyleOutline(style);
        doStyleShadow(style);
        doStyleVanish(style);
        
        doStyleBidi(style);
        
        return doStyleReplacement(style, txt);
    }

    private void doStyleVanish(StringBuilder style) {
        if (this.vanish){
            style.append("\n            <w:vanish/>");
        }
    }

    private void doStyleSmallCaps(StringBuilder style) {
        if (this.smallCaps){
            style.append("\n            <w:smallCaps/>");
        }
    }

    private void doStyleShadow(StringBuilder style) {
        if (this.shadow){
            style.append("\n            <w:shadow/>");
        }
    }

    private void doStyleOutline(StringBuilder style) {
        if (this.outline){
            style.append("\n            <w:outline/>");
        }
    }

    private void doStyleImprint(StringBuilder style) {
        if (this.imprint){
            style.append("\n            <w:imprint/>");
        }
    }

    private void doStyleEmboss(StringBuilder style) {
        if (this.emboss){
            style.append("\n            <w:emboss/>");
        }
    }

    private void doStyleDoubleStrike(StringBuilder style) {
        if (this.doubleStrike){
            style.append("\n            <w:dstrike/>");
        }
    }

    private void doStyleCaps(StringBuilder style) {
        if (this.caps){
            style.append("\n            <w:caps/>");
        }
    }

    private void doStyleStrike(StringBuilder style) {
        if (this.strike){
            style.append("\n            <w:strike/>");
        }
    }

    private void doStyleSuperscript(StringBuilder style) {
        if (this.superscript){
            style.append("\n            <w:vertAlign w:val=\"superscript\"/>");
        }
    }

    private void doStyleSubscript(StringBuilder style) {
        if (this.subscript) {
            style.append("\n            <w:vertAlign w:val=\"subscript\"/>");
        }
    }

    private void doStyleBgColor(StringBuilder style) {
        if (!bgColor.equals("")) {
            style.append("\n            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\""+ bgColor +"\" />\n");
        }
    }

    private void doStyleBold(StringBuilder style) {
        if (bold) {
            style.append("\n            	<w:b/>");
        }
    }

    private void doStyleItalic(StringBuilder style) {
        if (italic) {
            style.append("\n            	<w:i/>");
        }
    }

    private void doStyleUnderline(StringBuilder style) {
        if (underline) {
            style.append("\n			<w:u w:val=\"single\"/>");
        }
    }

    private void doStyleTextColorHexa(StringBuilder style) {
        if (!textColor.equals("")) {
            style.append("\n			<w:color w:val=\"" + textColor + "\"/>");
        }
    }

    private void doStyleColorEnum(StringBuilder style) {
        if (color != null && !color.getValue().equals("")) {
            style.append("\n			<w:color w:val=\"" + color.getValue() + "\"/>");
        }
    }

    private void doStyleFont(StringBuilder style) {
        // Smart Italic/Bold: This will make the font bold/italic according to
        // this.font
        String fontName = "";
        if (font != null) {
            fontName = font.getValue();
            
            /* 
             * issue 57: double bold. So no more smart bold for font.
             
            if (fontName.contains("Bold")) {
                bold = true;
            } else {
                //if is manually 'bold', I also change the font name
                if (bold) {
                    fontName += " Bold";
                }
            }

            if (fontName.contains("Italic")) {
                italic = true;
            } else {
                if (italic) {
                    fontName += " Italic";
                }
            }
             */
            
        }

        if (font != null) {
            style.append("\n			<w:rFonts w:ascii=\"" + fontName + "\" w:h-ansi=\"" + fontName + "\"/>\n");
            style.append("\n			<wx:font wx:val=\"" + fontName + "\"/>");
        }
    }

    private void doStyleFontSize(StringBuilder style) {
        if (!"".equals(fontSize)) {
            String ffsize = "\n               <w:sz w:val=\"" + fontSize
                    + "\" />\n";
            ffsize += "\n               <w:sz-cs w:val=\"" + fontSize
                    + "\" />\n";
            style.append(ffsize);
        }
    }

    private String doStyleReplacement(StringBuilder style, String txt) {
        if (!"".equals(style.toString())) {
            style.insert(0, "\n	 <w:rPr>");
            style.append("\n	 </w:rPr>");
            txt = txt.replace("{styleText}", style.toString());// Convention:
                                                               // apply styles
        }
        // Convention: replace unused styles after...
        txt = txt.replaceAll("[{]style(.*)[}]", "");
        return txt;
    }

    private void doStyleBidi(StringBuilder style) {
        if (!this.bidi.equals("")) style.append("<w:lang w:bidi=\"" + this.bidi + "\" />");
    }    
    
    // ### Getters and setters... ###

    /**
     *
     * This is the ParagraphPiece! I am using Covariant Return Type!!! to be
     * honest, I have never thought how to use and finally here we go!!! It will
     * give the chance to eliminate the necessity of type cast for elements.
     *
     */
    @Override
    public ParagraphPiece create() {
        return (ParagraphPiece) super.create();
    }

    /**
     * Set the text to Bold
     * @return
     */
    public ParagraphPieceStyle bold() {
        this.bold = true;
        return this;
    }

    public ParagraphPieceStyle italic() {
        this.italic = true;
        return this;
    }

    public ParagraphPieceStyle underline() {
        this.underline = true;
        return this;
    }

    /**
     * If you know the color code, just to straight to the point! Eg.: yellow:
     * FFFF00, black: 000000, red: FF0000, blue: 0000FF, green: 008000, etc...
     *
     * If you want, you can use the class Color.whatever_color.
     *
     * @param hexadecimal
     *            color code
     */
    public ParagraphPieceStyle textColor(String textColor) {
        this.textColor = textColor;
        return this;
    }
    
    /**
     * If you know the color code, just to straight to the point! Eg.: yellow:
     * FFFF00, black: 000000, red: FF0000, blue: 0000FF, green: 008000, etc...
     *
     * If you want, you can use the class Color.whatever_color.
     *
     * @param hexadecimal
     *            color code
     */
    public ParagraphPieceStyle bgColor(String bgColor) {
        this.bgColor = bgColor;
        return this;
    }
    
    /***
     * Set text color from the Enum @Color, case you don't know any hexa code color
     * @param color
     * @return
     */
    public ParagraphPieceStyle textColor(Color color) {
        this.color = color;
        return this;
    }
    
    public ParagraphPieceStyle font(Font font) {
        this.font = font;
        return this;
    }

    /***
     * Specify the font size the same way you see on MW Word.
     */
    public ParagraphPieceStyle fontSize(String fontSize) {
        this.fontSize = Integer.parseInt(fontSize) * 2 + "" ;
        return this;
    }
    
    /**
     * It makes the value Capital case
     * @return
     */
    public ParagraphPieceStyle caps() {
        this.caps = true;
        return this;
    }
    public ParagraphPieceStyle doubleStrike() {
        this.doubleStrike = true;
        return this;
    }
    public ParagraphPieceStyle emboss() {
        this.emboss = true;
        return this;
    }
    public ParagraphPieceStyle imprint() {
        this.imprint = true;
        return this;
    }
    public ParagraphPieceStyle outline() {
        this.outline = true;
        return this;
    }
    public ParagraphPieceStyle shadow() {
        this.shadow = true;
        return this;
    }
    
    /**
     * It makes capital case but with middle letters a bit smaller the the first one in the word
     * @return
     */
    public ParagraphPieceStyle smallCaps() {
        this.smallCaps = true;
        return this;
    }
    public ParagraphPieceStyle strike() {
        this.strike = true;
        return this;
    }
    public ParagraphPieceStyle subscript() {
        this.subscript = true;
        return this;
    }
    public ParagraphPieceStyle superscript() {
        this.superscript = true;
        return this;
    }
    
    /**
     * It makes the text hidden or doesn't show it.  
     * @return
     */
    public ParagraphPieceStyle vanish() {
        this.vanish = true;
        return this;
    }

    /**
     * Use this to specify special characters. Eg.: Hebreus, use HE
     * @param style
     */
    public ParagraphPieceStyle bidi(String bidi) {
        this.bidi = bidi;
        return this;
    }

}
