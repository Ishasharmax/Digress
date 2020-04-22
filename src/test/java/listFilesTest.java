import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


public class listFilesTest{
    @Test
    public void listFilesTest() throws IOException {
        storyFile test1 = new storyFile("findFile1","C:\\Test");
        storyFile test2 = new storyFile("findFile2","C:\\Test");
        storyFile test3 = new storyFile("findFile3","C:\\Test");
        System.out.println("reading files");
        listFiles.listAllFiles("C:");
        System.out.println("-------------------------------------------------");
    }

}
