import java.util.LinkedList;
import java.util.Map;

public class Story extends Node {
    public String Tittle;
    LinkedList<String> Tags = new LinkedList<String>();
    Map<Integer,Node> stroyNode = new Map<,Node>();
    public Node root;
    public Node currentNode;

    public Story(int idIn,String titleIn, String storyContentIn, LinkedList tagsIn, Map stroyNode) throws IllegalArgumentException {
        super(idIn, storyContentIn);
        Tittle = titleIn;
        Tags = tagsIn;


    }

}
