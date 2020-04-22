import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class DigressUI {

    public static Story createStory (int idIn, String titleIn, String rootContent, LinkedList< String > tagsIn) throws IllegalArgumentException{
        return new Story(idIn, titleIn, rootContent);
    }

    public static void playStory (Story story){
        System.out.println("Playing story");
        System.out.println(story.printAllNodes());
    }

    public static void editStoryContent (Story storyChosen, String newContent, int contentToEdit) throws IllegalArgumentException{
        storyChosen.editNodeStoryContent(contentToEdit, newContent);

        /*if (newContent == " " || newContent == "") throw new IllegalArgumentException("New content cannot be empty");
        /*else if (storyChosen.getTitle().equalsIgnoreCase(newContent))
            throw new IllegalArgumentException("New content entered is same as previous content");
        else{
            for (int i=0;i<storyChosen.)
            storyChosen.getRoot();
        }
        else if (!storyChosen.getTitle().equalsIgnoreCase(newContent)){
            storyChosen.getRoot();
            editStoryContent
        }*/
    }

    public static void addNodes (int idIn, Story storyChosen, String newContent, boolean endNode) throws IllegalArgumentException {
        storyChosen.addNode(newContent,storyChosen.getID(), idIn, newContent);
    }

    public static void uploadStory (String fileForUpload) throws IOException {
        int count;
        FileReader fileEx=null;
        try {
            fileEx = new FileReader(fileForUpload);
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        assert fileEx != null;
        while ((count=fileEx.read())!=-1)
            System.out.print((char)count);
        fileEx.close();
    }

    public static Story getTestStory(){
        String node1 = "You are walking down a dark, dingy hallway. Where would you like to go?";
        String node2 = "You see cobwebs in front of you and a dark shadowy figure. What would you like to do next?";
        String node3 = "There is a giant spider coming down the hallway towards you.";
        String node4 = "You see a vial on the ground with a strange glowing liquid.";
        String node5 = "You approach the figure and you can see nothing but darkness beneath his hood. 'What brings you this way, traveler?'  he asks.";
        String node6 = "You have no weapons and were slain by the spider. Try again?";
        String node7 = "You charmed the spider with your great personality. He will now fight alongside you.";
        String node8 = "You picked up the vial and put it in your bag.";
        String node9 = "For some reason you decided to drink the weird liquid. You died. Whoops.";
        String node10 = "You see a glowing door to your right.";
        String node11 = "He pulls out a dagger and slays you. Stronger than he looks!";
        String endNode = "I don't feel like writing this story anymore lol";

        Story testStory = new Story(44, "Labyrinth", node1);
        testStory.addNode(node2,1, 1, "Continue straight");
        testStory.addNode(node3, 1, 2, "Take a left");
        testStory.addNode(node4, 1, 3, "Turn right");
        testStory.addNode(node5, 2, 1, "Approach the figure");
        testStory.findNode(2).setChild(2, "Retreat backward", testStory.getRoot());
        testStory.addNode(node6, 3, 1, "Fight the spider");
        testStory.addNode(node7, 3, 2, "Befriend the spider");
        testStory.findNode(7).setEndNode();
        testStory.findNode(3).setChild(3, "Run back", testStory.getRoot());
        testStory.addNode(node8, 4, 1, "Pick it up for later");
        testStory.addNode(node9, 4, 2, "Drink it");
        testStory.findNode(9).setEndNode();
        testStory.addNode(node10, 4, 3, "Keep moving");
        testStory.addNode(node11, 5, 1,"Shut up and fight me fool");
        testStory.findNode(11).setEndNode();
        testStory.addNode(endNode, 5, 2, "I am looking for the holy grail");
        testStory.findNode(12).setEndNode();;
        testStory.findNode(7).setChild(1, "Continue moving", testStory.findNode(12));
        testStory.findNode(8).setChild(1, "Continue down the hallway", testStory.findNode(12));
        testStory.findNode(10).setChild(1, "Try to enter the room", testStory.findNode(12));

        return testStory;
    }

    public static void editTitle (Story storyChosen, String newTitle) throws IllegalArgumentException {
        if (newTitle == " " || newTitle == "") throw new IllegalArgumentException("Title cannot be empty");
        else if (storyChosen.getTitle().equalsIgnoreCase(newTitle))
            throw new IllegalArgumentException("New title entered is same as previous title");
        else if (!storyChosen.getTitle().equalsIgnoreCase(newTitle)){
            storyChosen = new Story(storyChosen.getID(), newTitle, storyChosen.getRoot().getStoryContent());
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        String title=null;
        Story storySelected=null;
        LinkedList<Story> storyCol=new LinkedList<Story>();
        storyCol.add(getTestStory());
        do {
            System.out.println("What you want to do? Enter a #");
            System.out.println("1. Create a story");
            System.out.println("2. Load Story");
            System.out.println("3. Exit");
            userChoice = scanner.nextInt();
            if (userChoice==1){
                Random ran = new Random();
                int id = ran.nextInt(100);
                String root=null;
                System.out.println("Title of the story: ");
                try{
                    title=scanner.next();
                }catch(IllegalArgumentException e) {
                    System.out.println("Invalid title");
                }
                System.out.println("Add root node: ");
                try{
                    root= scanner.next();
                }catch(Exception e) {
                    System.out.println("Invalid root content");
                }
                System.out.println("Tag: ");
                LinkedList<String> tag = new LinkedList<>();
                String tagEx= scanner.next();
                tag.add(0, tagEx);
                Story story1 = createStory (id, title, root, tag);
                storyCol.add(getTestStory());
            }
            else if (userChoice==2){
                System.out.println("How you want to read the story?");
                System.out.println("1. Read a story already in the database");
                System.out.println("2. Read an uploaded story");
                int userChoice2= scanner.nextInt();
                if (userChoice2 == 1) {
                    System.out.println("How you want to find the story");
                    System.out.println("1. Search through the name");
                    System.out.println("2. Search through database");
                    int storyChoice1 = scanner.nextInt();
                    if(storyChoice1==1) {
                        System.out.println("Enter story title");
                        String titleEntered = scanner.next();
                        for(int i=0;i<storyCol.size();i++) {
                            try {
                                if (titleEntered.equalsIgnoreCase(storyCol.get(i).getTitle())){
                                    storySelected=storyCol.get(i);
                                }
                            }catch (IllegalArgumentException e) {
                                System.out.println("No story exist with that title");
                                System.out.println("Please Enter a valid story title: ");
                                titleEntered = scanner.next();                             }
                        }
                        System.out.println("What do you want to do with the story? (Play/Edit)");
                        String storyChoice2 = scanner.next();
                        if (storyChoice2.equalsIgnoreCase("Play")) {
                            playStory(storySelected);
                            System.out.println("You want to play it again?(Y/N)");
                            String choice = scanner.next();
                            do {
                                playStory(storySelected);
                            }
                            while (choice.equalsIgnoreCase("N"));
                        } else if (storyChoice2.equalsIgnoreCase("Edit")) {
                            int editChoice;
                            do {
                                System.out.println("What you want to edit? ");
                                System.out.println("1. Edit the title");
                                System.out.println("2. Add new content");
                                System.out.println("3. Edit existing content");
                                System.out.println("4. Done editing");
                                editChoice = scanner.nextInt();
                                if (editChoice == 1){
                                    System.out.println("Enter new title:");
                                    String newTitle = scanner.next();
                                    editTitle(storySelected, newTitle);
                                    System.out.println("You successfully changed the title of the story");
                                } else if (editChoice == 2) {
                                    System.out.println("Enter ID:");
                                    int newID = scanner.nextInt();
                                    System.out.println("Enter new content:");
                                    String newContent = scanner.next();

                                    //ask for end node condition
                                    addNodes(newID, storySelected, newContent, false);
                                    System.out.println("You successfully added content to the story");
                                } else {
                                    System.out.println("Enter node number you want to edit:");
                                    int numEntered = scanner.nextInt();
                                    System.out.println("Enter content for edit:");
                                    String editedContent = scanner.next();
                                    editStoryContent(storySelected, editedContent, numEntered);
                                    System.out.println("You successfully changed the content of the story");
                                }
                            } while (editChoice != 4);
                        }else System.out.println("invalid input");
                    }
                    else if (storyChoice1==2) {
                        System.out.println("Enter title of the story:");
                        System.out.println(storyCol.toString());
                        String storyChoice = scanner.next();
                        for (int i = 0; i < storyCol.size(); i++) {
                            try {
                                if (storyChoice.equalsIgnoreCase(storyCol.get(i).getTitle())) {
                                    storySelected = storyCol.get(i);
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println("No story exist with that title");
                                System.out.println("Please Enter a valid story title: ");
                                storyChoice = scanner.next();
                            }
                        }
                        System.out.println("What do you want to do with the story? (Play/Edit)");
                        String storyChoice2 = scanner.next();
                        if (storyChoice2.equalsIgnoreCase("Play")) {
                            playStory(storySelected);
                            System.out.println("You want to play it again?(Y/N)");
                            String choice = scanner.next();
                            do {
                                playStory(storySelected);
                            }
                            while (choice.equalsIgnoreCase("N"));
                        } else if (storyChoice2.equalsIgnoreCase("Edit")) {
                            int editChoice;
                            do {
                                System.out.println("What you want to edit? ");
                                System.out.println("1. Edit the title");
                                System.out.println("2. Add new content");
                                System.out.println("3. Edit existing content");
                                System.out.println("4. Done editing");
                                editChoice = scanner.nextInt();
                                if (editChoice == 1) {
                                    System.out.println("Enter new title:");
                                    String newTitle = scanner.next();
                                    editTitle(storySelected, newTitle);
                                    System.out.println("You successfully changed the title of the story");
                                } else if (editChoice == 2) {
                                    System.out.println("Enter ID:");
                                    int newID = scanner.nextInt();
                                    System.out.println("Enter new content:");
                                    String newContent = scanner.next();

                                    //ask for end node condition
                                    addNodes(newID, storySelected, newContent, false);
                                    System.out.println("You successfully added content to the story");
                                } else {
                                    System.out.println("Enter node number you want to edit:");
                                    int numEntered = scanner.nextInt();
                                    System.out.println("Enter content for edit:");
                                    String editedContent = scanner.next();
                                    editStoryContent(storySelected, editedContent, numEntered);
                                    System.out.println("You successfully changed the content of the story");
                                }
                            } while (editChoice != 4);
                        } else System.out.println("invalid input");
                    }
                }else if (userChoice2 == 2) {
                    System.out.println("Enter file name");
                    String fileName = scanner.next();
                    FileReader text = new FileReader(fileName);
                    Scanner scan = new Scanner(text);
                    uploadStory(fileName);
                    //play or edit the story in function
                }
            }
        }while (userChoice!=3);
    }
}

