package word.w2004.elements;

import word.api.interfaces.IFluentElement;
import word.w2004.style.HeadingStyle;
import word.w2004.style.HeadingStyle.Align;

public class Title1 extends AbstractHeading<HeadingStyle> implements IFluentElement<Title1> {

	private Title1(String value, Align align) {
		super("Title1", value, align);
	}
	
	public static Title1 with(String value, Align align) {
		return new Title1(value, align);
	}

	@Override
	public Title1 create() {
		return this;
	}

}
