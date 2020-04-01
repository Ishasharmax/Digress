import java.util.*;

public class Story {

    private int id;
    private String title;
    private Node root;
    private HashMap<Integer, Node> storyNodes;
    LinkedList<String> tags;


    public Story(int idIn, String titleIn, String rootContent, LinkedList<String> tagsIn){
        id = idIn;
        if (titleIn == " " || titleIn == ""){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        title = titleIn;
        if (rootContent == " " || rootContent == ""){
            throw new IllegalArgumentException("Root cannot be empty");
        }
        root = new Node(1, rootContent);
        storyNodes = new HashMap();
        storyNodes.put(1, root);
        tags = tagsIn;
    }

    public Node getNext(int choiceValue){
        if(storyNodes.size()-1 < choiceValue) {
            throw new IllegalArgumentException("A node with this choice value does not exist");
        }
        for (HashMap.Entry entry : storyNodes.entrySet()) {
            Integer key = (Integer) entry.getKey();
            Node currentNode = storyNodes.get(key);
            return currentNode.getNext(choiceValue);
        }
        return storyNodes.get(1);
    }

    public void editNode(int nodeID, String editChoice){
        Scanner scanner = new Scanner(System.in);
        if (editChoice == "story content"){
            System.out.print("Enter what you would like the new story content to be: ");
            String newContent = scanner.nextLine();
            editNodeStoryContent(nodeID, newContent);
        }
        if (editChoice == "children"){
            System.out.print("Enter the choice value of the child you want to change: ");
            int child1ChoiceValue = scanner.nextInt();
            System.out.print("Enter the choice value you want this child to have: ");
            int child2ChoiceValue = scanner.nextInt();
            editNodeChildren(nodeID, child1ChoiceValue, child2ChoiceValue);
        }
        else{
            throw new IllegalArgumentException("Invalid choice");
        }
    }

    public void editNodeStoryContent(int nodeID, String newStoryContent){
        Node nodeToChange = findNode(nodeID);
        if (nodeToChange == null){
            throw new IllegalArgumentException("A node with this ID does not exist");
        }
        if (newStoryContent == "" || newStoryContent == " "){
            throw new IllegalArgumentException("Story content cannot be empty");
        }
        nodeToChange.editStoryContent(newStoryContent);
    }

    public void editNodeChildren(int nodeID, int child1ChoiceValue, int child2ChoiceValue){
        Node nodeToChange = findNode(nodeID);
        if (nodeToChange.getNextNodes().isEmpty()){
            throw new IllegalArgumentException("This node has no children");
        }
        HashMap<Integer, String> nextConditions = nodeToChange.getNextConditions();
        HashMap<Integer, Node> nextNodes = nodeToChange.getNextNodes();
        Node child1 = nodeToChange.getNext(child1ChoiceValue);
        Node child2 = nodeToChange.getNext(child2ChoiceValue);
        if (child1 == null || child2 == null){
            throw new IllegalArgumentException("A node with this choice value does not exist");
        }
        String child1Condition = nextConditions.get(child1ChoiceValue);
        String child2Condition = nextConditions.get(child2ChoiceValue);
        nextConditions.replace(child1ChoiceValue, child2Condition);
        nextConditions.replace(child2ChoiceValue, child1Condition);
        nextNodes.replace(child1ChoiceValue, child2);
        nextNodes.replace(child2ChoiceValue, child1);
    }

    public void addNode(String storyContent, int parentID, int choiceValue, String condition){
        if (findNode(parentID) == null) {
            throw new IllegalArgumentException("A node with this ID does not exist");
        }
        if (storyContent.equals("") || storyContent == " "){
            throw new IllegalArgumentException("Story content cannot be empty");
        }
        Node parent = findNode(parentID);
        int nodeID = storyNodes.size() + 1;
        Node sNode = new Node(nodeID, storyContent, parent);
        storyNodes.put(nodeID, sNode);
        parent.setChild(choiceValue, condition, sNode);
    }
    public void deleteNode(int nodeID) throws IllegalArgumentException{
        if (findNode(nodeID)==null){
            throw new IllegalArgumentException("Node is not exist");
        }else{
            storyNodes.remove(nodeID);
        }
    }
    Node findNode(int nodeID) throws IllegalArgumentException{ //hardcoded test to supplement addnode
        if(storyNodes.size() < 1) {
            throw new IllegalArgumentException("There has to be at least one story node");
        }
        if (storyNodes.get(nodeID) == null){
            throw new IllegalArgumentException("A node with this ID does not exist");
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

    public int getID(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public LinkedList<String> getTags(){
        return tags;
    }

}