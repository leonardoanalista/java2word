package word.w2004;

import word.api.interfaces.IBody;
import word.api.interfaces.IDocument;
import word.api.interfaces.IElement;
import word.api.interfaces.IFooter;
import word.api.interfaces.IHeader;

/**
 * This is the main class in this API. It represents the MS Word document.
 * @author leonardo_correa
 *
 */
public class Document2004 implements IDocument, IElement{

    private boolean hasBeenCalledBefore = false; // if getContent has already been called, I cached the result for future invocations

    private StringBuilder txt = new StringBuilder();
    private IBody body = new Body2004();
    private boolean isLandscape = false;

    //Document properties <o:DocumentProperties>
    private String title = "Java2word title";
    private String subject = "Created by Java2word library";
    private String keywords = "java2word, word document";
    private String description = "";
    private String category = "";
    private String author = "Leonardo Correa";
    private String lastAuthor = "Leonardo Correa";
    private String manager = "Leonardo Correa";
    private String company = "Java2word, coding for fun!";

    private String encoding = Encoding.UTF_8.getValue();

    @Override
    public String getContent() {
        if(hasBeenCalledBefore ){
            return txt.toString();
        }else{
            hasBeenCalledBefore = true;
        }
        txt.append(this.getUri());
        txt.append(getDocumentHead());

        txt.append(this.getBody().getContent());

        txt.append("\n</w:wordDocument>");

        String finalString = setUpPageOrientation(txt.toString());

        return finalString;
    }

    /**
     * Returns the Document head that contains: DocumentProperties, fonts and styles
     * @return
     */
    private Object getDocumentHead() {
        String docHead = DocumentHead;

        // replace properties place holder
        docHead = docHead.replace("{title}", title);
        docHead = docHead.replace("{subject}", subject);
        docHead = docHead.replace("{keywords}", keywords);
        docHead = docHead.replace("{description}", description);
        docHead = docHead.replace("{category}", category);
        docHead = docHead.replace("{author}", author);
        docHead = docHead.replace("{lastAuthor}", lastAuthor);
        docHead = docHead.replace("{manager}", manager);
        docHead = docHead.replace("{company}", company);

        return docHead;
    }

    private String setUpPageOrientation(String txt) {
        if(isLandscape) {
            String orientation = "    <w:sectPr wsp:rsidR=\"00F04FB2\" wsp:rsidSect=\"00146B2A\">\n"
                + "      <w:pgSz w:w=\"16834\" w:h=\"11904\" w:orient=\"landscape\"/>\n"
                + "      <w:pgMar w:top=\"1800\" w:right=\"1440\" w:bottom=\"1800\" w:left=\"1440\" w:header=\"708\" w:footer=\"708\" w:gutter=\"0\"/>\n"
                + "      <w:cols w:space=\"708\"/>\n" + "    </w:sectPr>";
            txt = txt.replace("</w:body>", orientation + "\n</w:body>");
        }
        return txt;
    }

    @Override
    public void setPageOrientationLandscape() {
        isLandscape = true;
    }

    //### Getters and Setters
    @Override
    public IBody getBody() {
        return body;
    }
    @Override
    public IFooter getFooter() {//forward it to the body
        return this.getBody().getFooter();
    }
    @Override
    public IHeader getHeader() {
        return this.getBody().getHeader(); //forward it to the body
    }

    /**
     * This is an alias to 'getBody().addEle'
     */
    @Override
    public void addEle(IElement e) {
        this.getBody().addEle(e);
    }

    /**
     * This is an alias to 'getBody().addEle'
     */
    @Override
    public void addEle(String str) {
        this.getBody().addEle(str);
    }

    @Override
    public String toString() {
        return this.getContent();
    }

