package word.w2004.elements;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElement;

public class HyperLink implements IElement, IFluentElement <HyperLink> {
    private String uri;
    private String title;

    private String HYPERLINK_TEMPLATE =
        "<w:p wsp:rsidR=\"0023207C\" wsp:rsidRDefault=\"00A30FBB\">\n"
       +"  <w:hlink w:dest=\"{uri}\">\n"
       +"    <w:r wsp:rsidRPr=\"00A30FBB\">\n"
       +"      <w:rPr>\n"
       +"        <w:rStyle w:val=\"Hyperlink\"/>\n"
       +"      </w:rPr>\n"
       +"      <w:t>{title}</w:t>\n"
       +"    </w:r>\n"
       +"  </w:hlink>\n"
       +"</w:p>\n";

    private HyperLink(String uri, String title) {
        this.uri = uri;
        this.title = title;
    }

    public final HyperLink create() {
        return this;
    }

    public static HyperLink with(String uri, String title) {
        return new HyperLink(uri, title);
    }

    public String getContent() {
        return HYPERLINK_TEMPLATE.replace("{uri}", this.uri).replace("{title}", this.title);
    }

}
