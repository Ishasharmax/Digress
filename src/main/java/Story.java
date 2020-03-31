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
        storyNodes.put(1, root);
        tags = tagsIn;
    }

    public Node getNext(int choiceValue){
        for (HashMap.Entry entry : storyNodes.entrySet()) {
            Integer key = (Integer) entry.getKey();
            Node currentNode = storyNodes.get(key);
            return currentNode.getNext(choiceValue);
        }
        return root;
    }

    public void editNode(int nodeID, String editChoice){

    }

    public void editNodeStoryContent(int nodeID, String newStoryContent){
        Node nodeToChange = findNode(nodeID);
        nodeToChange.editStoryContent(newStoryContent);
    }

    public void editNodeChildren(int nodeID, int childChoiceValue, int newChoiceValue){
        Node nodeToChange = findNode(nodeID);
        HashMap<Integer, Node> nextMap = nodeToChange.getNextNodes();
        Node childToChange = nodeToChange.getNext(childChoiceValue);
        String condition = nodeToChange.getNextConditions().get(childChoiceValue);
        Node oldNode = nodeToChange.getNext(newChoiceValue);
        String oldCondition = nodeToChange.getNextConditions().get(newChoiceValue);
        nodeToChange.setChild(childChoiceValue, oldCondition, oldNode);
        nodeToChange.setChild(newChoiceValue, condition, childToChange);
    }

    public void deleteNode(int nodeID){

    }

    public void addNode(String storyContent, int parentID, int choiceValue, String condition){
        if(storyContent.equals("") || findNode(parentID) == null) {
            throw new IllegalArgumentException("There has to be at least one story node");
        }
        Node parent = findNode(parentID);
        int nodeID = storyNodes.size() + 1;
        Node sNode = new Node(nodeID, storyContent, parent);
        storyNodes.put(nodeID, sNode);
        parent.setChild(choiceValue, condition, sNode);
    }


    Node findNode(int nodeID) throws IllegalArgumentException{ //hardcoded test to supplement addnode
        if(storyNodes.size() < 1) {
            throw new IllegalArgumentException("There has to be at least one story node");
        }
        return storyNodes.get(nodeID);
    }

    public void printCurrentNode() { //return current node as a string instead of print
        for (HashMap.Entry entry : storyNodes.entrySet()) {
            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
        }
    }

    public Node getRoot(){
        return root;
    }

    public HashMap getStoryNodes(){
        return storyNodes;
    }


}
