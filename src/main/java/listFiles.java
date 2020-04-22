import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class listFiles {

    public static void fileContent(File file) throws IOException{
        System.out.println("read file " + file.getCanonicalPath() );
        try(BufferedReader br  = new BufferedReader(new FileReader(file))){
            String strLine;
            // Read lines from the file, returns null when end of stream
            // is reached
            while((strLine = br.readLine()) != null){
                System.out.println("Line is - " + strLine);
            }
        }
    }
    public static void listAllFiles(File folder){
        System.out.println("The folder: ");
        File[] fileNames = folder.listFiles();
        for(File file : fileNames) {
            if (file.isDirectory()) {
                listAllFiles(file);
            }
            else{
                try {
                    fileContent(file);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            }
        }
        /*
    This uses a walk function to follow the specific path of a computer
    and print each files contents(used txt files for the tests)
    */
//    public static void fileContent(Path filePath) throws IOException{
//        System.out.println("read file " + filePath);
//        List<String> fileList = Files.readAllLines(filePath);
//        System.out.println("" + fileList);
//    }
//
//    public static void listAllFiles(String path) throws IOException {
//        try(Stream<Path> paths = Files.walk(Paths.get(path))) {
//            paths.forEach(filePath ->{
//                if (Files.isRegularFile(filePath)) {
//                    try {
//                        fileContent(filePath);
//                    } catch (Exception e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//
//            });
//        }
//    }
    }