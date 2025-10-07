// Quinn Keenan, 05/10/2025

package com.github.kqfall1.kqfall1JavaUtils;

import com.github.kqfall1.kqfall1JavaUtils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public final class CollectionConverter
{
	public static List<String> normalizeStringsUpper(List<String> list)
	{
		List<String> normalizedList = new ArrayList<String>();

		for (String str : list)
		{
			normalizedList.add(StringUtils.normalizeUpper(str));
		}

		return normalizedList;
	}

	public static String[] normalizeStringsUpper(String[] arr)
	{
		int arrIndex;
		String[] normalizedArr = new String[arr.length];

		for (arrIndex = 0; arrIndex < arr.length; arrIndex++)
		{
			normalizedArr[arrIndex] = StringUtils.normalizeUpper(arr[arrIndex]);
		}

		return normalizedArr;
	}

	public static List<String> normalizeStringsLower(List<String> list)
	{
		List<String> normalizedList = new ArrayList<String>();

		for (String str : list)
		{
			normalizedList.add(StringUtils.normalizeLower(str));
		}

		return normalizedList;
	}

	public static String[] normalizeStringsLower(String[] arr)
	{
		int arrIndex;
		String[] normalizedArr = new String[arr.length];

		for (arrIndex = 0; arrIndex < arr.length; arrIndex++)
		{
			normalizedArr[arrIndex] = StringUtils.normalizeLower(arr[arrIndex]);
		}

		return normalizedArr;
	}

	public static int[] toIntArr(List<Integer> list)
	{
		int listIndex;
		int[] values = new int[list.size()];

		for (listIndex = 0; listIndex < values.length; listIndex++)
		{
			values[listIndex] = list.get(listIndex);
		}

		return values;
	}

	public static List<String> toStringList(List<Object> list)
	{
		List<String> values = new ArrayList<>();
		int listIndex;

		for (listIndex = 0; listIndex < list.size(); listIndex++)
		{
			values.add(list.get(listIndex).toString());
		}

		return values;
	}

	public static String[] toStringArr(Object[] arr)
	{
		int arrIndex;
		String[] values = new String[arr.length];

		for (arrIndex = 0; arrIndex < values.length; arrIndex++)
		{
			values[arrIndex] = arr[arrIndex].toString();
		}

		return values;
	}

	public static int[] reverse(int[] arr)
	{
		int arrIndex;
		int[] reverseArr = new int[arr.length];
		int reverseIndex = 0;

		for (arrIndex = arr.length - 1; arrIndex >= 0; arrIndex--)
		{
			reverseArr[reverseIndex] = arr[arrIndex];
			reverseIndex++;
		}

		return reverseArr;
	}

	public static Object[] reverse(Object[] arr)
	{
		int arrIndex;
		Object[] reverseArr = new Object[arr.length];
		int reverseIndex = 0;

		for (arrIndex = arr.length - 1; arrIndex >= 0; arrIndex--)
		{
			reverseArr[reverseIndex] = arr[arrIndex];
			reverseIndex++;
		}

		return reverseArr;
	}
}