// Quinn Keenan, 05/10/2025

package com.github.kqfall1.kqfall1JavaUtils;

import com.github.kqfall1.kqfall1JavaEnums.YesNoInput;
import com.github.kqfall1.kqfall1JavaExceptions.InvalidStringInputException;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.List;

public final class JOptionPaneHandler
{
	public static double promptForValidDouble(String prompt, double lowerBound, double upperBound)
	{
		String input;
		double inputDbl;

		while (true)
		{
			input = JOptionPane.showInputDialog(null, prompt);

			try
			{
				ObjectUtils.validateInputWasEntered(input);
				inputDbl = Double.parseDouble(input);
				ObjectUtils.validateArgument(inputDbl, "Input", lowerBound, upperBound);
				return inputDbl;
			}
			catch (NumberFormatException | InvalidStringInputException e)
			{
				showExceptionDialog(e.getMessage());
			}
		}
	}

	public static int promptForValidInteger(String prompt, int lowerBound, int upperBound)
	{
		String input;
		int inputInt;

		while (true)
		{
			input = StringUtils.normalizeLower(JOptionPane.showInputDialog(null, prompt));

			try
			{
				ObjectUtils.validateInputWasEntered(input);
				inputInt = Integer.parseInt(input);
				ObjectUtils.validateArgument(inputInt, "Input", lowerBound, upperBound);
				return inputInt;
			}
			catch (NumberFormatException | InvalidStringInputException e)
			{
				showExceptionDialog(e.getMessage());
			}
		}
	}

	public static String promptForValidString(String prompt, List<String> validStrings)
	{
		String input;
		List<String> normalizedValidStrings = CollectionConverter.normalizeStrings(validStrings);

		while (true)
		{
			input = StringUtils.normalizeLower(JOptionPane.showInputDialog(null, prompt));

			try
			{
				ObjectUtils.validateInputWasEntered(input);

				if (!normalizedValidStrings.contains(input))
				{
					throw new InvalidStringInputException(input);
				}

				return input;
			}
			catch (InvalidStringInputException e)
			{
				showExceptionDialog(e.getMessage());
			}
		}
	}

	public static String promptForValidString(String prompt, String[] validStrings)
	{
		String input;
		String[] normalizedValidStrings = CollectionConverter.normalizeStrings(validStrings);

		while (true)
		{
			input = StringUtils.normalizeLower(JOptionPane.showInputDialog(null, prompt));

			try
			{
				ObjectUtils.validateInputWasEntered(input);

				if (!Arrays.asList(normalizedValidStrings).contains(input))
				{
					throw new InvalidStringInputException(input);
				}

				return input;
			}
			catch (InvalidStringInputException e)
			{
				showExceptionDialog(e.getMessage());
			}
		}
	}

	public static YesNoInput promptForYesNoInput(String prompt)
	{
		int userInput = JOptionPane.showConfirmDialog(
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

	public static void showExceptionDialog(String message)
	{
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}