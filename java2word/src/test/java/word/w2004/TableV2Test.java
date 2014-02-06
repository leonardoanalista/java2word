package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;
import word.w2004.elements.TableV2;
import word.w2004.elements.tableElements.TableRow;


public class TableV2Test extends Assert {

    //TODO: write tests once table is ok
	
    @Test
    // New table has to return ""
    public void testCreateEmptyTable() {
        TableV2 tbl = new TableV2();
        assertEquals("", tbl.getContent());
    }
    
    @Test
    public void testStringEmptyString() {
    	TableV2 tbl = new TableV2();        
    	tbl.addRow( TableRow.with("") );
    	tableBasicCheckings(tbl.getContent());
    	
    	assertEquals(0, TestUtils.regexCount(tbl.getContent(), "<w:tblHeader/>")); //empty paragraph
    	assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:p><w:r></w:r></w:p>")); //empty paragraph
    }
    
    @Test
    public void testWithHeaderOnEveryPage() {
    	TableV2 tbl = new TableV2();        
    	tbl.addRow( TableRow.with("").withStyle().repeatTableHeaderOnEveryPage().create() );
    	tableBasicCheckings(tbl.getContent());
    	
    	assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:tblHeader/>")); //empty paragraph
    	assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:p><w:r></w:r></w:p>")); //empty paragraph
    }
    
    @Test
    public void testStringOnly() {
    	TableV2 tbl = new TableV2();        
        tbl.addRow( TableRow.with("str01", "str02").withStyle().repeatTableHeaderOnEveryPage().create() );
        
        assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>str01</w:t>"));
        assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>str02</w:t>"));
        
        tableBasicCheckings(tbl.getContent());
    }
	
    @Test
    public void testParagraph() {
    }

    @Test
    public void testParagraphWithStyle() {
    }
    
    @Test
    public void testParagraphWithPieces() {
    }
    
    @Test
    public void testMixStringParagraph() {
    }
    
    @Test
    public void testCellBackgorundColor() {
    }	
    
    
    
    
	private void tableBasicCheckings(String content) {
		assertEquals(1, TestUtils.regexCount(content, "<w:tbl>"));
        assertEquals(1, TestUtils.regexCount(content, "</w:tbl>"));
        assertEquals(2, TestUtils.regexCount(content, "<*w:tblGrid>"));
        assertEquals(1, TestUtils.regexCount(content, "<w:tblPr>"));
        assertEquals(1, TestUtils.regexCount(content, "</w:tblPr>"));
	}
	
}
