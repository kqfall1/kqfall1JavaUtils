package com.github.kqfall1.java.interfaces.inputters;

import java.util.concurrent.CompletableFuture;

/**
 * Defines a contract for receiving numeric input from actors.
 * 
 * @author Quinn Keenan
 * @since 23/10/2025
 */
@FunctionalInterface
public interface NumberInputter
{
	CompletableFuture<Double> getNumber
	(String prompt, double lowerBound, double upperBound);
}