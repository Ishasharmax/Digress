import java.util.HashMap;

public class Node {
    private int id;
    private String storyContent;
    private Node parentNode;
    private HashMap<Integer, Node> nextNodes;
    private HashMap<Integer, String> nextConditions;


    //used by Json
    public Node(){

    }

    public void setId(int id){
        this.id = id;
    }

    public void setStoryContent(String storyContent){
        this.storyContent = storyContent;
    }

    //root node constructor
    //add character limit here?
    public Node(int id, String storyContent) throws IllegalArgumentException{
        //should throw invalid argument exceptions higher up:
        //id must be unique, each story must have exactly one root node

        if (id < 1){
            throw new IllegalArgumentException("ID must be a positive number");
        }
        this.id = id;
        this.storyContent = storyContent;
        parentNode = null;
        nextNodes = new HashMap<>();
        nextConditions = new HashMap<>();
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
        nextConditions = new HashMap<>();
    }

    public boolean checkChoiceValueValid(int choiceValueIn){
        if(nextNodes.isEmpty()){
            return true;
        }
        //find max and make sure the new choiceValue is max+1
        int max = 0;
        for (Integer choiceValue: nextNodes.keySet()){
            if (choiceValue > max){
                max = choiceValue;
            }
        }
        if(choiceValueIn != max+1){
            return false;
        }
        return true;
    }

    public boolean checkConditionValid(String conditionIn){
        conditionIn = conditionIn.toLowerCase();
        char[] charArray = conditionIn.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!(ch >= 'a' && ch <= 'z')) {
                if (ch != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkConditionExists(String conditionIn){
        if(nextConditions.isEmpty()){
            return false;
        }
        for(String condition: nextConditions.values()){
            if(condition.equals(conditionIn)){
                return true;
            }
        }
        return false;
    }

    //sets map to the new child
    //this should be called whenever addNode is called in story class
    public void setChild(Integer choiceValue, String condition, Node childNode) throws IllegalArgumentException{
        if (nextNodes == null){
            this.nextNodes = new HashMap<>();
            this.nextConditions = new HashMap<>();
        }

        if(choiceValue < 1){
            throw new IllegalArgumentException("Choice value must be a positive number");
        }
        if(!checkChoiceValueValid(choiceValue)){
            throw new IllegalArgumentException("Choice value already exists or is too much larger than last choice value");
        }
        if(!checkConditionValid(condition)){
            throw new IllegalArgumentException("Condition must be only letters");
        }
        if(checkConditionExists(condition)){
            throw new IllegalArgumentException("Condition already exists");
        }
        if(childNode == null){
            throw new IllegalArgumentException("Child node does not exist");
        }
        nextNodes.put(choiceValue, childNode);
        nextConditions.put(choiceValue, condition);
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
    public int getId(){
        return id;
    }

    public HashMap<Integer, String> getNextConditions(){
        return nextConditions;
    }

    public void editStoryContent(String newStoryContent){
        storyContent = newStoryContent;
    }

}