    @Override
    public Document2004 title(String title) {
        this.title = title;
        return this;
    }
    @Override
    public Document2004 subject(String subject) {
        this.subject = subject;
        return this;
    }
    @Override
    public Document2004 keywords(String keywords) {
        this.keywords = keywords;
        return this;
    }
    @Override
    public Document2004 description(String description) {
        this.description = description;
        return this;
    }
    @Override
    public Document2004 category(String category) {
        this.category = category;
        return this;
    }
    @Override
    public Document2004 author(String author) {
        this.author = author;
        return this;
    }
    @Override
    public Document2004 lastAuthor(String lastAuthor) {
        this.lastAuthor = lastAuthor;
        return this;
    }
    @Override
    public Document2004 manager(String manager) {
        this.manager = manager;
        return this;
    }
    @Override
    public Document2004 company(String company) {
        this.company = company;
        return this;
    }

    @Override
    public Document2004 encoding(Encoding encoding) {
        this.encoding = encoding.getValue();
        return this;
    }
    
    @Override
    public Document2004 encoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    //######### Constants, variables #######

    @Override
    public String getUri() {
        String uri = "<?xml version=\"1.0\" encoding=\"" + this.encoding + "\" standalone=\"yes\"?> "
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
        return uri;
    }

    public static final String DocumentHead =
        "    <o:DocumentProperties> "
      + "        <o:Title>{title}</o:Title> \n"
      + "        <o:Subject>{subject}</o:Subject> \n"
      + "        <o:Keywords>{keywords}</o:Keywords> \n"
      + "        <o:Description>{description}</o:Description> \n"
      + "        <o:Category>{category}</o:Category> \n"
      + "        <o:Author>{author}</o:Author> "
      + "        <o:LastAuthor>{lastAuthor}</o:LastAuthor> "
      + "        <o:Manager>{manager}</o:Manager> \n"
      + "        <o:Company>{company}</o:Company> \n"
      + "        <o:Revision>1</o:Revision> "
      + "        <o:TotalTime>1</o:TotalTime> "
      + "        <o:Created>2010-07-16T07:18:00Z</o:Created> "
      + "        <o:LastSaved>2010-07-16T07:22:00Z</o:LastSaved> "
      + "        <o:Pages>1</o:Pages> "
      + "        <o:Words>0</o:Words> "
      + "        <o:Characters>0</o:Characters> "
      + "        <o:Bytes>1</o:Bytes> \n"
      + "        <o:Lines>1</o:Lines> "
      + "        <o:Paragraphs>1</o:Paragraphs> "
      + "        <o:CharactersWithSpaces>0</o:CharactersWithSpaces> "
      + "        <o:Version>1</o:Version> "
      + "    </o:DocumentProperties> "
      + "    <w:fonts> "
      + "        <w:defaultFonts w:ascii=\"Cambria\" w:fareast=\"Cambria\" w:h-ansi=\"Cambria\" w:cs=\"Times New Roman\"/> "
      + "        <w:font w:name=\"Times New Roman\"> "
      + "            <w:panose-1 w:val=\"02020603050405020304\"/> "
      + "            <w:charset w:val=\"00\"/> "
      + "            <w:family w:val=\"auto\"/> "
      + "            <w:pitch w:val=\"variable\"/> "
      + "            <w:sig w:usb-0=\"00000003\" w:usb-1=\"00000000\" w:usb-2=\"00000000\" w:usb-3=\"00000000\" w:csb-0=\"00000001\" w:csb-1=\"00000000\"/> "
      + "        </w:font> "
      + "        <w:font w:name=\"Calibri\"> "
      + "            <w:panose-1 w:val=\"020F0502020204030204\"/> "
      + "            <w:charset w:val=\"00\"/> "
      + "            <w:family w:val=\"auto\"/> "
      + "            <w:pitch w:val=\"variable\"/> "
      + "            <w:sig w:usb-0=\"00000003\" w:usb-1=\"00000000\" w:usb-2=\"00000000\" w:usb-3=\"00000000\" w:csb-0=\"00000001\" w:csb-1=\"00000000\"/> "
      + "        </w:font> "
      + "        <w:font w:name=\"Cambria\"> "
      + "            <w:panose-1 w:val=\"02040503050406030204\"/> "
      + "            <w:charset w:val=\"00\"/> "
      + "            <w:family w:val=\"auto\"/> "
      + "            <w:pitch w:val=\"variable\"/> "
      + "            <w:sig w:usb-0=\"00000003\" w:usb-1=\"00000000\" w:usb-2=\"00000000\" w:usb-3=\"00000000\" w:csb-0=\"00000001\" w:csb-1=\"00000000\"/> "
      + "        </w:font> "
      + "    </w:fonts> "
      + "    <w:styles> "
      + "        <w:versionOfBuiltInStylenames w:val=\"2\"/> "
      + "        <w:latentStyles w:defLockedState=\"off\" w:latentStyleCount=\"276\"> "
      + "            <w:lsdException w:name=\"Normal\"/> "
      + "            <w:lsdException w:name=\"heading 1\"/> "
      + "            <w:lsdException w:name=\"heading 2\"/> "
      + "            <w:lsdException w:name=\"heading 3\"/> "
      + "            <w:lsdException w:name=\"heading 4\"/> "
      + "            <w:lsdException w:name=\"heading 5\"/> "
      + "            <w:lsdException w:name=\"heading 6\"/> "
      + "            <w:lsdException w:name=\"heading 7\"/> "
      + "            <w:lsdException w:name=\"heading 8\"/> "
      + "            <w:lsdException w:name=\"heading 9\"/> "
      + "            <w:lsdException w:name=\"toc 1\"/> "
      + "            <w:lsdException w:name=\"toc 2\"/> "
      + "            <w:lsdException w:name=\"toc 3\"/> "
      + "            <w:lsdException w:name=\"toc 4\"/> "
      + "            <w:lsdException w:name=\"toc 5\"/> "
      + "            <w:lsdException w:name=\"toc 6\"/> "
      + "            <w:lsdException w:name=\"toc 7\"/> "
      + "            <w:lsdException w:name=\"toc 8\"/> "
      + "            <w:lsdException w:name=\"toc 9\"/> "
      + "            <w:lsdException w:name=\"caption\"/> "
      + "            <w:lsdException w:name=\"Title\"/> "
      + "            <w:lsdException w:name=\"Default Paragraph Font\"/> "
      + "            <w:lsdException w:name=\"Subtitle\"/> "
      + "            <w:lsdException w:name=\"Strong\"/> "
      + "            <w:lsdException w:name=\"Emphasis\"/> "
      + "            <w:lsdException w:name=\"Table Grid\"/> "
      + "            <w:lsdException w:name=\"Placeholder Text\"/> "
      + "            <w:lsdException w:name=\"No Spacing\"/> "
      + "            <w:lsdException w:name=\"Light Shading\"/> "
      + "            <w:lsdException w:name=\"Light List\"/> "
      + "            <w:lsdException w:name=\"Light Grid\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 1\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 2\"/> "
      + "            <w:lsdException w:name=\"Medium List 1\"/> "
      + "            <w:lsdException w:name=\"Medium List 2\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 1\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 2\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 3\"/> "
      + "            <w:lsdException w:name=\"Dark List\"/> "
      + "            <w:lsdException w:name=\"Colorful Shading\"/> "
      + "            <w:lsdException w:name=\"Colorful List\"/> "
      + "            <w:lsdException w:name=\"Colorful Grid\"/> "
      + "            <w:lsdException w:name=\"Light Shading Accent 1\"/> "
      + "            <w:lsdException w:name=\"Light List Accent 1\"/> "
      + "            <w:lsdException w:name=\"Light Grid Accent 1\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 1 Accent 1\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 2 Accent 1\"/> "
      + "            <w:lsdException w:name=\"Medium List 1 Accent 1\"/> "
      + "            <w:lsdException w:name=\"Revision\"/> "
      + "            <w:lsdException w:name=\"List Paragraph\"/> "
      + "            <w:lsdException w:name=\"Quote\"/> "
      + "            <w:lsdException w:name=\"Intense Quote\"/> "
      + "            <w:lsdException w:name=\"Medium List 2 Accent 1\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 1 Accent 1\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 2 Accent 1\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 3 Accent 1\"/> "
      + "            <w:lsdException w:name=\"Dark List Accent 1\"/> "
      + "            <w:lsdException w:name=\"Colorful Shading Accent 1\"/> "
      + "            <w:lsdException w:name=\"Colorful List Accent 1\"/> "
      + "            <w:lsdException w:name=\"Colorful Grid Accent 1\"/> "
      + "            <w:lsdException w:name=\"Light Shading Accent 2\"/> "
      + "            <w:lsdException w:name=\"Light List Accent 2\"/> "
      + "            <w:lsdException w:name=\"Light Grid Accent 2\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 1 Accent 2\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 2 Accent 2\"/> "
      + "            <w:lsdException w:name=\"Medium List 1 Accent 2\"/> "
      + "            <w:lsdException w:name=\"Medium List 2 Accent 2\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 1 Accent 2\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 2 Accent 2\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 3 Accent 2\"/> "
      + "            <w:lsdException w:name=\"Dark List Accent 2\"/> "
      + "            <w:lsdException w:name=\"Colorful Shading Accent 2\"/> "
      + "            <w:lsdException w:name=\"Colorful List Accent 2\"/> "
      + "            <w:lsdException w:name=\"Colorful Grid Accent 2\"/> "
      + "            <w:lsdException w:name=\"Light Shading Accent 3\"/> "
      + "            <w:lsdException w:name=\"Light List Accent 3\"/> "
      + "            <w:lsdException w:name=\"Light Grid Accent 3\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 1 Accent 3\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 2 Accent 3\"/> "
      + "            <w:lsdException w:name=\"Medium List 1 Accent 3\"/> "
      + "            <w:lsdException w:name=\"Medium List 2 Accent 3\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 1 Accent 3\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 2 Accent 3\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 3 Accent 3\"/> "
      + "            <w:lsdException w:name=\"Dark List Accent 3\"/> "
      + "            <w:lsdException w:name=\"Colorful Shading Accent 3\"/> "
      + "            <w:lsdException w:name=\"Colorful List Accent 3\"/> "
      + "            <w:lsdException w:name=\"Colorful Grid Accent 3\"/> "
      + "            <w:lsdException w:name=\"Light Shading Accent 4\"/> "
      + "            <w:lsdException w:name=\"Light List Accent 4\"/> "
      + "            <w:lsdException w:name=\"Light Grid Accent 4\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 1 Accent 4\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 2 Accent 4\"/> "
      + "            <w:lsdException w:name=\"Medium List 1 Accent 4\"/> "
      + "            <w:lsdException w:name=\"Medium List 2 Accent 4\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 1 Accent 4\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 2 Accent 4\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 3 Accent 4\"/> "
      + "            <w:lsdException w:name=\"Dark List Accent 4\"/> "
      + "            <w:lsdException w:name=\"Colorful Shading Accent 4\"/> "
      + "            <w:lsdException w:name=\"Colorful List Accent 4\"/> "
      + "            <w:lsdException w:name=\"Colorful Grid Accent 4\"/> "
      + "            <w:lsdException w:name=\"Light Shading Accent 5\"/> "
      + "            <w:lsdException w:name=\"Light List Accent 5\"/> "
      + "            <w:lsdException w:name=\"Light Grid Accent 5\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 1 Accent 5\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 2 Accent 5\"/> "
      + "            <w:lsdException w:name=\"Medium List 1 Accent 5\"/> "
      + "            <w:lsdException w:name=\"Medium List 2 Accent 5\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 1 Accent 5\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 2 Accent 5\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 3 Accent 5\"/> "
      + "            <w:lsdException w:name=\"Dark List Accent 5\"/> "
      + "            <w:lsdException w:name=\"Colorful Shading Accent 5\"/> "
      + "            <w:lsdException w:name=\"Colorful List Accent 5\"/> "
      + "            <w:lsdException w:name=\"Colorful Grid Accent 5\"/> "
      + "            <w:lsdException w:name=\"Light Shading Accent 6\"/> "
      + "            <w:lsdException w:name=\"Light List Accent 6\"/> "
      + "            <w:lsdException w:name=\"Light Grid Accent 6\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 1 Accent 6\"/> "
      + "            <w:lsdException w:name=\"Medium Shading 2 Accent 6\"/> "
      + "            <w:lsdException w:name=\"Medium List 1 Accent 6\"/> "
      + "            <w:lsdException w:name=\"Medium List 2 Accent 6\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 1 Accent 6\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 2 Accent 6\"/> "
      + "            <w:lsdException w:name=\"Medium Grid 3 Accent 6\"/> "
      + "            <w:lsdException w:name=\"Dark List Accent 6\"/> "
      + "            <w:lsdException w:name=\"Colorful Shading Accent 6\"/> "
      + "            <w:lsdException w:name=\"Colorful List Accent 6\"/> "
      + "            <w:lsdException w:name=\"Colorful Grid Accent 6\"/> "
      + "            <w:lsdException w:name=\"Subtle Emphasis\"/> "
      + "            <w:lsdException w:name=\"Intense Emphasis\"/> "
      + "            <w:lsdException w:name=\"Subtle Reference\"/> "
      + "            <w:lsdException w:name=\"Intense Reference\"/> "
      + "            <w:lsdException w:name=\"Book Title\"/> "
      + "            <w:lsdException w:name=\"Bibliography\"/> "
      + "            <w:lsdException w:name=\"TOC Heading\"/> "
      + "        </w:latentStyles> "
      + "        <w:style w:type=\"paragraph\" w:default=\"on\" w:styleId=\"Normal\"> "
      + "            <w:name w:val=\"Normal\"/> "
      + "            <w:rsid w:val=\"00D711DA\"/> "
      + "            <w:rPr> "
      + "                <wx:font wx:val=\"Cambria\"/> "
      + "                <w:sz w:val=\"24\"/> "
      + "                <w:sz-cs w:val=\"24\"/> "
      + "                <w:lang w:val=\"EN-AU\" w:fareast=\"EN-US\" w:bidi=\"AR-SA\"/> "
      + "            </w:rPr> "
      + "        </w:style> "
      + "        <w:style w:type=\"paragraph\" w:styleId=\"Heading1\"> "
      + "            <w:name w:val=\"heading 1\"/> "
      + "            <wx:uiName wx:val=\"Heading 1\"/> "
      + "            <w:basedOn w:val=\"Normal\"/> "
      + "            <w:next w:val=\"Normal\"/> "
      + "            <w:link w:val=\"Heading1Char\"/> "
      + "            <w:rsid w:val=\"00401F80\"/> "
      + "            <w:pPr> "
      + "                <w:keepNext/> "
      + "                <w:keepLines/> "
      + "                <w:spacing w:before=\"480\"/> "
      + "                <w:outlineLvl w:val=\"0\"/> "
      + "            </w:pPr> "
      + "            <w:rPr> "
      + "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\"/> "
      + "                <wx:font wx:val=\"Calibri\"/> "
      + "                <w:b/> "
      + "                <w:b-cs/> "
      + "                <w:color w:val=\"345A8A\"/> "
      + "                <w:sz w:val=\"32\"/> "
      + "                <w:sz-cs w:val=\"32\"/> "
      + "            </w:rPr> "
      + "        </w:style> "
      + "        <w:style w:type=\"paragraph\" w:styleId=\"Heading2\"> "
      + "            <w:name w:val=\"heading 2\"/> "
      + "            <wx:uiName wx:val=\"Heading 2\"/> "
      + "            <w:basedOn w:val=\"Normal\"/> "
      + "            <w:next w:val=\"Normal\"/> "
      + "            <w:link w:val=\"Heading2Char\"/> "
      + "            <w:rsid w:val=\"00401F80\"/> "
      + "            <w:pPr> "
      + "                <w:keepNext/> "
      + "                <w:keepLines/> "
      + "                <w:spacing w:before=\"200\"/> "
      + "                <w:outlineLvl w:val=\"1\"/> "
      + "            </w:pPr> "
      + "            <w:rPr> "
      + "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\"/> "
      + "                <wx:font wx:val=\"Calibri\"/> "
      + "                <w:b/> "
      + "                <w:b-cs/> "
      + "                <w:color w:val=\"4F81BD\"/> "
      + "                <w:sz w:val=\"26\"/> "
      + "                <w:sz-cs w:val=\"26\"/> "
      + "            </w:rPr> "
      + "        </w:style> "
      + "        <w:style w:type=\"paragraph\" w:styleId=\"Heading3\"> "
      + "            <w:name w:val=\"heading 3\"/> "
      + "            <wx:uiName wx:val=\"Heading 3\"/> "
      + "            <w:basedOn w:val=\"Normal\"/> "
      + "            <w:next w:val=\"Normal\"/> "
      + "            <w:link w:val=\"Heading3Char\"/> "
      + "            <w:rsid w:val=\"00401F80\"/> "
      + "            <w:pPr> "
      + "                <w:keepNext/> "
      + "                <w:keepLines/> "
      + "                <w:spacing w:before=\"200\"/> "
      + "                <w:outlineLvl w:val=\"2\"/> "
      + "            </w:pPr> "
      + "            <w:rPr> "
      + "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\"/> "
      + "                <wx:font wx:val=\"Calibri\"/> "
      + "                <w:b/> "
      + "                <w:b-cs/> "
      + "                <w:color w:val=\"4F81BD\"/> "
      + "            </w:rPr> "
      + "        </w:style> "
      + "        <w:style w:type=\"character\" w:default=\"on\" w:styleId=\"DefaultParagraphFont\"> "
      + "            <w:name w:val=\"Default Paragraph Font\"/> "
      + "        </w:style> "
      + "        <w:style w:type=\"table\" w:default=\"on\" w:styleId=\"TableNormal\"> "
      + "            <w:name w:val=\"Normal Table\"/> "
      + "            <wx:uiName wx:val=\"Table Normal\"/> "
      + "            <w:rPr> "
      + "                <wx:font wx:val=\"Cambria\"/> "
      + "                <w:lang w:val=\"EN-AU\" w:fareast=\"EN-US\" w:bidi=\"AR-SA\"/> "
      + "            </w:rPr> "
      + "            <w:tblPr> "
      + "                <w:tblInd w:w=\"0\" w:type=\"dxa\"/> "
      + "                <w:tblCellMar> "
      + "                    <w:top w:w=\"0\" w:type=\"dxa\"/> "
      + "                    <w:left w:w=\"108\" w:type=\"dxa\"/> "
      + "                    <w:bottom w:w=\"0\" w:type=\"dxa\"/> "
      + "                    <w:right w:w=\"108\" w:type=\"dxa\"/> "
      + "                </w:tblCellMar> "
      + "            </w:tblPr> "
      + "        </w:style> "
      + "        <w:style w:type=\"list\" w:default=\"on\" w:styleId=\"NoList\"> "
      + "            <w:name w:val=\"No List\"/> "
      + "        </w:style> "
      + "        <w:style w:type=\"character\" w:styleId=\"Heading1Char\"> "
      + "            <w:name w:val=\"Heading 1 Char\"/> "
      + "            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
      + "            <w:link w:val=\"Heading1\"/> "
      + "            <w:rsid w:val=\"00401F80\"/> "
      + "            <w:rPr> "
      + "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\" w:cs=\"Times New Roman\"/> "
      + "                <w:b/> "
      + "                <w:b-cs/> "
      + "                <w:color w:val=\"345A8A\"/> "
      + "                <w:sz w:val=\"32\"/> "
      + "                <w:sz-cs w:val=\"32\"/> "
      + "                <w:lang w:val=\"EN-AU\"/> "
      + "            </w:rPr> "
      + "        </w:style> "
      + "        <w:style w:type=\"character\" w:styleId=\"Heading2Char\"> "
      + "            <w:name w:val=\"Heading 2 Char\"/> "
      + "            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
      + "            <w:link w:val=\"Heading2\"/> "
      + "            <w:rsid w:val=\"00401F80\"/> "
      + "            <w:rPr> "
      + "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\" w:cs=\"Times New Roman\"/> "
      + "                <w:b/> "
      + "                <w:b-cs/> "
      + "                <w:color w:val=\"4F81BD\"/> "
      + "                <w:sz w:val=\"26\"/> "
      + "                <w:sz-cs w:val=\"26\"/> "
      + "                <w:lang w:val=\"EN-AU\"/> "
      + "            </w:rPr> "
      + "        </w:style> "
      + "        <w:style w:type=\"character\" w:styleId=\"Heading3Char\"> "
      + "            <w:name w:val=\"Heading 3 Char\"/> "
      + "            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
      + "            <w:link w:val=\"Heading3\"/> "
      + "            <w:rsid w:val=\"00401F80\"/> "
      + "            <w:rPr> "
      + "                <w:rFonts w:ascii=\"Calibri\" w:fareast=\"Times New Roman\" w:h-ansi=\"Calibri\" w:cs=\"Times New Roman\"/> "
      + "                <w:b/> "
      + "                <w:b-cs/> "
      + "                <w:color w:val=\"4F81BD\"/> "
      + "                <w:lang w:val=\"EN-AU\"/> "
      + "            </w:rPr> "
      + "        </w:style> "
      //Hyperlink Style
      + "        <w:style w:type=\"character\" w:styleId=\"Hyperlink\">j"
      + "            <w:name w:val=\"Hyperlink\"/> "
      + "            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
      + "            <w:rsid w:val=\"00A30FBB\"/> "
      + "            <w:rPr> "
      + "                <w:color w:val=\"0000FF\"/> "
      + "                <w:u w:val=\"single\"/> "
      + "            </w:rPr> "
      + "        </w:style> "
      //HEADER_N_FOOTER_STYLE
      +"        <w:style w:type=\"list\" w:default=\"on\" w:styleId=\"NoList\"> "
      +"            <w:name w:val=\"No List\"/> "
      +"        </w:style> "

