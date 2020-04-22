import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;


public class listFilesTest{
    @Test // = Passed but needs files to be existing in C: folder
    public void listFilesTest() throws NullPointerException {
        File folder = new File("C:\\Test");
        //folder.mkdir();
        listFiles listFiles = new listFiles();
        System.out.println("reading files");
        listFiles.listAllFiles(folder);
        System.out.println("-------------------------------------------------");
    }

//    @Test //Walk function way = Passed but needs files to be existing in C: folder
//    public void listFilesTest() throws IOException, NoSuchFileException {
//        System.out.println("reading files");
//        listFiles.listAllFiles("C:\\Test");
//        System.out.println("-------------------------------------------------");
//    }

}
