package com.github.kqfall1.utils;

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
		final String[] normalizedArr = new String[arr.length];

		for (arrIndex = 0; arrIndex < arr.length; arrIndex++)
		{
			normalizedArr[arrIndex] = StringUtils.normalizeUpper(arr[arrIndex]);
		}

		return normalizedArr;
	}

	public static String[] normalizeStringsLower(String[] arr)
	{
		int arrIndex;
		final String[] normalizedArr = new String[arr.length];

		for (arrIndex = 0; arrIndex < arr.length; arrIndex++)
		{
			normalizedArr[arrIndex] = StringUtils.normalizeLower(arr[arrIndex]);
		}

		return normalizedArr;
	}

	public static int[] toIntArr(Integer[] arr)
	{
		int index;
		final int[] values = new int[arr.length];

		for (index = 0; index < arr.length; index++)
		{
			values[index] = arr[index];
		}

		return values;
	}

	public static int[] toIntArr(Object[] arr)
	{
		int index;
		final int[] values =  new int[arr.length];

		for (index = 0; index < arr.length; index++)
		{
			values[index] = (int) arr[index];
		}

		return values;
	}

	public static Integer[] toIntegerArr(int[] arr)
	{
		int index;
		final Integer[] values = new Integer[arr.length];

		for (index = 0; index < arr.length; index++)
		{
			values[index] = arr[index];
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

	public static Object[] reverse(Object[] arr)
	{
		int arrIndex;
		final Object[] reverseArr = new Object[arr.length];
		int reverseIndex = 0;

		for (arrIndex = arr.length - 1; arrIndex >= 0; arrIndex--)
		{
			reverseArr[reverseIndex] = arr[arrIndex];
			reverseIndex++;
		}

		return reverseArr;
	}
}