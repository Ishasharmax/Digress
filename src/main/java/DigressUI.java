import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class DigressUI {

    public static void createStory (int idIn, String titleIn, String rootContent, LinkedList< String > tagsIn) {
        Story story1 = new Story(idIn, titleIn, rootContent, tagsIn);
    }

    public static void playStory () {
    }

    public static void editStory (String titleIn) throws IllegalArgumentException{
    }

    public static void addNodes (String titleIn) throws IllegalArgumentException {
    }

    public static void uploadStory (String fileForUpload) throws IOException {
        int count;
        // check if File exists or not
        FileReader fileEx=null;
        try {
            fileEx = new FileReader(fileForUpload);
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        // read from FileReader till the end of file
        while ((count=fileEx.read())!=-1)
            System.out.print((char)count);
            //close the file
        fileEx.close();
    }

    public static void editTitle (String titleIn) throws IllegalArgumentException{
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        String title=null;
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
                Story story1 = new Story(id, title, root, tag);
                createStory (id, title, root, tag);}
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
                        try {
                            if (titleEntered.equalsIgnoreCase("Any Story name")) {
                                title = titleEntered;
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("No story exist with that title");
                        }
                        System.out.println("What do you want to do with the story? (Play/Edit)");
                        String storyChoice2 = scanner.next();
                        if (storyChoice2.equalsIgnoreCase("Play")) {
                            playStory();
                            System.out.println("You want to play it again?(Y/N)");
                            String choice = scanner.next();
                            do {
                                playStory();
                            }
                            while (choice.equalsIgnoreCase("N"));
                        } else if (storyChoice2.equalsIgnoreCase("Edit")) {
                            int editChoice;
                            do {
                                System.out.println("What you want to edit? ");
                                System.out.println("1. Edit the title");
                                System.out.println("2. Add new nodes");
                                System.out.println("3. Edit Existing nodes");
                                System.out.println("4. Done editing");
                                editChoice = scanner.nextInt();
                                if (editChoice == 1) editTitle(title);
                                else if (editChoice == 2) addNodes(title);
                                else editStory(title);
                            } while (editChoice != 4);
                        }else System.out.println("invalid input");
                    }
                    else if (storyChoice1==2) {
                        //collection of all the stories is displayed, not sure how to represent them
                        System.out.println("Choose a story");
                        System.out.println("1. Story1");
                        System.out.println("2. Story2");
                        System.out.println("3. Story3");
                        int storyChoice = scanner.nextInt();
                        if (storyChoice == 1) {
                            System.out.println("What do you want to do with the story? (Play/Edit)");
                            String storyChoice2 = scanner.next();
                            if (storyChoice2.equalsIgnoreCase("Play")) {
                                playStory();
                                System.out.println("You want to play it again?(Y/N)");
                                String choice = scanner.next();
                                do {
                                    playStory();
                                }
                                while (choice.equalsIgnoreCase("N"));
                            } else if (storyChoice2.equalsIgnoreCase("Edit")) {
                                int editChoice;
                                do {
                                    System.out.println("What you want to edit? ");
                                    System.out.println("1. Edit the title");
                                    System.out.println("2. Add new nodes");
                                    System.out.println("3. Edit Existing nodes");
                                    System.out.println("4. Done editing");
                                    editChoice = scanner.nextInt();
                                    if (editChoice == 1) editTitle(title);
                                    else if (editChoice == 2) addNodes(title);
                                    else editStory(title);
                                } while (editChoice != 4);
                            }else System.out.println("invalid input");
                        }
                    }
                }else if (userChoice2 == 2) {
                    System.out.println("Enter file name");
                    String fileName = scanner.next();
                    //creating file instance to reference text file in Java
                    FileReader text = new FileReader(fileName);
                    //creating scanner instance to read File in Java
                    Scanner scan = new Scanner(text);
                    uploadStory(fileName);
                    //play or edit the story in function
                }
            }
        }while (userChoice!=3);
    }
}
