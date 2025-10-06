// Quinn Keenan, 05/10/2025

package com.github.kqfall1.kqfall1JavaUtils;

import java.util.List;

public final class CollectionConverter
{
	public static int[] toIntArr(List<Integer> list)
	{
		int[] values = new int[list.size()];
		int valuesIndex;

		for (valuesIndex = 0; valuesIndex < values.length; valuesIndex++)
		{
			values[valuesIndex] = list.get(valuesIndex);
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