package com.github.kqfall1.java.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Static class. Provides abstractions for repetitive, {@code Logger}-related tasks.
 * @author Quinn Keenan
 * @since 12/12/2025
 */
public final class LoggerUtils
{
	private LoggerUtils() {}

	public static Logger newFileLogger(String filePath, String name, boolean append)
	throws IOException
	{
		final var handler = new FileHandler(filePath, append);
		final var logger = Logger.getLogger(name);
		handler.setFormatter(new SimpleFormatter());
		logger.addHandler(handler);
		logger.setUseParentHandlers(false);
		return logger;
	}
}