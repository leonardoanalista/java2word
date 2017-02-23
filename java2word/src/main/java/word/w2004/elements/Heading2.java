package word.w2004.elements;

import word.api.interfaces.IFluentElement;
import word.w2004.style.HeadingStyle;
import word.w2004.style.HeadingStyle.Align;

public class Heading2 extends AbstractHeading<HeadingStyle> implements IFluentElement<Heading2>{

    //Constructor
    private Heading2(String value, Align align){
        super("Heading2", value, align);
    }

    /***
     * @param The value of the paragraph
     * @return the Fluent @Heading2
     */
    public static Heading2 with(String string, Align align) {
        return new Heading2(string, align);
    }
    
    public static Heading2 with(String string) {
        return new Heading2(string, Align.LEFT);
    }


    @Override
    public Heading2 create() {
        return this;
    }

    // #### Getters and setters ####

}
