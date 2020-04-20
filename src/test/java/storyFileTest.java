import org.junit.Assert;
import org.junit.jupiter.api.Test;

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
    public void removeFileTest(){

    }
    @Test
    public void editFileTest(){

    }
}
