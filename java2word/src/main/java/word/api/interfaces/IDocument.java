package word.api.interfaces;

import word.w2004.Document2004;
import word.w2004.Document2004.Encoding;

/**
 * The main interface for documents MS Word 2004+.
 *
 * @author leonardo
 *
 */
public interface IDocument extends IHasElement {

    /**
     * @return the URI ready to be added to the document
     */
    String getUri();

    /**
     * @return the body of the document
     */
    IBody getBody();

    /**
     * @return the header that may contain other elements
     */
    IHeader getHeader();

    /**
     * @return the Footer that may contain other elements
     */
    IFooter getFooter();

    /**
     * Sets page orientation to Landscape. Default is Portrait
     */
    void setPageOrientationLandscape();


    /**
     * @param title  Represents the title of the document. The title can be different than the file name. The title is used when searching for the document and also when creating Web pages from the document.
     * @return fluent @Document reference
     */
    public Document2004 title(String title);

    /**
     * @param subject Represents the subject of the document. This property can be used to group similar files together, so you can search for all files that have the same subject.
     * @return fluent @Document reference
     */
    public Document2004 subject(String subject);

    /**
     * @param keywords Represents keywords to be used when searching for the document.
     * @return fluent @Document reference
     */
    public Document2004 keywords(String keywords);

    /**
     * @param description Represents comments to be used when searching for the document.
     * @return fluent @Document reference
     */
    public Document2004 description(String description);

    /**
     * @param category Represents the author who created the document.
     * @return fluent @Document reference
     */
    public Document2004 category(String category);

    /**
     * @param author Represents the name of the author of the document.
     * @return fluent @Document reference
     */
    public Document2004 author(String author);

    /**
     * @param lastAuthor Represents the name of the author who last saved the document.
     * @return fluent @Document reference
     */
    public Document2004 lastAuthor(String lastAuthor);

    /**
     * @param manager Represents the manager of the author of the document. This property can be used to group similar files together, so you can search for all the files that have the same manager.
     * @return fluent @Document reference
     */
    public Document2004 manager(String manager);

    /**
     * @param company  Represents the company that employs the author. This property can be used to group similar files together, so you can search for all files that have the same company.
     * @return fluent @Document reference
     */
    public Document2004 company(String company);

    /**
     * @param encoding The encoding you want to use in your document. UTF-8 or ISO8859-1, according to the Enum @Encoding
     * @return
     */
    public Document2004 encoding(Encoding encoding);

    /**
     * It gives a chance to set up your own encoding by passing the final string ready to go.
     * @param encoding
     * @return
     */
    public Document2004 encoding(String encoding);


}
