import java.util.Map;

public class Node {
    private int id;
    private String storyContent;
    private Node parentNode;
    private int choiceValue;
    public Map<Integer, Node> nextNodes;

    //root node constructor
    public Node(int id, String storyContent) throws IllegalArgumentException{
        //should throw invalid argument exceptions higher up:
        //id must be unique, each story must have exactly one root node


    }

    //child node constructor
    public Node(int id, String storyContent, Node parentNode, int choiceValue) throws IllegalArgumentException{
        //should throw invalid argument exceptions higher up:
        //id must be unique choiceValue must be unique to parent


    }

    public String getStoryContent(){
        return this.storyContent;
    }
}
