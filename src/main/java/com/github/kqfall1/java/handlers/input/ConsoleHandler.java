package com.github.kqfall1.java.handlers.input;

import com.github.kqfall1.java.enums.YesNoInput;
import com.github.kqfall1.java.interfaces.ErrorPresenter;
import com.github.kqfall1.java.interfaces.inputters.NumberInputter;
import com.github.kqfall1.java.interfaces.inputters.StringInputter;
import com.github.kqfall1.java.interfaces.inputters.YesNoInputter;
import com.github.kqfall1.java.utils.CollectionConverter;
import com.github.kqfall1.java.utils.StringUtils;
import com.github.kqfall1.java.validators.InputValidator;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Handles IO operations through an encapsulated {@code PrintStream} and {@code Scanner}.
 *
 * <p>
 * Error traps prevent client service until valid input is submitted. Encapsulate
 * {@code ConsoleHandler} into {@code InputValidator} rather than using objects of
 * this type directly.
 * </p>
 *
 * @author Quinn Keenan
 * @since 24/10/2025
 */
public final class ConsoleHandler
implements ErrorPresenter, NumberInputter, StringInputter, YesNoInputter
{
	/**
 	* Used for error display formatting.
 	*/
	private static final String BOUNDARY = "--------------------------------------------------------------------------------------------------------------------------------";
	private final Scanner in;
	private final PrintStream out;

	public ConsoleHandler()
	{
		in = new Scanner(System.in);
		out = System.out;
	}

	/**
 	* @param in The input {@code Scanner}.
 	* @param out The output {@code PrintStream}.
	 * @throws IllegalStateException if either {@code in} or {@code out} is null.
 	*/
	public ConsoleHandler(Scanner in, PrintStream out)
	{
		InputValidator.validateObjIsNotNull("in", in);
		this.in = in;
		InputValidator.validateObjIsNotNull("out", out);
		this.out = out;
	}

	public Scanner getIn()
	{
		return in;
	}

	/**
 	* Catching IllegalArgumentException also catches NumberFormatException thrown in parse
 	* operations due to inheritance.
 	* @param prompt A string displayed to inform the actor of requested information.
	 *               A colon is displayed at the end of this parameter.
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
			input = promptAndRead(prompt);

			try
			{
				inputDbl = Double.parseDouble(input);
				InputValidator.validateNumber(inputDbl, "Input", lowerBound, upperBound);
				return inputDbl;
			}
			catch (IllegalArgumentException | NullPointerException e)
			{
				showException(e);
			}
		}
	}

	public PrintStream getOut()
	{
		return out;
	}

	/**
 	* @param prompt A string displayed to inform the actor of requested information.
	 *               A colon is displayed at the end of this parameter.
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
			input = promptAndRead(prompt);

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
 	* @param prompt A string displayed to inform the actor of requested information.
 	* @return {@code YesNoInput.YES} or {@code YesNoInput.NO}.
 	*/
	@Override
	public YesNoInput getYesNo(String prompt)
	{
		String input;

		while (true)
		{
			input = promptAndRead(prompt);

			if (StringUtils.normalizeLower(input).equals("yes"))
			{
				return YesNoInput.YES;
			}
			else if (StringUtils.normalizeLower(input).equals("no"))
			{
				return YesNoInput.NO;
			}

			showError(String.format("Input \"%s\" is invalid.", input));
		}
	}

	private String promptAndRead(String prompt)
	{
		out.printf("%s: ", prompt);
		return in.nextLine();
	}

	/**
 	* @param message A string displayed to inform the actor of an error.
	 *                A period is displayed at the end of this parameter.
 	*/
	@Override
	public void showError(String message)
	{
		out.printf("\n%s\n%s\n%s.",
			BOUNDARY,
			message,
			BOUNDARY
		);
	}

	@Override
	public void showException(Exception e)
	{
		final StringBuilder stacktrace = new StringBuilder();

		Arrays.stream(e.getStackTrace())
			.forEach(stackTraceElem ->
				stacktrace.append(String.format("%s\n", stackTraceElem.toString())
			));

		out.printf("\n%s\n%s Here is the stacktrace:\n\n%s%s\n\n",
			BOUNDARY,
			e.getMessage(),
			stacktrace,
			BOUNDARY
		);
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