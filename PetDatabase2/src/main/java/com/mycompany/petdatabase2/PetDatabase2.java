/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.petdatabase2;

/**
 *
 * @author Ziqi
 */
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class PetDatabase2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //creating two arrays to store pet names and pet ages
        String[] petNames = new String[5];
        int[] petAges = new int[5];
        int petCount = 0;
        loadPetData(petNames, petAges); // Load pet data from file

        System.out.println("Pet Database Program.");
        int choice = 0;

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllPets(petNames, petAges, petCount);
                    break;
                case 2:
                    addPet(petNames, petAges, petCount);
                    petCount = getPetCount(petNames); // Update pet count
                    break;
                case 3:
                    removePet(petNames, petAges, petCount);
                    petCount = getPetCount(petNames); // Update pet count
                    break;
                case 4:
                    savePetData(petNames, petAges, petCount);
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input, please try again.");

                    break;
            }
        } while (choice != 4);
    }
    
    //reads the pet data from file using a BufferedReader
    private static void loadPetData(String[] petNames, int[] petAges) {
    try (BufferedReader reader = new BufferedReader(new FileReader("petdata.txt"))) {
        String line;
        int petCount = 0;
        while ((line = reader.readLine()) != null && petCount < petNames.length) {
            String[] petData = line.split(",");
            String name = petData[0];
            int age = Integer.parseInt(petData[1]);
            petNames[petCount] = name;
            petAges[petCount] = age;
            petCount++;
        }
        System.out.println("Pet data loaded from file.");
    } catch (IOException e) {
        System.out.println("Error loading pet data: " + e.getMessage());
    }
}

    private static void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add new pets");
        System.out.println("3) Remove a pet");
        System.out.println("4) Exit program");
        System.out.print("\nYour choice: ");
    }

    //case 1
    private static void viewAllPets(String[] petNames, int[] petAges, int petCount) {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME    | AGE |");
        System.out.println("+----------------------+");
        for (int i = 0; i < petCount; i++) {
            //Using System.out.printf() to format the table
            //The ID is the index in the array
            System.out.printf("| %2d | %-7s | %3d |\n", i, petNames[i], petAges[i]);
        }
        System.out.println("+----------------------+");
        System.out.println(petCount + " rows in set.");
    }

    //case 2
    //Let users add pets; name and age. No more than 5
    private static void addPet(String[] petNames, int[] petAges, int petCount) {
        Scanner scanner = new Scanner(System.in);
        int petsAdded = 0;

        while (true) {
            System.out.print("add pet (name, age): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            if (petCount >= petNames.length) {
                System.out.println("Error: Database is full.");
                break;
            }
            //for splitting the user input
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Error: " + input + " is not a valid input.");
                continue;
            }

            String name = parts[0];
            String ageString = parts[1];

          
            int age = Integer.parseInt(ageString);

            if (age < 1 || age > 20) {
                System.out.println("Error: " + ageString + " is not a valid input.");
                continue;
            }

            petNames[petCount] = name;
            petAges[petCount] = age;
            petCount++;
            petsAdded++;
        }

        if (petsAdded > 0) {
            System.out.printf("%d pet added.\n", petsAdded);
        }
    }

    private static void removePet(String[] petNames, int[] petAges, int petCount) {
        Scanner scanner = new Scanner(System.in);
        
        viewAllPets(petNames, petAges, petCount);

        System.out.print("Enter the pet ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id < 0 || id >= petCount) {
            System.out.println("Error: ID " + id + " does not exist.");
            return;
        }

        String removedPetName = petNames[id];
        int removedPetAge = petAges[id];

        //shift the array elements to remove the pet
        for (int i = id; i < petCount - 1; i++) {
            petNames[i] = petNames[i + 1];
            petAges[i] = petAges[i + 1];
        }

        //reset the last element in the arrays
        petNames[petCount - 1] = null;
        petAges[petCount - 1] = 0;

        petCount--;

        System.out.println(removedPetName + " " + removedPetAge + " is removed.");
    }

    private static int getPetCount(String[] petNames) {
        int count = 0;
        for (String name : petNames) {
            if (name != null) {
                count++;
            }
        }
        return count;
    }

    private static void savePetData(String[] petNames, int[] petAges, int petCount) {
       //use a FileWriter to write the pet data to a file
        try ( FileWriter writer = new FileWriter("petdata.txt")) {
            for (int i = 0; i < petCount; i++) {
                //Each line in the file represents a pet's data, with the name and age separated by a comma.
                String petData = petNames[i] + "," + petAges[i] + "\n";
                writer.write(petData);
            }
            System.out.println("Pet data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving pet data: " + e.getMessage());
        }
    }
}
