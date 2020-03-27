import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class StoryTests {

    @Test
    void constructorTest(){

    }

    @Test
    void getNextTest(){

    }

    @Test
    void editNodeTest(){
        LinkedList<String> testTags = new LinkedList<>();
        testTags.add(1, "adventure");
        testTags.add(2, "strategy");
        Story testStory = new Story(1,"Story", "This is test content for the root", testTags);
        testStory.addNode("Content for the first child", 1);
        testStory.addNode("additional content for another node", 1);
        testStory.editNode(2);

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
