package bohonos.demski.mieldzioc.test.common;

import  org.junit.Assert;


import org.junit.Test;

import bohonos.demski.mieldzioc.mobilnyankieter.common.Pair;
/**
 * 
 * @author Dominik Demski
 *
 */
public class PairTest {

	@Test
	public void testSetGetFirst() {
		Pair<String, Integer> p1 = new Pair<String, Integer>();
		p1.setFirst("dominik");
		Assert.assertEquals(p1.getFirst(), "dominik");
	}

	@Test
	public void testSetGetSecond() {
		Pair<String, Integer> p1 = new Pair<String, Integer>();
		p1.setSecond(1);
		Assert.assertEquals(p1.getSecond(), new Integer(1));
	}

	@Test
	public void testEqualsFirstNullSecondNullObject() {
		Pair<String, Integer> p1 = new Pair<String, Integer>();
		Pair<Integer, String> p2 = new Pair<Integer, String>();
		
		Assert.assertEquals(p1, p2);
	}
	
	@Test
	public void testEqualsFirstNotNullAndFirstNullObject() {
		Pair<String, Integer> p1 = new Pair<String, Integer>();
		Pair<Integer, String> p2 = new Pair<Integer, String>();
		p1.setFirst("dominik");
		Assert.assertNotEquals(p1, p2);
	}
	
	@Test
	public void testEqualsBothNotNullObject() {
		Pair<String, Integer> p1 = new Pair<String, Integer>("dominik", new Integer(1));
		Pair<Integer, String> p2 = new Pair<Integer, String>(new Integer(1), "dominik");
		Assert.assertNotEquals(p1, p2);
	}
	
	@Test
	public void testEqualsShouldBeEquals() {
		Pair<String, Integer> p1 = new Pair<String, Integer>("dominik", new Integer(1));
		Pair<String, Integer> p2 = new Pair<String, Integer>("dominik", new Integer(1));
		Assert.assertEquals(p1, p2);
	}
	
	@Test
	public void testEqualsShouldntBeEquals() {
		Pair<String, Integer> p1 = new Pair<String, Integer>("dominik", new Integer(1));
		Pair<String, Integer> p2 = new Pair<String, Integer>("dominika", new Integer(1));
		Assert.assertNotEquals(p1, p2);
	}
	
	@Test
	public void testEqualsShouldntBeEqualsSecond() {
		Pair<String, Integer> p1 = new Pair<String, Integer>("dominik", new Integer(1));
		Pair<String, Integer> p2 = new Pair<String, Integer>("dominik", new Integer(2));
		Assert.assertNotEquals(p1, p2);
	}

}
