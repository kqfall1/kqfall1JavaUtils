package com.github.kqfall1.utils;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * Prints prompts and {@code Arrays.toString} values of arrays to an encapsulated {@code PrintStream}.
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

	public CollectionPrinter(PrintStream out)
	{
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
			out != null ? out : "null"
		);
	}
}