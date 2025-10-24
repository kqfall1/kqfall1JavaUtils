// Quinn Keenan, 05/10/2025

package com.github.kqfall1.utils;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public final class CollectionPrinter
{
	private final PrintStream out;

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
		out.println(String.format("%s: %s", prompt, Arrays.toString(arr)));
	}

	public void print(List<Object> list, String prompt)
	{
		out.println(String.format("%s: %s", prompt, list.toString()));
	}
}