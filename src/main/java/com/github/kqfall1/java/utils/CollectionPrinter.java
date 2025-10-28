package com.github.kqfall1.java.utils;

import com.github.kqfall1.java.validators.InputValidator;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * Prints prompts and {@code Arrays.toString} values of arrays to an encapsulated
 * {@code PrintStream}.
 * @author Quinn Keenan
 * @since 05/10/2025
 */
public final class CollectionPrinter
{
	private final PrintStream out;

	public CollectionPrinter()
	{
		out = System.out;
	}

	/**
 	* @param out The output {@code PrintStream}.
	 * @throws IllegalArgumentException if {@code out} is null.
 	*/
	public CollectionPrinter(PrintStream out)
	throws IllegalArgumentException
	{
		InputValidator.validateObjIsNotNull("out", out);
		this.out = out;
	}

	public PrintStream getOut()
	{
		return out;
	}

	public void print(Object[] arr, String prompt)
	{
		out.printf("%s: %s\n", prompt, Arrays.toString(arr));
	}

	@Override
	public String toString()
	{
		return String.format("%s[out=%s]",
			getClass().getName(),
			getOut()
		);
	}
}