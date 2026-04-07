package com.github.kqfall1.java.frameworks.awt;

import java.awt.*;
import java.util.*;

/**
 * Static class. Provides abstraction for repetitive, {@code AWT}-related tasks.
 *
 * @author kqfall1
 * @since 15/02/2026
 */
public final class AwtUtils
{
    private AwtUtils() {}

    /**
     * Returns all {@code Component} objects contained by a given {@code Container} and all nested {@code Container} objects.
     * @param boundingClass The common supertype to be shared by collected {@code Component} objects.
     * @param container The {@code Container} to collect {@code Component} objects from.
     * @return An array of collected components.
     */
    public static Component[] getNestedComponents(Optional<Class<? extends Component>> boundingClass, Container container)
    {
        Objects.requireNonNull(boundingClass, "\"boundingClass\" is null");
        Objects.requireNonNull(container, "\"container\" is null.");
        final var allComponents = new HashSet<Component>();
        final var containers = new ArrayDeque<>(Set.of(container));

        while (!containers.isEmpty())
        {
            final var currentContainer = containers.pop();

            for (var component : currentContainer.getComponents())
            {
                if (boundingClass.orElse(component.getClass()).isAssignableFrom(component.getClass()))
                {
                    allComponents.add(component);
                }

                if (component instanceof Container nestedContainer)
                {
                    containers.push(nestedContainer);
                }
            }
        }

        return allComponents.toArray(new Component[0]);
    }
}