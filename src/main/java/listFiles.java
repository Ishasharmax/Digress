import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class listFiles {

    public void File(Path filePath){

    }

    public static void fileContent(Path filePath) throws IOException{
        System.out.println("read file " + filePath);
        List<String> fileList = Files.readAllLines(filePath);
        System.out.println("" + fileList);
    }

    public static void listAllFiles(String path) throws IOException {
        try(Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.forEach(filePath ->{
                if (Files.isRegularFile(filePath)) {
                    try {
                        fileContent(filePath);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            });
        }
    }
}