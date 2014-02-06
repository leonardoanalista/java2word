package word.w2004;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import word.api.interfaces.IDocument;
import word.utils.TestUtils;
import word.utils.Utils;
import word.w2004.Document2004.Encoding;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Heading3;
import word.w2004.elements.HyperLink;
import word.w2004.elements.Image;
import word.w2004.elements.PageBreak;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.elements.Table;
import word.w2004.elements.TableV2;
import word.w2004.elements.tableElements.TableCell;
import word.w2004.elements.tableElements.TableEle;
import word.w2004.elements.tableElements.TableRow;
import word.w2004.style.Font;
import word.w2004.style.HeadingStyle.Align;

/**
 * @author leonardo_correa
 *
 */
public class Document2004Test extends Assert {

    @Test
    public void sanityTest() {
        IDocument myDoc = new Document2004();
        assertEquals(myDoc.getContent(), myDoc.getContent());
        assertEquals(myDoc.getContent(), myDoc.getContent());
    }

    @Test
    public void testUri() {
        IDocument myDoc = new Document2004();
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> "
                + "<?mso-application progid=\"Word.Document\"?> "
                + "<w:wordDocument xmlns:aml=\"http://schemas.microsoft.com/aml/2001/core\" "
                + " xmlns:dt=\"uuid:C2F41010-65B3-11d1-A29F-00AA00C14882\" xmlns:mo=\"http://schemas.microsoft.com/office/mac/office/2008/main\" "
                + " xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" "
                + " xmlns:mv=\"urn:schemas-microsoft-com:mac:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
                + " xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\" "
                + " xmlns:w=\"http://schemas.microsoft.com/office/word/2003/wordml\" "
                + " xmlns:wx=\"http://schemas.microsoft.com/office/word/2003/auxHint\" "
                + " xmlns:wsp=\"http://schemas.microsoft.com/office/word/2003/wordml/sp2\" "
                + " xmlns:sl=\"http://schemas.microsoft.com/schemaLibrary/2003/core\" "
                + " w:macrosPresent=\"no\" w:embeddedObjPresent=\"no\" w:ocxPresent=\"no\" "
                + " xml:space=\"preserve\"> "
                + " <w:ignoreSubtree w:val=\"http://schemas.microsoft.com/office/word/2003/wordml/sp2\" /> ";

        assertEquals("Uri is not as expected: ", expected, myDoc.getUri());
    }

