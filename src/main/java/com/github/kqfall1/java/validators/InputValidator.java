package com.github.kqfall1.java.validators;

import com.github.kqfall1.java.enums.YesNoInput;
import com.github.kqfall1.java.interfaces.inputters.NumberInputter;
import com.github.kqfall1.java.interfaces.inputters.StringInputter;
import com.github.kqfall1.java.interfaces.inputters.YesNoInputter;

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
public final class InputValidator
{
	private final NumberInputter numberInputter;
	private final StringInputter stringInputter;
	private final YesNoInputter yesNoInputter;

	public InputValidator(NumberInputter numberInputter, StringInputter stringInputter, YesNoInputter yesNoInputter)
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
	throws IllegalArgumentException
	{
		validateObjIsNotNull("numberInputter", numberInputter);
		return numberInputter.getNumber(prompt, lowerBound, upperBound);
	}

	public String promptForString
	(String prompt, String[] validStrings)
	throws IllegalArgumentException
	{
		validateObjIsNotNull("stringInputter", stringInputter);
		return stringInputter.getString(prompt, validStrings);
	}

	public YesNoInput promptForYesNo(String prompt)
	throws IllegalArgumentException
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
	throws IllegalArgumentException
	{
		if (num < lowerBound || num > upperBound)
		{
			throw new IllegalArgumentException(
				String.format("\n%s needs to remain between %.2f and %.2f inclusive. %.2f is invalid.",
				numName, lowerBound, upperBound, num)
			);
		}
	}

	/**
 	* Guards against null {@code Object} arguments.
 	* @param name The name of {@code obj}.
 	* @throws IllegalArgumentException when {@code obj} is null.
 	*/
	public static void validateObjIsNotNull(String name, Object obj)
	throws IllegalArgumentException
	{
		if (obj == null)
		{
			throw new IllegalArgumentException(String.format("%s is null.", name));
		}
	}
}