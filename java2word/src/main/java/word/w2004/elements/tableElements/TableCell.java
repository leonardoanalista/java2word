package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElement;
import word.api.interfaces.IFluentElementStylable;
import word.w2004.elements.Paragraph;

public class TableCell implements IElement, IFluentElement<TableCell>, IFluentElementStylable<TableCellStyle>{
	
	StringBuilder txt = new StringBuilder("");
	private TableCellStyle style = new TableCellStyle();
    private final String tableCellTop = "\n     <w:tc>\n        {styleCellPh}";
    private final String tableCellBottom = "\n      </w:tc>";
	
    @Override
    public String getContent() {
    	String withStyle = style.getNewContentWithStyle(txt.toString());
        return withStyle;
    }
    
    /* 
     * Private Contructor, so you have to use Fluent Interface to create it 
     * */
    private TableCell() {
    }

    private TableCell(Object cell) {		   	
    	if (cell instanceof String) { //new Par
        	txt.append(tableCellTop);
        	
        	String cellValue = cell.toString();
        	if(cellValue.equals("")){        		
        		txt.append("<w:p><w:r></w:r></w:p>");//Empty Paragraph        		
        	}else{
        		txt.append(Paragraph.with(cellValue).create().getContent());
        	}
    	
        	txt.append(tableCellBottom);    	
        } else if (cell instanceof IElement) {

        	if(cell instanceof TableCell){ 
        		//simple do a getContent because object is already a TableCell
        		txt.append(tableCellTop);
        		txt.append(((TableCell) cell).txt.toString()); //it is a Paragraph at this moment
        		txt.append(tableCellBottom);  
        	}else{
        		//Paragraph for example...
        		//apply parent styles
        		//((Paragraph) cell).withStyle().
            	txt.append( ((IElement) cell).getContent() );
        	}
        	
        } else {
            throw new IllegalArgumentException(
                    "Parameter can only be String of IElement. You gave me: " + cell.getClass().toString());
        }
        
    }

	@Override
	public TableCell create() {
		return this;
	}
	
	
    /**
     * It can receive a String, TableCell or any other IElement.
     * for String, text will be wrapped on a Paragraph.
     * 
     * Use TableCell when you need to apply style to the Cell. Eg.: Background color. 
     *  PS.: If you need to apply "bold", "italic" or "underline" to the cell, use a Paragraph with its Pieces.
     *  
     * If you pass an IElement, the getContent() method will be blindly call. So be careful on what you pass. 
     * Remember you can always wrap elements or build your own String XML that represents what you want - Be creative!   
     * 
     * You can also pass 2 different kinds objects at the same time. Eg.:
     * <code>
     * tbl.addRow( TableRowV2.with( TableCell.with(Paragraph.with("Paragraph01").create()), "Simple String" ).withStyle().bold().create() );
     * </code>
     *   
     **/
	public static TableCell with(Object cell) {
		if(cell instanceof TableCell){
			//it is already TableCell so no need to create another instance
			//it is a Paragraph at this moment. It needs Column Top and Bottom    		
			((TableCell) cell).txt.insert(0, "\n		<w:tc>\n		{styleCellPh}");    		
			((TableCell) cell).txt.append("\n		</w:tc>"); 
			
			return (TableCell) cell; 
		}else{
			TableCell tableCell = new TableCell(cell);
			return tableCell;
		}
	}

	@Override
	public TableCellStyle withStyle() {
		style.setElement(this);
        return style;
	}

}
