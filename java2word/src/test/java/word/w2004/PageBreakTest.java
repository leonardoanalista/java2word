package word.w2004;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;
import word.w2004.elements.PageBreak;

public class PageBreakTest extends Assert{

	@Test
	public void testPageBreak(){
		PageBreak pb = new PageBreak();
		assertEquals(1, TestUtils.regexCount(pb.getContent(), "<w:br w:type=\"page\" />"));
	}
	
	@Test
	public void testPageBreakFluent(){
		PageBreak pb = PageBreak.create();
		assertEquals(1, TestUtils.regexCount(pb.getContent(), "<w:br w:type=\"page\" />"));
	}
	
}
