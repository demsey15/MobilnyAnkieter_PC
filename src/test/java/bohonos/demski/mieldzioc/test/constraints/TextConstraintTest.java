package bohonos.demski.mieldzioc.test.constraints;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import bohonos.demski.mieldzioc.mobilnyankieter.constraints.TextConstraint;

public class TextConstraintTest {

	@Test
	public void testCheckCorrectnessMinLength5() {
		TextConstraint t = new TextConstraint(5, null, null);
		Assert.assertTrue(t.checkCorrectness("abcdefgh"));
	}
	
	@Test
	public void testCheckCorrectnessMinLength5Incorr() {
		TextConstraint t = new TextConstraint(5, null, null);
		Assert.assertTrue(!t.checkCorrectness("a"));
	}
	
	@Test
	public void testCheckCorrectnessMaxLength5() {
		TextConstraint t = new TextConstraint(null, 5, null);
		Assert.assertTrue(t.checkCorrectness(""));
	}
	
	@Test
	public void testCheckCorrectnessMaxLength5Incorr() {
		TextConstraint t = new TextConstraint(null, 5, null);
		Assert.assertTrue(!t.checkCorrectness("abcdefghi"));
	}
	
	@Test
	public void testCheckCorrectnessBetween3And8Length3() {
		TextConstraint t = new TextConstraint(3, 8, null);
		Assert.assertTrue(t.checkCorrectness("abc"));
	}
	
	@Test
	public void testCheckCorrectnessBetween3And8Length8() {
		TextConstraint t = new TextConstraint(3, 8, null);
		Assert.assertTrue(t.checkCorrectness("abcdefgh"));
	}

	@Test
	public void testCheckCorrectnessBetween3And8Length2Incorr() {
		TextConstraint t = new TextConstraint(3, 5, null);
		Assert.assertTrue(!t.checkCorrectness("ab"));
	}
	
	@Test
	public void testCheckCorrectnessBetween3And8Length10Incorr() {
		TextConstraint t = new TextConstraint(3, 5, null);
		Assert.assertTrue(!t.checkCorrectness("abcdefghij"));
	}
	
	@Test
	public void testCheckCorrectnessMatchRegex() {
		TextConstraint t = new TextConstraint(null, null, Pattern.compile("[0-9]{2}-[0-9]{3}"));
		Assert.assertTrue(t.checkCorrectness("64-500"));
	}
	
	@Test
	public void testCheckCorrectnessMatchRegexIncorr() {
		TextConstraint t = new TextConstraint(null, null, Pattern.compile("[0-9]{2}-[0-9]{3}"));
		Assert.assertTrue(!t.checkCorrectness("64-50"));
	}
}
