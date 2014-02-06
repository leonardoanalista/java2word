package word.w2004.style;

import word.api.interfaces.IElement;
import word.api.interfaces.ISuperStylin;

public abstract class AbstractStyle implements ISuperStylin {

	private IElement element;
	
	public void setElement(IElement element) {
		this.element = element;
	}
	
	public IElement create() {
		return this.element;
	}
	
}