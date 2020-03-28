import java.util.HashMap;
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

    public void editNode(int nodeID, String editChoice){

    }

    public void editNodeStoryContent(int nodeID, String newStoryContent){
        Node nodeToChange = findNode(nodeID);
        nodeToChange.editStoryContent(newStoryContent);
    }

    public void editNodeChildren(int nodeID, int mapNodeID, int newChoiceValue){
        Node nodeToChange = findNode(nodeID);
        nodeToChange.editChildren(int choiceValueToChange, newChoiceValue);
    }

    public void deleteNode(int nodeID){

    }

    public void addNode(String storyContent, int parentID){

    }

    public Node findNode(int nodeID){
        return new Node(0, "dfnsalk");
    }

    public void printCurrentNode(){

    }

    public Node getRoot(){
        return new Node(0, "dfnsalk");
    }

    public HashMap getStoryNodes(){
        return new HashMap();
    }


}
