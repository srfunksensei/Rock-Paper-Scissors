package com.rps.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

public class RandomEnumTest {

	private enum EmptyTestEnum {}

	@Test
	public void testNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> RandomEnum.getValue(null));
	}

	@Test
	public void testEmptyEnum() {
		EmptyTestEnum value = RandomEnum.getValue(EmptyTestEnum.class);
		Assertions.assertNull(value, "Should return null for an empty Enum!");
	}
	
	@Test
	public void testRegularEnum() {
		DayOfWeek value = RandomEnum.getValue(DayOfWeek.class);
		Assertions.assertNotNull(value, "Should not return null for a regular Enum!");
	}
}
