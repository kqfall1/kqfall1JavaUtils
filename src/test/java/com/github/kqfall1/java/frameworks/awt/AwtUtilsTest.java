package com.github.kqfall1.java.frameworks.awt;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Optional;
import javax.swing.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class AwtUtilsTest
{
    ArrayList<Window> windows;

    @Test
    public void getNestedComponentsTest() throws InterruptedException, InvocationTargetException
    {
        final var boundingClass = TextComponent.class;
        int boundingNestedComponentCount = 0;
        final var container = new Container();
        container.setVisible(true);
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

            EventQueue.invokeAndWait(() -> container.add(component));

            if (boundingClass.isAssignableFrom(component.getClass()))
            {
                boundingNestedComponentCount++;
            }

            Assertions.assertEquals(boundingNestedComponentCount, AwtUtils.getNestedComponents(Optional.of(boundingClass), container).length);
        }

        Assertions.assertEquals(count, AwtUtils.getNestedComponents(Optional.empty(), container).length);
        Assertions.assertEquals(0, AwtUtils.getNestedComponents(Optional.of(Window.class), container).length);
        Assertions.assertEquals(0, AwtUtils.getNestedComponents(Optional.of(List.class), container).length);
    }

    @Test
    public void getRootWindowTest() throws InterruptedException, InvocationTargetException
    {
        final var button = new JButton();
        var frame = new Frame();
        frame.setVisible(true);
        windows = new ArrayList<>();

        EventQueue.invokeAndWait(() ->
        {
            button.addActionListener(e -> AwtUtils.getRootWindow((Component) e.getSource()).ifPresent(windows::add));
            button.addActionListener(e -> AwtUtils.getRootWindow(e).ifPresent(windows::add));
            button.doClick();
        });
        Assertions.assertTrue(this.windows.isEmpty());

        EventQueue.invokeAndWait(() ->
        {
            frame.add(button);
            button.doClick();
        });
        Assertions.assertEquals(2, this.windows.size());

        final var jFrame = new JFrame();

        EventQueue.invokeAndWait(() ->
        {
            jFrame.add(button);
            button.doClick();
        });
        Assertions.assertEquals(4, this.windows.size());

        EventQueue.invokeAndWait(() ->
        {
            jFrame.setJMenuBar(new JMenuBar());
            final var jMenuItem = new JMenuItem();
            jMenuItem.addActionListener(e -> AwtUtils.getRootWindow((Component) e.getSource()).ifPresent(windows::add));
            jMenuItem.addActionListener(e -> AwtUtils.getRootWindow(e).ifPresent(windows::add));
            final var jMenu = new JMenu();
            jMenu.add(jMenuItem);
            jFrame.getJMenuBar().add(jMenu);
            jMenuItem.doClick();
        });
        Assertions.assertEquals(6, this.windows.size());

        EventQueue.invokeAndWait(() ->
        {
            final var nestedJPanel1 = new JPanel();
            final var nestedJPanel2 = new JPanel();
            final var nestedJPanel3 = new JPanel();
            jFrame.add(nestedJPanel1);
            jFrame.remove(button);
            nestedJPanel1.add(nestedJPanel2);
            nestedJPanel2.add(nestedJPanel3);
            nestedJPanel3.add(button);
            button.doClick();
        });
        Assertions.assertEquals(8, this.windows.size());
    }

    @Test
    public void getSizeRelativeToDisplayBoundsTest() throws InterruptedException, InvocationTargetException
    {
        final var jButton = new JButton();
        final var jFrame = new JFrame();
        jFrame.setVisible(true);
        final var jPanel = new JPanel();
        Assertions.assertEquals(Optional.empty(), AwtUtils.getSizeRelativeToDisplayBounds(jPanel, 0.5, -0.5));
        EventQueue.invokeAndWait(() -> jFrame.add(jPanel));
        Assertions.assertTrue(AwtUtils.getSizeRelativeToDisplayBounds(jPanel, 0.5, -0.5).isPresent());
        EventQueue.invokeAndWait(() -> jFrame.add(jButton));
        Assertions.assertTrue(AwtUtils.getSizeRelativeToDisplayBounds(jButton, 0.5, -0.5).isPresent());
        EventQueue.invokeAndWait(() ->
        {
            jFrame.remove(jButton);
            jFrame.remove(jPanel);
        });
        Assertions.assertEquals(Optional.empty(), AwtUtils.getSizeRelativeToDisplayBounds(jPanel, 0.5, -0.5));
        jFrame.dispose();
    }
}