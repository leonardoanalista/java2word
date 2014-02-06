package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.ISuperStylin;

public class TableRowStyle implements ISuperStylin{
	
	private IElement element;
	StringBuilder style = new StringBuilder("");
	
	private boolean bold = false;
	private String bgColor = "";
	private boolean repeatTableHeaderOnEveryPage = false;
	
	@Override
	public String getNewContentWithStyle(String txt) {
		
		txt = doStyleBold(style, txt);
		doStyleBgColor(style);
		doStyleRepeatTableHeader(style);
		
		if(!"".equals(style.toString())){
			style.insert(0, "\n<w:trPr>");
			style.append("\n</w:trPr>\n");			
		}
		
		return txt.replace("{styleRowPh}", style);
	}

	@Override
	public void setElement(IElement element) {
		this.element = element;	
	}

	
	@Override
	public TableRow create() {
		/**
		 *  This is Covariant Return if you wanna know. No many people use it because the need is pretty rare. 
		 *  I am returning a subtype of the IElement when overriding the method.  
		 */
		return (TableRow) this.element;
	}

	
	//### Useful external methods ############################
    /**
     * (Experiment, beta) Set the text to Bold to the whole line.
     * It actually cascades the bold to every Paragraph RUN. This is under test and trying to find a better solution.
     * Apparently is only possible to apply bold and other format to Paragraph RUNs. 
     * @return
     */
	public TableRowStyle bold() {
	    //TODO: This doesn't work so trying to apply style to all paragraphs runs
	    this.bold = true;
	    return this;
	}
	
	/**
	 * Table will show this on every page. It is very useful when you are building reports.
	 * @return
	 */
    public TableRowStyle repeatTableHeaderOnEveryPage() {
        this.repeatTableHeaderOnEveryPage = true;
        return this;
    }
    
    /**
     * It Sets up the background color for the cell.
     * */
    public TableRowStyle bgColor(String bgColor) {
		this.bgColor = bgColor;
		return this;
	}
    
	
	//### Chunk of code bellow ######################################
    
    private String doStyleBold(StringBuilder style, String txt) { //bold can only be applied to "run|rPr" not "pPr" 
        //hardcode applying style to the Paragraph
        if (bold ) {
            //style.append("\n            	<w:b/>");
            txt = txt.replace("<w:r>", "<w:r>\n	<w:rPr>\n            	<w:b/>\n	</w:rPr>\n"); 
        }
        return txt;
    }

    private void doStyleBgColor(StringBuilder style) {
        if (!"".equals(bgColor)) {
            style.append("\n            	<w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"" + this.bgColor + "\"/>\n");
        }
    }
    
    private void doStyleRepeatTableHeader(StringBuilder style) {
    	if (repeatTableHeaderOnEveryPage) {
    		style.append("\n            	<w:tblHeader/>\n");
    	}
    }
	
}
