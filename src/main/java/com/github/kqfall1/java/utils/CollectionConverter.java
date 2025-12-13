package com.github.kqfall1.java.utils;

import java.lang.reflect.Array;

/**
 * Static class. Provides abstractions for repetitive, collection-related tasks.
 *
 * <p><strong>Contract:</strong> All methods in this class expect arrays that are
 * non-null and contain no null elements. Violations will result in
 * {@code NullPointerException}. Future expansion may add support for null-tolerant
 * operations.</p>
 *
 * @author Quinn Keenan
 * @since 05/10/2025
 */
public final class CollectionConverter
{
	private CollectionConverter() {}

	public static String[] normalizeStringsUpper(String[] arr)
	{
		int arrIndex;
		final var normalizedArr = new String[arr.length];

		for (arrIndex = 0; arrIndex < arr.length; arrIndex++)
		{
			normalizedArr[arrIndex] = StringUtils.normalizeUpper(arr[arrIndex]);
		}

		return normalizedArr;
	}

	public static String[] normalizeStringsLower(String[] arr)
	{
		int arrIndex;
		final var normalizedArr = new String[arr.length];

		for (arrIndex = 0; arrIndex < arr.length; arrIndex++)
		{
			normalizedArr[arrIndex] = StringUtils.normalizeLower(arr[arrIndex]);
		}

		return normalizedArr;
	}

	public static Integer[] toIntegerArr(Number[] arr)
	{
		int arrIndex;
		final var values = new Integer[arr.length];

		for (arrIndex = 0; arrIndex < arr.length; arrIndex++)
		{
			values[arrIndex] = arr[arrIndex].intValue();
		}

		return values;
	}

	public static String[] toStringArr(Object[] arr)
	{
		int arrIndex;
		final String[] values = new String[arr.length];

		for (arrIndex = 0; arrIndex < values.length; arrIndex++)
		{
			values[arrIndex] = arr[arrIndex].toString();
		}

		return values;
	}
}