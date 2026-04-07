package com.github.kqfall1.java.handlers.io;

import com.github.kqfall1.java.enums.YesNoInput;
import com.github.kqfall1.java.interfaces.FailurePresenter;
import com.github.kqfall1.java.interfaces.inputters.NumberInputter;
import com.github.kqfall1.java.interfaces.inputters.StringInputter;
import com.github.kqfall1.java.interfaces.inputters.YesNoInputter;
import com.github.kqfall1.java.utils.CollectionConverter;
import com.github.kqfall1.java.managers.InputManager;
import java.awt.*;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Handles user IO operations through an encapsulated {@code Scanner} and {@code PrintWriter}.
 *
 * <p>Error traps prevent client service until valid input is submitted. Encapsulate
 * {@code ConsoleIoHandler} into {@code InputManager} rather than using objects of
 * this type directly.</p>
 *
 * @author Quinn Keenan
 * @since 24/10/2025
 */
public final class ConsoleIoHandler implements FailurePresenter, NumberInputter, StringInputter, YesNoInputter
{
	/**
 	* Used for error display formatting.
 	*/
	private static final String BOUNDARY = "-".repeat(128);
	private static final String DEFAULT_PROMPT = "Please provide input";
	private final Scanner in;
	private final PrintWriter out;

	public ConsoleIoHandler()
	{
		in = new Scanner(System.in);
		out = new PrintWriter(System.out, true);
	}

	/**
 	* @param in The input {@code Scanner}.
 	* @param out The output {@code PrintWriter}.
	 * @throws NullPointerException if either {@code in} or {@code out} is {@code null}.
 	*/
	public ConsoleIoHandler(Scanner in, PrintWriter out)
	{
		Objects.requireNonNull(in, "\"in\" is null.");
		Objects.requireNonNull(out, "\"out\" is null.");
		this.in = in;
		this.out = out;
	}

	public Scanner getIn()
	{
		return in;
	}

	/**
 	* Catching {@code IllegalArgumentException} also catches {@code NumberFormatException} thrown in parse
 	* operations due to inheritance.
 	* @param prompt A {@code String} displayed to inform the actor of requested information.
	 * A colon is displayed at the end of this parameter.
 	* @param lowerBound The lowest acceptable number.
 	* @param upperBound The highest acceptable number.
	 * @return A completed {@code CompletableFuture} that encapsulates a valid, user-inputted {@code Double}.
 	*/
	@Override
	public CompletableFuture<Double> getNumber(Optional<String> prompt, double lowerBound, double upperBound)
	{
		while (true)
		{
			final var input = promptAndRead(prompt.orElse(DEFAULT_PROMPT));

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

	public PrintWriter getOut()
	{
		return out;
	}

	/**
 	* @param prompt A {@code String} displayed to inform the actor of requested information.
	 *               A colon is displayed at the end of this parameter.
 	* @param validStrings All acceptable {@code String} values.
	 * @return A completed {@code CompletableFuture} that encapsulates a valid, user-inputted {@code String}.
 	*/
	@Override
	public CompletableFuture<String> getString(Optional<String> prompt, Optional<String[]> validStrings)
	{
		while (true)
		{
			final var input = promptAndRead(prompt.orElse(DEFAULT_PROMPT));
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
 	* @param prompt A {@code String} displayed to inform the actor of requested information.
 	* @return A completed {@code CompletableFuture} that encapsulates either {@code YesNoInput.YES} or {@code YesNoInput.NO}.
 	*/
	@Override
	public CompletableFuture<YesNoInput> getYesNo(Optional<String> prompt)
	{
		while (true)
		{
			final var input = promptAndRead(prompt.orElse(DEFAULT_PROMPT));
			final var parsedInput = YesNoInput.of(input.trim());

			if (parsedInput.isPresent())
			{
				return CompletableFuture.completedFuture(parsedInput.get());
			}

			presentFailure(String.format("Input \"%s\" is invalid.", input));
		}
	}

	@Override
	public void presentFailure(String message, Component... components)
	{
		out.printf("%s\n%s\n%s\n",
			BOUNDARY,
			message,
			BOUNDARY
		);
	}

	/**
	 * Prompts the actor for input by printing {@code prompt}; accepts the next line of input.
	 * @param prompt A {@code String} to be displayed to inform the actor of requested information.
	 * @return The next line of input from the actor.
	 */
	private String promptAndRead(String prompt)
	{
		getOut().printf("%s: ", prompt);
		return getIn().nextLine();
	}

	@Override
	public String toString()
	{
		return String.format("%s[in=%s,out=%s]",
			getClass().getName(),
			getIn(),
			getOut()
		);
	}
}