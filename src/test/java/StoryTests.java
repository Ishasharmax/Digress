import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.lang.String;
import static org.junit.jupiter.api.Assertions.*;

public class StoryTests {

    @Test
    void constructorTest(){

    }

    @Test
    void getNextTest(){

    }

    @Test
    void editNodeStoryContentTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(1, "adventure");
        testTags.add(2, "strategy");
        Story testStory = new Story(1,"Story", "This is test content for the root", testTags);
        testStory.addNode("Content for the first child", 1);
        testStory.addNode("additional content for another node", 1);
        testStory.editNodeStoryContent(2, "this is the changed story content");
        assertEquals("this is the changed story content", testStory.findNode(2).getStoryContent());
        assertEquals("This is test content for the root", testStory.getRoot().getStoryContent());
        assertEquals("Content for the first child", testStory.findNode(1).getStoryContent());
        assertFalse(testStory.findNode(2).getStoryContent().contentEquals("additional content for another node"));

    }

    @Test
    void addNodeTest(){

    }

    @Test
    void deleteNodeTest(){

    }

    @Test
    void findNodeTest(){

    }

    @Test
    void printCurrentNodeTest(){

    }

    @Test
    void getRootTest(){

    }

    @Test
    void getStoryNodesTest(){

    }
}
