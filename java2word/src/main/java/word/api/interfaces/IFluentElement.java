package word.api.interfaces;

/**
 * @author no one
 * 
 */
public interface IFluentElement <F>{

	/**
	 * This method is just to keep the API consistent when you do something like:
	 * 		Heading1 myHEading = Heading1.with("h3333").<b>create();</b>
	 * It is just to have that "create()" at the end but the following code is the same:
	 * 		Heading1 myHEading = Heading1.with("h3333");
	 * 
	 * Anyway, it is here for sake of consistency or semantic
	 * 
	 * @return
	 * 
	 */
	public F create();
	
}
