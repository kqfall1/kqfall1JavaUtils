package com.github.kqfall1.java.utils;

import java.util.Arrays;
import java.util.Objects;

/**
 * Static class. Provides abstractions for repetitive, collection-related tasks.
 *
 * <p>All methods in this class expect arrays that are non-null.</p>
 *
 * @author Quinn Keenan
 * @since 05/10/2025
 */
public final class CollectionConverter
{
	private CollectionConverter() {}

	public static String[] normalizeStringsLower(String[] arr)
	{
		return Arrays.stream(arr).filter(Objects::nonNull).map(StringUtils::normalizeLower).toArray(String[]::new);
	}

	public static String[] normalizeStringsUpper(String[] arr)
	{
		return Arrays.stream(arr).filter(Objects::nonNull).map(StringUtils::normalizeUpper).toArray(String[]::new);
	}

	public static Integer[] toIntegerArr(Number[] arr)
	{
		return Arrays.stream(arr).filter(Objects::nonNull).map(Number::intValue).toArray(Integer[]::new);
	}

	public static String[] toStringArr(Object[] arr)
	{
		return Arrays.stream(arr).filter(Objects::nonNull).map(Object::toString).toArray(String[]::new);
	}
}