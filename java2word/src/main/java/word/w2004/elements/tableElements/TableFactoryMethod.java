package word.w2004.elements.tableElements;

/**
 * @author leonardo_correa Factory Method for Table Elements, Header, Columns
 *         and Footer
 * 
 *         Here is the logic to decide which instance create and return
 * 
 */
public class TableFactoryMethod {

	private static TableFactoryMethod instance;

	private TableFactoryMethod() {
	}

	public static TableFactoryMethod getInstance() {
		if (instance == null) {
			instance = new TableFactoryMethod();
		}
		return instance;
	}

	public ITableItemStrategy getTableItem(TableEle tableEle) {
		if (tableEle == null) {
			return null;	
		} 
		
		return getTableEle(tableEle);
	}

	private ITableItemStrategy getTableEle(TableEle tableEle) {
		if (tableEle.getValue().equals("tableDef")) {
			return new TableDefinition();
		}else if (tableEle.getValue().equals("th")) {
			return new TableHeader();
		}else if (tableEle.getValue().equals("td")) {
			return new TableCol();
		}else { //if (tableEle.getValue().equals("tf")) {
			return new TableFooter();
		}
	}

}
