package com.github.kqfall1.java.javax.swing;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Static class. Provides abstraction for repetitive, {@code AWT}-related tasks.
 *
 * @author kqfall1
 * @since 15/02/2026
 */
public final class AwtUtils
{
    private AwtUtils() {}

    public static Component[] getNestedComponents(Container container)
    {
        assert container != null : "container == null";
        final var ALL_COMPONENTS = new HashSet<Component>();
        _getNestedComponents(container, ALL_COMPONENTS);
        return ALL_COMPONENTS.toArray(new Component[0]);
    }

    private static void _getNestedComponents(Container container, Set<Component> allComponents)
    {
        for (Component component : container.getComponents())
        {
            allComponents.add(component);

            if (component instanceof Container nestedContainer)
            {
                _getNestedComponents(nestedContainer, allComponents);
            }
        }
    }
}