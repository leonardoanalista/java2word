package word.w2004.elements.tableElements;

/**
 * @author leonardo_correa
 * This is returns all that crap at the top of the table, including table properties.
 * 
 * It also returns the end of the table. 
 */
public class TableDefinition implements ITableItemStrategy{

	@Override
	public String getTop(int cols) {
		String top = 
			 "\n	<w:tbl> "
			+"\n            <w:tblPr> "
			+"\n                <w:tblW w:w=\"0\" w:type=\"auto\"/> "
			+"\n                <w:tblBorders> "
			+"\n                    <w:top w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                    <w:left w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                    <w:bottom w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                    <w:right w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                    <w:insideH w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                    <w:insideV w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> "
			+"\n                </w:tblBorders> "
			+"\n                <w:tblLook w:val=\"00BF\"/> "
			+"\n            </w:tblPr> "
			;
		top += getTblGrid(cols);
		return top;
	}

	private String getTblGrid(int cols) {
		String tblGrid = 
			 "\n			<w:tblGrid> ";
		for (int i = 0; i < cols; i++) {
			tblGrid += "\n				<w:gridCol w:w=\"4258\"/> ";
		}
		tblGrid += "\n			</w:tblGrid> ";
		return tblGrid;
	}

	@Override
	public String getBottom() {
		return "\n	</w:tbl>";
	}

	@Override
	public String getMiddle() {
		return null;
	}

	@Override
	public String getTop() {
		return null;
	}
}
