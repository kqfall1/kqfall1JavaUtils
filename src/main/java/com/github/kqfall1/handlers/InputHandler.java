// Quinn Keenan, 06/10/2025

package com.github.kqfall1.handlers;

import com.github.kqfall1.enums.YesNoInput;
import com.github.kqfall1.exceptions.InvalidStringInputException;
import com.github.kqfall1.interfaces.inputters.*;

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
		return
			numberInputter != null
				? numberInputter.getNumber(prompt, lowerBound, upperBound)
				: 0;
	}

	public String promptForString(String prompt, String[] validStrings)
	{
		return
			stringInputter != null
				? stringInputter.getString(prompt, validStrings)
				: null;
	}

	public YesNoInput promptForYesNo(String prompt)
	{
		return yesNoInputter != null
			? yesNoInputter.getYesNo(prompt)
			: null;
	}

	public static void validateInputWasEntered(String userInput)
	{
		if (userInput == null || userInput.isEmpty())
		{
			throw new InvalidStringInputException();
		}
	}

	public static void validateNumber(double arg, String argName, double lowerBound, double upperBound)
	{
		if (arg < lowerBound || arg > upperBound)
		{
			throw new IllegalArgumentException(
				String.format("\n%s needs to remain between %.2f and %.2f inclusive. %.2f is invalid.",
				argName, lowerBound, upperBound, arg)
			);
		}
	}
}