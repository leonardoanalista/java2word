package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElementStylable;

public class TableRow implements IElement, IFluentElementStylable<TableRowStyle>{

	private StringBuilder txt = new StringBuilder("");
	private TableRowStyle style = new TableRowStyle();
	private final String tableRowTop = "\n <w:tr>{styleRowPh}";
	private final String tableRowBottom = "\n  </w:tr>";
	
	public TableRow(Object[] cols) {
        txt.append(tableRowTop);

        for (int i = 0; i < cols.length; i++) {
		Object col = cols[i];
		TableCell cell;
		if (col instanceof TableCell) {
			//If col already TableCell
			cell = (TableCell) col;
		} else {
			//TableCell knows how to do the rest.
			cell = TableCell.with(col).create();
		}
		txt.append(cell.getContent());
        }
		
        txt.append(tableRowBottom);
	}

	@Override
	public TableRowStyle withStyle() {
		style.setElement(this);
        return style;
	}

	@Override
	public String getContent() {
		String withStyle = style.getNewContentWithStyle(txt.toString());
		return withStyle;
	}

	public static TableRow with(Object ... colls) {		
		return new TableRow(colls);
	}

}
