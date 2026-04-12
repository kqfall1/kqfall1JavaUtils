package com.github.kqfall1.java.frameworks.awt.swing;

import com.github.kqfall1.java.enums.YesNoInput;
import com.github.kqfall1.java.interfaces.inputters.NumberInputter;
import com.github.kqfall1.java.interfaces.inputters.StringInputter;
import com.github.kqfall1.java.interfaces.inputters.YesNoInputter;
import com.github.kqfall1.java.managers.InputManager;
import java.util.concurrent.CompletableFuture;
import java.util.Optional;
import java.util.Set;
import javax.swing.JTextField;

/**
 * A {@code JTextField} that exposes methods to fetch, parse, and validate actor input.
 *
 * @author kqfall1
 * @since 14/02/2026
 */
public final class ValidatedJTextField extends JTextField implements NumberInputter, StringInputter, YesNoInputter
{
    private static <T> CompletableFuture<T> fail(String input)
    {
        return CompletableFuture.failedFuture(new IllegalArgumentException(String.format("Input \"%s\" is invalid.", input)));
    }

    @Override
    public CompletableFuture<Double> getNumber(Optional<String> prompt, double lowerBound, double upperBound)
    {
        final var input = getText();

        try
        {
            final var parsedInput = Double.parseDouble(input.trim());
            InputManager.validateNumber(parsedInput, "parsedInput", lowerBound, upperBound);
            return CompletableFuture.completedFuture(parsedInput);
        }
        catch (IllegalArgumentException | NullPointerException e)
        {
            return fail(input);
        }
    }

    @Override
    public CompletableFuture<String> getString(Optional<String> prompt, Optional<String[]> validStrings)
    {
        final var input = getText();

        if (Set.of(validStrings.orElse(new String[] {input})).contains(input))
        {
            return CompletableFuture.completedFuture(input);
        }

        return fail(input);
    }

    @Override
    public CompletableFuture<YesNoInput> getYesNo(Optional<String> prompt)
    {
        final var input = getText();
        return YesNoInput.of(input).map(CompletableFuture::completedFuture).orElseGet(() -> fail(input));
    }
}