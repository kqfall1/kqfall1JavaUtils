package com.github.kqfall1.java.interfaces;

import java.awt.*;

/**
 * Defines a contract for displaying the information of a failed process to actors.
 *
 * @author Quinn Keenan
 * @since 24/10/2025
 */
public interface FailurePresenter
{
	void presentFailureMessage(String message);
	void updateGuiAfterFailure(Component... components);
}