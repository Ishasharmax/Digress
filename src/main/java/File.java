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
    Path path;
    private Map<String, fileName> fileMap;
    public File(String fileNameIn) throws FileNotFoundException {
        fileName = fileNameIn;
        path = findPath(fileName);
    }

    public Path findPath(String fileNameIn)throws FileNotFoundException{
        Path p = Paths.get(fileNameIn);
        Path folder = p.getParent();
        if(folder!=null){
            return folder;
        }else{
            throw new FileNotFoundException("Can not find the file");
        }
    }

    public Path getPath() {
        return path;
    }


    public void importFile(String fileNameIn, LinkedList TagsIn) throws FileNotFoundException {
        //int idIn, String titleIn, String rootContent, LinkedList<String> tagsIn
        File newFile = new File(fileName);
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
    public void outputFile(String fileNameIn){

    }
    public void removeFile(String fileNameIN) throws FileNotFoundException {
        File newFile = new File(fileNameIN);
        if (newFile.delete()) {
            System.out.println("Deleted the folder: " + newFile.getName());
        } else {
            System.out.println("Failed to delete the folder.");
        }
    }

    public void editFile(String fileName, String content){

    }

}