      + "        <w:style w:type=\"paragraph\" w:styleId=\"Header\"> "
      +"            <w:name w:val=\"header\"/> "
      +"            <wx:uiName wx:val=\"Header\"/> "
      +"            <w:basedOn w:val=\"Normal\"/> "
      +"            <w:link w:val=\"HeaderChar\"/> "
      +"            <w:rsid w:val=\"00B5709B\"/> "
      +"            <w:pPr> "
      +"                <w:tabs> "
      +"                    <w:tab w:val=\"center\" w:pos=\"4320\"/> "
      +"                    <w:tab w:val=\"right\" w:pos=\"8640\"/> "
      +"                </w:tabs> "
      +"            </w:pPr> "
      +"            <w:rPr> "
      +"                <wx:font wx:val=\"Cambria\"/> "
      +"            </w:rPr> "
      +"        </w:style> "
      +"        <w:style w:type=\"character\" w:styleId=\"HeaderChar\"> "
      +"            <w:name w:val=\"Header Char\"/> "
      +"            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
      +"            <w:link w:val=\"Header\"/> "
      +"            <w:rsid w:val=\"00B5709B\"/> "
      +"            <w:rPr> "
      +"                <w:sz w:val=\"24\"/> "
      +"                <w:sz-cs w:val=\"24\"/> "
      +"            </w:rPr> "
      +"        </w:style> "
      +"        <w:style w:type=\"paragraph\" w:styleId=\"Footer\"> "
      +"            <w:name w:val=\"footer\"/> "
      +"            <wx:uiName wx:val=\"Footer\"/> "
      +"            <w:basedOn w:val=\"Normal\"/> "
      +"            <w:link w:val=\"FooterChar\"/> "
      +"            <w:rsid w:val=\"00B5709B\"/> "
      +"            <w:pPr> "
      +"                <w:tabs> "
      +"                    <w:tab w:val=\"center\" w:pos=\"4320\"/> "
      +"                    <w:tab w:val=\"right\" w:pos=\"8640\"/> "
      +"                </w:tabs> "
      +"            </w:pPr> "
      +"            <w:rPr> "
      +"                <wx:font wx:val=\"Cambria\"/> "
      +"            </w:rPr> "
      +"        </w:style> "
      +"        <w:style w:type=\"character\" w:styleId=\"FooterChar\"> "
      +"            <w:name w:val=\"Footer Char\"/> "
      +"            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
      +"            <w:link w:val=\"Footer\"/> "
      +"            <w:rsid w:val=\"00B5709B\"/> "
      +"            <w:rPr> "
      +"                <w:sz w:val=\"24\"/> "
      +"                <w:sz-cs w:val=\"24\"/> "
      +"            </w:rPr> "
      +"        </w:style> "
      +"        <w:style w:type=\"character\" w:styleId=\"PageNumber\"> "
      +"            <w:name w:val=\"page number\"/> "
      +"            <wx:uiName wx:val=\"Page Number\"/> "
      +"            <w:basedOn w:val=\"DefaultParagraphFont\"/> "
      +"            <w:rsid w:val=\"009F65CC\"/> "
      +"        </w:style> "

