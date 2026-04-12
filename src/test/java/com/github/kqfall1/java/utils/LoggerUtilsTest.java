package com.github.kqfall1.java.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.Optional;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class LoggerUtilsTest
{
    private void _getFileLoggerTest(Logger logger)
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
    public void getFileLoggerTest() throws IOException
    {
        var logger = LoggerUtils.getFileLogger(Optional.empty(), false);
        _getFileLoggerTest(logger);

        logger = LoggerUtils.getFileLogger(Optional.of(Path.of(System.getProperty("java.io.tmpdir"), Instant.now().toString().replace(':', '.'))), true);
        _getFileLoggerTest(logger);
    }
}