package com.github.kqfall1.java.frameworks.awt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;

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

    /**
     * Retrieves the {@code Window} containing the {@code Component}-type source of a given {@code ActionEvent}.
     * @param e An {@code ActionEvent}.
     * @return An {@code Optional} encapsulating the {@code Window} that contains the source of {@code e}, or
     * {@code Optional.empty()} if not found.
     */
    public static Optional<Window> getRootWindow(ActionEvent e)
    {
        Objects.requireNonNull(e, "\"e\" is null.");

        if (e.getSource() instanceof JMenuItem jMenuItem)
        {
            return (Optional.of(jMenuItem)
                .map(JComponent::getParent)
                .filter(container -> container instanceof JPopupMenu)
                .map(jPopupMenuContainer -> (JPopupMenu) jPopupMenuContainer)
                .map(JPopupMenu::getInvoker)
                .map(SwingUtilities::getWindowAncestor)
            );
        }
        else
        {
            return Optional.ofNullable((Window) SwingUtilities.getAncestorOfClass(Window.class, (Component) e.getSource()));
        }
    }
}