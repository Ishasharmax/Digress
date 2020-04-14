import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.lang.String;
import static org.junit.jupiter.api.Assertions.*;

public class GlobalVariablesTests {

    @Test
    void constructorTest(){
        GlobalVariables testVars = new GlobalVariables();
        assertEquals(0, testVars.variables.size());
    }

    @Test
    void getVariableTests(){
        GlobalVariables testVars = new GlobalVariables();
        assertEquals(0, testVars.variables.size());

        testVars.addString("Health", "Full");
        assertEquals("Full", testVars.variables.get("Health"));
        assertThrows(IllegalArgumentException.class, ()-> testVars.variables.get("Life"));

        testVars.addString("Life", "Max");
        assertEquals("Max", testVars.variables.get("Life"));

        testVars.addInt("Power", 10);
        assertEquals(10, testVars.variables.get("Power"));
    }

    @Test
    void addStringTests(){
        GlobalVariables testVars = new GlobalVariables();
        assertEquals(0, testVars.variables.size());
        testVars.addString("Health", "Full");
        assertEquals(1, testVars.variables.size());
        assertEquals("Full", testVars.variables.get("Health"));
        assertThrows(IllegalArgumentException.class, ()-> testVars.variables.get("Life"));

        testVars.addString("Life", "Max");
        assertEquals(2, testVars.variables.size());
        assertEquals("Max", testVars.variables.get("Life"));

        assertThrows(IllegalArgumentException.class, ()-> testVars.addString("Health", "Low"));
        assertThrows(IllegalArgumentException.class, ()-> testVars.addString("Life", "Min"));
    }

    @Test
    void addIntTests(){
        GlobalVariables testVars = new GlobalVariables();
        assertEquals(0, testVars.variables.size());
        testVars.addInt("Health", 10);
        assertEquals(10, testVars.variables.get("Health"));

        testVars.addInt("Level", 0);
        assertEquals(0, testVars.variables.get("Level"));
        assertThrows(IllegalArgumentException.class, ()-> testVars.addInt("Life", -1));

        testVars.addInt("Life", 100);
        assertEquals(100, testVars.variables.get("Life"));

        assertThrows(IllegalArgumentException.class, ()-> testVars.addInt("Life", 50));
        assertThrows(IllegalArgumentException.class, ()-> testVars.addInt("Level", 50));
        assertThrows(IllegalArgumentException.class, ()-> testVars.addInt("Health", 50));
    }

    @Test
    void removeVariableTests(){
        GlobalVariables testVars = new GlobalVariables();
        testVars.addInt("Health", 10);
        assertEquals(10, testVars.variables.get("Health"));

        testVars.addString("Life", "Max");
        assertEquals("Max", testVars.variables.get("Life"));
        assertEquals(2, testVars.variables.size());

        testVars.removeVariable("Health");
        assertEquals(1, testVars.variables.size());
        assertThrows(IllegalArgumentException.class, ()-> testVars.variables.get("Health"));

        assertThrows(IllegalArgumentException.class, ()-> testVars.removeVariable("Power"));

        testVars.removeVariable("Life");
        assertEquals(0, testVars.variables.size());
        assertThrows(IllegalArgumentException.class, ()-> testVars.variables.get("Health"));
    }


}
