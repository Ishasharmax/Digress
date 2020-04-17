import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.lang.String;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class JsonTestClass {


    @Test
    void story1Tests(){

    }

    @Test
    void story2Tests() throws IllegalArgumentException, IOException {
        //create a story from Json file
        Story testStory = JsonUtil.fromJsonFile("src/test/ex2.json", Story.class);

        //check the id, title and root were stored correctly
        assertEquals(3, testStory.getID());
        assertEquals("Story3", testStory.getTitle());

        Node rootNode = testStory.getRoot();
        assertEquals(1, rootNode.getId());
        assertEquals("Content2", rootNode.getStoryContent());

        //load in a node from Json file and check it was stored properly
        Node node2 = JsonUtil.fromJsonFile("src/test/story2node2.json", Node.class);
        assertEquals(2, node2.getId());
        assertEquals("This is a test node", node2.getStoryContent());
        assertEquals(rootNode.getId(), node2.getParentNode().getId());

        //add node to story and make sure it was added properly
        testStory.addExistingNode(node2);
        assertEquals(2, testStory.getCurrNode().getId());
        assertEquals("This is a test node", testStory.getCurrNode().getStoryContent());
        assertEquals(rootNode.getId(), testStory.getCurrNode().getId());

        //load in another node and check it was loaded properly
        Node node3 = JsonUtil.fromJsonFile("src/test/story2node3.json", Node.class);
        assertEquals(3, node3.getId());
        assertEquals("testNode3", node3.getStoryContent());
        assertEquals(rootNode.getId(), node3.getParentNode().getId());

        //add node to story and check it was added properly
        testStory.addExistingNode(node3);
        assertEquals(3, testStory.getCurrNode().getId());
        assertEquals(rootNode.getId(), testStory.getCurrNode().getId());

        //make sure node 2 still exists
        assertEquals("This is a test node", testStory.findNode(2).getStoryContent());

        //add another node and check
        Node node4 = JsonUtil.fromJsonFile("src/test/story2node4.json", Node.class);
        assertEquals(4, node4.getId());
        assertEquals("testNode4", node4.getStoryContent());
        assertEquals(node3.getId(), node4.getParentNode().getId());

        //add to story
        testStory.addExistingNode(node4);
        assertEquals(4, testStory.getCurrNode().getId());
        assertEquals(node3.getId(), testStory.getCurrNode().getId());

        //check all of the other nodes are still present
        assertEquals("Content2", testStory.findNode(1).getStoryContent());
        assertEquals("This is a test node", testStory.findNode(2).getStoryContent());
        assertEquals("testNode3", testStory.findNode(3).getStoryContent());

        //check the nodes are properly linked
        assertEquals("Content2", testStory.findNode(4).getParentNode().getParentNode().getStoryContent());

        //add another node with the same id as an existing one
        Node node5 = JsonUtil.fromJsonFile("src/test/story2node5.json", Node.class);
        assertEquals(4, node5.getId());
        assertEquals("Repeat node", node5.getStoryContent());
        assertEquals(rootNode.getId(), node5.getParentNode().getId());

        //should throw an exception if added to story
        assertThrows(IllegalArgumentException.class, ()-> testStory.addExistingNode(node5));

        //check current node is still node4
        assertEquals("testNode4", testStory.getCurrNode().getStoryContent());
    }

    @Test
    void story3Tests(){

    }
}
