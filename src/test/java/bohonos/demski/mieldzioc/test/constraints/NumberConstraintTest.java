package bohonos.demski.mieldzioc.test.constraints;

import org.junit.Assert;
import org.junit.Test;

import bohonos.demski.mieldzioc.mobilnyankieter.constraints.NumberConstraint;

public class NumberConstraintTest {

	@Test
	public void testCheckCorrectnessCorrectMinValue() {
		NumberConstraint n = new NumberConstraint(5.5, null, false, null);
		Assert.assertTrue(n.checkCorrectness("7.1"));
	}
	
	@Test
	public void testCheckCorrectnessCorrectMinMaxValue() {
		NumberConstraint n = new NumberConstraint(5.5, 8.30, false, null);
		Assert.assertTrue(n.checkCorrectness("8.30"));
	}
	
	@Test
	public void testCheckCorrectnessInCorrectMinValue() {
		NumberConstraint n = new NumberConstraint(5.5, null, false, null);
		Assert.assertTrue(!n.checkCorrectness("5.4"));
	}
	
	@Test
	public void testCheckCorrectnessInCorrectMinMaxValue() {
		NumberConstraint n = new NumberConstraint(5.5, 8.30, false, null);
		Assert.assertTrue(!n.checkCorrectness("8.31"));
	}
	
	@Test
	public void testCheckCorrectnessMustBeIntegerBetweenCorrect() {
		NumberConstraint n = new NumberConstraint(5.5, 8.30, true, null);
		Assert.assertTrue(n.checkCorrectness("8"));
	}

	@Test
	public void testCheckCorrectnessMustBeIntegerBetweenIncorrect() {
		NumberConstraint n = new NumberConstraint(5.5, 8.30, true, null);
		Assert.assertTrue(!n.checkCorrectness("8.1"));
	}
	
	@Test
	public void testCheckCorrectnessIntegerBetweenNotEquals() {
		NumberConstraint n = new NumberConstraint(5.5, 8.30, true, 8.0);
		Assert.assertTrue(n.checkCorrectness("7.0"));
	}
	@Test
	public void testCheckCorrectnessIntegerBetweenNotEqualsIncorr() {
		NumberConstraint n = new NumberConstraint(5.5, 8.30, true, 8.0);
		Assert.assertTrue(!n.checkCorrectness("8.0"));
	}
	@Test
	public void testCheckCorrectnessNotBetween() {
		NumberConstraint n = new NumberConstraint(5.5, 8.30, false, null, true);
		Assert.assertTrue(n.checkCorrectness("8.31"));
	}
	@Test
	public void testCheckCorrectness5_4NotBetween() {
		NumberConstraint n = new NumberConstraint(5.5, 8.30, false, null, true);
		Assert.assertTrue(n.checkCorrectness("5.4"));
	}
	@Test
	public void testCheckCorrectness5_5NotBetweenIncorr() {
		NumberConstraint n = new NumberConstraint(5.5, 8.30, false, null, true);
		Assert.assertTrue(!n.checkCorrectness("5.5"));
	}
	@Test
	public void testCheckCorrectness8_3NotBetweenIncorr() {
		NumberConstraint n = new NumberConstraint(5.5, 8.30, false, null, true);
		Assert.assertTrue(!n.checkCorrectness("8.3"));
	}
	@Test
	public void testCheckCorrectnessNotNumber() {
		NumberConstraint n = new NumberConstraint(5.5, 8.30, false, null, true);
		Assert.assertTrue(!n.checkCorrectness("Ala ma 22 lata"));
	}
}
