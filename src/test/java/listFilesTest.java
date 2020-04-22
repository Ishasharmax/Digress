import org.junit.jupiter.api.Test;

import java.io.File;


public class listFilesTest{
    @Test
    public void listFiles(){
        File folder = new File("C:\\Test");
        listFiles Files = new listFiles();
        System.out.println("reading files");
        listFiles.listAllFiles(folder);
        System.out.println("-------------------------------------------------");
    }
}
