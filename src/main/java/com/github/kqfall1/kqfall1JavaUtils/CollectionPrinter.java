// Quinn Keenan, 05/10/2025

package com.github.kqfall1.kqfall1JavaUtils;

import java.util.List;

public final class CollectionPrinter
{
	private CollectionPrinter() {}

	public static void print(int[] arr, String prompt)
	{
		StringBuilder arrSb = new StringBuilder();
		int arrIndex;

		arrSb.append(String.format("%s: { ", prompt));

		for (arrIndex = 0; arrIndex < arr.length; arrIndex++)
		{
			if (arrIndex < arr.length - 1)
			{
			arrSb.append(String.format("%d, ", arr[arrIndex]));
			}
			else
			{
				arrSb.append(String.format("%d ", arr[arrIndex]));
			}
		}

		arrSb.append("}");
		System.out.println(arrSb.toString());
	}

	public static void print(Object[] arr, String prompt)
	{
		System.out.println(String.format("%s:", prompt));

		for (Object o : arr)
		{
			System.out.println(String.format("\t%s", o));
		}
	}

	public static void print(List<?> list, String prompt)
	{
		System.out.println(String.format("%s:", prompt));

		for (Object o : list)
		{
			System.out.println(String.format("\t%s", o));
		}
	}
}