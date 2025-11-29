package com.github.kqfall1.java.utils;

import java.lang.reflect.Array;

/**
 * Static class. Provides abstractions for repetitive, collection-related tasks.
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
		int index;
		final var values = new Integer[arr.length];

		for (index = 0; index < arr.length; index++)
		{
			values[index] = (int) arr[index];
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

	public static <T> T[] reverse(T[] arr)
	{
		int arrIndex;
		int reverseIndex = 0;

		@SuppressWarnings("unchecked")
		T[] reverseArr = (T[]) Array.newInstance(
			arr.getClass().getComponentType(),
			arr.length
		);

		for (arrIndex = arr.length - 1; arrIndex >= 0; arrIndex--)
		{
			reverseArr[reverseIndex++] = arr[arrIndex];
		}

		return reverseArr;
	}
}