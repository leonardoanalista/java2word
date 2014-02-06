package word.w2004;

import word.api.interfaces.IElement;
import word.api.interfaces.IFooter;

public class Footer2004 implements IFooter{
	
	StringBuilder txt = new StringBuilder("");
	private boolean hasBeenCalledBefore = false; // if getContent has already been called, I cached the result for future invocations
	private boolean showPageNumber = true;
	
	public void addEle(IElement e) {
		this.txt.append("\n" + e.getContent());
	}

	public void addEle(String str) {
		this.txt.append("\n" + str);		
	}

	public String getContent() {
		if("".equals(txt.toString())){
			return "";
		}
		if(hasBeenCalledBefore ){
			return txt.toString();	
		}else{
			hasBeenCalledBefore = true;
		}
		
		txt.insert(0, HEADER_TOP);
		if(showPageNumber){
			txt.append(PAGE_NUMBER);
		}	
		txt.append(HEADER_BOTTON);
		
		return txt.toString();
	}
	
	public void showPageNumber(boolean value){
		this.showPageNumber  = value;
	}
	
	private static String HEADER_TOP = "\n	<w:ftr w:type=\"odd\">";
	private static String HEADER_BOTTON = "\n	</w:ftr>";
	private static String PAGE_NUMBER = 
		"\n                <wx:pBdrGroup> "
		+"\n                    <wx:apo> "
		+"\n                        <wx:jc wx:val=\"right\"/> "
		+"\n                    </wx:apo> "
		+"\n                    <w:p wsp:rsidR=\"00595002\" wsp:rsidRDefault=\"00595002\" wsp:rsidP=\"00165A2B\"> "
		+"\n                        <w:pPr> "
		+"\n                            <w:pStyle w:val=\"Footer\"/> "
		+"\n                            <w:framePr w:wrap=\"around\" w:vanchor=\"text\" w:hanchor=\"margin\" w:x-align=\"right\" w:y=\"1\"/> "
		+"\n                            <w:rPr> "
		+"\n                                <w:rStyle w:val=\"PageNumber\"/> "
		+"\n                            </w:rPr> "
		+"\n                        </w:pPr> "
		+"\n                        <w:r> "
		+"\n                            <w:rPr> "
		+"\n                                <w:rStyle w:val=\"PageNumber\"/> "
		+"\n                            </w:rPr> "
		+"\n                            <w:fldChar w:fldCharType=\"begin\"/> "
		+"\n                        </w:r> "
		+"\n                        <w:r> "
		+"\n                            <w:rPr> "
		+"\n                                <w:rStyle w:val=\"PageNumber\"/> "
		+"\n                            </w:rPr> "
		+"\n                            <w:instrText>PAGE  </w:instrText> "
		+"\n                        </w:r> "
		+"\n                        <w:r> "
		+"\n                            <w:rPr> "
		+"\n                                <w:rStyle w:val=\"PageNumber\"/> "
		+"\n                            </w:rPr> "
		+"\n                            <w:fldChar w:fldCharType=\"separate\"/> "
		+"\n                        </w:r> "
		+"\n                        <w:r> "
		+"\n                            <w:rPr> "
		+"\n                                <w:rStyle w:val=\"PageNumber\"/> "
		+"\n                                <w:noProof/> "
		+"\n                            </w:rPr> "
		+"\n                            <w:t>2</w:t> "
		+"\n                        </w:r> "
		+"\n                        <w:r> "
		+"\n                            <w:rPr> "
		+"\n                                <w:rStyle w:val=\"PageNumber\"/> "
		+"\n                            </w:rPr> "
		+"\n                            <w:fldChar w:fldCharType=\"end\"/> "
		+"\n                        </w:r> "
		+"\n                    </w:p> "
		+"\n                </wx:pBdrGroup> \n"		
		;

}
