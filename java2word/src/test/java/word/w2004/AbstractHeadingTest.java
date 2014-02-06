package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;
import word.w2004.elements.AbstractHeading;

public class AbstractHeadingTest extends Assert{

	//### TODO: I won't test the method applyStyle because I will pull this off to another class in order to reuse this for all other "stylable" class. 
	
	//anonymous implementation - this is the way I leaned how to test abstract classes. 
	@SuppressWarnings("rawtypes")
    AbstractHeading heading1 = new AbstractHeading("Heading1", "h111") {
		
	};
	
	@Test
	public void sanityTest(){
		assertEquals(1, TestUtils.regexCount(heading1.getTemplate(), "<w:p wsp:rsidR*"));
		assertEquals(1, TestUtils.regexCount(heading1.getTemplate(), "<w:t>[{]value[}]</w:t>")); //it has to have the pace holder
		assertEquals(1, TestUtils.regexCount(heading1.getTemplate(), "</w:p>"));
		assertEquals(1, TestUtils.regexCount(heading1.getTemplate(), "<w:pStyle w:val=\"Heading1\" />")); // it has to replace the Type of Heading
	}
	
}
