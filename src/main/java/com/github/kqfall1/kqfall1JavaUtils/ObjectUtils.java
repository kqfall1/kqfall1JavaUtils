// Quinn Keenan, 06/10/2025

package com.github.kqfall1.kqfall1JavaUtils;

import com.github.kqfall1.kqfall1JavaExceptions.InvalidStringInputException;

public final class ObjectUtils
{
	public static void validateArgument(double arg, String argName, double lowerBound, double upperBound)
	{
		if (arg < lowerBound || arg > upperBound)
		{
			throw new IllegalArgumentException(
				String.format("\n%s\n needs to remain between %.2f and %.2f inclusive. %.2f is invalid.",
					argName, lowerBound, upperBound, arg
				)
			);
		}
	}

	public static void validateInputWasEntered(String userInput)
	{
		if (userInput == null || userInput.isEmpty())
		{
			throw new InvalidStringInputException();
		}
	}
}