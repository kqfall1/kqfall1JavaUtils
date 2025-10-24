package com.github.kqfall1.interfaces;

/**
 * Defines a contract for displaying error information to actors.
 * @author Quinn Keenan
 * @since 24/10/2025
 */
@FunctionalInterface
public interface ErrorPresenter
{
	void showError(Exception e);
}