package com.github.kqfall1.java.interfaces.inputters;

/**
 * Defines a contract for numeric input from actors.
 * @author Quinn Keenan
 * @since 23/10/2025
 */
@FunctionalInterface
public interface NumberInputter
{
	double getNumber(String prompt, double lowerBound, double upperBound);
}