// Quinn Keenan, 05/10/2025

package com.github.kqfall1.kqfall1JavaUtils;

import com.github.kqfall1.kqfall1JavaEnums.YesNoInput;
import com.github.kqfall1.kqfall1JavaExceptions.InvalidStringInputException;
import javax.swing.JOptionPane;

public final class JOptionPaneOutput
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
		String userInputString;

		while (true)
		{
			userInputString = JOptionPane.showInputDialog(
				null,
				prompt
			);

			try
			{
				if (userInputString == null)
				{
					throw new InvalidStringInputException();
				}
				else if (userInputString.length() != 1 || !userInputString.equalsIgnoreCase("Y") &&  !userInputString.equalsIgnoreCase("N"))
				{
					throw new InvalidStringInputException(userInputString);
				}

				if (userInputString.equalsIgnoreCase("Y"))
				{
					return YesNoInput.YES;
				}
				else
				{
					return YesNoInput.NO;
				}
			}
			catch (InvalidStringInputException e)
			{
				showExceptionDialog(e.getMessage());
			}
		}
	}

	public static void showExceptionDialog(String message)
	{
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}