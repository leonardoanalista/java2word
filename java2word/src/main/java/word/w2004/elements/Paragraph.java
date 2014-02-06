package word.w2004.elements;

import java.util.ArrayList;
import java.util.List;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElement;
import word.api.interfaces.IFluentElementStylable;
import word.w2004.style.ParagraphStyle;


/**
 *
 * @author leonardo
 *
 * Use this class to create paragraphs.
 *
 *  @see
 *  ParagraphPiece
 *
 */
public class Paragraph implements IElement, IFluentElement<Paragraph>, IFluentElementStylable <ParagraphStyle> {

    private ParagraphPiece[] pieces = null;
    private ParagraphStyle style = new ParagraphStyle();
    private List<Tab> tabs = new ArrayList<Tab>();

    private Paragraph() {        
    }
    
    /**
     *
     * @param pieces
     *
     * It receives many ParagraphPieces with their own style/formating
     *
     */
    public Paragraph(ParagraphPiece ... pieces) {
        this.pieces = pieces;
    }

    @Override
    public String getContent() {
        if(pieces == null) {  // || pieces.length == 0){
            return "";
        }

        StringBuilder sb = new StringBuilder("");
        for (ParagraphPiece piece : pieces) {
            sb.append(piece.getContent());
        }

        String txt =
            "	<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"00000000\">"
            +"\n		{styleText}" // {styleText} is inside styleText
            +"\n		{tabs}"
            +"\n		{value}"
            +"\n	</w:p>";


        if("".equals(sb.toString())){ //if there is no content in the pieces, there is no return - just empty string.
            return "";
        }else{
            txt = doAddTabs(txt);

            //For convention, it should be the last thing before returning the xml content.
            txt = style.getNewContentWithStyle(txt);
            return txt.replace("{value}", sb.toString());
        }
    }

    private String doAddTabs(String txt) {
        String addTab = "";
        if (tabs != null && !tabs.isEmpty()) {
            addTab = "  <w:pPr>" + "\n    <w:tabs>";
            for(Tab tab : tabs) {
                addTab += "\n        <w:tab w:val=\""+tab.getAlign().getValue()+"\" w:pos=\""+tab.getPosition() + "\" />";
            }
            addTab += "\n    </w:tabs>" + "\n </w:pPr>";
        }
        txt = txt.replace("{tabs}", addTab);
        return txt;
    }


    //## Getters and Setters
    @Override
    public ParagraphStyle withStyle() {
        style.setElement(this);
        return style;
    }

    /**
     * created a Paragraph with a simple @ParagraphPiece inside
     * @param value 
     * @return
     */
    public static Paragraph with(String value) {
        //if(value == null || "".equals(value)){
            //return null;
        //}
        Paragraph par = new Paragraph();
        ParagraphPiece piece = ParagraphPiece.with(value);
        par.pieces = new ParagraphPiece[1];
        par.pieces[0] = piece;
        return par;
        //return new Paragraph(value);
    }

    public static Paragraph withPieces(ParagraphPiece ... pieces) {
        return new Paragraph(pieces);
    }

    @Override
    public Paragraph create() {
        return this;
    }

    /***
     * Configures the Align and position of the tabs ('\t'). Position is pretty much the size of EACH tab or each '\t'.
     * @param tabAlign Right or Left according to the Enum @TabAlign
     * @param position kind of size of EACH tab or each '\t'
     * @return the fluent actual paragraph
     */
    public Paragraph addTab(TabAlign tabAlign, int position) {
        tabs.add(new Tab(tabAlign, position));
        return this;
    }

    //### TODO: maybe refactor this to another class...don't know...
    public enum TabAlign {

        LEFT("left"), RIGHT("right");

        private String value;


        private TabAlign(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private class Tab {
        private TabAlign align;
        private int position;

        public Tab(TabAlign pAlign, int pPosition) {
            align = pAlign;
            position = pPosition;
        }

        public TabAlign getAlign() {
            return align;
        }

        public int getPosition() {
            return position;
        }
    }

}
