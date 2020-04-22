import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;


public class listFilesTest{
    @Test // = Passed but needs files to be existing in C: folder
    public void listFilesTest() throws NullPointerException, IOException {
        String basicPath = "C:\\DigressListTest";
        File folder = new File(basicPath);
        if(folder.mkdir()){
            System.out.println("New Directory made");
        }
        else{
            System.out.println("Directory exists");
        }
        File txtFile = new File(basicPath + "\\testFile1.txt");
        FileWriter writer = new FileWriter(txtFile);
        writer.write("Test data for the first txt file");
        writer.close();

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
