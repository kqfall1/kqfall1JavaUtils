package com.github.kqfall1.java.interfaces.inputters;

import com.github.kqfall1.java.enums.YesNoInput;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Assertions;

public final class InputterTests
{
    public static CompletableFuture<Optional<Double>> numberInputterTest
    (NumberInputter numberInputter, Optional<String> prompt, double lowerBound, double upperBound)
    {
        return numberInputter.getNumber(prompt, lowerBound, upperBound)
            .exceptionally(throwable ->
            {
                Assertions.assertNotNull(throwable.getCause());
                Assertions.assertTrue(throwable.getCause() instanceof IllegalArgumentException
                        || throwable.getCause() instanceof NullPointerException);
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
                Assertions.assertInstanceOf(IllegalArgumentException.class, throwable.getCause());
                return null;
            })
            .thenApply(string ->
            {
                validStrings.ifPresent(strings -> Assertions.assertTrue(List.of(strings).contains(string)));
                return Optional.ofNullable(string);
            });
    }

    public static CompletableFuture<Optional<YesNoInput>> yesNoInputterTest(YesNoInputter yesNoInputter, Optional<String> prompt)
    {
        return yesNoInputter.getYesNo(prompt)
            .exceptionally(throwable ->
            {
                Assertions.assertInstanceOf(NullPointerException.class, throwable.getCause());
                return null;
            })
            .thenApply(Optional::ofNullable);
    }
}