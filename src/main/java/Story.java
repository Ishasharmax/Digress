import java.util.LinkedList;
import java.util.Map;

public class Story extends Node {
    public String Tittle;
    LinkedList<String> Tags = new LinkedList<String>();
    public Node root;
    public Node currentNode;

    public Story(int idIn,String titleIn, String storyContentIn, LinkedList tagsIn) throws IllegalArgumentException {
        super(idIn, storyContentIn);
        Tittle = titleIn;
        Tags = tagsIn;
        Map<Integer,Node> stroyNode = new Map<,Node>();
        
    }

}
