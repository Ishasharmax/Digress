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

        //check file name
        assertEquals("testFile",test1.getFileName());

        //check file path
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
        //check the parent root
        assertEquals(1,test1.importFile().getID());
        assertEquals("testFile",test1.importFile().getTitle());
        assertEquals("You are walking down a dark, dingy hallway. Where would you like to go?",test1.importFile().getRootContent());

        //check how many children
        assertEquals(10,test1.importFile().getChoiceVal());

        //check child
        //key
        assertEquals("Continue straight",test1.importFile().findNode(2).getStoryContent());
        assertEquals("Take a left",test1.importFile().findNode(3).getStoryContent());
        assertEquals("test ten",test1.importFile().findNode(11).getStoryContent());

        //choice value
        assertEquals("test ten",test1.importFile().getRoot().getNext(10).getStoryContent());
        assertEquals("test seven",test1.importFile().getRoot().getNext(7).getStoryContent());
        assertEquals("test four",test1.importFile().getRoot().getNext(4).getStoryContent());

        //check empty file
        assertThrows(FileNotFoundException.class, ()-> new storyFile("test_file","/main/java/test_file.txt").checkPath());
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
    public void outputFileTest() throws IOException,IllegalArgumentException {
        //create a story
        Story testStory = new Story(1,"Story", "This is test content for the root");
        testStory.addNode("Content for the first child", 1, 1, "First choice");
        testStory.addNode("additional content for another node", 1, 2, "Second choice");
        testStory.addNode("more content", 1, 3, "Third choice");

        //create a story file
        storyFile test1 = new storyFile("outputFile","src/main/java/testFilePackage/outputFile.txt");
        test1.outputFile(testStory);

        //check if the file already exist
        storyFile test2 = new storyFile("repeatFile","src/main/java/testFilePackage/repeatFile.txt");
        assertThrows(IllegalArgumentException.class, ()-> test2.outputFile(testStory));
    }
    @Test
    public void editFileTest(){

    }

}
