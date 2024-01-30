package com.app.selenium.fileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileOperations {

    private static final String filePath_Root = "MyFile.txt";
    private static final String filePath_Folder = "filesFolder";
    public static void createFile() throws IOException {
        File fileFolder = new File(filePath_Folder);
        File file_root = new File(filePath_Root);


        if( !fileFolder.exists() && !file_root.exists() ){
            fileFolder.mkdirs();
            file_root.createNewFile();
        }
    }

    public static void readFileFromFolder() throws IOException {
        File file = new File(filePath_Root);
        FileInputStream fileInputStream = new FileInputStream(file);
        while(fileInputStream.read()!= -1){
            System.out.print(fileInputStream.read());
        }
    }
}
