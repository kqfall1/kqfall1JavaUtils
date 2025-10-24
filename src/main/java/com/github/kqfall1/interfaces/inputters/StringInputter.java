package com.github.kqfall1.interfaces.inputters;

/**
 * Defines a contract for string input from actors.
 * @author Quinn Keenan
 * @since 24/10/2025
 */
@FunctionalInterface
public interface StringInputter
{
	/**
 	* @param prompt A string displayed to inform the actor of requested information.
 	*               A colon is appended to {@code prompt}.
 	* @param validStrings All acceptable strings.
 	* @return A valid, user-inputted String.
 */
	String getString(String prompt, String[] validStrings);
}