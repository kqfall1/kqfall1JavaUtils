package com.github.kqfall1.java.interfaces;

import java.awt.*;

/**
 * Defines a contract for displaying the information of a failed process to actors (most notably the actors of an AWT-based application).
 *
 * @author Quinn Keenan
 * @since 24/10/2025
 */
@FunctionalInterface
public interface FailurePresenter
{
	void presentFailure(String message, Component... components);
}