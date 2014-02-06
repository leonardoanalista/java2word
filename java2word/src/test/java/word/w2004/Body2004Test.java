package word.w2004;

import org.junit.Test;

import word.api.interfaces.IElement;
import word.utils.TestUtils;
import word.w2004.elements.Paragraph;

import junit.framework.Assert;

public class Body2004Test extends Assert{

    @Test
    public void sanityTest(){
        Body2004 bd = new Body2004();
        assertEquals(2, TestUtils.regexCount(bd.getContent(), "<*w:body>"));
    }

    @Test
    public void testAddEle(){
        Body2004 bd = new Body2004();
        bd.addEle(new IElement() {
            @Override
            public String getContent() {
                return "I am an element";
            }
        });
        assertEquals(2, TestUtils.regexCount(bd.getContent(), "<*w:body>"));
        assertEquals(1, TestUtils.regexCount(bd.getContent(), "I am an element"));
    }

    @Test
    public void testAaddEleString(){
        Body2004 bd = new Body2004();
        bd.addEle("<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>"); //this is a breakline
        assertEquals(2, TestUtils.regexCount(bd.getContent(), "<*w:body>"));
        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>"));
    }

    @Test
    public void testHeader(){
        Body2004 bd = new Body2004();
        bd.getHeader().addEle(Paragraph.with("header01"));
        assertEquals(2, TestUtils.regexCount(bd.getContent(), "<*w:hdr"));
        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:t>header01</w:t>"));
    }

    @Test
    public void testFooter(){
        Body2004 bd = new Body2004();
        bd.getFooter().addEle(Paragraph.with("footer01"));
        assertEquals(2, TestUtils.regexCount(bd.getContent(), "<*w:ftr"));
        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:t>footer01</w:t>"));
    }

    @Test
    public void testHeaderAndFooterSame(){
        Body2004 bd = new Body2004();
        bd.getHeader().addEle(Paragraph.with("header01"));
        bd.getFooter().addEle(Paragraph.with("footer01"));
        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:t>header01</w:t>"));
        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:t>footer01</w:t>"));
        //System.out.println(bd.getContent());
    }

    @Test
    public void testHideHeaderAndFooter(){
        Body2004 bd = new Body2004();
        assertFalse(bd.getHeader().getHideHeaderAndFooterFirstPage());// default is false
        bd.getHeader().setHideHeaderAndFooterFirstPage(true);
        assertTrue(bd.getHeader().getHideHeaderAndFooterFirstPage());
        bd.getHeader().addEle(Paragraph.with("p1"));
        //System.out.println(bd.getContent());

        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:t>p1</w:t>"));
        assertEquals(2, TestUtils.regexCount(bd.getContent(), "<*w:sectPr"));

        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:hdr w:type=\"first\">"));
        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:ftr w:type=\"first\">"));

        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:pgSz w:w"));
        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:pgMar"));
        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:cols w:space"));
    }

    @Test
    public void testshowHeaderAndFooter(){
        Body2004 bd = new Body2004();
        bd.getHeader().addEle(Paragraph.with("p1"));
        assertFalse(bd.getHeader().getHideHeaderAndFooterFirstPage());// default is false

        assertEquals(1, TestUtils.regexCount(bd.getContent(), "<w:t>p1</w:t>"));
        assertEquals(2, TestUtils.regexCount(bd.getContent(), "<*w:sectPr"));

        assertEquals(0, TestUtils.regexCount(bd.getContent(), "<w:hdr w:type=\"first\">"));
        assertEquals(0, TestUtils.regexCount(bd.getContent(), "<w:ftr w:type=\"first\">"));

        assertEquals(0, TestUtils.regexCount(bd.getContent(), "<w:pgSz w:w"));
        assertEquals(0, TestUtils.regexCount(bd.getContent(), "<w:pgMar"));
        assertEquals(0, TestUtils.regexCount(bd.getContent(), "<w:cols w:space"));
    }

}
