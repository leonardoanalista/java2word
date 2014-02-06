package word;

import junit.framework.Assert;

import org.junit.Test;

import word.utils.TestUtils;

public class TestUtilsTest extends Assert{

	@Test
	public void testRegex(){
		assertNotNull(new TestUtils());
		assertEquals(1, TestUtils.regexCount("leonardo", "leo"));
	}
	
	@Test
	public void testRegexNotFound(){
		assertEquals(0, TestUtils.regexCount("xxxxx xxxx", "leo"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRegexNull01(){
		assertEquals(0, TestUtils.regexCount("xxxxxxxxx", null));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRegexNull02(){
		assertEquals(0, TestUtils.regexCount(null, "xxx"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRegexNull03(){
		assertEquals(0, TestUtils.regexCount(null, null));
	}
	
	
}
