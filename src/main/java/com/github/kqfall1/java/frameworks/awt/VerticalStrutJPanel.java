package com.github.kqfall1.java.frameworks.awt;

import java.awt.*;
import javax.swing.*;

/**
 * Helper class that is used to create {@code Component} mutable objects that imitate those returned by
 * {@code Box.createVerticalStrut}.
 *
 * @author kqfall1
 * @since 17/05/2026
 */
public final class VerticalStrutJPanel extends JPanel
{
    private double sizeMultiplier;

    public VerticalStrutJPanel(double sizeMultiplier)
    {
        assert sizeMultiplier > 0;
        setOpaque(false);
        setSizeMultiplier(sizeMultiplier);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return AwtUtils.getSizeRelativeToDisplayBounds(this, 0, sizeMultiplier).orElseGet(Dimension::new);
    }

    public double getSizeMultiplier()
    {
        return sizeMultiplier;
    }

    public void setSizeMultiplier(double sizeMultiplier)
    {
        this.sizeMultiplier = sizeMultiplier;
    }
}