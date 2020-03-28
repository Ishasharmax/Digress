import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Story {

    private int id;
    private String title;
    private Node root;
    private HashMap<Integer, Node> storyNodes;
    LinkedList<String> tags;


    public Story(int idIn, String titleIn, String rootContent, LinkedList<String> tagsIn){
        id = idIn;
        title = titleIn;
        root = new Node(1, rootContent);
        storyNodes = new HashMap();
        storyNodes.put(0, root);
        tags = tagsIn;
    }

    public Node getNext(int choiceValue){
        return root;
    }

    public void editNode(int nodeID){

    }

    public void deleteNode(int nodeID){

    }

    public void addNode(String storyContent, int parentID){

    }

    Node findNode(int nodeID) throws IllegalArgumentException{
        if(storyNodes.size() < 1) {
            throw new IllegalArgumentException("There has to be at least one story node");
        }
        Iterator<Integer> iterator = storyNodes.keySet().iterator();
        while(iterator.hasNext()) {
            Integer currKey = iterator.next();
            if(nodeID == currKey){
                return storyNodes.get(currKey);
            }
        }
        return storyNodes.get(-1);
    }

    public void printCurrentNode() {
        for (HashMap.Entry entry : storyNodes.entrySet()) {
            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
        }
    }

    public Node getRoot(){
        return new Node(0, "dfnsalk");
    }

    public HashMap getStoryNodes(){
        return new HashMap();
    }


}
