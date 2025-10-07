// Quinn Keenan, 06/10/2025

package com.github.kqfall1.kqfall1JavaUtils;

import java.util.List;

public final class ArrayUtils
{
	public static boolean containsSubstring(List<String> list, String substring)
	{
		for (String str : list)
		{
			if (str.contains(substring))
			{
				return true;
			}
		}

		return false;
	}

	public static boolean containsSubstring(String[] arr, String substring)
	{
		for (String str : arr)
		{
			if (str.contains(substring))
			{
				return true;
			}
		}

		return false;
	}

	public static boolean contains(List<String> list, String string)
	{
		for (String str : list)
		{
			if (str.equals(string))
			{
				return true;
			}
		}

		return false;
	}

	public static boolean contains(String[] arr, String string)
	{
		for (String str : arr)
		{
			if (str.equals(string))
			{
				return true;
			}
		}

		return false;
	}
}