      + "    </w:styles> "
      + "    <w:docPr> "
      + "        <w:view w:val=\"print\"/> "
      + "        <w:zoom w:percent=\"100\"/> "
      + "        <w:proofState w:spelling=\"clean\" w:grammar=\"clean\"/> "
      + "        <w:defaultTabStop w:val=\"720\"/> "
      + "        <w:drawingGridHorizontalSpacing w:val=\"360\"/> "
      + "        <w:drawingGridVerticalSpacing w:val=\"360\"/> "
      + "        <w:displayHorizontalDrawingGridEvery w:val=\"0\"/> "
      + "        <w:displayVerticalDrawingGridEvery w:val=\"0\"/> "
      + "        <w:punctuationKerning/> "
      + "        <w:characterSpacingControl w:val=\"DontCompress\"/> "
      + "        <w:allowPNG/> "
      + "        <w:doNotSaveWebPagesAsSingleFile/> "
      + "        <w:savePreviewPicture/> "
      + "        <w:validateAgainstSchema/> "
      + "        <w:saveInvalidXML w:val=\"off\"/> "
      + "        <w:ignoreMixedContent w:val=\"off\"/> "
      + "        <w:alwaysShowPlaceholderText w:val=\"off\"/> "
      + "        <w:compat> "
      + "            <w:breakWrappedTables/> "
      + "            <w:snapToGridInCell/> "
      + "            <w:wrapTextWithPunct/> "
      + "            <w:useAsianBreakRules/> "
      + "            <w:dontGrowAutofit/> "
      + "        </w:compat> "
      + "        <wsp:rsids> "
      + "            <wsp:rsidRoot wsp:val=\"00401F80\"/> "
      + "        </wsp:rsids> "
      + "    </w:docPr> ";


    public enum Encoding{
        UTF_8("UTF-8"), 
        ISO8859_1("ISO8859-1"),
        /**
         * This is suitable for cirrylic text
         */
        WINDOWS_1251("windows-1251");

        private String value;

        Encoding(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }

    };


}
