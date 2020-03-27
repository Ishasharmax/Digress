import java.util.Map;

public class Node {
    private int id;
    private String storyContent;
    private Node parentNode;
    private Map<Integer, Node> nextNodes;

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
    }

    //sets map to the new child
    //this should be called whenever addNode is called in story class
    public void setChild(Integer condition, Node childNode) throws IllegalArgumentException{

    }

    public Node getNext(int choiceValueIn){
        return null;
    }

    public String getStoryContent(){
        return this.storyContent;
    }

    public Node getParentNode(){
        return this.parentNode;
    }


}
