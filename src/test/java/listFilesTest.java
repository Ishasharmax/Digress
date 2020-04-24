import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class listFilesTest{ //With txt files
    @Test // = Passed but needs files to be existing in C: folder
    public void listFilesTest1() throws NullPointerException, IOException {
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

    @Test
    public void listFilesTest2() throws NullPointerException, IOException {
        String basicPath = "C:\\DigressListTest";
        File folder = new File(basicPath);
        if(folder.mkdir()){
            System.out.println("New Directory made");
        }
        else{
            System.out.println("Directory exists");
        }

        //Root for json file
        JSONObject wStory = new JSONObject();
        wStory.put("id", "1");
        wStory.put("title", "TestMe");
        wStory.put("rootCont", "This is for the test so the file can be created");
        wStory.put("tagsIn", "Comedy");

        JSONObject cOfStory = new JSONObject();
        wStory.put("id", "2");
        wStory.put("rootCont", "This is to help the test");

        JSONArray storyList = new JSONArray();
        storyList.add(wStory);
        storyList.add(cOfStory);

        File jsonFile = new File(basicPath + "\\jsonFile1.json");

        try (FileWriter writer = new FileWriter(jsonFile);) {
            writer.write(storyList.toJSONString());
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        listFiles listFiles = new listFiles();
        System.out.println("reading files");
        listFiles.listAllFiles(folder);
        System.out.println("-------------------------------------------------");

    }
}
