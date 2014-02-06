package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;
import word.w2004.elements.Paragraph;


public class Header2004Test extends Assert{

    @Test
    public void sanityTest(){
        Header2004 hd = new Header2004();
        assertEquals("", hd.getContent());
    }

    @Test
    public void testAddEle(){
        Header2004 hd = new Header2004();
        hd.addEle(Paragraph.with("p1"));
        assertEquals(2, TestUtils.regexCount(hd.getContent(), "<*w:hdr"));
        assertEquals(1, TestUtils.regexCount(hd.getContent(), "<w:t>p1</w:t>"));
    }

    @Test
    public void testAddEleString(){
        Header2004 hd = new Header2004();
        hd.addEle("<w:t>p1</w:t>");
        assertEquals(2, TestUtils.regexCount(hd.getContent(), "<*w:hdr"));
        assertEquals(1, TestUtils.regexCount(hd.getContent(), "<w:t>p1</w:t>"));
    }

    @Test
    public void testHideHeaderandFooter(){ //this has to be tested in the body...
        Header2004 hd = new Header2004();
        hd.setHideHeaderAndFooterFirstPage(true);
        assertTrue(hd.getHideHeaderAndFooterFirstPage());
    }

}
