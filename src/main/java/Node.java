import java.util.HashMap;

public class Node {
    private int id;
    private String storyContent;
    private Node parentNode;
    private HashMap<Integer, Node> nextNodes;

    //root node constructor
    //add character limit here?
    public Node(int idIn, String storyContentIn) throws IllegalArgumentException{
        //should throw invalid argument exceptions higher up:
        //id must be unique, each story must have exactly one root node

        if (idIn < 1){
            throw new IllegalArgumentException("ID must be a positive number");
        }
        id = idIn;
        storyContent = storyContentIn;
        parentNode = null;
        nextNodes = new HashMap<>();
    }

    //child node constructor
    public Node(int idIn, String storyContentIn, Node parentNodeIn) throws IllegalArgumentException{
        //should throw invalid argument exceptions higher up: id must be unique

        if (idIn < 1){
            throw new IllegalArgumentException("ID must be a positive number");
        }

        if(parentNodeIn == null){
            throw new IllegalArgumentException("Parent node does not exist");
        }

        id = idIn;
        storyContent = storyContentIn;
        parentNode = parentNodeIn;
        nextNodes = new HashMap<>();
    }

    public boolean checkConditionExists(int conditionIn){
        if(getNextNodes().isEmpty()){
            return false;
        }
        for (Integer condition: getNextNodes().keySet()){
            if(condition == conditionIn){
                return true;
            }
        }
        return false;
    }

    //sets map to the new child
    //this should be called whenever addNode is called in story class
    public void setChild(Integer condition, Node childNode) throws IllegalArgumentException{
        if(condition < 1){
            throw new IllegalArgumentException("Condition must be a positive number");
        }
        if(checkConditionExists(condition)){
            throw new IllegalArgumentException("Condition already exists in the parent");
        }
        if(childNode == null){
            throw new IllegalArgumentException("Child node does not exist");
        }
        nextNodes.put(condition, childNode);
    }

    public Node getNext(int choiceValue){
        return nextNodes.get(choiceValue);
    }

    public String getStoryContent(){
        return storyContent;
    }

    public Node getParentNode(){
        return parentNode;
    }

    public HashMap<Integer, Node> getNextNodes(){
        return nextNodes;
    }

    public void editStoryContent(String newStoryContent){
    }
}
