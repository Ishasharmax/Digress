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
        story1.addNode(node1);

    }

    @Test
    void story2Tests(){

    }

    @Test
    void story3Tests(){

    }
}
