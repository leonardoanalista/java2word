package word.w2004.elements;

import word.api.interfaces.IFluentElement;
import word.w2004.style.HeadingStyle;

public class Heading1 extends AbstractHeading<HeadingStyle> implements IFluentElement<Heading1> { // implements IFluentElementStylable<HeadingStyle>

    //Constructor
    private Heading1(String value){
        super("Heading1", value);
    }

    //this method is specific for each class. Constructor can be different...Don't know if we can make it generic
    /***
     * @param The value of the paragraph
     * @return the Fluent @Heading1
     */
    public static Heading1 with(String value) {
        return new Heading1(value);
    }

    @Override
    public Heading1 create() {
        return this;
    }

}
