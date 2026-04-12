package com.github.kqfall1.java.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Optional;

/**
 * Static class. Provides abstractions for repetitive, {@code Logger}-related tasks.
 *
 * @author kqfall1
 * @since 12/12/2025
 */
public final class LoggerUtils
{
	private LoggerUtils() {}

	public static void main(String[] args)
	{
		System.out.println(Instant.now().toString().replace(':', '.'));
	}

	public static Logger newFileLogger(Optional<Path> filePath, boolean append) throws IOException
	{
		final var path = filePath.orElseGet(() ->
		{
			try
			{
				return Files.createTempFile(null, null);
			}
			catch (IOException e)
			{
				throw new UncheckedIOException(e);
			}
		});

		if (!path.isAbsolute() || path.getNameCount() == 0)
		{
			throw new IllegalArgumentException("The supplied path is invalid.");
		}

		final var handler = new FileHandler(path.toString(), append);
		final var logger = Logger.getLogger(path.toString());
		handler.setFormatter(new SimpleFormatter());
		logger.addHandler(handler);
		logger.setUseParentHandlers(false);
		return logger;
	}
}