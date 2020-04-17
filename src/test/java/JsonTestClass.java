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
        //story1.addNode(node1);

    }

    @Test
    void story2Tests(){

    }

    @Test
    void story3Tests() throws IOException {
        Story storyT3 = JsonUtil.fromJsonFile("src/test/Story3.json", Story.class);
        //checking ID
        assertEquals(1, storyT3.getID());
        //checking title
        assertEquals("Story3", storyT3.getTitle());
        //checking root content
        assertEquals("Main Content", storyT3.getRootContent());
        //checking tags
        assertEquals("Horror", storyT3.getTags().getFirst());

        //importing node 3-1
        Node node1 = JsonUtil.fromJsonFile("src/test/Story3-1.json", Node.class);
        //checking getters
        assertEquals(2, node1.getId());
        assertEquals("Starting content for story 3", node1.getStoryContent());

        //importing node 3-2
        Node node2 = JsonUtil.fromJsonFile("src/test/Story3-2.json", Node.class);
        //checking getters
        assertEquals(3, node2.getId());
        assertEquals("Second content for story 3", node1.getStoryContent());

        //importing node 3-3
        Node node3 = JsonUtil.fromJsonFile("src/test/Story3-3.json", Node.class);
        //checking getters
        assertEquals(4, node3.getId());
        assertEquals("Third content for story 3", node1.getStoryContent());

        //importing node 3-4
        Node node4 = JsonUtil.fromJsonFile("src/test/Story3-4.json", Node.class);
        //checking getters
        assertEquals(5, node4.getId());
        assertEquals("Fourth content for story 3", node1.getStoryContent());
    }
}
