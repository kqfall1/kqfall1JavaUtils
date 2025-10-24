// Quinn Keenan, 05/10/2025

package com.github.kqfall1.handlers;

import com.github.kqfall1.enums.YesNoInput;
import com.github.kqfall1.exceptions.InvalidStringInputException;
import com.github.kqfall1.interfaces.inputters.NumberInputter;
import com.github.kqfall1.interfaces.inputters.StringInputter;
import com.github.kqfall1.interfaces.inputters.YesNoInputter;
import com.github.kqfall1.utils.CollectionConverter;
import com.github.kqfall1.utils.StringUtils;
import javax.swing.JOptionPane;
import java.util.Arrays;

public final class JOptionPaneHandler
implements NumberInputter, StringInputter, YesNoInputter
{
	public double getNumber(String prompt, double lowerBound, double upperBound)
	{
		String input;
		double inputDbl;

		while (true)
		{
			input = JOptionPane.showInputDialog(null, prompt);

			try
			{
				InputHandler.validateInputWasEntered(input);
				inputDbl = Double.parseDouble(input);
				InputHandler.validateNumber(inputDbl, "Input", lowerBound, upperBound);
				return inputDbl;
			}
			catch (IllegalArgumentException | InvalidStringInputException e)
			{
				showException(e.getMessage());
			}
		}
	}

	public String getString(String prompt, String[] validStrings)
	{
		String input;
		String[] normalizedValidStrings = CollectionConverter.normalizeStringsLower(validStrings);

		while (true)
		{
			input = StringUtils.normalizeLower(JOptionPane.showInputDialog(null, prompt));

			try
			{
				InputHandler.validateInputWasEntered(input);

				if (!Arrays.asList(normalizedValidStrings).contains(input))
				{
					throw new InvalidStringInputException(input);
				}

				return input;
			}
			catch (InvalidStringInputException e)
			{
				showException(e.getMessage());
			}
		}
	}

	public YesNoInput getYesNo(String prompt)
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

	private static void showException(String message)
	{
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}