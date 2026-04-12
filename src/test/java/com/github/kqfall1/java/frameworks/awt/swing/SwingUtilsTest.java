package com.github.kqfall1.java.frameworks.awt.swing;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class SwingUtilsTest
{
    ArrayList<JFrame> jFrames;

    @Test
    public void getRootJFrameTest()
    {
        final var jButton = new JButton();
        jFrames = new ArrayList<>();
        jButton.addActionListener(this::jComponentActionListener);
        jButton.doClick();
        Assertions.assertTrue(this.jFrames.isEmpty());

        var frame = new JFrame();
        frame.add(jButton);
        jButton.doClick();
        Assertions.assertEquals(1, this.jFrames.size());

        frame.setJMenuBar(new JMenuBar());
        var jMenuItem = new JMenuItem();
        jMenuItem.addActionListener(this::jComponentActionListener);
        var jMenu = new JMenu();
        jMenu.add(jMenuItem);
        frame.getJMenuBar().add(jMenu);
        jMenuItem.doClick();
        Assertions.assertEquals(2, this.jFrames.size());
    }

    private void jComponentActionListener(ActionEvent e)
    {
        final var optionalJFrame = SwingUtils.getRootJFrame(e);
        optionalJFrame.ifPresent(jFrames::add);
    }
}