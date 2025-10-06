// Quinn Keenan, 05/10/2025

package com.github.kqfall1.kqfall1JavaUtils;

import com.github.kqfall1.kqfall1JavaEnums.YesNoInput;
import com.github.kqfall1.kqfall1JavaExceptions.InvalidStringInputException;
import javax.swing.JOptionPane;

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
				if (userInput == null)
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

	public static YesNoInput promptForYesNoInput(String prompt)
	{
		int userInput;

		userInput = JOptionPane.showConfirmDialog(
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