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
    public Story importFile() throws IOException,IllegalArgumentException {
        FileInputStream readFile = checkPath();
        System.setIn(readFile);
        Scanner scanFile = new Scanner(System.in);
        String line = scanFile.nextLine();
        if(scanFile.hasNextLine()) {
            //ID
            int parentID = 1;
            //create story node
            Story newStory = new Story(parentID, fileName, line);
            //for loop check key
            boolean isOpen = false;
            boolean isClose = false;
            //choice value and condition
            String choice = "";
            String value = "";
            String newV = "false";
            //check if there's child node
            int count = 0;
            while (scanFile.hasNextLine()) {
                line = scanFile.nextLine();
                //reset counting value
                choice = "";
                value = "";
                isOpen = false;
                isClose = false;
                //check the choice value
                for (int j = 0; j < line.length(); j++){
                    if (line.charAt(j)=='['){
                        isOpen = true;
                    }else if(line.charAt(j)==']'){
                        isClose = true;
                        isOpen = false;
                    }else if(isClose==false && isOpen==true){
                        choice = choice + String.valueOf(line.charAt(j));
                    }else {
                        value = value + String.valueOf(line.charAt(j));
                    }
                }
                //add child node
                if(choice.isEmpty()==false && value.isEmpty()==false) {
                    //String storyContent, int parentID, int choiceValue, String condition
                    newStory.addNode(value, parentID, Integer.parseInt(choice), value);
                }
            }
            return newStory;
        }else{
            throw new IllegalArgumentException("File is empty");
        }

    }
    public void outputFile(Story outputStory, int nodeID,String fileNameIn){

    }

    /**
     * delete file from directory
     * @throws IOException
     */
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
