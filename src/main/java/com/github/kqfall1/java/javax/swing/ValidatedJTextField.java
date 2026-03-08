package com.github.kqfall1.java.javax.swing;

import com.github.kqfall1.java.enums.YesNoInput;
import com.github.kqfall1.java.interfaces.inputters.NumberInputter;
import com.github.kqfall1.java.interfaces.inputters.StringInputter;
import com.github.kqfall1.java.interfaces.inputters.YesNoInputter;
import com.github.kqfall1.java.managers.InputManager;
import com.github.kqfall1.java.utils.StringUtils;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import javax.swing.JTextField;

/**
 * A {@code JTextField} that exposes methods to fetch, parse, and validate user input.
 *
 * @author kqfall1
 * @since 14/02/2026
 */
public final class ValidatedJTextField extends JTextField implements NumberInputter, StringInputter, YesNoInputter
{
    private <T> CompletableFuture<T> fail(String input)
    {
        return CompletableFuture.failedFuture(new IllegalArgumentException(String.format(
            "Input \"%s\" is invalid.",
            input
        )));
    }

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

    @Override
    public CompletableFuture<String> getString(String prompt, String[] validStrings)
    {
        final var INPUT = getText().trim();

        if (validStrings == null || validStrings.length == 0 || Set.of(validStrings).contains(INPUT))
        {
            return CompletableFuture.completedFuture(INPUT);
        }
        else
        {
            return fail(INPUT);
        }
    }

    @Override
    public CompletableFuture<YesNoInput> getYesNo(String prompt)
    {
        final var INPUT = getText().trim();

        try
        {
            final var PARSED_INPUT = YesNoInput.valueOf(StringUtils.normalizeUpper(INPUT));
            return CompletableFuture.completedFuture(PARSED_INPUT);
        }
        catch (IllegalArgumentException e)
        {
            return fail(INPUT);
        }
    }
}