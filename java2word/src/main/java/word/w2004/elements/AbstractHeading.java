package word.w2004.elements;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElementStylable;
import word.w2004.style.HeadingStyle;

/**
 * @author leonardo
 * @param <E>
 * Heading is utilized to organize documents the same way you do for web pages.
 * You can use Heading1 to 3.
 */
public abstract class AbstractHeading<E> implements IElement,  IFluentElementStylable<E>{

    /**
     * this is actual heading1, heading2 or heading3.
     */
    private String headingType;
    private String value; //value/text for the Heading

    protected AbstractHeading(String headingType, String value){
        this.headingType = headingType;
        this.value = value;
    }

    private String template =
        "\n<w:p wsp:rsidR=\"004429ED\" wsp:rsidRDefault=\"00000000\" wsp:rsidP=\"004429ED\">"
        +"\n	<w:pPr>"
        +"\n		<w:pStyle w:val=\"{heading}\" />"
        +"\n		{styleAlign}"
        +"\n	</w:pPr>"
        +"\n	<w:r>"
        +"\n		{styleText}"
        +"\n		<w:t>{value}</w:t>"
        +"\n	</w:r>"
        +"\n</w:p>";

    private HeadingStyle style = new HeadingStyle();

    @Override
    public String getContent() {
        if("".equals(this.value)){
            return "";
        }

        //For convention, it should be the last thing before returning the xml content.
        String txt = style.getNewContentWithStyle(getTemplate());

        return txt.replace("{value}", this.value);
    }


    // #### Getters and setters ####
    public String getTemplate() {
        return this.template.replace("{heading}", this.headingType);
    }

    //Implements the stylable and the heading classes reuse it
    @Override
    @SuppressWarnings("unchecked")
    public E withStyle() {
        this.style.setElement(this); //, Heading1.class
        return (E) this.style;
    }

}
