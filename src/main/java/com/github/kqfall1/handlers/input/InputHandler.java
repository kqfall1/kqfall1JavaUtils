package com.github.kqfall1.handlers.input;

import com.github.kqfall1.enums.YesNoInput;
import com.github.kqfall1.exceptions.InvalidStringInputException;
import com.github.kqfall1.interfaces.inputters.*;

/**
 * Provides an interface to use {@code Inputter}-implementing objects.
 *
 * <p>
 * Encapsulates multiple different {@code Inputter} sources, though they are immutable.
 * Provides public APIs for prompting users for various data types.
 * </p>
 *
 * @author Quinn Keenan
 * @since 24/10/2025
 */
public final class InputHandler
{
	private final NumberInputter numberInputter;
	private final StringInputter stringInputter;
	private final YesNoInputter yesNoInputter;

	public InputHandler(NumberInputter numberInputter, StringInputter stringInputter, YesNoInputter yesNoInputter)
	{
		this.numberInputter = numberInputter;
		this.stringInputter = stringInputter;
		this.yesNoInputter = yesNoInputter;
	}

	public NumberInputter getNumberInputter()
	{
		return numberInputter;
	}

	public StringInputter getStringInputter()
	{
		return stringInputter;
	}

	public YesNoInputter getYesNoInputter()
	{
		return yesNoInputter;
	}

	public double promptForNumber(String prompt, double lowerBound, double upperBound)
	{
		validateObjIsNotNull("numberInputter", numberInputter);
		return numberInputter.getNumber(prompt, lowerBound, upperBound);
	}

	public String promptForString(String prompt, String[] validStrings)
	{
		validateObjIsNotNull("stringInputter", stringInputter);
		return stringInputter.getString(prompt, validStrings);
	}

	public YesNoInput promptForYesNo(String prompt)
	{
		validateObjIsNotNull("yesNoInputter", yesNoInputter);
		return yesNoInputter.getYesNo(prompt);
	}

	@Override
	public String toString()
	{
		return String.format("%s[numberInputter=%s,stringInputter=%s,yesNoInputter=%s]",
			getClass().getName(),
			numberInputter != null ? numberInputter : "null",
			stringInputter != null ? stringInputter : "null",
			yesNoInputter != null ?  yesNoInputter : "null"
		);
	}

	public static void validateInputWasEntered(String userInput)
	{
		if (userInput == null || userInput.isEmpty())
		{
			throw new InvalidStringInputException();
		}
	}

	public static void validateNumber(double num, String argName, double lowerBound, double upperBound)
	{
		if (num < lowerBound || num > upperBound)
		{
			throw new IllegalArgumentException(
				String.format("\n%s needs to remain between %.2f and %.2f inclusive. %.2f is invalid.",
				argName, lowerBound, upperBound, num)
			);
		}
	}

	public static void validateObjIsNotNull(String name, Object obj)
	{
		if (obj == null)
		{
			throw new IllegalStateException(String.format("%s is null.", name));
		}
	}
}