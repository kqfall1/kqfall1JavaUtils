package com.github.kqfall1.java.managers;

import com.github.kqfall1.java.enums.YesNoInput;
import com.github.kqfall1.java.interfaces.inputters.NumberInputter;
import com.github.kqfall1.java.interfaces.inputters.StringInputter;
import com.github.kqfall1.java.interfaces.inputters.YesNoInputter;

/**
 * Provides an abstraction to use {@code Inputter}-implementing objects
 * (ie, {@code Handler} objects).
 *
 * <p>
 * Encapsulates multiple different {@code Inputter} sources, though they are immutable.
 * Provides public APIs for prompting users for various data types. Any of the
 * encapsulated fields may be null.
 * </p>
 *
 * @author Quinn Keenan
 * @since 24/10/2025
 */
public final class InputManager
{
	private final NumberInputter numberInputter;
	private final StringInputter stringInputter;
	private final YesNoInputter yesNoInputter;

	public InputManager(NumberInputter numberInputter, StringInputter stringInputter, YesNoInputter yesNoInputter)
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

	public double promptForNumber
	(String prompt, double lowerBound, double upperBound)
	{
		return numberInputter.getNumber(prompt, lowerBound, upperBound);
	}

	public String promptForString
	(String prompt, String[] validStrings)
	{
		return stringInputter.getString(prompt, validStrings);
	}

	public YesNoInput promptForYesNo(String prompt)
	{
		return yesNoInputter.getYesNo(prompt);
	}

	@Override
	public String toString()
	{
		return String.format("%s[numberInputter=%s,stringInputter=%s,yesNoInputter=%s]",
			getClass().getName(),
			getNumberInputter() != null ? getNumberInputter() : "null",
			getStringInputter() != null ? getStringInputter() : "null",
			getYesNoInputter() != null ?  getYesNoInputter() : "null"
		);
	}

	/**
 	* Guards against invalid numbers.
 	* @param num User input.
 	* @param numName The name of {@code num}.
 	* @param lowerBound The lowest acceptable number.
 	* @param upperBound The highest acceptable number.
	 * @throws IllegalArgumentException when {@code num} is out of bounds.
 	*/
	public static void validateNumber
	(double num, String numName, double lowerBound, double upperBound)
	{
		if (num < lowerBound || num > upperBound)
		{
			throw new IllegalArgumentException(
				String.format("\n%s needs to remain between %.2f and %.2f inclusive. %.2f is invalid.",
				numName, lowerBound, upperBound, num)
			);
		}
	}
}