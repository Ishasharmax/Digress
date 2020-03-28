import org.junit.jupiter.api.Test;

import java.util.LinkedList;

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
