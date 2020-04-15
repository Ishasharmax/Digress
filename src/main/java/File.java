import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.io.File;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class File {
    String fileName;
    String path;

    public File(String fileNameIn){
        fileName = fileNameIn;
        
    }


    public boolean checkFile(String fileName){
        File newFile = new File(this.path);
        if(newFile==null){
            return false;
        }else {
            return true;
        }
    }
    public void importFile(String fileName, LinkedList TagsIn) throws FileNotFoundException {
        //int idIn, String titleIn, String rootContent, LinkedList<String> tagsIn
        if(checkFile(fileName)==false) {
            throw new FileNotFoundException("Can not find the file");
        }else{
            File newFile = new File("src/main/java/testFile.txt");
            Scanner scanner = new Scanner((Readable) newFile);
            String data = " ";
            while (scanner.hasNextLine()) {
                if(data==" "){
                    data= scanner.nextLine();
                    int key = data.charAt(1);
                    Story newStory = new Story(key,fileName,"",TagsIn);
                }
                System.out.println(data);
            }
            scanner.close();
        }
    }
    public void outputFile(String fileName){

    }
    public void removeFile(String fileName) throws FileNotFoundException {
        File newFile = new File("src/main/java/testFile.txt");
        if (newFile.delete()) {
            System.out.println("Deleted the folder: " + newFile.getName());
        } else {
            System.out.println("Failed to delete the folder.");
        }
    }

    public void editFile(String fileName, String content){

    }

}
