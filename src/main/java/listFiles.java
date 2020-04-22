import java.io.File;

public class listFiles {
    private static File currFolder;

    public static void listAllFiles(File folder){
        currFolder = folder;
        System.out.println("All the Files in this folder are: ");
        File[] fNames = currFolder.listFiles();
        for(File file : fNames){
            if(file.isDirectory()){
                listAllFiles(file);
            }
        }
    }

    public File getCurrFolder(){
        return currFolder;
    }

    p\
}
