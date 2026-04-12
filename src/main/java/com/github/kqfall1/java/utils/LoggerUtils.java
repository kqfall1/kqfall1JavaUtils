package com.github.kqfall1.java.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

	/**
	 * Provides a {@code Logger} with a pre-configured {@code FileHandler}.
	 *
	 * <p>The returned {@code Logger} object's {@code name} will be the absolute {@code Path} of the associated log {@code File}
	 * created by this method. Be advised that passing the absolute {@code Path} of the log {@code File} of an existing
	 * {@code Logger} created by this method will return the same {@code Logger} instance.</p>
	 * @param filePath The intended log {@code File} object's associated {@code Path}. The OS's temporary directory will
	 *                 have a log {@code File} created with an arbitrary name if {@code Optional.empty()} is passed.
	 * @param append Whether the {@code Logger} object's {@code FileHandler} should append text to the log {@code File}
	 *               object's existing data.
	 * @return A {@code Logger} with a pre-configured {@code FileHandler}.
	 * @throws IOException If the temporary directory does not exist.
	 */
	public static Logger getFileLogger(Optional<Path> filePath, boolean append) throws IOException
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

		final var logger = Logger.getLogger(path.toString());

		if (logger.getHandlers().length == 0)
		{
			final var handler = new FileHandler(path.toString(), append);
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
			logger.setUseParentHandlers(false);
		}

		return logger;
	}
}