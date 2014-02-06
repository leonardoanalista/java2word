package word.w2004.elements;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElement;
import word.api.interfaces.IFluentElementStylable;
import word.w2004.style.ParagraphPieceStyle;

/**
 * 
 * @author leonardo
 *
 * Use this class ONLY inside Paragraph in order to format pieces of a paragraph.
 * for example, if you want to make one and only one word of the paragraph bold.
 * ALWAYS USE THIS CLASS INSIDE A PARAGRAPH. OTHERWISE WON'T WORK. This is the way you should use it:
 * <pre>
 * myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("This is one piece.").create()));
 * </pre>
 * NEVER LIKE THIS:
 * <pre>
 * myDoc.addEle(ParagraphPiece.with("This is one piece.").create());
 * </pre> 
 */
public class ParagraphPiece implements IElement, IFluentElement<ParagraphPiece>, IFluentElementStylable <ParagraphPieceStyle> {
	
	private String value = "";
	private ParagraphPieceStyle style = new ParagraphPieceStyle();
	
	String txt =
	 "\n		<w:r>"
	+"\n			{styleText}"
	+"\n			<w:t>{value}</w:t>"
	+"\n		</w:r>";

	private ParagraphPiece() {
    }

    @Override
	public String getContent() {
		if("".equals(this.value) || this.value == null){ // null is very unusual. That the reason null comparison is after empty verification. I am not sure if we use ApacheUtils we can achieve the same  
			return "";
		}
		
		//For convention, it should be the last thing before returning the xml content.
		txt = style.getNewContentWithStyle(txt);
		
		return txt.replace("{value}", this.value);
	}
	
	//### Gettets and Setters
	@Override
	public ParagraphPieceStyle withStyle() {
		this.style.setElement(this);
		return this.style;
	}

	public static ParagraphPiece with(String value) {
	    ParagraphPiece par = new ParagraphPiece();
	    par.value = value;
	    return par;
		//return new ParagraphPiece(value);
	}
	
	@Override
	public ParagraphPiece create() {
		return this;
	}
	
}
