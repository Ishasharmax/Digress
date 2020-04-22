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

    public static void playStory(Story storyChosen){
        System.out.println(storyChosen.getRootContent());
    }

    public static void editStoryContent (Story storyChosen, String newContent, int contentToEdit) throws IllegalArgumentException{
        storyChosen.editNodeStoryContent(contentToEdit, newContent);
    }

    public static void addNodes (int choiceVal, Story storyChosen, String newContent, String condition) throws IllegalArgumentException {
        storyChosen.addNode(newContent, storyChosen.getRoot().getId(), choiceVal, condition);
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
                storyCol.add(story1);
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
                                titleEntered = scanner.next();
                            }
                        }
                        System.out.println("What do you want to do with the story? (Play/Edit)");
                        String storyChoice2 = scanner.next();
                        if (storyChoice2.equalsIgnoreCase("Play")) {
                            String choice;
                            do{
                                playStory(storySelected);
                                System.out.println("You want to play it again?(Y/N)");
                                choice = scanner.next();
                            } while(!choice.equalsIgnoreCase("N"));
                        }else if (storyChoice2.equalsIgnoreCase("Edit")) {
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
                                    System.out.println("Enter condition:");
                                    String condition = scanner.next();
                                    addNodes(newID, storySelected, newContent, condition);
                                    System.out.println("You successfully added content to the story");
                                }else if (editChoice == 3){
                                    System.out.println("Enter node number you want to edit:");
                                    int numEntered = scanner.nextInt();
                                    System.out.println("Enter content for edit:");
                                    String editedContent = scanner.next();
                                    editStoryContent(storySelected, editedContent, numEntered);
                                    System.out.println("You successfully changed the content of the story");
                                }
                            }while (editChoice != 4);
                        }else System.out.println("invalid input");
                    }
                    else if (storyChoice1==2) {
                        for (int i = 0; i < storyCol.size(); i++) {
                            System.out.println(storyCol.get(i).getTitle());
                        }
                        System.out.println("Enter title of the story:");
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
                                    int choiceVal = scanner.nextInt();
                                    System.out.println("Enter new content:");
                                    String newContent = scanner.next();
                                    System.out.println("Enter condition content:");
                                    String condition = scanner.next();
                                    addNodes(choiceVal, storySelected, newContent, condition);
                                    System.out.println("You successfully added content to the story");
                                } else if (editChoice == 3){
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
                    }else if (storyChoice2.equalsIgnoreCase("Edit")) {
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
                                int choiceVal = scanner.nextInt();
                                System.out.println("Enter new content:");
                                String newContent = scanner.next();
                                System.out.println("Enter new condition:");
                                String condition = scanner.next();
                                addNodes(choiceVal, storySelected, newContent, condition);
                                System.out.println("You successfully added content to the story");
                            }else if (editChoice == 3){
                                System.out.println("Enter node number you want to edit:");
                                int numEntered = scanner.nextInt();
                                System.out.println("Enter content for edit:");
                                String editedContent = scanner.next();
                                editStoryContent(storySelected, editedContent, numEntered);
                                System.out.println("You successfully changed the content of the story");
                            }
                        }while (editChoice != 4);
                    }else System.out.println("Invalid input");
                }
            }
        }while (userChoice!=3);
    }
}

