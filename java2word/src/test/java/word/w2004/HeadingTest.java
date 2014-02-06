package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.api.interfaces.IElement;
import word.utils.TestUtils;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Heading3;
import word.w2004.style.HeadingStyle;

public class HeadingTest extends Assert{

    @Test
    public void testH1Style(){
        Heading1 h1 = (Heading1) Heading1.with("222222").withStyle().bold().italic().create();

        assertEquals(2, TestUtils.regexCount(h1.getContent(), "<*w:rPr>"));
        assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:jc w:val=\"left\" />")); //default is left
        assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:b/>"));
        assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:i/>"));

    }

    @Test
    public void testH1(){
        Heading1 h1 = Heading1.with("h1");
        assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:p wsp:rsidR*"));
        assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:t>h1</w:t>"));
        assertEquals(1, TestUtils.regexCount(h1.getContent(), "</w:p>"));
        assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:pStyle w:val=\"Heading1\" />"));
    }

    @Test
    public void testH1fluent(){
        Heading1 h1 = Heading1.with("h1").create();
        assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:p wsp:rsidR*"));
        assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:t>h1</w:t>"));
        assertEquals(1, TestUtils.regexCount(h1.getContent(), "</w:p>"));
        assertEquals(1, TestUtils.regexCount(h1.getContent(), "<w:pStyle w:val=\"Heading1\" />"));
    }

    @Test
    public void testH2(){
        Heading2 h2 = Heading2.with("h2");
        assertEquals(1, TestUtils.regexCount(h2.getContent(), "<w:p wsp:rsidR*"));
        assertEquals(1, TestUtils.regexCount(h2.getContent(), "<w:t>h2</w:t>"));
        assertEquals(1, TestUtils.regexCount(h2.getContent(), "</w:p>"));
        assertEquals(1, TestUtils.regexCount(h2.getContent(), "<w:pStyle w:val=\"Heading2\" />"));
    }

    @Test
    public void testH2Fluent(){
        Heading2 h2 = Heading2.with("h2").create();
        assertEquals(1, TestUtils.regexCount(h2.getContent(), "<w:p wsp:rsidR*"));
        assertEquals(1, TestUtils.regexCount(h2.getContent(), "<w:t>h2</w:t>"));
        assertEquals(1, TestUtils.regexCount(h2.getContent(), "</w:p>"));
        assertEquals(1, TestUtils.regexCount(h2.getContent(), "<w:pStyle w:val=\"Heading2\" />"));
    }

    @Test
    public void testH3(){
        Heading3 h3 = Heading3.with("h3");
        assertEquals(1, TestUtils.regexCount(h3.getContent(), "<w:p wsp:rsidR*"));
        assertEquals(1, TestUtils.regexCount(h3.getContent(), "<w:t>h3</w:t>"));
        assertEquals(1, TestUtils.regexCount(h3.getContent(), "</w:p>"));
        assertEquals(1, TestUtils.regexCount(h3.getContent(), "<w:pStyle w:val=\"Heading3\" />"));
    }

    @Test
    public void testH3Fluent(){
        Heading3 h3 = Heading3.with("h3").create();
        assertEquals(1, TestUtils.regexCount(h3.getContent(), "<w:p wsp:rsidR*"));
        assertEquals(1, TestUtils.regexCount(h3.getContent(), "<w:t>h3</w:t>"));
        assertEquals(1, TestUtils.regexCount(h3.getContent(), "</w:p>"));
        assertEquals(1, TestUtils.regexCount(h3.getContent(), "<w:pStyle w:val=\"Heading3\" />"));
    }

    @Test
    public void testEmpty(){
        Heading1 h1 = Heading1.with("");
        assertEquals("", h1.getContent());

        Heading2 h2 = Heading2.with("");
        assertEquals("", h2.getContent());

        Heading3 h3 = Heading3.with("");
        assertEquals("", h3.getContent());
    }

    @Test
    public void testFluent(){
        Heading1 h1 = (Heading1) Heading1.with("h111").withStyle().bold().italic().align(HeadingStyle.Align.CENTER).create();
        Heading2 h2 = (Heading2) Heading2.with("h222").withStyle().bold().italic().align(HeadingStyle.Align.CENTER).create();
        Heading3 h3 = (Heading3) Heading3.with("h222").withStyle().bold().italic().align(HeadingStyle.Align.CENTER).create();
        verifyStyles(h1);
        verifyStyles(h2);
        verifyStyles(h3);
    }

    private void verifyStyles(IElement e) {
        assertEquals(2, TestUtils.regexCount(e.getContent(), "<*w:rPr>"));
        assertEquals(1, TestUtils.regexCount(e.getContent(), "<w:jc w:val=\"center\" />")); //default is left
        assertEquals(1, TestUtils.regexCount(e.getContent(), "<w:b/>"));
        assertEquals(1, TestUtils.regexCount(e.getContent(), "<w:i/>"));
    }


}
