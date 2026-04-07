package com.github.kqfall1.java.handlers.io;

import com.github.kqfall1.java.enums.YesNoInput;
import com.github.kqfall1.java.interfaces.FailurePresenter;
import com.github.kqfall1.java.interfaces.inputters.NumberInputter;
import com.github.kqfall1.java.interfaces.inputters.StringInputter;
import com.github.kqfall1.java.interfaces.inputters.YesNoInputter;
import com.github.kqfall1.java.managers.InputManager;
import com.github.kqfall1.java.utils.CollectionConverter;
import java.awt.*;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * Handles user IO operations through {@code JOptionPane} objects.
 *
 * <p>Error traps prevent client service until valid input is submitted. Encapsulate
 * {@code JOptionPaneIoHandler} into {@code InputManager} rather than using objects of
 * this type directly.</p>
 *
 * @author Quinn Keenan
 * @since 05/10/2025
 */
public final class JOptionPaneIoHandler implements FailurePresenter, NumberInputter, StringInputter, YesNoInputter
{
	private static final String DEFAULT_PROMPT = "Please provide input.";

	/**
	 * <p>Catching {@code IllegalArgumentException} also catches {@code NumberFormatException} thrown in parse
	 * operations due to inheritance.</p>
 	* @param prompt A {@code String} displayed to inform the actor of requested information.
 	* @param lowerBound The lowest acceptable number.
 	* @param upperBound The highest acceptable number.
 	* @return A completed {@code CompletableFuture} that encapsulates a valid, user-inputted {@code Double}.
 	*/
	@Override
	public CompletableFuture<Double> getNumber(Optional<String> prompt, double lowerBound, double upperBound)
	{
		while (true)
		{
			final var input = JOptionPane.showInputDialog(null, prompt.orElse(DEFAULT_PROMPT));

			try
			{
				final var parsedInput = Double.parseDouble(input.trim());
				InputManager.validateNumber(parsedInput, "parsedInput", lowerBound, upperBound);
				return CompletableFuture.completedFuture(parsedInput);
			}
			catch (IllegalArgumentException | NullPointerException e)
			{
				presentFailure(e.getMessage());
			}
		}
	}

	/**
 	* @param prompt A {@code String} displayed to inform the actor of requested information.
 	* @param validStrings All acceptable {@code String} values.
 	* @return A completed {@code CompletableFuture} that encapsulates a valid, user-inputted {@code String}.
 	*/
	@Override
	public CompletableFuture<String> getString(Optional<String> prompt, Optional<String[]> validStrings)
	{
		while (true)
		{
			final var input = JOptionPane.showInputDialog(null, prompt.orElse(DEFAULT_PROMPT));
			final var normalizedValidStrings
					= Set.of(CollectionConverter.normalizeStringsLower(validStrings.orElse(new String[] { input.trim() })));

			if (normalizedValidStrings.contains(input))
			{
				return CompletableFuture.completedFuture(input);
			}
			else
			{
				presentFailure(String.format("Input \"%s\" is invalid.", input));
			}
		}
	}

	/**
 	* <p>No error traps are present in this method. This is due to user input corresponding
	 * to a {@code JOptionPane} {@code int} constant rather than a {@code String}.</p>
 	* @param prompt A {@code String} displayed to inform the actor of requested information.
 	* @return A completed {@code CompletableFuture} that encapsulates either {@code YesNoInput.YES} or {@code YesNoInput.NO}.
 	*/
	@Override
	public CompletableFuture<YesNoInput> getYesNo(Optional<String> prompt)
	{
		final int userInput = JOptionPane.showConfirmDialog(
			null,
			prompt.orElse(DEFAULT_PROMPT),
			"Confirmation Required",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);

		if (userInput == JOptionPane.YES_OPTION)
		{
			return CompletableFuture.completedFuture(YesNoInput.YES);
		}
		else
		{
			return CompletableFuture.completedFuture(YesNoInput.NO);
		}
	}

	@Override
	public void presentFailure(String message, Component... components)
	{
		JOptionPane.showMessageDialog(
			null,
			message,
			"Error",
			JOptionPane.ERROR_MESSAGE
		);
	}
}