    @Test
    public void testDefaultEncodingUTF() {
        IDocument myDoc = new Document2004();
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(), "encoding=\"UTF-8\""));

        IDocument myDoc02 = new Document2004();
        myDoc02.encoding(Encoding.UTF_8);
        
        assertEquals(1, TestUtils.regexCount(myDoc02.getContent(), "encoding=\"UTF-8\""));
    }

    @Test
    public void testDefaultEncodingISO8859_1() {
        IDocument myDoc = new Document2004();
        myDoc.encoding(Encoding.ISO8859_1);
        
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(), "encoding=\"ISO8859-1\""));
    }
    
    @Test
    public void testDefaultEncodingAsString() {
        IDocument myDoc = new Document2004();
        myDoc.encoding("windows-1251");

        assertEquals(1, TestUtils.regexCount(myDoc.getContent(), "encoding=\"windows-1251\""));
    }


    @Test
    public void testDocHeadWithDefaults() {
        IDocument myDoc = new Document2004();
        assertEquals(2, TestUtils.regexCount(myDoc.getContent(),
                "<*o:DocumentProperties>")); // open/close test
        assertEquals(2, TestUtils.regexCount(myDoc.getContent(), "<*w:fonts>")); // open/close
                                                                                 // test
        assertEquals(2, TestUtils.regexCount(myDoc.getContent(), "<*w:styles>")); // open/close
                                                                                  // test
        assertEquals(2, TestUtils.regexCount(myDoc.getContent(), "<*w:docPr>")); // open/close
                                                                                 // test
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<w:view w:val=\"print\"/>")); // set up as print to be able to
                                               // view page breaks etc...

        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Title>Java2word title</o:Title>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Subject>Created by Java2word library</o:Subject>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Keywords>java2word, word document</o:Keywords>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Description></o:Description>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Category></o:Category>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Author>Leonardo Correa</o:Author>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:LastAuthor>Leonardo Correa</o:LastAuthor>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Manager>Leonardo Correa</o:Manager>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Company>Java2word, coding for fun!</o:Company>"));
    }

    @Test
    public void testDocHead() {
        IDocument myDoc = new Document2004();
        myDoc.title("my title").subject("my subject").keywords("my keywords")
                .description("my description").category("my category")
                .author("the author").lastAuthor("the last author")
                .manager("the manager").company("my company");

        assertEquals(2, TestUtils.regexCount(myDoc.getContent(),
                "<*o:DocumentProperties>")); // open/close test
        assertEquals(2, TestUtils.regexCount(myDoc.getContent(), "<*w:fonts>")); // open/close
                                                                                 // test
        assertEquals(2, TestUtils.regexCount(myDoc.getContent(), "<*w:styles>")); // open/close
                                                                                  // test
        assertEquals(2, TestUtils.regexCount(myDoc.getContent(), "<*w:docPr>")); // open/close
                                                                                 // test
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<w:view w:val=\"print\"/>")); // set up as print to be able to
                                               // view page breaks etc...

        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Title>my title</o:Title>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Subject>my subject</o:Subject>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Keywords>my keywords</o:Keywords>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Description>my description</o:Description>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Category>my category</o:Category>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Author>the author</o:Author>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:LastAuthor>the last author</o:LastAuthor>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Manager>the manager</o:Manager>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getContent(),
                "<o:Company>my company</o:Company>"));
    }

    @Test
    public void testHead() {
        IDocument myDoc = new Document2004();
        assertTrue(myDoc.getContent().contains("<w:docPr>"));
        assertTrue(myDoc.getContent()
                .contains("<w:view w:val=\"print\"/>"));
        assertTrue(myDoc.getContent()
                .contains("<w:zoom w:percent=\"100\"/>"));
        assertTrue(myDoc.getContent().contains("</w:docPr>"));
    }

    @Test
    public void testGetHeader() {
        IDocument myDoc = new Document2004();
        assertTrue(myDoc.getBody().getContent().contains("<w:body>"));
        assertTrue(myDoc.getBody().getContent().contains("</w:body>"));
    }

    @Test
    public void testBody() {
        IDocument myDoc = new Document2004();

        assertEquals(1,
                TestUtils.regexCount(myDoc.getBody().getContent(), "<w:body>"));
        assertEquals(1,
                TestUtils.regexCount(myDoc.getBody().getContent(), "</w:body>"));
    }

    @Test
    public void testHeader() {
        IDocument myDoc = new Document2004();

        assertEquals("", myDoc.getHeader().getContent());

        myDoc.getHeader().addEle(Paragraph.with("paragraph01").create());

        assertEquals(1, TestUtils.regexCount(myDoc.getHeader().getContent(),
                "<w:hdr w:type=\"odd\">"));
        assertEquals(1, TestUtils.regexCount(myDoc.getHeader().getContent(),
                "<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"00000000\">"));
        assertEquals(1, TestUtils.regexCount(myDoc.getHeader().getContent(),
                "<w:t>paragraph01</w:t>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getHeader().getContent(),
                "</w:hdr>"));
    }

    @Test
    public void testFooter() {
        IDocument myDoc = new Document2004();

        assertEquals("", myDoc.getFooter().getContent());

        myDoc.getFooter().addEle(Paragraph.with("paragraph01").create());

        assertEquals(1, TestUtils.regexCount(myDoc.getFooter().getContent(),
                "<w:ftr w:type=\"odd\">"));
        assertEquals(1, TestUtils.regexCount(myDoc.getFooter().getContent(),
                "<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"00000000\">"));
        assertEquals(1, TestUtils.regexCount(myDoc.getFooter().getContent(),
                "<w:t>paragraph01</w:t>"));
        assertEquals(1, TestUtils.regexCount(myDoc.getFooter().getContent(),
                "</w:ftr>"));
    }

    @Test
    public void testPageOrientationDefault() {
        IDocument doc = new Document2004();
        assertEquals(0, TestUtils.regexCount(doc.getContent(), "landscape"));
    }

    @Test
    public void testPageOrientationLandscape() {
        IDocument doc = new Document2004();
        doc.setPageOrientationLandscape();

        assertEquals(1, TestUtils.regexCount(doc.getContent(), "landscape"));
    }

    @Test
    public void testAddElementAliasString() {
        IDocument myDoc = new Document2004();
        myDoc.addEle(Heading1.with("heading1").create().getContent());

        assertTrue(myDoc.getBody().getContent().contains("<w:body>"));
        assertTrue(myDoc.getBody().getContent().contains("<w:t>heading1</w:t>"));
        assertTrue(myDoc.getBody().getContent().contains("</w:body>"));
    }

    @Ignore 
    // ignored by default just to not create files in your system or break the build...
    @Test
    public void testJava2wordAllInOne() {

        IDocument myDoc = new Document2004();
        // myDoc.setPageOrientationLandscape();
        // default is Portrait be can be changed.
        myDoc.encoding(Encoding.UTF_8); //or ISO8859-1. Default is UTF-8

        myDoc.addEle(BreakLine.times(1).create()); // this is one breakline

        // Headings
        myDoc.addEle(Heading1.with("===== Java2word ======").create());
        myDoc.addEle(Heading2.with("===== Headings ======").create());
        myDoc.addEle(Paragraph
                .with("This doc has been generated by the unit test testJava2wordAllInOne() in the class DocumentTest2004Test.java.")
                .create());
        myDoc.addEle(BreakLine.times(1).create());

        myDoc.addEle(Paragraph
                .with("I will try to use a little bit of everything in the API Java2word. "
                        + "I realised that is very dificult to keep the doucmentation updated "
                        + "so this is where I will demostrate how to do some cool things with Java2word!")
                .create());

        myDoc.addEle(Heading1.with("Heading01 without styling").create());
        myDoc.addEle(Heading2.with("Heading02 with style Center").withStyle()
                .align(Align.CENTER).italic().create());
        myDoc.addEle(Heading3.with("Heading03 aligned Right").withStyle().bold()
                .align(Align.RIGHT).create());

        // Paragraph and ParagrapPiece
        myDoc.addEle(Heading2.with("===== Paragraph and ParagrapPiece ======")
                .create());
        myDoc.addEle(Paragraph.with("I am a very simple paragraph.").create());

        myDoc.addEle(BreakLine.times(1).create());
        ParagraphPiece myParPiece01 = ParagraphPiece
                .with("If you use the class 'Paragraph', you will have limited style. Maybe only paragraph Aligment or BgColor.");
        ParagraphPiece myParPiece02 = ParagraphPiece
                .with("In order to use more advanced style, you have to use ParagraphPiece");
        ParagraphPiece myParPiece03 = ParagraphPiece
                .with("One example of this is when you want to make ONLY one word BOLD or ITALIC. the way to to this is create many pieces, format them separetely and put all together in a Paragraph object. Example:");

        myDoc.addEle(Paragraph.withPieces(myParPiece01, myParPiece02,
                myParPiece03).create());

        ParagraphPiece myParPieceJava = ParagraphPiece.with("I like Java and ")
                .withStyle().font(Font.COURIER).create();
        ParagraphPiece myParPieceRuby = ParagraphPiece.with("Ruby!!! ")
                .withStyle().bold().italic().create();
        ParagraphPiece myParPieceAgile = ParagraphPiece
                .with("I actually like Java, Ruby Agile, BDD, Cucumber... ")
                .withStyle().textColor("008000").create();

        myDoc.addEle(Paragraph.withPieces(myParPieceJava, myParPieceRuby,
                myParPieceAgile).create());

        myDoc.addEle(BreakLine.times(2).create());
        myDoc.addEle(Paragraph.withPieces(
                ParagraphPiece.with("This is a manual 'bold' and 'italic'")
                        .withStyle().font(Font.COURIER).bold().italic()
                        .create()).create());
        myDoc.addEle(Paragraph
                .withPieces(
                        ParagraphPiece
                                .with("This is the SAME as the above line but with 'Smart' Bold/Italic ")
                                .withStyle().font(Font.COURIER_BOLD_ITALIC)
                                .create()).create());
        myDoc.addEle(BreakLine.times(1).create());

        // font size
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("No size")
                .create(), ParagraphPiece.with(" but I am size 24.").withStyle()
                .fontSize("24").create()));

        //ParagraphPiece and other format/styles
        myDoc.addEle(BreakLine.times(1).create());
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("New ParagraphPiece styles have been implemented. Here they are:").withStyle().fontSize("14").create()));

        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("The Style is: ").create(), ParagraphPiece.with("Subscript").withStyle().subscript().create()));
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("The Style is: ").create(), ParagraphPiece.with("Superscript").withStyle().superscript().create()));
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("The Style is: ").create(), ParagraphPiece.with("Strike").withStyle().strike().create()));
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("The Style is: ").create(), ParagraphPiece.with("Caps").withStyle().caps().create()));
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("The Style is: ").create(), ParagraphPiece.with("SmallCaps").withStyle().smallCaps().create()));
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("The Style is: ").create(), ParagraphPiece.with("DoubleStrike").withStyle().doubleStrike().create()));
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("The Style is: ").create(), ParagraphPiece.with("Emboss").withStyle().emboss().create()));
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("The Style is: ").create(), ParagraphPiece.with("Imprint").withStyle().imprint().create()));
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("The Style is: ").create(), ParagraphPiece.with("Outline").withStyle().outline().create()));
        myDoc.addEle(Paragraph.withPieces(ParagraphPiece.with("The Style is: ").create(), ParagraphPiece.with("Shadow").withStyle().shadow().create()));
        myDoc.addEle(BreakLine.times(2).create());

        myDoc.addEle(Paragraph.with("Remember to scape special characters like empsersand &amp;").create());  

        
        // Document Header and Footer
        myDoc.addEle(BreakLine.times(2).create());
        myDoc.addEle(Heading2.with("===== Document Header and Footer ======")
                .create());
        myDoc.addEle(Paragraph
                .with("By default everything is added to the Body when you do 'myDoc.addEle(...)'."
                        + " But you can add elements to the Header and/or Footer. Other cool thing is show page number or not.")
                .create());

        myDoc.addEle(BreakLine.times(2).create());
        myDoc.addEle(Paragraph
                .with("Page number is displayed by default but you can disable: 'myDoc.getFooter().showPageNumber(false)' ")
                .create());

        myDoc.addEle(BreakLine.times(2).create());
        myDoc.addEle(Paragraph
                .with("you can also hide Header and Footer in the first Page. This is useful for when you have a cover page.: 'myDoc.getHeader().setHideHeaderAndFooterFirstPage(true)' ")
                .create());

        myDoc.getHeader().addEle(
                Paragraph.withPieces(
                        ParagraphPiece.with("I am in the"),
                        ParagraphPiece.with(" Header ").withStyle().bold()
                                .create(), ParagraphPiece.with("of all pages"))
                        .create());

        myDoc.getFooter().addEle(
                Paragraph.with("I am in the Footer of all pages").create());

        // Images
        myDoc.addEle(BreakLine.times(1).create());
        myDoc.addEle(Heading2.with("===== Images ======").create());
        myDoc.addEle(Paragraph
                .with("Images can be created from diferent locations. It can be from your local machine, from web URL or classpath.")
                .create());

        myDoc.addEle(Paragraph.with(
                "This one is coming from WEB, google web site: ").create());
        myDoc.addEle(Image
                .from_WEB_URL("http://www.google.com/images/logos/ps_logo2.png"));

        myDoc.addEle(BreakLine.times(2).create());
        myDoc.addEle(Paragraph.with("You can change the image dimensions:.")
                .create());
        myDoc.addEle(Image
                .from_WEB_URL("http://www.google.com/images/logos/ps_logo2.png")
                .setHeight("40").setWidth("80").create());

        myDoc.addEle(BreakLine.times(2).create());
        myDoc.addEle(Paragraph
                .with("You can always be creative and mix up images inside other IElements. Eg.: Paragraphs, Tables, etc.")
                .create());

        myDoc.addEle(Paragraph
                .with("This document inside the paragraph, coming from '/src/test/resources/dtpick.gif': "
                        + Image.from_FULL_LOCAL_PATHL(
                                Utils.getAppRoot()
                                        + "/src/test/resources/dtpick.gif")
                                .getContent()));

        myDoc.addEle(BreakLine.times(1).create());

        
        myDoc.addEle(PageBreak.create());
        // Table
        myDoc.addEle(Heading2.with("===== Table ======").create());
        myDoc.addEle(Paragraph
                .with("Table of The Best Soccer Players Ever and Their Number of Gols:")
                .create());
        myDoc.addEle(BreakLine.times(1).create());

        Table tbl = new Table();
        tbl.addTableEle(TableEle.TH, "Name", "Number of gols", "Country");
        tbl.setRepeatTableHeaderOnEveryPage();

        tbl.addTableEle(TableEle.TD, "* Arthur Friedenreich", "1329", "Brazil");
        tbl.addTableEle(TableEle.TD, "Pele", "1281", "Brazil");
        tbl.addTableEle(TableEle.TD, "Romario", "1002", "Brazil");
        tbl.addTableEle(TableEle.TD, "Tulio Maravilha", "956", "Brazil");
        tbl.addTableEle(TableEle.TD, "** Zico", "815", "Brazil");
        tbl.addTableEle(TableEle.TD, "Roberto Dinamite", "748", "Brazil");
        tbl.addTableEle(TableEle.TD, "Di Stéfano", "715", "Argentina");
        tbl.addTableEle(TableEle.TD, "Puskas", "689", "Hungary");
        tbl.addTableEle(TableEle.TD, "Flávio", "591", "Brazil");
        tbl.addTableEle(TableEle.TD, "James McGory", "550", "Scotland");
        tbl.addTableEle(TableEle.TD, "*** Leonardo Correa", "299", "Brazil/Australia");
        tbl.addTableEle(TableEle.TD, "Maradona", "258", "Argentina");

        tbl.addTableEle(TableEle.TF, "Total", "1,100,000.00", " ");

        myDoc.addEle(tbl);

        myDoc.addEle(BreakLine.times(1).create());

        myDoc.addEle(Paragraph
                .withPieces(
                        ParagraphPiece
                        .with("* Arthur Friedenreich - unofficial stats")
                        .withStyle().italic().create()).create());
        myDoc.addEle(Paragraph
                .withPieces(
                        ParagraphPiece
                                .with("** Zico was a mid-fieldfer and managed to score all those goals!")
                                .withStyle().italic().create()).create());
        myDoc.addEle(Paragraph
                .withPieces(
                        ParagraphPiece
                                .with("*** Leonardo Correa's goals (me) including futsal, soccer, friendly games, training games, so on... (but not playstation)")
                                .withStyle().italic().create()).create());

        // HyperLink
        myDoc.addEle(BreakLine.times(1).create());

        myDoc.addEle(HyperLink.with("http://google.com", "Hyperlink to google.com").create());

        // PageBreaks
        myDoc.addEle(Heading2.with("===== PageBreak ======").create());
        myDoc.addEle(Paragraph.with("There is a PAGE BREAK after this line:")
                .create());
        myDoc.addEle(PageBreak.create());
        myDoc.addEle(Paragraph.with("There is a PAGE BREAK before this line:")
                .create());



        String myWord = myDoc.getContent();
        
        TestUtils.createLocalDoc(myDoc.getContent());
    }
    // ### END all-in-one
    
    
    @Ignore
    @Test
    public void testTableV2() throws FileNotFoundException {
        IDocument myDoc = new Document2004();
        TableV2 tbl = new TableV2();
        
        tbl.addRow( TableRow.with("Table Header in all Pages", "Usefull for reports").withStyle().repeatTableHeaderOnEveryPage().create() );
        
        tbl.addRow( TableRow.with("Simple String cell", "Another simple String cell") ); 
        tbl.addRow( TableRow.with( TableCell.with(Paragraph.with("TableCell- Style to the whole cell, Par").create()), "Simple String" ).withStyle().bold().create() );
        tbl.addRow( TableRow.with("Style to the whole cell, Str", "String").withStyle().bold().create() );
        tbl.addRow( TableRow.with( TableCell.with(Paragraph.with("TableRowV2 with merge").create()).withStyle().gridSpan(2).create() ).withStyle().bold().create() );
        tbl.addRow( TableRow.with( TableCell.with(Paragraph.withPieces( ParagraphPiece.with("Paragraph with Style inside TableCell").withStyle().bold().fontSize("20").create() ).create()).withStyle().bgColor("00FFFF").create(), "String"  ));
        
        String img = Image.from_WEB_URL("http://www.google.com/images/logos/ps_logo2.png").setHeight("100").setWidth("300").create().getContent();
        tbl.addRow( TableRow.with("this google logo: ", "Image here: "+ img + " == image before") );
        
        for (int i = 0; i < 10; i++) {
            tbl.addRow( TableRow.with("111", "") );            
        }
        
        tbl.addRow( TableRow.with("LAST", "LAST") );            
        
       // System.out.println(tbl.getContent());
        myDoc.addEle(tbl.getContent());
        
        //System.out.println(TableRowV2.with("Style applied to the whole line").withStyle().bold().create().getContent());
        
//        Table tbl2 = new Table();
//        tbl2.setRepeatTableHeaderOnEveryPage();
//        tbl2.addTableEle(TableEle.TH, "");
//        System.out.println(tbl2.getContent());
//        myDoc.addEle(tbl2);
		
        TestUtils.createLocalDoc(myDoc.getContent());
    }

}
