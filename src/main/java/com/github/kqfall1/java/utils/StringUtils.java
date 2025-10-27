package com.github.kqfall1.java.utils;

/**
 * Static class. Provides abstractions for repetitive, string-related tasks.
 * @author Quinn Keenan
 * @since 24/10/2025
 */
public final class StringUtils
{
	private StringUtils() {}

	public static String normalizeLower(String str)
	{
		return str.trim().toLowerCase();
	}

	public static String normalizeUpper(String str)
	{
		return str.trim().toUpperCase();
	}
}