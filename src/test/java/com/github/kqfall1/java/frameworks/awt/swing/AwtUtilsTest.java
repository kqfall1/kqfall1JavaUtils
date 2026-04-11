package com.github.kqfall1.java.frameworks.awt.swing;

import com.github.kqfall1.java.frameworks.awt.AwtUtils;
import java.awt.*;
import java.util.Optional;
import javax.swing.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class AwtUtilsTest
{
    @Test
    public void getNestedComponentsTest()
    {
        final var boundingClass = TextComponent.class;
        int boundingNestedComponentCount = 0;
        final var container = new Container();
        var count = 0;
        final var typeOptionCount = 4;

        for (; count < 200; count++)
        {
            final var componentTypeOption = count % typeOptionCount;

            final var component = switch(componentTypeOption)
            {
                case 0 -> new TextArea();
                case 1 -> new TextField();
                case 2-> new Button();
                default -> new ScrollPane();
            };

            container.add(component);

            if (boundingClass.isAssignableFrom(component.getClass()))
            {
                boundingNestedComponentCount++;
            }

            Assertions.assertEquals(
                boundingNestedComponentCount,
                AwtUtils.getNestedComponents(Optional.of(boundingClass), container).length
            );
        }

        Assertions.assertEquals(count, AwtUtils.getNestedComponents(Optional.empty(), container).length);
        Assertions.assertEquals(0, AwtUtils.getNestedComponents(Optional.of(Window.class), container).length);
        Assertions.assertEquals(0, AwtUtils.getNestedComponents(Optional.of(List.class), container).length);
    }
}