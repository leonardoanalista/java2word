package word.api.interfaces;

public interface IHeader extends IHasElement{

	void setHideHeaderAndFooterFirstPage(boolean value);
	boolean getHideHeaderAndFooterFirstPage();
	
	String getHideHeaderAndFooterFirstPageXml();
	
}
