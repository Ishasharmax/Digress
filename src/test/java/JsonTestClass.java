import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.lang.String;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.core.JsonProcessingException;


public class JsonTestClass {


    @Test
    void story1Tests() throws IOException {

        //importing a Story object
        Story story1 = JsonUtil.fromJsonFile("src/test/Story1.json", Story.class);
        //checking ID
        assertEquals(5, story1.getID());
        //checking title
        assertEquals("Story", story1.getTitle());
        //checking root content
        assertEquals("Content", story1.getRootContent());
        //checking tags
        assertEquals("Adventure", story1.getTags().getFirst());

        //importing node
        Node node1 = JsonUtil.fromJsonFile("src/test/s1n1.json", Node.class);
        //checking Node getters
        assertEquals(2, node1.getId());
        assertEquals("Content for first node in S1", node1.getStoryContent());

        //adding node to story
        story1.addExistingNode(node1);
        //set node as child of root
        story1.getRoot().setChild(1, "Choice 1", node1);
        assertEquals("Content for first node in S1", story1.getRoot().getNext(1).getStoryContent());
        assertEquals(2, story1.getRoot().getNext(1).getId());

        //adding another child to the root
        Node node2 = JsonUtil.fromJsonFile("src/test/s1n2.json", Node.class);
        //checking Node getters
        assertEquals(3, node2.getId());
        assertEquals("Content for second node in S1", node2.getStoryContent());
        story1.addExistingNode(node2);
        story1.getRoot().setChild(2,"Choice 2", node2);
        assertEquals("Content for second node in S1", story1.getRoot().getNext(2).getStoryContent());
        assertEquals(3, story1.getRoot().getNext(2).getId());

        //editing node
        story1.editNodeStoryContent(3, "New content");
        assertEquals("New content", story1.findNode(3).getStoryContent());

        //adding another child
        Node node3 = JsonUtil.fromJsonFile("src/test/s1n3.json", Node.class);
        //getters
        assertEquals(4, node3.getId());
        assertEquals("Content for third node in S1", node3.getStoryContent());
        story1.addExistingNode(node3);
        story1.getRoot().setChild(3,"Choice 3", node3);
        assertEquals("Content for third node in S1", story1.getRoot().getNext(3).getStoryContent());
        assertEquals(4, story1.getRoot().getNext(3).getId());
        //deleting the node
        story1.deleteNode(4);
        assertThrows(IllegalArgumentException.class, ()-> story1.findNode(4));
        assertThrows(IllegalArgumentException.class, ()-> story1.getRoot().getNext(3));

        //adding node to child node
        Node node4 = JsonUtil.fromJsonFile("src/test/s1n4.json", Node.class);
        //getters
        assertEquals(5, node4.getId());
        assertEquals("Content for fourth node in S1", node4.getStoryContent());
        story1.addExistingNode(node4);
        story1.getRoot().getNext(1).setChild(1, "Choice 1", node4);
        assertEquals(5, story1.getRoot().getNext(1).getId());
        assertEquals("Content for fourth node in S1", story1.getRoot().getNext(1).getStoryContent());

        //adding invalid node to story
        Node node5 = JsonUtil.fromJsonFile("src/test/s1n5.json", Node.class);
        //getters
        assertEquals(2, node5.getId());
        assertEquals("This node's ID already exists, shouldn't be added", node5.getStoryContent());
        //adding
        assertThrows(IllegalArgumentException.class, ()-> story1.addExistingNode(node5));
        
    }

    @Test
    void story2Tests(){

    }

    @Test
    void story3Tests(){

    }
}
