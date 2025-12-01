package com.github.kqfall1.java.handlers.input;

import com.github.kqfall1.java.enums.YesNoInput;
import com.github.kqfall1.java.interfaces.ErrorPresenter;
import com.github.kqfall1.java.interfaces.inputters.NumberInputter;
import com.github.kqfall1.java.interfaces.inputters.StringInputter;
import com.github.kqfall1.java.interfaces.inputters.YesNoInputter;
import com.github.kqfall1.java.utils.CollectionConverter;
import com.github.kqfall1.java.utils.StringUtils;
import com.github.kqfall1.java.managers.InputManager;
import javax.swing.JOptionPane;
import java.util.Arrays;

/**
 * Handles user IO operations through {@code JOptionPane} panes.
 *
 * <p>
 * Error traps prevent client service until valid input is submitted. Encapsulate
 * {@code JOptionPaneHandler} into {@code InputValidator} rather than using objects of
 * this type directly.
 * </p>
 *
 * @author Quinn Keenan
 * @since 05/10/2025
 */
public final class JOptionPaneHandler
implements ErrorPresenter, NumberInputter, StringInputter, YesNoInputter
{
	/**
	 * Catching IllegalArgumentException also catches NumberFormatException thrown in parse
	 * operations due to inheritance.
 	* @param prompt A string displayed to inform the actor of requested information.
 	* @param lowerBound The lowest acceptable number.
 	* @param upperBound The highest acceptable number.
 	* @return A valid, user-inputted {@code double}.
 	*/
	@Override
	public double getNumber(String prompt, double lowerBound, double upperBound)
	{
		String input;
		double inputDbl;

		while (true)
		{
			input = JOptionPane.showInputDialog(null, prompt);

			try
			{
				inputDbl = Double.parseDouble(input);
				InputManager.validateNumber(inputDbl, "Input", lowerBound, upperBound);
				return inputDbl;
			}
			catch (IllegalArgumentException | NullPointerException e)
			{
				showException(e);
			}
		}
	}

	/**
 	* @param prompt A string displayed to inform the actor of requested information.
 	* @param validStrings All acceptable strings.
 	* @return A valid, user-inputted string.
 	*/
	@Override
	public String getString(String prompt, String[] validStrings)
	{
		String input;
		final String[] normalizedValidStrings = CollectionConverter.normalizeStringsLower(validStrings);

		while (true)
		{
			input = StringUtils.normalizeLower(JOptionPane.showInputDialog(null, prompt));

			if (Arrays.asList(normalizedValidStrings).contains(input))
			{
				return input;
			}
			else
			{
				showError(String.format("Input \"%s\" is invalid.", input));
			}
		}
	}

	/**
 	* <p>
	 * No error traps are present in this method. This is due to user input corresponding
	 * to a {@code JOptionPane} {@code int} constant rather than a {@code String}.
 	* </p>
 	* @param prompt A string displayed to inform the actor of requested information.
 	* @return {@code YesNoInput.YES} or {@code YesNoInput.NO}.
 	*/
	@Override
	public YesNoInput getYesNo(String prompt)
	{
		final int userInput = JOptionPane.showConfirmDialog(
			null,
			prompt,
			"Confirmation Required",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);

		if (userInput == JOptionPane.YES_OPTION)
		{
			return YesNoInput.YES;
		}
		else
		{
			return YesNoInput.NO;
		}
	}

	@Override
	public void showError(String message)
	{
		JOptionPane.showMessageDialog(
			null,
			message,
			"Error",
			JOptionPane.ERROR_MESSAGE
		);
	}

	@Override
	public void showException(Exception e)
	{
		JOptionPane.showMessageDialog(
			null,
			e.getMessage(),
			"Error",
			JOptionPane.ERROR_MESSAGE
		);
	}
}