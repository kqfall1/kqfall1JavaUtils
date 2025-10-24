package com.github.kqfall1.interfaces.inputters;

/**
 * Defines a contract for numeric input from actors.
 * @author Quinn Keenan
 * @since 23/10/2025
 */
@FunctionalInterface
public interface NumberInputter
{
	/**
 	* @param prompt A string displayed to inform the actor of requested information.
	 *               A colon is appended to {@code prompt}.
 	* @param lowerBound The lowest acceptable number.
 	* @param upperBound The highest acceptable number.
 	* @return A valid, user-inputted double.
 	*/
	double getNumber(String prompt, double lowerBound, double upperBound);
}