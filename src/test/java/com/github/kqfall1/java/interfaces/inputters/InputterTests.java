package com.github.kqfall1.java.interfaces.inputters;

import com.github.kqfall1.java.enums.YesNoInput;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Assertions;

public final class InputterTests
{
    public static Double getPositiveRandomNumber(Optional<Double> minimumValue)
    {
        return Math.random() + minimumValue.orElse(0.0);
    }

    public static Optional<String> getNewPrompt(Optional<String> oldPrompt)
    {
        if (oldPrompt.isEmpty())
        {
            return Optional.of("Something");
        }
        else
        {
            return Optional.empty();
        }
    }

    public static CompletableFuture<Optional<Double>> numberInputterTest
    (NumberInputter numberInputter, Optional<String> prompt, double lowerBound, double upperBound)
    {
        return numberInputter.getNumber(prompt, lowerBound, upperBound)
            .exceptionally(throwable ->
            {
                Assertions.assertTrue(throwable instanceof IllegalArgumentException || throwable instanceof NullPointerException);
                return null;
            })
            .thenApply(number ->
            {
                if (number != null)
                {
                    Assertions.assertTrue(number >= lowerBound && number <= upperBound);
                }

                return Optional.ofNullable(number);
            });
    }

    public static CompletableFuture<Optional<String>> stringInputterTest
    (StringInputter stringInputter, Optional<String> prompt, Optional<String[]> validStrings)
    {
        return stringInputter.getString(prompt, validStrings)
            .exceptionally(throwable ->
            {
                Assertions.assertInstanceOf(IllegalArgumentException.class, throwable);
                return null;
            })
            .thenApply(string ->
            {
                if (string != null)
                {
                    validStrings.ifPresent(strings -> Assertions.assertTrue(List.of(strings).contains(string)));
                }

                return Optional.ofNullable(string);
            });
    }

    public static CompletableFuture<Optional<YesNoInput>> yesNoInputterTest(YesNoInputter yesNoInputter, Optional<String> prompt)
    {
        return yesNoInputter.getYesNo(prompt)
            .exceptionally(throwable ->
            {
                Assertions.assertInstanceOf(IllegalArgumentException.class, throwable);
                return null;
            })
            .thenApply(Optional::ofNullable);
    }
}