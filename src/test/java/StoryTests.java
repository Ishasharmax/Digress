import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.lang.String;
import static org.junit.jupiter.api.Assertions.*;

public class StoryTests {

    @Test
    void constructorTest(){
        LinkedList<String> tags = new LinkedList<>();
        tags.add("adventure");
        Story testStory = new Story(0, "Title", "Root content", tags);
        assertEquals(0, testStory.getID());
        assertEquals("Title", testStory.getTitle());
        assertFalse(testStory.getRoot() == null);
        assertEquals("Root content", testStory.getRoot().getStoryContent());
        assertEquals("adventure", testStory.getTags().getFirst());
    }

    @Test
    void getNextTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(0, "adventure");
        testTags.add(1, "strategy");
        Story testStory = new Story(1,"Story", "This is test content for the root", testTags);
        testStory.addNode("Content for the first child", 1, 1, "First choice");
        testStory.addNode("additional content for another node", 1, 2, "Second choice");
        testStory.addNode("more content", 1, 3, "Third choice");
        Node testNode1 = testStory.getNext(1);
        assertEquals("Content for the first child", testNode1.getStoryContent());
        Node testNode2 = testStory.getNext(2);
        assertEquals("additional content for another node", testNode2.getStoryContent());
        Node testNode3 = testStory.getNext(3);
        assertEquals("more content", testNode3.getStoryContent());
        assertThrows(IllegalArgumentException.class, ()-> testStory.getNext(4));

    }

    @Test
    void editNodeStoryContentTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(0, "adventure");
        testTags.add(1, "strategy");
        Story testStory = new Story(0,"Story", "This is test content for the root", testTags);
        testStory.addNode("Content for the first child", 1, 1, "First choice");
        testStory.addNode("additional content for another node", 2, 1, "First choice");
        testStory.editNodeStoryContent(3, "this is the changed story content");
        assertEquals("this is the changed story content", testStory.findNode(3).getStoryContent());
        assertEquals("This is test content for the root", testStory.getRoot().getStoryContent());
        assertEquals("Content for the first child", testStory.findNode(2).getStoryContent());
        assertFalse(testStory.findNode(2).getStoryContent().contentEquals("additional content for another node"));

        assertThrows(IllegalArgumentException.class, ()-> testStory.editNodeStoryContent(4, "valid story content"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.editNodeStoryContent(2, ""));
        assertThrows(IllegalArgumentException.class, ()-> testStory.editNodeStoryContent(2, " "));
    }

    @Test
    void editNodeChildrenTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(0, "adventure");
        testTags.add(1, "strategy");
        Story testStory = new Story(1,"Story", "This is test content for the root", testTags);
        testStory.addNode("Content for the first child", 1, 1, "First choice");
        testStory.addNode("additional content for another node", 1, 2, "Second choice");
        testStory.addNode("more content", 1, 3, "Third choice");
        testStory.editNodeChildren(1,2,3);
        assertEquals("additional content for another node", testStory.getRoot().getNext(3).getStoryContent());
        assertEquals("more content", testStory.getRoot().getNext(2).getStoryContent());
        assertThrows(IllegalArgumentException.class, ()-> testStory.editNodeChildren(2,1,2));
        assertThrows(IllegalArgumentException.class, ()-> testStory.editNodeChildren(1, 5, 2));
        assertThrows(IllegalArgumentException.class, ()-> testStory.editNodeChildren(1, 2, 5));
    }

    @Test
    void addNodeTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(0, "adventure");
        testTags.add(1, "strategy");
        Story testStory = new Story(1,"Story", "This is test content for the root", testTags);
        testStory.addNode("Content for the first child", 1, 1, "First choice");
        testStory.addNode("additional content for another node", 1, 2, "Second choice");

        assertThrows(IllegalArgumentException.class, ()-> testStory.addNode("test content", 0, 4, "test choice"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.addNode("", 1, 4, "test choice"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.addNode(" ", 1, 4, "test choice"));
    }

    @Test
    void deleteNodeTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(1, "adventure");
        testTags.add(2, "comedy");
        Story testStory1 = new Story(1,"Story", "This is test content for the root", testTags);
        testStory1.addNode("[2] Content for the first child", 1,1,"1C");
        testStory1.addNode("[3] Content for the first child", 1,2,"2C");
        testStory1.addNode("[4] Content for the second child", 2,1,"1C");
        testStory1.addNode("[5] Content for the second child", 2,2,"2C");
        testStory1.addNode("[6] Content for the second child", 2,3,"3C");

//        //test delete node without child nodes
//        testStory1.deleteNode(5);
//        assertEquals(null,testStory1.findNode(5));
//        //test delete parent node
//        testStory1.deleteNode(2);
//        assertEquals(null,testStory1.findNode(2));
//        assertEquals(4,testStory1.findNode(4));
//        assertEquals(6,testStory1.findNode(6));
//
//        //test delete unkind node
//        assertThrows(IllegalArgumentException.class,()-> testStory1.deleteNode(12));

    }

    @Test
    void findNodeTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(0, "adventure");
        testTags.add(1, "comedy");
        Story testStory1 = new Story(1,"Story", "This is test content for the root", testTags);
        testStory1.addNode("Content for the first child", 1, 1, "First choice");
        testStory1.addNode("additional content for another node", 1, 2, "Second choice");
        testStory1.addNode("more content", 1, 3, "Third choice");
        testStory1.findNode(2);
    }

    @Test
    void printCurrentNodeTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(0, "adventure");
        Story testStory2 = new Story(1,"Story", "This is test content for the root", testTags);
        testStory2.addNode("Content for the first child", 1, 1, "First choice");
        testStory2.printCurrentNode();

        testTags.add(1, "horror");
        testStory2.addNode("additional content for another node", 1, 2, "Second choice");
        testStory2.printCurrentNode();
    }

}
