package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.w2004.style.HeadingStyle;

public class HeadingStyleTest extends Assert{

	@Test
	public void sanityTest(){
		HeadingStyle style = new HeadingStyle();
		assertNotNull(style);
	}
	
}
