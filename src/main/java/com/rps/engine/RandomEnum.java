package com.rps.engine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class to randomize enums
 * 
 * @author mb
 *
 */
public class RandomEnum {
	
	private RandomEnum() {}

	/**
	 * Computes a random value of type clazz
	 * 
	 * @param clazz class type of enum
	 * @return a random enum of type clazz
	 */
	public static <T extends Enum<?>> T getValue(final Class<T> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("This method expects an enum class as parameter, null is not allowed!");
		}

		T result = null;

		final List<T> values = Arrays.asList(clazz.getEnumConstants());

		if (!values.isEmpty()) {
			Collections.shuffle(values);
			result = values.get(0);
		}

		return result;
	}
}
