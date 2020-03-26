import java.util.Map;

public class Node {
    private int id;
    private String storyContent;
    private Node parentNode;
    private int choiceValue;
    public Map<Integer, Node> nextNodes;

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
    public Node(int idIn, String storyContentIn, Node parentNodeIn, int choiceValueIn) throws IllegalArgumentException{
        //should throw invalid argument exceptions higher up:
        //id must be unique choiceValue must be unique to parent

        if (idIn < 1){
            throw new IllegalArgumentException("ID must be a positive number");
        }

        if(parentNodeIn == null){
            throw new IllegalArgumentException("Parent node does not exist");
        }

        if(choiceValueIn < 1){
            throw new IllegalArgumentException("Choice value must be a positive number");
        }

        id = idIn;
        storyContent = storyContentIn;
        parentNode = parentNodeIn;
        choiceValue = choiceValueIn;
    }

    public String getStoryContent(){
        return this.storyContent;
    }
}
