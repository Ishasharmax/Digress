import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class newDeleteTests {
    @Test
    void newDeleteNodeTests(){
        //Based on deleting nodes
        Story testStory1 = new Story(1,"Story", "This is test content for the root");
        testStory1.addNode("Test Content 1");
        testStory1.addNode("Test Content for another Node");
        testStory1.addNode("Woah more test content");
        testStory1.addNode("Delete Me");
        testStory1.addNode("I'm a child of 6, should be deleted");
        testStory1.addNode("I'm a child of 6, should also be deleted");
        //Testing if size is correctly 7
        assertEquals(testStory1.getStoryNodes().size(),7);
        //Find the necessary node to delete
        testStory1.findNode(5);
        //Deleting Node and should delete children
        //testStory1.deleteNode(5);


        //Based on relinking nodes
        Story testStory2 = new Story(1,"Story", "This is test content for the root");
        testStory2.addNode("Test Content 1");
        testStory2.addNode("Test Content for another Node");
        testStory2.addNode("Woah more test content");
        testStory2.addNode("Delete Me");
        testStory2.addNode("I'm a child of 6, but i should be relinked to 2");
        testStory2.addNode("I'm a child of 6, but i should be relinked to 3");
        //Testing if size is correctly 7
        assertEquals(testStory2.getStoryNodes().size(),7);
        //Find the necessary node to delete
        testStory2.findNode(5);
        //Deleting Node and should delete children
        //testStory2.deleteNode(5);

        //Based on node without children
        Story testStory3 = new Story(1,"Story", "This is test content for the root");
        testStory3.addNode("Test Content 3, but delete me");
        //Testing if size is correctly 2
        assertEquals(testStory3.getStoryNodes().size(),2);
        //Find the necessary node to delete
        testStory3.findNode(2);
        //Deleting Node without children
        //testStory3.deleteNode(2);

        //Based on root
        Story testStory4 = new Story(1,"Story", "This is test content for the root");
        //Testing if size is correctly 1
        assertEquals(testStory4.getStoryNodes().size(),1);
        //Find the necessary node to delete
        testStory4.findNode(1);
        //Deleting Node without children
        //testStory4.deleteNode(1);

        //Deleting multiple nodes
        Story testStory5 = new Story(1,"Story", "This is test content for the root");
        testStory5.addNode("Test Content 1");
        testStory5.addNode("Test Content for another Node");
        testStory5.addNode("Woah more test content");
        testStory5.addNode("Delete Me");
        testStory5.addNode("I'm a child of 6, but i should be relinked to 2");
        testStory5.addNode("I'm a child of 6, but i should be relinked to 3");
        testStory5.addNode("I'm a new node with more content");
        testStory5.addNode("I should be deleted though");
        testStory5.addNode("I'm a child of 7");
        testStory5.addNode("I'm a child of 8, relinked with 7");
        //Testing if size is correctly 10
        assertEquals(testStory5.getStoryNodes().size(),10);
        //Find the necessary node to delete
        testStory5.findNode(5);
        //Deleting Node and should relink 6 to 3
        //testStory5.deleteNode(5);
        //Testing if size is correctly 9
        assertEquals(testStory5.getStoryNodes().size(),9);
        //Find the necessary node to delete
        testStory5.findNode(8);
        //Deleting Node and should relink to 7
        //testStory5.deleteNode(8);
    }
}
