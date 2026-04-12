package com.github.kqfall1.java.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.Instant;

public final class LoggerUtilsTest
{
    private void _newFileLoggerTest(Logger logger)
    {
        final var logFile = Path.of(logger.getName()).toFile();
        long previousLogFileLength;

        for (int count = 0; count < 200; count++)
        {
            previousLogFileLength = logFile.length();
            logger.info("Something\n");
            Assertions.assertTrue(logFile.length() > previousLogFileLength);
        }
    }

    @Test
    public void newFileLoggerTest() throws IOException
    {
        var logger = LoggerUtils.newFileLogger(Optional.empty(), false);
        _newFileLoggerTest(logger);

        logger = LoggerUtils.newFileLogger(Optional.of(Path.of(System.getProperty("java.io.tmpdir"), Instant.now().toString().replace(':', '.'))), true);
        _newFileLoggerTest(logger);
    }
}