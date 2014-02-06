package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.elements.Table;
import word.w2004.elements.tableElements.TableCol;
import word.w2004.elements.tableElements.TableDefinition;
import word.w2004.elements.tableElements.TableEle;
import word.w2004.elements.tableElements.TableFooter;
import word.w2004.style.ParagraphStyle.Align;

public class TableTest extends Assert {

    @Test
    // New table has to return ""
    public void testCreateEmptyTable() {
        Table tbl01 = new Table();
        assertEquals("", tbl01.getContent());
    }

    // ### TH - Table Header ###
    @Test
    public void testTableWithArray() {
        Table tbl = new Table();
        String[] cols = { "aaa", "bbb" };
        tbl.addTableEle(TableEle.TH, cols);

        tableBasicCheckings(tbl.getContent());
        assertTrue(tbl.getContent().contains("<w:tr ")); // TH
        assertTrue(tbl.getContent().contains("<w:t>aaa</w:t>")); // TH
        assertTrue(tbl.getContent().contains("<w:t>bbb</w:t>")); // TH
        assertTrue(tbl.getContent().contains("</w:tr>"));// TH

        assertTrue(tbl.getContent().contains("</w:tbl>"));
    }

    @Test /* It still has to render the table header and cell */
    public void testCreateTableEmptyTH() {
        Table tbl = new Table();

        tbl.addTableEle(TableEle.TH, "");
        tbl.addTableEle(TableEle.TD, "");

        tableBasicCheckings(tbl.getContent());
        assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:r wsp:rsidRPr=\"004374EC\"> "));
        assertEquals(2, TestUtils.regexCount(tbl.getContent(), "<w:t></w:t>"));
    }

    @Test
    public void testTableHeaderNoRepeat() {
        Table tbl = new Table();
        tbl.addTableEle(TableEle.TH, "Name");
        assertEquals(0, TestUtils.regexCount(tbl.getContent(), "[{]tblHeader[}]"));
    }
    
    @Test
    public void testTableHeaderWITHRepeatHeader() {
        Table tbl = new Table();
        tbl.setRepeatTableHeaderOnEveryPage();
        
        tbl.addTableEle(TableEle.TH, "Name");
        tableBasicCheckings(tbl.getContent());
        assertEquals(0, TestUtils.regexCount(tbl.getContent(), "[{]tblHeader[}]"));
        assertEquals(2, TestUtils.regexCount(tbl.getContent(), "<*w:trPr>"));
        assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:tblHeader/>"));
    }
    
    @Test
    public void testTableDefinition() {
        TableDefinition tbldef = new TableDefinition();
        
        assertEquals(1, TestUtils.regexCount(tbldef.getTop(), "<*w:tbl>"));
        assertEquals(2, TestUtils.regexCount(tbldef.getTop(), "<*w:tblPr>"));

        assertNull(tbldef.getMiddle());

        assertEquals(1, TestUtils.regexCount(tbldef.getBottom(), "</w:tbl>"));
    }

    @Test
    public void testTableCol() {
        TableCol tblcol = new TableCol();
        assertEquals(1, TestUtils.regexCount(tblcol.getTop(), "<*w:tr"));
        assertEquals(2, TestUtils.regexCount(tblcol.getMiddle(), "<*w:tc>"));
        assertEquals(1, TestUtils.regexCount(tblcol.getMiddle(),
                "<w:t>[{]value[}]</w:t>")); // test placeholder
        assertEquals(1, TestUtils.regexCount(tblcol.getBottom(), "</w:tr>"));
    }

    @Test
    public void testTableFooter() {
        TableFooter tblFooter = new TableFooter();
        assertEquals(1, TestUtils.regexCount(tblFooter.getTop(), "<*w:tr"));
        assertEquals(2, TestUtils.regexCount(tblFooter.getMiddle(), "<*w:tc>"));
        // In this framework, footer has to be bold...
        assertEquals(1, TestUtils.regexCount(tblFooter.getMiddle(), "<w:b/>")); 
        assertEquals(1, TestUtils.regexCount(tblFooter.getMiddle(),
                "<w:t>[{]value[}]</w:t>")); // test placeholder
        assertEquals(1, TestUtils.regexCount(tblFooter.getBottom(), "</w:tr>"));
    }

    @Test
    public void testEmpty() {
        Table tbl = new Table();
        String[] arr = {};
        tbl.addTableEle(TableEle.TABLE_DEF, arr);
        assertEquals("", tbl.getContent());
    }

    // ### Full Table!!! ###
    @Test
    public void testCreateFullTable() {
        Table tbl = new Table();
        tbl.addTableEle(TableEle.TH, "Name", "Salary");

        tbl.addTableEle(TableEle.TD, "Leonardo", "100,000.00");
        tbl.addTableEle(TableEle.TD, "Romario", "1,000,000.00");

        tbl.addTableEle(TableEle.TF, "Total", "1,100,000.00");

        
        tableBasicCheckings(tbl.getContent());

        assertEquals(4, TestUtils.regexCount(tbl.getContent(), "<w:tr"));
        assertEquals(4, TestUtils.regexCount(tbl.getContent(), "</w:tr>"));

        assertEquals(1,
                TestUtils.regexCount(tbl.getContent(), "<w:t>Name</w:t>"));
        assertEquals(1,
                TestUtils.regexCount(tbl.getContent(), "<w:t>Salary</w:t>"));

        assertEquals(1,
                TestUtils.regexCount(tbl.getContent(), "<w:t>Leonardo</w:t>"));
        assertEquals(1,
                TestUtils.regexCount(tbl.getContent(), "<w:t>100,000.00</w:t>"));
        assertEquals(1,
                TestUtils.regexCount(tbl.getContent(), "<w:t>Romario</w:t>"));
        assertEquals(1, TestUtils.regexCount(tbl.getContent(),
                "<w:t>1,000,000.00</w:t>"));

        assertEquals(1,
                TestUtils.regexCount(tbl.getContent(), "<w:t>Total</w:t>"));
        assertEquals(1, TestUtils.regexCount(tbl.getContent(),
                "<w:t>1,100,000.00</w:t>"));

        assertEquals(1, TestUtils.regexCount(tbl.getContent(), "</w:tbl>"));

        assertEquals(tbl.getContent(), tbl.getContent());

    }

    @Test
    public void testCreateEmptyCell() {
        Table tbl = new Table();
        tbl.addTableEle(TableEle.TD, "Leonardo", "");
        
        assertEquals(1,
                TestUtils.regexCount(tbl.getContent(), "<w:t>Leonardo</w:t>"));
        assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t></w:t> "));        
    }
    
    
    @Test
    public void testParagraphSanity() {
    	Table tbl = new Table();
    	tbl.addTableEle(TableEle.TD, Paragraph.with("Flamengo").create());
    	tableBasicCheckings(tbl.getContent());
    	assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>Flamengo</w:t>"));
    }
    
    @Test
    public void testEmptyParagraph() {
        Table tbl = new Table();
        tbl.addTableEle(TableEle.TD, Paragraph.with("").create());
        
        tableBasicCheckings(tbl.getContent());
        assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:tr"));
        assertEquals(1, TestUtils.regexCount(tbl.getContent(), "</w:tr>"));

        assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t></w:t> "));
    }

    @Test
    public void testParagraphStyle() {
    	Table tbl = new Table();
    	Paragraph p1 = (Paragraph) Paragraph.withPieces(ParagraphPiece.with("Flamengo").withStyle().bold().italic().create()).withStyle().align(Align.RIGHT).create();
    	tbl.addTableEle(TableEle.TD, p1);
    	tableBasicCheckings(tbl.getContent());
    	
    	assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:b/>"));
    	assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:i/>"));
    	assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:jc w:val=\"right\"/>"));
    	assertEquals(1, TestUtils.regexCount(tbl.getContent(), "<w:t>Flamengo</w:t>"));
    }
    
	private void tableBasicCheckings(String content) {
		assertEquals(1, TestUtils.regexCount(content, "<w:tbl>"));
        assertEquals(1, TestUtils.regexCount(content, "</w:tbl>"));
        assertEquals(2, TestUtils.regexCount(content, "<*w:tblGrid>"));
        assertEquals(1, TestUtils.regexCount(content, "<w:tblPr>"));
        assertEquals(1, TestUtils.regexCount(content, "</w:tblPr>"));
	}

}
