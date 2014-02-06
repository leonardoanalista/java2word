package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.api.interfaces.IElement;
import word.utils.TestUtils;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.Font;

public class ParagraphPieceTest extends Assert {

    @Test
    public void sanityTest() {
        IElement par = ParagraphPiece.with("");
        assertEquals(par.getContent(), "");
    }

    @Test
    public void sanityTest01() {
        IElement par = ParagraphPiece.with(null);
        assertEquals(par.getContent(), "");
    }

    @Test
    public void testGetContent() {
        IElement par = ParagraphPiece.with("piece01");

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1,
                TestUtils.regexCount(par.getContent(), "<w:t>piece01</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));

        // if there is no style, shouldn't have this
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<*w:rPr>"));
    }

    private void doBasicChecking(IElement par,  String value) {
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:t>" + value + "</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:rPr>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:rPr>"));
    }
    
    @Test
    public void testGetContentWithStyleALL() {
        IElement par = ParagraphPiece.with("piece01").withStyle().bold()
                .italic().underline().fontSize("24")
                .font(Font.COURIER).textColor("008000").create();

        doBasicChecking(par, "piece01");

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"48\" />"));
    }

    @Test
    public void testGetContentWithStyleBold() {
        IElement par = ParagraphPiece.with("piece01").withStyle().bold().create();

        doBasicChecking(par, "piece01");

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));

    }

    @Test
    public void testGetContentWithStyleItalic() {
        IElement par = ParagraphPiece.with("piece01").withStyle().italic().create();

        doBasicChecking(par, "piece01");

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold

        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic

        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));

    }

    @Test
    public void testGetContentWithStyleUnderline() {
        IElement par = ParagraphPiece.with("piece01").withStyle()
                .underline().create();

        doBasicChecking(par, "piece01");

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic

        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));

    }

    @Test
    public void testGetContentWithStyleFont() {
        IElement par = ParagraphPiece.with("piece01").withStyle()
                .font(Font.COURIER).create();

        doBasicChecking(par, "piece01");

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));

    }

    @Test
    public void testGetContentWithStyleTextColor() {
        IElement par = ParagraphPiece.with("piece01").withStyle()
                .textColor("008000").create();

        doBasicChecking(par, "piece01");

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:u w:val=\"single\"/>")); // underline
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "w:color w:val=\"008000\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));
    }

    @Test
    public void testGetContentWithStyleFontSize() {
        IElement par = ParagraphPiece.with("piece01").withStyle()
                .fontSize("24").create();

        doBasicChecking(par, "piece01");

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:u w:val=\"single\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(), "w:color w:val=\"008000\"/>")); // underline
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<w:rFonts w:ascii=\"Courier\" w:h-ansi=\"Courier\"/>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(),
                "<wx:font wx:val=\"Courier\"/>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:sz w:val=\"(.*)\" />"));
        assertEquals(1, TestUtils.regexCount(par.getContent(),
                "<w:sz-cs w:val=\"(.*)\" />"));
    }

    @Test
    public void testGetContentWithStyleBGcolor() {
        IElement par = ParagraphPiece.with("piece01").withStyle()
        .bgColor("FFFF00")
        .create();

        doBasicChecking(par, "piece01");

        assertEquals(1, TestUtils.regexCount(par.getContent(), "FFFF00")); //Background Color
    }

    @Test
    public void testNOsmartFont() {
        /***
         * the font is "ARIAL_NARROW", so there should not be any bold tag in it.
         */

        IElement par = ParagraphPiece.with("piece01").withStyle().font(Font.ARIAL_NARROW).create();

        doBasicChecking(par, "piece01");

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold

        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
    }

    @Test
    public void testFontBold() {
        /***
         * the font is "ARIAL_NARROW_BOLD", so there has to be a 'smart' bold tag in it.
         * There should not be any 'italic' this time
         */
        IElement par = ParagraphPiece.with("piece01").withStyle().font(Font.ARIAL_NARROW_BOLD).create();
        
        doBasicChecking(par, "piece01");

        assertEquals(3, TestUtils.regexCount(par.getContent(), "Arial Narrow Bold")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
    }

    @Test
    public void testFontItalic() {
        /***
         * the font is "ARIAL_NARROW_ITALIC", so there has to be a 'smart' Italic tag in it.
         * There should not be any 'bold' this time
         */
        IElement par = ParagraphPiece.with("piece01").withStyle().font(Font.ARIAL_NARROW_ITALIC).create();

        doBasicChecking(par, "piece01");

        assertEquals(3, TestUtils.regexCount(par.getContent(), "Arial Narrow Italic")); // 
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // italic
    }

    @Test
    public void testFontItalicAndBold() {
        /***
         * the font is "ARIAL_NARROW_ITALIC", so there has to be both 'smart' Italic and 'bold' tags in it.
         */
        IElement par = ParagraphPiece.with("piece01").withStyle().font(Font.ARIAL_NARROW_BOLD_ITALIC).create();

        doBasicChecking(par, "piece01");
        
        assertEquals(3, TestUtils.regexCount(par.getContent(), "Arial Narrow Bold Italic")); // bold
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:b/>")); // can't have any standalone
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:i/>")); // can't have any standalone
    }

    @Test
    public void testSubscript() {
        IElement par = ParagraphPiece.with("piece01").withStyle().subscript().create();
        doBasicChecking(par, "piece01");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:vertAlign w:val=\"subscript\"/>"));
    }
    
    @Test
    public void testSuperscript() {
        IElement par = ParagraphPiece.with("piece01").withStyle().superscript().create();
        doBasicChecking(par, "piece01");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:vertAlign w:val=\"superscript\"/>"));
    }
    
    @Test
    public void testCaps() {
        IElement par = ParagraphPiece.with("piece01").withStyle().caps().create();
        doBasicChecking(par, "piece01");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:caps/>"));
    }
    
    @Test
    public void testDoubleStrike() {
        IElement par = ParagraphPiece.with("piece01").withStyle().doubleStrike().create();
        doBasicChecking(par, "piece01");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:dstrike/>"));
    }
    
    @Test
    public void testStrike() {
        IElement par = ParagraphPiece.with("piece01").withStyle().strike().create();
        doBasicChecking(par, "piece01");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:strike/>"));
    }
    
    @Test
    public void testEmboss() {
        IElement par = ParagraphPiece.with("piece01").withStyle().emboss().create();
        doBasicChecking(par, "piece01");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:emboss/>"));
    }
    
    @Test
    public void testImprint() {
        IElement par = ParagraphPiece.with("piece01").withStyle().imprint().create();
        doBasicChecking(par, "piece01");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:imprint/>"));
    }
    
    @Test
    public void testOutline() {
        IElement par = ParagraphPiece.with("piece01").withStyle().outline().create();
        doBasicChecking(par, "piece01");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:outline/>"));
    }
    
    @Test
    public void testShadow() {
        IElement par = ParagraphPiece.with("piece01").withStyle().shadow().create();
        doBasicChecking(par, "piece01");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:shadow/>"));
    }

    @Test
    public void testSmallCaps() {
        IElement par = ParagraphPiece.with("piece01").withStyle().smallCaps().create();
        doBasicChecking(par, "piece01");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:smallCaps/>"));
    }
    
    @Test
    public void testVanish() {
        IElement par = ParagraphPiece.with("piece01").withStyle().vanish().create();
        doBasicChecking(par, "piece01");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:vanish/>"));
    }
    
    
    @Test
    public void testBidiNoValue(){
        ParagraphPiece par = ParagraphPiece.with("111");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:r>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:t>111</w:t>"));
        assertEquals(1, TestUtils.regexCount(par.getContent(), "</w:r>"));
        assertEquals(0, TestUtils.regexCount(par.getContent(), "<w:lang w:bidi=\".*\" />"));
    }
    
    @Test
    public void testBidi(){
        ParagraphPiece par = ParagraphPiece.with("111").withStyle().bidi("HE").create();
        doBasicChecking(par, "111");
        assertEquals(1, TestUtils.regexCount(par.getContent(), "<w:lang w:bidi=\".*\" />"));
        
    }
    

}
