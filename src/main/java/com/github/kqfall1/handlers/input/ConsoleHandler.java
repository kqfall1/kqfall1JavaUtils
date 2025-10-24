package com.github.kqfall1.handlers.input;

import com.github.kqfall1.enums.YesNoInput;
import com.github.kqfall1.exceptions.InvalidStringInputException;
import com.github.kqfall1.interfaces.ErrorPresenter;
import com.github.kqfall1.interfaces.inputters.*;
import com.github.kqfall1.utils.CollectionConverter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Handles user IO operations through an encapsulated {@code PrintStream} and {@code Scanner}.
 *
 * <p>
 * Error traps prevent client service until valid input is submitted. Encapsulate
 * {@code ConsoleHandler} into {@code InputHandler} rather than using objects of
 * this type directly.
 * </p>
 *
 * @author Quinn Keenan
 * @since 24/10/2025
 */
public final class ConsoleHandler
implements ErrorPresenter, NumberInputter, StringInputter, YesNoInputter
{
	private final Scanner in;
	private final PrintStream out;

	public ConsoleHandler()
	{
		in = new Scanner(System.in);
		out = System.out;
	}

	public ConsoleHandler(Scanner in, PrintStream out)
	{
		this.in = in;
		this.out = out;
	}

	@Override
	public double getNumber(String prompt, double lowerBound, double upperBound)
	{
		String input;
		double inputDbl;

		while (true)
		{
			input = promptAndRead(prompt);

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

	public PrintStream getOut()
	{
		return out;
	}

	@Override
	public String getString(String prompt, String[] validStrings)
	{
		String input;
		final String[] normalizedValidStrings = CollectionConverter.normalizeStringsLower(validStrings);

		while (true)
		{
			input = promptAndRead(prompt);

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
		String input;

		while (true)
		{
			input = promptAndRead(prompt);

			try
			{
				InputHandler.validateInputWasEntered(input);
				return YesNoInput.valueOf(input);
			}
			catch (InvalidStringInputException | IllegalArgumentException e)
			{
				showError(e);
			}
		}
	}

	private String promptAndRead(String prompt)
	{
		out.printf("%s: ", prompt);
		return in.nextLine();
	}

	@Override
	public void showError(Exception e)
	{
		final StringBuilder stacktrace = new StringBuilder();
		final String boundary = "--------------------------------------------------------------------------------------------------------------------------------";

		Arrays.stream(e.getStackTrace())
			.forEach(stackTraceElem ->
				stacktrace.append(String.format("%s\n", stackTraceElem.toString())
			));

		out.printf("\n%s\n%s Here is the stacktrace:\n\n%s%s\n\n",
			boundary,
			e.getMessage(),
			stacktrace,
			boundary);
	}

	@Override
	public String toString()
	{
		return String.format("%s[in=%s,out=%s]",
			getClass().getName(),
			in != null ? in : "null",
			out != null ? out : "null"
		);
	}
}