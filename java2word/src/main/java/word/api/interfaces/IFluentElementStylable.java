package word.api.interfaces;

/**
 * @author no one
 * 
 * I invented the word "Stylable". Don't try to look up at the dictionary pls.
 * This will make all Style classes fluent. You are able to write code like:
 * 
 *  	Heading1.with("h3333").withStyle().setBold(true);
 *
 */
public interface IFluentElementStylable <S>{

	/**
	 * This method returns style for the element. The element knows who is his style class, but the style doesn't.
	 * This method will do this:
	 * 	1) set up the itself to the style class
	 *      this.getStyle().setElement(this); //, Heading1.class
	 *  2) Return the style class    
	 *	return this.getStyle();
	 *
	 */
	public S withStyle();
	
}
