package SpellChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        LinearProbingHash<String> hash = null;

        try {
            hash = loadHashFromFile("dict.txt");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


        while(option != 6) {
            System.out.println("1. Load another file");
            System.out.println("2. Insert a word");
            System.out.println("3. Delete a word");
            System.out.println("4. Search a word");
            System.out.println("5. Check a file");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Try to load another file: ");
                    String fileToLoad = scanner.next();
                    try {
                        hash = loadHashFromFile(fileToLoad);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    System.out.println("Word to insert: ");
                    String wordToInsert = scanner.next();
                    try {
                        hash.insert(wordToInsert);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 3:
                    System.out.println("Word to delete: ");
                    String wordToDelete = scanner.next();
                    try {
                        hash.delete(wordToDelete);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 4:
                    System.out.println("Word to search: ");
                    String wordToSearch = scanner.next();
                    String result = "";
                    try {
                        result = hash.find(wordToSearch);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println(result + " is found.");
                    break;
                case 5:
                    System.out.println("File to check: ");
                    String fileToCheck = scanner.next();
                    try {
                        exportErrors(hash,fileToCheck);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
            }
        }


    }

    public static LinearProbingHash<String> loadHashFromFile(String name) throws FileNotFoundException {
        LinearProbingHash<String> hash = new LinearProbingHash<>(3000);
        File file = new File(name);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            hash.insert(line);
        }
        return hash;
    }

    public static void exportErrors(LinearProbingHash<String> hash, String fileForCheckName) throws Exception {
        File file = new File("checkedList.txt");
        FileWriter writer = new FileWriter(file);

        File fileForCheck = new File(fileForCheckName);
        Scanner scanner = new Scanner(fileForCheck);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            String found = hash.find(line);
            if(found == null){
                System.out.println(line + " is not found.");
                writer.write(line + " is not found.\n");
            }
        }


        writer.close();

        System.out.println("Checking is completed.");
    }
}