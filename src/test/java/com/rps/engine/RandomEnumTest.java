package com.rps.engine;

import java.time.DayOfWeek;

import org.junit.Assert;
import org.junit.Test;

public class RandomEnumTest {

	private enum EmptyTestEnum {}

	@Test(expected = IllegalArgumentException.class)
	public void testNull() {
		RandomEnum.getValue(null);
	}

	@Test
	public void testEmptyEnum() {
		EmptyTestEnum value = RandomEnum.getValue(EmptyTestEnum.class);
		Assert.assertNull("Should return null for an empty Enum!", value);
	}
	
	@Test
	public void testRegularEnum() {
		DayOfWeek value = RandomEnum.getValue(DayOfWeek.class);
		Assert.assertNotEquals("Should not return null for a regular Enum!", value);
	}
}
