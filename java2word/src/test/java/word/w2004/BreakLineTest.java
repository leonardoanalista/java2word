package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElement;
import word.api.interfaces.ISuperStylin;
import word.utils.TestUtils;
import word.w2004.elements.BreakLine;

public class BreakLineTest extends Assert {
    
    @Test
    public void testBreakDefaultTest() {
        BreakLine br = new BreakLine();
        assertEquals(
                "\n<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>",
                br.getContent());
        int tot = TestUtils
                .regexCount(br.getContent(),
                        "(<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>)");
        assertEquals(1, tot);
        
    }

    @Test
    public void testBreakTimes() {
        BreakLine br = BreakLine.times(3).create();
        int tot = TestUtils
                .regexCount(br.getContent(),
                        "(<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>)");
        assertEquals(3, tot);
    }

    @Test
    public void testBreakNumberConstructor() {
        BreakLine br = new BreakLine(1);
        assertEquals(
                "\n<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>",
                br.getContent());
        int tot = TestUtils
                .regexCount(br.getContent(),
                        "(<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>)");
        assertEquals(1, tot);
    }

    @Test
    public void testBreakNumberConstructor02() {
        BreakLine br02 = new BreakLine(2);
        int tot = TestUtils
                .regexCount(br02.getContent(),
                        "(<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>)");
        assertEquals(2, tot);

        BreakLine br04 = new BreakLine(4);
        assertEquals(
                TestUtils
                        .regexCount(br04.getContent(),
                                "(<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>)"),
                4);
    }

    
    static { 
        new BreakLine().create();
        BreakLine.times(1).create();  
    } 
    
}
