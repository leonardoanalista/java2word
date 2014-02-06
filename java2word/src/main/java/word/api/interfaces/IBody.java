package word.api.interfaces;

public interface IBody extends IHasElement{
	
	/**
	 * @author leonardo_correa
	 * This is to provide another way to add raw text to the body or replace something like style or whatever you want.
	 * 
	 * Like Serializable, IBody has no methods or fields and serves only to identify the semantics of being IBody. 
	 */	
	
	public IHeader getHeader();
//	public void setHeader(IHeader header);
	
	public IFooter getFooter();
//	public void setFooter(IFooter footer);	
	
}
