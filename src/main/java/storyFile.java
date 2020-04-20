import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class storyFile {

    String fileName;
    String path;
    //constructor
    public storyFile(String fileNameIn, String pathIn) throws IllegalArgumentException{
        if(fileNameIn!=" "||pathIn!=" "){
            fileName = fileNameIn;
            path = pathIn;
        }else {
            throw new IllegalArgumentException("Wrong file name or path");
        }

    }

//    public Path findPath(String fileNameIn)throws FileNotFoundException{
//        Path p = Paths.get(fileNameIn);
//        Path folder = p.getParent();
//        if(folder!=null){
//            return folder;
//        }else{
//            throw new FileNotFoundException("Can not find the file");
//        }
//    }

    /**
     * Check if there's a file in this path
     * @return true if file is here, false if can't find the file
     */
    public FileInputStream checkPath() throws IOException, NullPointerException {
        FileInputStream testFile = new FileInputStream(path);
        if(testFile!=null){
            return testFile;
        }else{
            throw new FileNotFoundException("Incorrect Path");
        }
    }

    /**
     * input txt file
     * @throws IOException if path is empty
     * @throws IllegalArgumentException if the file have no content
     */
    public void importFile() throws IOException,IllegalArgumentException {
        FileInputStream readFile = checkPath();
        System.setIn(readFile);
        Scanner scanfile = new Scanner(System.in);
        String line = scanfile.nextLine();
        if(line!=null) {
            //ID
            int lineNum = 1;
            //key
            int ChoiceNum = 1;
            //create story node
            Story newStory = new Story(lineNum, fileName, line);
            lineNum += 1;
            line = scanfile.nextLine();
            //check if there's child node
            while (line != null) {
                String checkLine[] = line.split("[,. ?]+");
                //check the choice value
                if (Integer.parseInt(String.valueOf(checkLine[0]))==ChoiceNum) {
                    newStory.addNode(line,lineNum,ChoiceNum,line);
                    ChoiceNum += 1;
                }
                line = scanfile.nextLine();
                lineNum++;
            }
        }else{
            throw new IllegalArgumentException("File is empty");
        }

    }
    public void outputFile(String fileNameIn){

    }
    public void deleteFile() throws IOException {
        Files.deleteIfExists(Paths.get(path));
    }

    public void editFile(String fileName, String content){

    }

    //getters
    public String getPath() {
        return path;
    }
    public String getFileName(){
        return fileName;
    }

}
