import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.lang.String;
import static org.junit.jupiter.api.Assertions.*;

public class FileTest {
    @Test
    public void checkPathTest(){
        File testFile = JsonUtil.fromJsonFile("src/test/java/validFile.json", File.class);
        Assert.assertEquals(4, testFile.numberOfAccounts());
    }
    @Test
    public void importFileTest(){
    }
    @Test
    public void outputFileTest(){

    }
    @Test
    public void removeFileTest(){

    }
    @Test
    public void editFileTest(){

    }
}
