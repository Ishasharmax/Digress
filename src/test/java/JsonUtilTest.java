import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.lang.String;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.core.JsonProcessingException;


public class JsonUtilTest {

    @Test
    void exportImportJsonTest() throws IOException {
        //set up a basic story
        Story testStoryOut = new Story(1, "Adventure", "rootContent");
        testStoryOut.addNode("nodeContent1", 1, 1, "choice a");
        testStoryOut.addNode("nodeContent2", 1, 2, "choice b");
        testStoryOut.addNode("nodeContent3", 2, 1, "choice a");
        testStoryOut.addNode("nodeContent4", 3, 1, "choice b");

        JsonUtil.exportToJson("src/test/testStory1.json", testStoryOut);

        Story testStoryIn = JsonUtil.importFromJson("src/test/testStory1.json", Story.class);
        //check everything was imported correctly
        assertEquals("rootContent", testStoryIn.getRootContent());
        assertEquals("nodeContent1", testStoryIn.findNode(2));
        assertEquals("nodeContent2", testStoryIn.findNode(3));
        assertEquals("nodeContent3", testStoryIn.findNode(4));
        assertEquals("nodeContent4", testStoryIn.findNode(5));
    }
}
