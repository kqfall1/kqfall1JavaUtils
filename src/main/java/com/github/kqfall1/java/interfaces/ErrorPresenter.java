package com.github.kqfall1.java.interfaces;

/**
 * Defines a contract for displaying error information to actors.
 * @author Quinn Keenan
 * @since 24/10/2025
 */
public interface ErrorPresenter
{
	void showException(Exception e);
	void showError(String message);
}