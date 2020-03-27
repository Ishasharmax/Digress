import java.util.*;
import java.io.*;


public class Story {
    int ID;
    String title;
    Node root;
    Map<Integer, Node> storyNodes;
    LinkedList<String> tags;
    Node currentNode;

    Node findNode(int nodeID) {
        Iterator<Integer> iterator = storyNodes.keySet().iterator();
        while(iterator.hasNext()) {
            Integer currKey = iterator.next();
            if(nodeID == currKey){
                return storyNodes.get(currKey);
            }
        }
        return storyNodes.get(-1);
    }
}
