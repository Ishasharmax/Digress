import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.lang.String;
import static org.junit.jupiter.api.Assertions.*;

public class StoryTests {

    @Test
    void constructorTest(){
        Story testStory = new Story(0, "Title", "Root content");
        assertEquals(0, testStory.getID());
        assertEquals("Title", testStory.getTitle());
        assertFalse(testStory.getRoot() == null);
        assertEquals("Root content", testStory.getRoot().getStoryContent());
        assertEquals("Root content", testStory.getCurrNode().getStoryContent());
    }

    @Test
    void getNextTest(){
        Story testStory = new Story(1,"Story", "This is test content for the root");
        testStory.addNode("Content for the first child", 1, 1, "First choice");
        testStory.addNode("additional content for another node", 1, 2, "Second choice");
        testStory.addNode("more content", 1, 3, "Third choice");
        testStory.setCurrentNode(testStory.getRoot());
        Node testNode1 = testStory.getNext(1);
        assertEquals("Content for the first child", testNode1.getStoryContent());
        testStory.setCurrentNode(testStory.getRoot());
        Node testNode2 = testStory.getNext(2);
        assertEquals("additional content for another node", testNode2.getStoryContent());
        testStory.setCurrentNode(testStory.getRoot());
        Node testNode3 = testStory.getNext(3);
        assertEquals("more content", testNode3.getStoryContent());
        testStory.setCurrentNode(testStory.getRoot());
        assertThrows(IllegalArgumentException.class, ()-> testStory.getNext(4));

    }


    @Test
    void editNodeStoryContentTest(){
        Story testStory = new Story(0,"Story", "This is test content for the root");
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
        Story testStory = new Story(1,"Story", "This is test content for the root");
        testStory.addNode("Content for the first child", 1, 1, "First choice");
        testStory.addNode("additional content for another node", 1, 2, "Second choice");
        testStory.addNode("more content", 1, 3, "Third choice");
        testStory.editNodeChildren(1, 2, "delete");
        assertEquals(2, testStory.nodeConnections.get(1).size());
        assertEquals("more content", testStory.findNode(testStory.nodeConnections.get(1).get(1)).getStoryContent());
    }

    @Test
    void addNodeTest(){
        Story testStory = new Story(1,"Story", "This is test content for the root");
        testStory.addNode("Content for the first child", 1, 1, "First choice");
        testStory.addNode("additional content for another node", 1, 2, "Second choice");
        assertEquals(2, testStory.nodeConnections.get(1).size());
        assertEquals("Content for the first child", testStory.findNode(testStory.nodeConnections.get(1).get(0)).getStoryContent());
        assertEquals("additional content for another node", testStory.findNode(testStory.nodeConnections.get(1).get(2)).getStoryContent());
        assertThrows(IllegalArgumentException.class, ()-> testStory.addNode("test content", 0, 4, "test choice"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.addNode("", 1, 4, "test choice"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.addNode(" ", 1, 4, "test choice"));
    }

    @Test
    void deleteNodeTest(){
        Story testStory = new Story(1,"Story", "This is test content for the root");
        testStory.addNode("【2】", 1, 1, "First choice");
        testStory.addNode("【3】", 1, 2, "Second choice");
        testStory.addNode("【4】", 1, 3, "Third choice");
        testStory.addNode("【5】", 1, 4, "Fourth choice");
        testStory.addNode("【6】", 1, 5, "Five choice");

        //test if there's 6 nodes
        assertTrue(testStory.getStoryNodes().size()==6);

        //test delete node 2
        assertFalse(testStory.findNode(2)==null);
        testStory.deleteNode(2);

        //test if node 2 not exist and throw error
        assertEquals(null, testStory.findNode(2));

        //test if other node is exist
        assertFalse(testStory.findNode(3)==null);
        assertFalse(testStory.findNode(4)==null);
        assertFalse(testStory.findNode(5)==null);
        assertFalse(testStory.findNode(6)==null);

        //test delete unknown node
        assertThrows(IllegalArgumentException.class,()-> testStory.deleteNode(12));
    }

    @Test
    void findNodeTest(){
        Story testStory1 = new Story(1,"Story", "This is test content for the root");
        testStory1.addNode("Content for the first child", 1, 1, "First choice");
        testStory1.addNode("additional content for another node", 1, 2, "Second choice");
        testStory1.addNode("more content", 1, 3, "Third choice");
        testStory1.findNode(2);
    }

    @Test
    void printCurrentNodeTest(){
        Story testStory2 = new Story(1,"Story", "This is test content for the root");
        testStory2.addNode("Content for the first child", 1, 1, "First choice");
        testStory2.printCurrentNode();

        testStory2.addNode("additional content for another node", 1, 2, "Second choice");
        testStory2.printCurrentNode();
    }


//    @Test
//    void exportTest() throws IOException {
//        Story testStory = new Story(1,"Story Title", "This is test content for the root");
//        testStory.addNode("Content for the first child", 1, 1, "First choice");
//        testStory.addNode("additional content for another node", 1, 2, "Second choice");
//        testStory.editNodeStoryContent(2, "new content");
//        testStory.addNode("content", 1, 3, "Third choice");
//        testStory.deleteNode(4);
//        testStory.addVariable("Health", "int", 5);
//        testStory.exportStory("src/main/java");
//    }


}
