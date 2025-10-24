package com.github.kqfall1.handlers.input;

import com.github.kqfall1.enums.YesNoInput;
import com.github.kqfall1.exceptions.InvalidStringInputException;
import com.github.kqfall1.interfaces.ErrorPresenter;
import com.github.kqfall1.interfaces.inputters.*;
import com.github.kqfall1.utils.CollectionConverter;
import com.github.kqfall1.utils.StringUtils;
import javax.swing.JOptionPane;
import java.util.Arrays;

/**
 * Handles user IO operations through {@code JOptionPane} panes.
 *
 * <p>
 * Error traps prevent client service until valid input is submitted. Encapsulate
 * {@code JOptionPaneHandler} into {@code InputHandler} rather than using objects of
 * this type directly.
 * </p>
 *
 * @author Quinn Keenan
 * @since 05/10/2025
 */
public final class JOptionPaneHandler
implements ErrorPresenter, NumberInputter, StringInputter, YesNoInputter
{
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
				InputHandler.validateInputWasEntered(input);
				inputDbl = Double.parseDouble(input);
				InputHandler.validateNumber(inputDbl, "Input", lowerBound, upperBound);
				return inputDbl;
			}
			catch (IllegalArgumentException | InvalidStringInputException e)
			{
				showError(e);
			}
		}
	}

	@Override
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
				showError(e);
			}
		}
	}

	@Override
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

	@Override
	public void showError(Exception e)
	{
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}
}