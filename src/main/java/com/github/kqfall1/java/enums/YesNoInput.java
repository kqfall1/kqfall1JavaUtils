package com.github.kqfall1.java.enums;

import com.github.kqfall1.java.utils.StringUtils;
import java.util.Objects;
import java.util.Optional;

/**
 * Normalized binary input for yes or no decisions.
 *
 * @author Quinn Keenan
 * @since 06/10/2025
 */
public enum YesNoInput
{
	YES, NO;

	public static Optional<YesNoInput> of(String input)
	{
		Objects.requireNonNull(input, "\"input\" is null.");

		try
		{
			return Optional.of(YesNoInput.valueOf(StringUtils.normalizeUpper(input.trim())));
		}
		catch (IllegalArgumentException e)
		{
			return Optional.empty();
		}
	}

	public static YesNoInput of(boolean input)
	{
		return input ? YES : NO;
	}

	public boolean toBoolean()
	{
		return this == YES;
	}
}