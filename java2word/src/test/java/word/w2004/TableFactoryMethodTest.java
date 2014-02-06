package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.w2004.elements.tableElements.TableCol;
import word.w2004.elements.tableElements.TableDefinition;
import word.w2004.elements.tableElements.TableEle;
import word.w2004.elements.tableElements.TableFactoryMethod;
import word.w2004.elements.tableElements.TableFooter;
import word.w2004.elements.tableElements.TableHeader;

public class TableFactoryMethodTest extends Assert{
	
	@Test
	public void testGetInstance(){
		TableFactoryMethod instance = TableFactoryMethod.getInstance();
		assertNotNull(instance);
	}
	
	@Test
	public void testGetTableItem(){
		TableFactoryMethod instance = TableFactoryMethod.getInstance();
		
		assertTrue(instance.getTableItem(TableEle.TABLE_DEF) instanceof TableDefinition);
		assertTrue(instance.getTableItem(TableEle.TH) instanceof TableHeader);
		assertTrue(instance.getTableItem(TableEle.TD) instanceof TableCol);
		assertTrue(instance.getTableItem(TableEle.TF) instanceof TableFooter);
		assertTrue(instance.getTableItem(TableEle.TD) instanceof TableCol);
		
		assertNull(instance.getTableItem(null));
	}
	
	
}
	