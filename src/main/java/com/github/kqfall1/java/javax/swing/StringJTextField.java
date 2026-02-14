package com.github.kqfall1.java.javax.swing;

import com.github.kqfall1.java.interfaces.inputters.StringInputter;
import java.util.concurrent.CompletableFuture;
import java.util.Set;
import javax.swing.JTextField;

/**
 * A {@code JTextField} that is integrated with {@code kqfall1JavaUtils} abstractions.
 *
 * @author kqfall1
 * @since 14/02/2026
 */
public final class StringJTextField extends JTextField implements StringInputter
{
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
            return CompletableFuture.failedFuture(new IllegalArgumentException(String.format(
                "Input \"%s\" is invalid.",
                INPUT
            )));
        }
    }
}