package word.api.interfaces;

/**
 * @author leonardo_correa
 * <p>Interface implemented by all Elements of the API. This is the minimal required.</p>
 *
 * <p>The class which implements this method can have others helper methods</p>
 *
 */
public interface IElement {

	/**
	 * <p>This method returns the content (XML or HTML) of the Element and the content.</p>
	 * <p>If you are using W2004, the return will be the XML required to generate the element.</p>
	 *
	 * <p>Important: Once you call this method, the Document value is cached an no elements can be added later.</p>
	 *
	 * @return this is the String value of the element ready to be appended/inserted in the Document.<br>
	 *
	 * <p>This is the XML that generates a <code>BreakLine</code>:</p>
	 * <code>
	 *  <w:p wsp:rsidR='008979E8' wsp:rsidRDefault='008979E8'/>
	 * </code>
	 */
	public String getContent();

}
