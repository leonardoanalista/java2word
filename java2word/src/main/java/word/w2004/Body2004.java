package word.w2004;

import word.api.interfaces.IBody;
import word.api.interfaces.IElement;
import word.api.interfaces.IFooter;
import word.api.interfaces.IHeader;

public class Body2004 implements IBody {

	StringBuilder txt = new StringBuilder("");
	IHeader header = new Header2004();
	IFooter footer = new Footer2004();
	

	public void addEle(IElement e) {
		this.txt.append("\n" + e.getContent());
	}
	
	public void addEle(String str) {
		this.txt.append("\n" + str);
	}

	public String getContent() {
		StringBuilder res = new StringBuilder();
		res.append("\n<w:body>");

		res.append(txt.toString());
		
		String header = this.getHeader().getContent();
		String footer = this.getFooter().getContent();
		if (!"".equals(header) || !"".equals(footer)){
			String header_footer_top = "<w:sectPr wsp:rsidR=\"00DB1FE5\" wsp:rsidSect=\"00471A86\">";
			String header_footer_botton = "</w:sectPr>";
			
			res.append("\n" + header_footer_top);
			res.append(header);//header has to be inside the w:body
			res.append(footer);//header has to be inside the w:body
			if (this.getHeader().getHideHeaderAndFooterFirstPage()){
				res.append(this.getHeader().getHideHeaderAndFooterFirstPageXml());
			}
			
			res.append("\n" + header_footer_botton);
		}
		
		res.append("\n</w:body>");
		return res.toString();
	}
	

	//### Getters and setters ###
	public IHeader getHeader() {
		return header;
	}
	public IFooter getFooter() {
		return footer;
	}

	
}
