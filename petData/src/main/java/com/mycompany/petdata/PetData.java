/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.petdata;

/**
 *
 * @author Ziqi
 */
import java.util.ArrayList;
import java.util.Scanner;

public class PetData {

    public static void main(String[] args) {
        //create a new arrayList called pets, which holds objects of the class Pet.
        ArrayList<Pet> pets = new ArrayList<Pet>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pet Database Program.");
        boolean exit = false;

        while (true) {
            displayMenu();

            //Error handling
            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    // code that handles the exception
                    System.out.println("Invalid input, please try again.");
                    System.out.print("Your choice: ");
                }
            }

            //use switch() method to let user to choose
            switch (choice) {
                case 1:
                    viewAllPets(pets);
                    break;
                case 2:
                    addPet(pets);
                    break;
                case 3:
                    updatePet(pets);
                    break;
                case 4:
                    removePet(pets);
                    break;
                case 5:
                    searchByName(pets);
                    break;
                case 6:
                    searchByAge(pets);
                    break;
                case 7:
                    exit = true;
                    System.out.print("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
            //case 7 will trigger this if statement
            if (exit) {
                break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        System.out.println("3) Update an existing pet");
        System.out.println("4) Remove an existing pet");
        System.out.println("5) Search pets by name");
        System.out.println("6) Search pets by age");
        System.out.println("7) Exit program");
        System.out.print("\nYour choice: ");
    }

    //case 1
    private static void viewAllPets(ArrayList<Pet> pets) {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        for (int i = 0; i < pets.size(); i++) {
            //The ID is the index in the array the object is located
            Pet pet = pets.get(i);
            //Using System.out.printf() to format the table
            System.out.printf("|  %d | %s     |  %d |\n", i, pet.getName(), pet.getAge());
        }

        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", pets.size());
    }

    //case 2
    //Add pets let the user add as many pets as they want
    private static void addPet(ArrayList<Pet> pets) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int i = 0;
        while (!exit) {
            System.out.print("add pet (name, age): ");
            String input = scanner.nextLine();

            //The user type “done” to end reading
            if (input.equals("done")) {
                exit = true;
            } else {
                //for splitting the user input
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    Pet pet = new Pet(name, age);
                    pets.add(pet);
                    i += 1;
                } else {
                    System.out.println("Invalid input, please try again.");
                }
            }
        }
        //System.out.printf("%d pet added.\n", pets.size());
        System.out.printf("%d pet added.\n", i);
    }

    //case 5
    //Search pets by name 
    private static void searchByName(ArrayList<Pet> pets) {
        Scanner scanner = new Scanner(System.in);
        //Prompts the user for a name and then displays a table showing all pets matching the name
        System.out.print("\nEnter the name to search: ");
        String name = scanner.nextLine();
        int i = 0;
        boolean exit = false;
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                if (!exit) {
                    System.out.println("+----------------------+");
                    System.out.println("| ID | NAME      | AGE |");
                    System.out.println("+----------------------+");
                    exit = true;
                }
                System.out.printf("|  %d | %s     |  %d |\n", pets.indexOf(pet), pet.getName(), pet.getAge());
                i += 1;
            }
        }
        if (i == 0) {
            System.out.println("No pets found with that name.");
        } else {
            System.out.println("+----------------------+");
            System.out.printf("%d rows in set.\n", i);
        }
    }

    //case 6
    //Search pets by age
    private static void searchByAge(ArrayList<Pet> pets) {
        Scanner scanner = new Scanner(System.in);
        //Prompts the user for an age and shows a table consists of pets with that age
        System.out.print("\nEnter the age to search: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        boolean exit = false;
        int i = 0;
        for (Pet pet : pets) {
            if (pet.getAge() == age) {
                if (!exit) {
                    System.out.println("+----------------------+");
                    System.out.println("| ID | NAME      | AGE |");
                    System.out.println("+----------------------+");
                    exit = true;
                }
                System.out.printf("|  %d | %s     |  %d |\n", pets.indexOf(pet), pet.getName(), pet.getAge());
                i += 1;
            }
        }
        if (i == 0) {
            System.out.println("No pets found with that name.");
        } else {
            System.out.println("+----------------------+");
            System.out.printf("%d rows in set.\n", i);
        }
    }

    private static void updatePet(ArrayList<Pet> pets) {
        Scanner scanner = new Scanner(System.in);
        //Shows the user a table of all the pets 
        viewAllPets(pets);

        System.out.print("Enter the ID of the pet to update: ");
        //reads the pet ID that the user wants to update.
        int id = scanner.nextInt();
        scanner.nextLine();

        //if the id is valid
        if (id >= 0 && id < pets.size()) {
            Pet pet = pets.get(id);//get id from user prompt
            String oldName = pet.getName();
            int oldAge = pet.getAge();

            System.out.print("Enter new name and new age: ");
            String input = scanner.nextLine();
            //split the input into an array of strings using the split() method
            String[] parts = input.split(" ");
            if (parts.length == 2) {
                String newName = parts[0];
                int newAge = Integer.parseInt(parts[1]);
                pet.setName(newName);
                pet.setAge(newAge);
                //%s is used to print a string
                //%d is used to print an integer
                System.out.printf("%s %d changed to %s %d.\n\n", oldName, oldAge, newName, newAge);

            } else {
                System.out.println("Invalid input.");
            }
        } else {
            System.out.println("Invalid ID, no pet found.");
        }
    }

    private static void removePet(ArrayList<Pet> pets) {
        Scanner scanner = new Scanner(System.in);
        //Shows the user a table of all the pets 
        viewAllPets(pets);

        System.out.print("Enter the ID of the pet to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        //make sure the id is valid
        if (id >= 0 && id < pets.size()) {
            //get id before the remove() from user prompt
            Pet pet = pets.get(id);
            //get name and age
            String name = pet.getName();
            int age = pet.getAge();
            //use remove()
            pets.remove(id);

            System.out.printf("%s %d is removed.\n\n", name, age);
        } else {
            System.out.println("Invalid ID, no pet found.");
        }
    }

}
