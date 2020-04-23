import testFilePackage.GlobalVariables;

import java.util.*;

public class Story {

    private int id;
    private String title;
    private String rootCont;
    private int choiceVal;
    private Node root;
    private Node currentNode;
    private HashMap<Integer, Node> storyNodes;
    LinkedList<String> tags;
    GlobalVariables variables;

    //used by Json
    public Story(){

    }

    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setRootCont(String rootCont){
        this.rootCont = rootCont;
    }


    public Story(int id, String title, String rootCont){
        this.id = id;
        if (title == " " || title == ""){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
        if (rootCont == " " || rootCont == ""){
            throw new IllegalArgumentException("Root cannot be empty");
        }
        this.rootCont = rootCont; //holds the beginning content outside of node
        root = new Node(1, rootCont);
        currentNode = root;
        storyNodes = new HashMap();
        storyNodes.put(1, root);
        variables = new GlobalVariables();
        tags = new LinkedList<String>();
    }

    public void addExistingNode(Node nodeIn) throws IllegalArgumentException{
        if (storyNodes == null){
            this.storyNodes = new HashMap<>();
            this.root = new Node(1, rootCont);
            storyNodes.put(1, root);
        }
        if(nodeIn == null){
            throw new IllegalArgumentException("Node cannot be null");
        }
        //already exists
        if(findNode(nodeIn.getId()) != null){
            throw new IllegalArgumentException("A node already exists with that ID");
        }
        storyNodes.put(nodeIn.getId(), nodeIn);
        currentNode = nodeIn;
    }

    public Node getNext(int choiceValue){
        if(storyNodes.size()-1 < choiceValue) {
            throw new IllegalArgumentException("A node with this choice value does not exist");
        }
        currentNode = currentNode.getNext(choiceValue);
        return currentNode;
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
        choiceVal = choiceValue;
        Node parent = findNode(parentID);
        int nodeID = storyNodes.size() + 1;
        Node sNode = new Node(nodeID, storyContent, parent);
        storyNodes.put(nodeID, sNode);
        currentNode = storyNodes.get(nodeID); //Every time a node gets added, it becomes the current node
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
            return null;
        }
        currentNode = storyNodes.get(nodeID); //Every time findNode is used, the node becomes the current node
        return storyNodes.get(nodeID);
    }

    public String printCurrentNode(){
        String node = currentNode.getStoryContent();
        Map nextConditions = currentNode.getNextConditions();
        for (int i = 1; i <= nextConditions.size(); i++){
            node += "\n(" + i + ") " + nextConditions.get(i);
        }
        return node;
    }

    public String printNode(int nodeId){
        Node node = findNode(nodeId);
        String nodeStr = node.getStoryContent();
        Map nextConditions = node.getNextConditions();
        for (int i = 1; i <= nextConditions.size(); i++){
            nodeStr += "\n(" + i + ") " + nextConditions.get(i);
        }
        return nodeStr;
    }

    public String printAllNodes(){
        String allNodes = "";
        for(int i = 1; i <= storyNodes.size(); i++){
            if(i > 1){
                allNodes += "\n";
            }
            allNodes += "(" + i + ") " + storyNodes.get(i).getStoryContent();
        }
        return allNodes;
    }

    public void addVariable(String name, String type, Object value){
        if (!type.equals("string") && !type.equals("int")){
            throw new IllegalArgumentException("Must be a valid type");
        }
        if (type.equals("string")){
            variables.addString(name, value.toString());
        }
        if (type.equals("int")){
            variables.addInt(name, (Integer) value);
        }
    }

    public void addTag(String tagToAdd){
        tags.add(tagToAdd);
    }

    public Object getVariable(String name){
        return variables.getVariable(name);
    }

    public void removeVariable(String name){
        variables.removeVariable(name);
    }

    public void clearVariables(){
        variables.clearVariables();
    }

    public void printVariable(String name){
        variables.printVariable(name);
    }

    public void editVariable(String name, Object newValue){
        variables.editVariable(name, newValue);
    }

    public Node getRoot(){
        return root;
    }

    public Node getCurrNode(){ //returns the currentNode as Node(numbers) as a location
        return currentNode;
    }

    public void setCurrentNode(Node nodeToAssign){
        this.currentNode = nodeToAssign;
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

    public String getRootContent(){
        return rootCont;
    }


    public int getChoiceVal(){
        return choiceVal;
    }

    public LinkedList<String> getTags(){
        return tags;
    }

    public GlobalVariables getGlobalVariables(){
        return variables;
    }

}