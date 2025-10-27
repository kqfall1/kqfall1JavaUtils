package com.github.kqfall1.java.interfaces.inputters;

/**
 * Defines a contract for string input from actors.
 * @author Quinn Keenan
 * @since 24/10/2025
 */
@FunctionalInterface
public interface StringInputter
{
	String getString(String prompt, String[] validStrings);
}