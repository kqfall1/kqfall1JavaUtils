package com.github.kqfall1.java.javax.swing;

import com.github.kqfall1.java.interfaces.inputters.NumberInputter;
import com.github.kqfall1.java.managers.InputManager;
import java.util.concurrent.CompletableFuture;
import javax.swing.JTextField;

/**
 * A {@code JTextField} that is integrated with {@code kqfall1JavaUtils} abstractions.
 *
 * @author kqfall1
 * @since 14/02/2026
 */
public final class NumberJTextField extends JTextField implements NumberInputter
{
    @Override
    public CompletableFuture<Double> getNumber(String prompt, double lowerBound, double upperBound)
    {
        try
        {
            final var INPUT = Double.parseDouble(getText().trim());
            InputManager.validateNumber(INPUT, "INPUT", lowerBound, upperBound);
            return CompletableFuture.completedFuture(INPUT);
        }
        catch (IllegalArgumentException | NullPointerException e)
        {
            return CompletableFuture.failedFuture(e);
        }
    }
}