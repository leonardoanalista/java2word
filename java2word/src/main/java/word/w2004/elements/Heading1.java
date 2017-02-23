package word.w2004.elements;

import word.api.interfaces.IFluentElement;
import word.w2004.style.HeadingStyle;
import word.w2004.style.HeadingStyle.Align;

public class Heading1 extends AbstractHeading<HeadingStyle> implements IFluentElement<Heading1> { // implements IFluentElementStylable<HeadingStyle>

    //Constructor
    private Heading1(String value, Align align){
        super("Heading1", value, align);
    }

    //this method is specific for each class. Constructor can be different...Don't know if we can make it generic
    /***
     * @param The value of the paragraph
     * @return the Fluent @Heading1
     */
    public static Heading1 with(String value, Align align) {
        return new Heading1(value, align);
    }
    
    public static Heading1 with(String value) {
        return new Heading1(value, Align.LEFT);
    }

    @Override
    public Heading1 create() {
        return this;
    }

}
