package com.tests.selenium.tests.fileTests;

import com.app.selenium.fileUtils.FileOperations;
import org.testng.annotations.Test;

import java.io.IOException;

public class LearnFiles {

    @Test
    public void verifyFileCreation() throws IOException {
        FileOperations.createFile();
    }

    @Test
    public void verifyFileContent() throws IOException {
        FileOperations.readFileFromFolder();
    }

}
