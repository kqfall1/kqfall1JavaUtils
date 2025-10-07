// Quinn Keenan, 07/10/2025

package com.github.kqfall1.kqfall1JavaUtils;

public final class StringUtils
{
	public static String normalizeLower(String str)
	{
		return str.trim().toLowerCase();
	}

	public static String normalizeUpper(String str)
	{
		return str.trim().toUpperCase();
	}
}