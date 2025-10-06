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
}