package com.github.kqfall1.java.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class YesNoInputTest
{
    @Test
    public void ofTestBoolean()
    {
        final var yes = YesNoInput.of(true);
        final var no = YesNoInput.of(false);
        Assertions.assertEquals(YesNoInput.YES, yes);
        Assertions.assertEquals(YesNoInput.NO, no);
    }

    @Test
    public void ofTestString()
    {
        var yes = YesNoInput.of("ye");
        var no = YesNoInput.of("noo");
        Assertions.assertTrue(yes.isEmpty());
        Assertions.assertTrue(no.isEmpty());

        no = YesNoInput.of("  ");
        Assertions.assertTrue(no.isEmpty());
        Assertions.assertThrows(NullPointerException.class, () -> YesNoInput.of(null));

        yes = YesNoInput.of("  YeS  ");
        no = YesNoInput.of("  nO  ");
        Assertions.assertTrue(yes.isPresent());
        Assertions.assertTrue(no.isPresent());
        Assertions.assertEquals(YesNoInput.YES, yes.orElse(YesNoInput.NO));
        Assertions.assertEquals(YesNoInput.NO, no.orElse(YesNoInput.YES));
    }

    @Test
    public void toBooleanTest()
    {
        Assertions.assertTrue(YesNoInput.YES.toBoolean());
        Assertions.assertFalse(YesNoInput.NO.toBoolean());
    }
}