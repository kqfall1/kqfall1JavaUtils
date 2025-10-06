// Quinn Keenan, 05/10/2025

package com.github.kqfall1.kqfall1JavaUtils;

import com.github.kqfall1.kqfall1JavaEnums.YesNoInput;
import com.github.kqfall1.kqfall1JavaExceptions.InvalidStringInputException;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.List;

public final class JOptionPaneHandler
{
	public static int promptForValidInteger(String prompt, int lowerBound, int upperBound)
	{
		String userInput;
		int userInputAsInt;

		while (true)
		{
			userInput = JOptionPane.showInputDialog(null, prompt);

			try
			{
				if (userInput == null || userInput.isEmpty())
				{
					throw new InvalidStringInputException();
				}

				userInputAsInt = Integer.parseInt(userInput);

				if (userInputAsInt < lowerBound || userInputAsInt > upperBound)
				{
					throw new InvalidStringInputException(lowerBound, upperBound);
				}

				return userInputAsInt;
			}
			catch (NumberFormatException | InvalidStringInputException e)
			{
				showExceptionDialog(e.getMessage());
			}
		}
	}

	public static String promptForValidString(String prompt, List<String> validStrings)
	{
		String userInput;
		List<String> normalizedValidStrings = CollectionConverter.normalizeStrings(validStrings);
		int validStringsIndex;

		while (true)
		{
			userInput =  JOptionPane.showInputDialog(null, prompt);

			try
			{
				if (userInput == null || userInput.isEmpty())
				{
					throw new InvalidStringInputException();
				}
				else if (!normalizedValidStrings.contains(userInput.toUpperCase()))
				{
					throw new InvalidStringInputException(userInput);
				}

				return userInput;
			}
			catch (InvalidStringInputException e)
			{
				showExceptionDialog(e.getMessage());
			}
		}
	}

	public static String promptForValidString(String prompt, String[] validStrings)
	{
		String[] normalizedValidStrings = CollectionConverter.normalizeStrings(validStrings);
		String userInput;

		while (true)
		{
			userInput =  JOptionPane.showInputDialog(null, prompt);

			try
			{
				if (userInput == null || userInput.isEmpty())
				{
					throw new InvalidStringInputException();
				}
				else if (!Arrays.asList(normalizedValidStrings).contains(userInput.toUpperCase()))
				{
					throw new InvalidStringInputException(userInput);
				}

				return userInput;
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