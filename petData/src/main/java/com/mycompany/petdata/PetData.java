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
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Update an existing pet");
            System.out.println("4) Remove an existing pet");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit program");
            System.out.print("\nYour choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            //use switch() method to let user to choose
            switch (choice) {
                case 1:
                    viewAllPets(pets);
                    break;
                case 2:
                    addPet(pets);
                    break;
                case 3:
                    //updatePet(pets);
                    break;
                case 4:
                    //removePet(pets);
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
            if (exit) {
                break;
            }
        }
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

}
