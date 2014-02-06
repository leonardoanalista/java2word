package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.ISuperStylin;

public class TableCellStyle implements ISuperStylin{
	
	private IElement element;
	StringBuilder style = new StringBuilder("");
	
	private String bgColor = "";
	private int gridSpan = 0;
	
	@Override
	public String getNewContentWithStyle(String txt) {
		
		doStyleBgColor(style);
		doStyleGridSpan(style);
		
		if(!"".equals(style.toString())){
			style.insert(0, "<w:tcPr>");
			style.append("</w:tcPr>\n");			
		}
		
		return txt.replace("{styleCellPh}", style);
	}

	@Override
	public void setElement(IElement element) {
		this.element = element;		
	}

	@Override
	public IElement create() {
		return this.element;
	}

	//### Useful external methods ############################
    public TableCellStyle bgColor(String bgColor) {
		this.bgColor = bgColor;
		return this;
	}
    /*
     * 2 means: it will merge this cell with the second one. 
     * 3 means: it will merge this cell with the second AND the third ones. 
     * 
     * */
    public TableCellStyle gridSpan(int gridSpan) {
    	this.gridSpan = gridSpan;
    	return this;
    }
    
    
	//### Chunk of code ######################################
    private void doStyleBgColor(StringBuilder style) {
    	if (!"".equals(bgColor)) {
    		style.append("\n            	<w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"" + this.bgColor + "\"/>\n");
    	}
    }
	
    private void doStyleGridSpan(StringBuilder style) {
    	if (gridSpan > 0) {
    		style.append("\n            	<w:gridSpan w:val=\"" + this.gridSpan + "\"/>");
    	}
    }
    
}
