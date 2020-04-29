import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class newDeleteTests {
    @Test
    void newDeleteNodeTests(){
        //Based on deleting nodes
        Story testStory1 = new Story(1,"Story", "This is test content for the root");
        testStory1.addNode("Test Content 1", 1, 1, "First choice");
        testStory1.addNode("Test Content for another Node", 1, 2, "Second choice");
        testStory1.addNode("Woah more test content", 1, 3, "Third choice");
        testStory1.addNode("Delete Me", 1, 4, "Fourth choice");
        testStory1.addNode("I'm a child of 6, should be deleted", 1, 5, "Fifth choice");
        testStory1.addNode("I'm a child of 6, should also be deleted", 1, 6, "Sixth choice");
        //Testing if size is correctly 7
        assertEquals(testStory1.getStoryNodes().size(),7);
        //Find the necessary node to delete
        testStory1.findNode(5);
        //Deleting Node and should delete children
        testStory1.deleteNode(5);


        //Based on relinking nodes
        Story testStory2 = new Story(1,"Story", "This is test content for the root");
        testStory2.addNode("Test Content 1", 1, 1, "First choice");
        testStory2.addNode("Test Content for another Node", 1, 2, "Second choice");
        testStory2.addNode("Woah more test content", 1, 3, "Third choice");
        testStory2.addNode("Delete Me", 1, 4, "Fourth choice");
        testStory2.addNode("I'm a child of 6, but i should be relinked to 2", 1, 5, "Fifth choice");
        testStory2.addNode("I'm a child of 6, but i should be relinked to 3", 1, 6, "Sixth choice");
        //Testing if size is correctly 7
        assertEquals(testStory1.getStoryNodes().size(),7);
        //Find the necessary node to delete
        testStory1.findNode(5);
        //Deleting Node and should delete children
        testStory1.deleteNode(5);
    }
}
