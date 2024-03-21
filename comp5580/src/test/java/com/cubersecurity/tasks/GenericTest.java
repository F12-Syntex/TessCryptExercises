package com.cubersecurity.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.cybersecurity.utils.FileUtils;

public abstract class GenericTest {

    protected enum TestType {
        TESS("tess.txt"), TESS26("tess26.txt"), TESS27("tess27.txt");

        private String fileName;

        TestType(String fileName) {
            this.fileName = fileName;
        }

        /**
         * Get the file name of the text type
         * @return
         */
        public String getFileName() {
            return this.fileName;
        }
    }

    /**
     * Read the file content of the given test type
     * 
     * @param type the test type to read
     * @return the file content
     */
    public String readFile(TestType type) {
        Path path = FileUtils.getPath("res", "inputfiles", type.getFileName());
        try {
            String fileContent = new String(Files.readAllBytes(path));
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
