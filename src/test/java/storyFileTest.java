import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.lang.String;
import static org.junit.jupiter.api.Assertions.*;

public class storyFileTest {
    @Test
    public void storyFileTest() throws IllegalArgumentException {
        storyFile test1 = new storyFile("testFile","src/main/java/testFilePackage/testFile.txt");
        assertEquals("testFile",test1.getFileName());
        assertEquals("src/main/java/testFilePackage/testFile.txt",test1.getPath());
    }

    @Test
    public void checkPathTest() throws IOException {
        //check exception error
        assertThrows(FileNotFoundException.class, ()-> new storyFile("testFile","/main/java/falseFile.txt").checkPath());
    }
    @Test
    public void importFileTest() throws IOException {
        storyFile test1 = new storyFile("testFile","src/main/java/testFilePackage/testFile.txt");
        test1.importFile();

    }
    @Test
    public void outputFileTest(){

    }
    @Test
    public void deleteFileTest() throws IOException {
        //create a random file for test
        File deleteFile = new File("src/main/java/testFilePackage/deleteFile.txt");
        deleteFile.createNewFile();
        //delete the file
        storyFile test1 = new storyFile("deleteFile","src/main/java/testFilePackage/deleteFile.txt");
        test1.deleteFile();
        //check if the file is still exist
        assertThrows(FileNotFoundException.class, ()-> new storyFile("deleteFile","/main/java/deleteFile.txt").checkPath());

    }
    @Test
    public void editFileTest(){

    }
}
