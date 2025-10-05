// Quinn Keenan, 05/10/2025

package com.github.kqfall1.kqfall1JavaUtils;

import java.util.List;

public final class CollectionPrinter
{
	private CollectionPrinter() {}

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