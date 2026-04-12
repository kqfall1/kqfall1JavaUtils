package com.github.kqfall1.java.frameworks.awt.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;
import javax.swing.*;

/**
 * Static class. Provides abstraction for repetitive, {@code Swing}-related tasks.
 *
 * @author kqfall1
 * @since 12/04/2026
 */
public final class SwingUtils
{
    private SwingUtils() {}

    /**
     * Gets the root {@code JFrame} associated with a given {@code ActionEvent}.
     * @param e An {@code ActionEvent}.
     * @return An {@code Optional} encapsulating the {@code JFrame} associated with {@code e}, or {@code Optional.empty()} if not found.
     */
    public static Optional<JFrame> getRootJFrame(ActionEvent e)
    {
        if (e.getSource() instanceof JMenuItem jMenuItem)
        {
            return (Optional.of(jMenuItem)
                .map(JComponent::getParent)
                .filter(container -> container instanceof JPopupMenu)
                .map(jPopupMenuContainer -> (JPopupMenu) jPopupMenuContainer)
                .map(JPopupMenu::getInvoker)
                .map(SwingUtilities::getWindowAncestor)
                .filter(window -> window instanceof JFrame)
                .map(jFrameWindow -> (JFrame) jFrameWindow)
            );
        }
        else
        {
            return Optional.ofNullable(SwingUtilities.getAncestorOfClass(JFrame.class, (Component) e.getSource()))
                .filter(container -> container instanceof JFrame)
                .map(jFrameContainer -> (JFrame) jFrameContainer);
        }
    }
}