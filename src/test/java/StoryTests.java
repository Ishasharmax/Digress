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
    void editNodeChildrenTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(1, "adventure");
        testTags.add(2, "strategy");
        Story testStory = new Story(1,"Story", "This is test content for the root", testTags);
        testStory.addNode("Content for the first child", 1);
        testStory.addNode("additional content for another node", 1);
        testStory.addNode("more content", 1);
        testStory.editNodeChildren(1,2,3);
        assertEquals("additional content for another node", testStory.getRoot().getNext(3).getStoryContent());
        assertEquals("more content", testStory.getRoot().getNext(2).getStoryContent());
        assertThrows(IllegalArgumentException.class, ()-> testStory.editNodeChildren(2,1,2));
    }

    @Test
    void addNodeTest(){

    }

    @Test
    void deleteNodeTest(){

    }

    @Test
    void findNodeTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(1, "adventure");
        testTags.add(2, "comedy");
        Story testStory1 = new Story(1,"Story", "This is test content for the root", testTags);
        testStory1.addNode("Content for the first child", 1);
        testStory1.addNode("additional content for another node", 1);
        testStory1.findNode(2);
    }

    @Test
    void printCurrentNodeTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(1, "adventure");
        Story testStory2 = new Story(1,"Story", "This is test content for the root", testTags);
        testStory2.addNode("Content for the first child", 1);
        testStory2.printCurrentNode();

        testTags.add(2, "horror");
        testStory2.addNode("additional content for another node", 1);
        testStory2.printCurrentNode();
    }

    @Test
    void getRootTest(){

    }

    @Test
    void getStoryNodesTest(){

    }
}
