package com.github.kqfall1.exceptions;

/**
 * Exception thrown when a user submits invalid string input.
 * @author Quinn Keenan
 * @since 05/10/2025
 */
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
}