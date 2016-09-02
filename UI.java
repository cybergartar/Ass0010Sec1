package Ass0010Sec1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class UI {

    private static Scanner input = new Scanner(System.in);
    private static char choice;

    static int printMainMenu(){
        clearConsole();
        for(int i = 0; i < 80; i++)
            System.out.print("=");
        System.out.println();

        System.out.println("|  _ \\| ____/ ___| | |/ /  \\/  |_ _|_   _| |");
        System.out.println("| |_) |  _|| |  _  | ' /| |\\/| || |  | | | |    ");
        System.out.println("|  _ <| |__| |_| | | . \\| |  | || |  | | | |___");
        System.out.println("|_| \\_\\_____\\____| |_|\\_\\_|  |_|___| |_| |_____|");

        for(int i = 0; i < 80; i++)
            System.out.print("=");
        System.out.println();

        System.out.println("Please select your role");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("0. Exit program");
        System.out.print("Enter number (0-2): ");

        choice = input.next().charAt(0);

        if(!isValidChoice(choice, '2')){
            printError("Invalid input! (Accept only 0 to 2)");
            return -1;
        }
        return (choice - '0');

    }

    static Student printStudentLogin(){
        String id, name, surname;
        boolean validLogin = false;
        do{
            clearConsole();
            for(int i = 0; i < 80; i++)
                System.out.print("=");
            System.out.println();

            System.out.println("/ ___|| |_ _   _  __| | ___ _ __ | |");
            System.out.println("\\___ \\| __| | | |/ _` |/ _ \\ '_ \\| __|");
            System.out.println(" ___) | |_| |_| | (_| |  __/ | | | |");
            System.out.println("|____/ \\__|\\__,_|\\__,_|\\___|_| |_|\\__|");

            for(int i = 0; i < 80; i++)
                System.out.print("=");
            System.out.println();

            System.out.println("Please enter student ID, name, and surname");
            System.out.print("Student ID: ");   id = input.next();
            System.out.print("Name: ");   name = input.next();
            System.out.print("Surname: ");   surname = input.next();
            validLogin = true;

            if(id.length() != 8)
                validLogin = false;
            else
                for (int i = 0; i < 8; i++)
                    if(!Character.isDigit(id.charAt(i)))
                        validLogin = false;

            if(!validLogin)
                printError("Invalid student ID!");

        }while (!validLogin);

        return new Student(id, name, surname);
    }

    static int printStudentMenu(String studentName){
        clearConsole();
        for(int i = 0; i < 80; i++)
            System.out.print("=");
        System.out.println();

        System.out.println("/ ___|| |_ _   _  __| | ___ _ __ | |");
        System.out.println("\\___ \\| __| | | |/ _` |/ _ \\ '_ \\| __|");
        System.out.println(" ___) | |_| |_| | (_| |  __/ | | | |");
        System.out.println("|____/ \\__|\\__,_|\\__,_|\\___|_| |_|\\__|");

        for(int i = 0; i < 80; i++)
            System.out.print("=");
        System.out.println();

        System.out.println("Hello " + studentName + "!, What do you want to do today?");
        System.out.println("1. Enroll");
        System.out.println("2. Add Subject");
        System.out.println("3. Change Subject");
        System.out.println("4. Remove Subject");
        System.out.println("5. Submit");
        System.out.println("0. Return to main menu");
        System.out.print("Enter number (0-5): ");

        choice = input.next().charAt(0);

        if(!isValidChoice(choice, '5')){
            printError("Invalid input! (Accept only 0 to 5)");
            return -1;
        }
        return (choice - '0');

    }

    static void printAllSubjects(){
        ArrayList<Subject.SubjectInfo> subjects = Subject.getSubjects();
        clearConsole();
        System.out.println("╔════════════╦═══════════════════════════════════════════════════════╦═════════╗");
        System.out.println("║ Subject ID ║                        Name                           ║ Credits ║");
        System.out.println("╠════════════╬═══════════════════════════════════════════════════════╬═════════╣");

        for(Subject.SubjectInfo i : subjects){
            System.out.print("║  " + i.id + "  ║ " + i.name);
            for(int j = 0; j < 54-i.name.length(); j++)
                System.out.print(" ");
            System.out.println("║    " + i.credits + "    ║");
        }

        System.out.println("╚════════════╩═══════════════════════════════════════════════════════╩═════════╝");

    }
    private static void clearConsole(){
        try {
            final String os = System.getProperty("os.name");

            if(os.contains("Windows"))
                Runtime.getRuntime().exec("cls");
            else
                System.out.print("\033[H\033[2J");
                    System.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidChoice(char choice, char maxBound){
        if(!Character.isDigit(choice) || (choice > maxBound || choice < '0'))
            return false;

        return true;
    }

    static void printBestDimensionNotifier(){
        System.out.println("This program run best on command line or terminal with 80*20 dimension");
        System.out.println("Press ENTER to continue....");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void printError(String errMessage){
        System.out.println(errMessage);
        System.out.println("Press ENTER to try again....");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
