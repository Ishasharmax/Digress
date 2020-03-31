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
        Iterator<Integer> iterator = storyNodes.keySet().iterator();
        while(iterator.hasNext()) {
            Integer currKey = iterator.next();
            if (currKey != null) {
                return storyNodes.get(currKey);
            }
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
//        Node nodeToChange = findNode(nodeID);
//        HashMap<Integer, Node> nextMap = nodeToChange.getNextNodes();
//        Node childToChange = nextMap.get(childChoiceValue);
//        String condition = nodeToChange.getNextConditions().get(childChoiceValue);
//        nodeToChange.setChild(newChoiceValue, condition, childToChange);
    }

    public void deleteNode(int nodeID) throws IllegalArgumentException{
        if (findNode(nodeID)==null){
            throw new IllegalArgumentException("Can't find the nodes");
        }else{
            if(findNode((nodeID)).getNextNodes()!=null){
//                findNode((nodeID)).getParentNode().setChild(findNode((nodeID)).getNext(),findNode((nodeID)).getNextNodes());

            }else{
                findNode((nodeID)).getParentNode();
            }
        }
    }

    public void addNode(String storyContent, int parentID){
        if(storyContent.equals(" ") || parentID < 1) {
            throw new IllegalArgumentException("There has to be at least one story node");
        }
        if(storyNodes.size() == 0) {
            Node sNode = new Node(1, storyContent);
            storyNodes.put(1, sNode);
        }
        else {
            int count = 0;
            Iterator<Integer> iterator = storyNodes.keySet().iterator();
            while(iterator.hasNext()) {
                Integer currKey = iterator.next();
                count++;
            }
            Node sNode = new Node(count, storyContent);
            storyNodes.put(count, sNode);
        }
    }

    Node findNode(int nodeID) throws IllegalArgumentException{ //hardcoded test to supplement addnode
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
        return storyNodes.get(0);
    }

    public void printCurrentNode() { //return current node as a string instead of print
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
