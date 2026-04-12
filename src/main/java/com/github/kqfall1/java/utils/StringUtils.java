package com.github.kqfall1.java.utils;

import java.util.Objects;

/**
 * Static class. Provides abstractions for repetitive, {@code String}-related tasks.
 *
 * @author Quinn Keenan
 * @since 24/10/2025
 */
public final class StringUtils
{
	private StringUtils() {}

	public static String normalizeLower(String string)
	{
		Objects.requireNonNull(string);
		return string.trim().toLowerCase();
	}

	public static String normalizeUpper(String string)
	{
		Objects.requireNonNull(string);
		return string.trim().toUpperCase();
	}
}