package com.cybersecurity.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for file operations
 */
public class FileUtils {

    /**
     * Get a path from a list of strings
     * @param paths list of strings, which will be joined by the file separator
     * @return Path
     */
    public static Path getPath(String... paths) {
        return Paths.get(String.join(File.separator, paths));
    }

}
