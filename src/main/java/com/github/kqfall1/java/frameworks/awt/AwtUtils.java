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
     * @return An {@code Optional} encapsulating the {@code Window} that contains the source of {@code e}, or
     * {@code Optional.empty()} if not found.
     */
    public static Optional<Window> getRootWindow(ActionEvent e)
    {
        Objects.requireNonNull(e, "\"e\" is null.");
        return _getRootWindow((Component) e.getSource());
    }

    /**
     * Retrieves the {@code Window} containing a given {@code Component}.
     * @return An {@code Optional} encapsulating the {@code Window} that contains {@code component}, or
     * {@code Optional.empty()} if not found.
     */
    public static Optional<Window> getRootWindow(Component component)
    {
        Objects.requireNonNull(component, "\"component\" is null.");
        return _getRootWindow(component);
    }

    private static Optional<Window> _getRootWindow(Component component)
    {
        if (component instanceof JMenuItem jMenuItem)
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
            return Optional.ofNullable((Window) SwingUtilities.getAncestorOfClass(Window.class, component));
        }
    }

    /**
     * Used to calculate the size of a {@code Component} or GUI margin relative to the size of the {@code bounds} of the
     * {@code GraphicsDevice} device displaying it.
     * @param widthMultiplier The multiplier to apply to the width of the {@code bounds} of the current {@code GraphicsDevice}.
     * @param heightMultiplier The multiplier to apply to the height of the {@code bounds} of the current {@code GraphicsDevice}.
     * @return An {@code Optional} encapsulating the {@code Dimension} representing the desired relative size, or
     * {@code Optional.empty} if {@code component} has no ancestor.
     */
    public static Optional<Dimension> getSizeRelativeToDisplayBounds(Component component, double widthMultiplier, double heightMultiplier)
    {
        return getRootWindow(component)
            .map(window -> (Component) window)
            .map(Component::getGraphicsConfiguration)
            .map(GraphicsConfiguration::getBounds)
            .map(displayBounds ->
            {
                return new Dimension(
                    (int) (displayBounds.getWidth() * widthMultiplier),
                    (int) (displayBounds.getHeight() * heightMultiplier)
                );
            });
    }
}