package com.github.kqfall1.java.managers;

import com.github.kqfall1.java.enums.YesNoInput;
import com.github.kqfall1.java.interfaces.inputters.NumberInputter;
import com.github.kqfall1.java.interfaces.inputters.StringInputter;
import com.github.kqfall1.java.interfaces.inputters.YesNoInputter;
import java.util.concurrent.CompletableFuture;

/**
 * Provides an abstraction to use {@code Inputter}-implementing objects
 * (ie, {@code Handler} objects).
 *
 * <p>
 * Encapsulates multiple different {@code Inputter} sources. Provides public APIs
 * for prompting users for various data types. Any of the encapsulated {@code Inputter}
 * fields may be null.
 * </p>
 *
 * @author Quinn Keenan
 * @since 24/10/2025
 */
public final class InputManager
{
	private NumberInputter numberInputter;
	private StringInputter stringInputter;
	private YesNoInputter yesNoInputter;

	public InputManager(NumberInputter numberInputter, StringInputter stringInputter, YesNoInputter yesNoInputter)
	{
		this.numberInputter = numberInputter;
		this.stringInputter = stringInputter;
		this.yesNoInputter = yesNoInputter;
	}

	public CompletableFuture<Double> getNumber
	(String prompt, double lowerBound, double upperBound)
	{
		return numberInputter.getNumber(prompt, lowerBound, upperBound);
	}

	public NumberInputter getNumberInputter()
	{
		return numberInputter;
	}

	public CompletableFuture<String> getString
	(String prompt, String[] validStrings)
	{
		return stringInputter.getString(prompt, validStrings);
	}

	public StringInputter getStringInputter()
	{
		return stringInputter;
	}

	public CompletableFuture<YesNoInput> getYesNo(String prompt)
	{
		return yesNoInputter.getYesNo(prompt);
	}

	public YesNoInputter getYesNoInputter()
	{
		return yesNoInputter;
	}

	public void setNumberInputter(NumberInputter inputter)
	{
		this.numberInputter = inputter;
	}

	public void setStringInputter(StringInputter inputter)
	{
		this.stringInputter = inputter;
	}

	public void setYesNoInputter(YesNoInputter inputter)
	{
		this.yesNoInputter = inputter;
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