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
        Node testNode2 = new Node(2, "testing2", testNode);

        //check throws exception if id is not a positive number
        assertThrows(IllegalArgumentException.class, ()-> new Node(-1, "testing", testNode));

        //check throws exception if parent node is null
        assertThrows(IllegalArgumentException.class, ()-> new Node(-1, "testing", null));

        //check throws exception if choiceValue is not a positive number
        assertThrows(IllegalArgumentException.class, ()-> new Node(-1, "testing", testNode));
    }

    @Test
    //there's no way to test this because the maps are set to null by default
    void checkConditionExistsTest(){
        //check if condition does not exist

        //check if condition does not exist when there are multiple conditions

        //check if condition already exists in parent

    }

    @Test
    void getNextTest(){
        //sets a node as a child of the root node
        Node rootNode = new Node(1, "root node");
        Node childNode = new Node(2, "child node 1", rootNode);
        rootNode.setChild(1, childNode);
        assertEquals("child node 1", rootNode.getNext(1).getStoryContent());

        //sets a node as a child of another child node
        Node childNode2 = new Node(3, "child node 2", childNode);
        childNode.setChild(1, childNode2);
        assertEquals("child node 2", childNode.getNext(1).getStoryContent());

        //check the new node is still linked to parent
        assertEquals("child node 2", rootNode.getNext(1).getNext(1).getStoryContent());

        //sets another node as a child of the same node
        Node childNode3 = new Node(4, "child node 3", childNode);
        childNode.setChild(2, childNode3);
        assertEquals("child node 3", childNode.getNext(2).getStoryContent());

        //check the new node is still linked to parent
        assertEquals("child node 3", rootNode.getNext(1).getNext(2).getStoryContent());

        //should throw exception if a node is already set for the same condition
        Node childNode4 = new Node(5, "child node 4", childNode);
        assertThrows(IllegalArgumentException.class, ()-> childNode.setChild(1, childNode4));

        //check throws exception if condition is not a positive number
        assertThrows(IllegalArgumentException.class, ()-> childNode.setChild(-4, childNode4));

        //check throws exception if child node is null
        assertThrows(IllegalArgumentException.class, ()-> childNode.setChild(3, null));
    }

    @Test
    void editNodeTest(){

    }
}
