import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
    @Test
    void constructorTest() throws IllegalArgumentException{
        //working root node
        Node testNode = new Node(1, "testing");
        assertEquals("testing", testNode.getStoryContent());

        //check throws exception if id is not a positive number
        assertThrows(IllegalArgumentException.class, ()-> new Node(-1, "testing"));

        //regular node with parent
        Node testNode2 = new Node(2, "testing2", testNode, 1);

        //check throws exception if id is not a positive number
        assertThrows(IllegalArgumentException.class, ()-> new Node(-1, "testing", testNode, 1));

        //check throws exception if parent node is null
        assertThrows(IllegalArgumentException.class, ()-> new Node(-1, "testing", null, 2));

        //check throws exception if choiceValue is not a positive number
        assertThrows(IllegalArgumentException.class, ()-> new Node(-1, "testing", testNode, 1));
    }

    @Test
    void getNextTest(){

    }

    @Test
    void editNodeTest(){

    }
}
