import org.junit.jupiter.api.Test;

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
        testStory.editNodeChildren(1,2,3);
        assertEquals("additional content for another node", testStory.getRoot().getNext(3).getStoryContent());
        assertEquals("more content", testStory.getRoot().getNext(2).getStoryContent());
        assertThrows(IllegalArgumentException.class, ()-> testStory.editNodeChildren(2,1,2));
        assertThrows(IllegalArgumentException.class, ()-> testStory.editNodeChildren(1, 5, 2));
        assertThrows(IllegalArgumentException.class, ()-> testStory.editNodeChildren(1, 2, 5));
    }

    @Test
    void addNodeTest(){
        Story testStory = new Story(1,"Story", "This is test content for the root");
        testStory.addNode("Content for the first child", 1, 1, "First choice");
        testStory.addNode("additional content for another node", 1, 2, "Second choice");
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

    @Test
    void addVariableTest(){
        Story testStory = new Story(1,"Story", "This is test content for the root");
        assertEquals(0, testStory.variables.getSize());

        //adding strings
        testStory.variables.addString("Health", "Full");
        assertEquals(1, testStory.variables.variables.size());
        assertEquals("Full", testStory.variables.variables.get("Health"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.getVariable("Life"));

        testStory.variables.addString("Life", "Max");
        assertEquals(2, testStory.variables.variables.size());
        assertEquals("Max", testStory.variables.getVariable("Life"));

        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addString("Health", "Low"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addString("Life", "Min"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addString("", "Low"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addString(" ", "Min"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addString("                            ", "Min"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addString("Power", ""));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addString("Power", " "));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addString("Power", "                          "));

        //adding ints
        testStory.variables.addInt("H", 10);
        assertEquals(10, testStory.variables.variables.get("H"));

        testStory.variables.addInt("L", 0);
        assertEquals(0, testStory.variables.variables.get("L"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addInt("E", -1));

        testStory.variables.addInt("E", 100);
        assertEquals(100, testStory.variables.variables.get("E"));

        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addInt("E", 50));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addInt("L", 50));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addInt("H", 50));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addInt("", 50));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addInt(" ", 50));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.addInt("                   ", 50));
    }

    @Test
    void getVariableTest(){
        Story testStory = new Story(1,"Story", "This is test content for the root");
        assertEquals(0, testStory.variables.getSize());

        testStory.variables.addString("Health", "Full");
        assertEquals("Full", testStory.variables.getVariable("Health"));
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.getVariable("Life"));

        testStory.variables.addString("Life", "Max");
        assertEquals("Max", testStory.variables.getVariable("Life"));

        testStory.variables.addInt("Power", 10);
        assertEquals(10, testStory.variables.getVariable("Power"));
    }

    @Test
    void removeVariableTest(){
        Story testStory = new Story(1,"Story", "This is test content for the root");
        assertEquals(0, testStory.variables.getSize());

        testStory.variables.addInt("Health", 10);
        assertEquals(10, testStory.variables.variables.get("Health"));

        testStory.variables.addString("Life", "Max");
        assertEquals("Max", testStory.variables.variables.get("Life"));
        assertEquals(2, testStory.variables.variables.size());

        testStory.variables.removeVariable("Health");
        assertEquals(1, testStory.variables.variables.size());
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.getVariable("Health"));

        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.removeVariable("Power"));

        testStory.variables.removeVariable("Life");
        assertEquals(0, testStory.variables.variables.size());
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.getVariable("Life"));
    }

    @Test
    void clearVariablesTest(){
        Story testStory = new Story(1,"Story", "This is test content for the root");
        assertEquals(0, testStory.variables.getSize());

        testStory.variables.addInt("Health", 10);
        testStory.variables.addString("Life", "Max");
        testStory.variables.addInt("Level", 0);
        assertEquals(3, testStory.variables.variables.size());

        testStory.variables.clearVariables();
        assertEquals(0, testStory.variables.variables.size());

        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.clearVariables());
    }

    @Test
    void editVariablesTest(){
        Story testStory = new Story(1,"Story", "This is test content for the root");
        assertEquals(0, testStory.variables.getSize());
        testStory.variables.addInt("Health", 10);
        testStory.variables.addString("Life", "Max");
        testStory.variables.addInt("Level", 0);
        assertEquals(3, testStory.variables.variables.size());
        assertEquals(10, testStory.variables.getVariable("Health"));
        assertEquals("Max", testStory.variables.getVariable("Life"));
        assertEquals(0, testStory.variables.getVariable("Level"));

        testStory.variables.editVariable("Health", 5);
        testStory.variables.editVariable("Life", "Min");
        testStory.variables.editVariable("Level", 5);

        assertEquals(3, testStory.variables.variables.size());
        assertEquals(5, testStory.variables.getVariable("Health"));
        assertEquals("Min", testStory.variables.getVariable("Life"));
        assertEquals(5, testStory.variables.getVariable("Level"));

        //changing string to int value
        testStory.variables.editVariable("Life", 10);
        assertEquals(10, testStory.variables.getVariable("Life"));

        //changing int to string value
        testStory.variables.editVariable("Health", "Full");
        testStory.variables.editVariable("Level", "One");

        assertEquals("Full", testStory.variables.getVariable("Health"));
        assertEquals("One", testStory.variables.getVariable("Level"));

        //nonexistent variable
        assertThrows(IllegalArgumentException.class, ()-> testStory.variables.editVariable("Energy", 5));

    }



}
