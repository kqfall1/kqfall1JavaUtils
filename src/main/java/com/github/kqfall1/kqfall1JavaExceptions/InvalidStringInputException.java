// Quinn Keenan, 05/10/2025

package com.github.kqfall1.kqfall1JavaExceptions;

public final class InvalidStringInputException extends RuntimeException
{
	private static final String NO_INPUT_MSG = "You must provide input.";

	public InvalidStringInputException()
	{
		super(NO_INPUT_MSG);
	}

	public InvalidStringInputException(String input)
	{
		super(input == null || input.isEmpty()
			? NO_INPUT_MSG
			: String.format("Input \"%s\" is invalid.", input)
		);
	}

	public InvalidStringInputException(int lowerBound, int upperBound)
	{
		super(String.format("You must input a number between %d and %d inclusive.", lowerBound, upperBound));
	}
}