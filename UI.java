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

    private static void printStudentBar(){
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
    }

    static Student printStudentLogin(){
        String id, name, surname;
        boolean validLogin = true;
        do{
            validLogin = true;
            printStudentBar();

            System.out.println("Please enter student ID, name, and surname or type \"Back\" to go back");
            System.out.print("Student ID: ");   id = input.next();
            if(id.equals("Back")){
                return new Student("00000000", "", "");
            }
            System.out.print("Name: ");   name = input.next();
            System.out.print("Surname: ");   surname = input.next();

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
        printStudentBar();

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

    static void printAllSubjects(ArrayList<Subject.SubjectInfo> subject){
        clearConsole();
        System.out.println("╔════════════╦═══════════════════════════════════════════════════════╦═════════╗");
        System.out.println("║ Subject ID ║                        Name                           ║ Credits ║");
        System.out.println("╠════════════╬═══════════════════════════════════════════════════════╬═════════╣");

        for(Subject.SubjectInfo i : subject){
            System.out.print("║  " + i.id + "  ║ " + i.name);
            for(int j = 0; j < 54-i.name.length(); j++)
                System.out.print(" ");
            System.out.println("║    " + i.credits + "    ║");
        }

        System.out.println("╚════════════╩═══════════════════════════════════════════════════════╩═════════╝");

    }

    static int enrollMenu(Student student){
        System.out.println();
        System.out.println("Your current credits is " + student.getCredits() + "/23");
        System.out.print("Please enter subject ID you want to enroll, type \"See\" to see enrolled subject or \"Fin\" to finish : ");

        String command = input.next();

        if(command.equals("See"))
            if(student.getEnrolledSubject().size() == 0)
                UI.printError("You haven't enrolled any subject yet!");
            else
                printAllSubjects(student.getEnrolledSubject());
        else if(command.equals("Fin"))
            if(student.getCredits() < 9)
                UI.printError("Your current credits are less than 9. Please enroll more");
            else
                return 1;
        else{
            boolean validID = true;
            if(command.length() != 8)
                UI.printError("Invalid subject ID!");
            else{
                for (int i = 0; i < 8; i++)
                    if(!Character.isDigit(command.charAt(i))) {
                        UI.printError("Invalid subject ID!");
                        validID = false;
                        break;
                }
                if(validID){
                    boolean found = false;
                    for(Subject.SubjectInfo i : Subject.subjects){
                        if(i.id.equals(command)){
                            found = true;
                            boolean isEnrolled = false;
                            for(Subject.SubjectInfo j : student.getEnrolledSubject())
                                if(j.id.equals(command)){
                                    UI.printError("You have enrolled this subject!");
                                    isEnrolled = true;
                                }
                            if(!isEnrolled){
                                student.enroll(i);
                                student.setEnrollStatus(true);
                            }
                        }

                  }
                  if(!found)
                      UI.printError("Subject not found!");
                }
            }


        }
        return -1;
    }

    static void addMenu(Student student){
        boolean isFinished = false;
        do {
            System.out.println();
            System.out.println("Your current credits is " + student.getCredits() + "/23");
            System.out.print("Please enter subject ID you want to add or type \"See\" to see enrolled subject or type \"Cancel\" to go back: ");
            String command = input.next();
            if(command.equals("See"))
                UI.printAllSubjects(student.getEnrolledSubject());
            else if(command.equals("Cancel"))
                isFinished = true;
            else{
                boolean validID = true;
                if(command.length() != 8)
                    UI.printError("Invalid subject ID!");
                else{
                    for (int i = 0; i < 8; i++)
                        if(!Character.isDigit(command.charAt(i))) {
                            UI.printError("Invalid subject ID!");
                            validID = false;
                            break;
                        }
                    if(validID){
                        boolean found = false;
                        for(Subject.SubjectInfo i : Subject.subjects){
                            if(i.id.equals(command)){
                                found = true;
                                boolean isEnrolled = false;
                                for(Subject.SubjectInfo j : student.getEnrolledSubject())
                                    if(j.id.equals(command)){
                                        UI.printError("You have enrolled this subject!");
                                        isEnrolled = true;
                                    }
                                if(!isEnrolled){
                                    student.enroll(i);
                                    student.setEnrollStatus(true);
                                    isFinished = true;
                                    System.out.println("Add complete! Press ENTER to continue");
                                    try {
                                        System.in.read();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }
                        if(!found)
                            UI.printError("Subject not found!");
                    }
                }
            }


        } while(!isFinished);



    }

    static void changeMenu(Student student){
        String findID, replaceID;
        boolean validFind, validReplace;

        printAllSubjects(student.getEnrolledSubject());

        do{
            validFind = true;
            System.out.println();
            System.out.print("Please enter subject ID of which you want to change or type \"Cancel\" to cancel: ");
            findID = input.next();
            if(findID.equals("Cancel"))
                return;
            for (int i = 0; i < 8; i++)
                if(!Character.isDigit(findID.charAt(i))) {
                    UI.printError("Invalid subject ID!");
                    validFind = false;
                    break;
                }
            if(validFind){
                boolean found = false;
                for(Subject.SubjectInfo i : student.getEnrolledSubject()){
                    if(i.id.equals(findID)) {
                        student.remove(i);
                        found = true;
                        validFind = true;
                        break;
                    }
                }
                if(!found) {
                    UI.printError("You have not enrolled this subject!");
                    validFind = false;
                }
            }

        } while(!validFind);

        do{
            validReplace = true;
            System.out.println();
            System.out.print("Please enter subject ID of which you want to change to: ");
            replaceID = input.next();
            for (int i = 0; i < 8; i++)
                if(!Character.isDigit(replaceID.charAt(i))) {
                    UI.printError("Invalid subject ID!");
                    validReplace = false;
                    break;
                }
            if(validReplace){
                boolean found = false;
                for(Subject.SubjectInfo i : Subject.subjects){
                    if(i.id.equals(replaceID)) {
                        student.enroll(i);
                        found = true;
                        validReplace = true;
                        break;
                    }
                }
                if(!found) {
                    UI.printError("Subject not found!");
                    validReplace = false;
                }
            }
        } while(!validReplace);

        System.out.println("Change complete! Press ENTER to continue");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void removeMenu(Student student){
        String removeID;
        boolean validRemove;

        printAllSubjects(student.getEnrolledSubject());
        do{
            validRemove = true;
            System.out.println();
            System.out.print("Please enter subject ID of which you want to remove or type \"Cancel\" to cancel: ");
            removeID = input.next();
            if(removeID.equals("Cancel"))
                return;
            for (int i = 0; i < 8; i++)
                if(!Character.isDigit(removeID.charAt(i))) {
                    UI.printError("Invalid subject ID!");
                    validRemove = false;
                    break;
                }
            if(validRemove){
                boolean found = false;
                for(Subject.SubjectInfo i : student.getEnrolledSubject()){
                    if(i.id.equals(removeID)) {
                        if(student.getCredits() - i.credits < 9){
                            UI.printError("Cannot remove! Your credits will less than 9");
                            validRemove = false;
                        }
                        else{
                            student.remove(i);
                            validRemove = true;
                        }
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    UI.printError("You have not enrolled this subject!");
                    validRemove = false;
                }
            }

        } while(!validRemove);

        System.out.println("Remove complete! Press ENTER to continue");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static int submitMenu(){
        printStudentBar();

        System.out.println("Are you sure to do this? This action cannot be undone");
        System.out.println("1. I'm sure!");
        System.out.println("2. No");
        System.out.print("Enter number (1 or 2): ");

        choice = input.next().charAt(0);

        if(!isValidChoice(choice, '2')){
            printError("Invalid input! (Accept only 1 or 2)");
            return -1;
        }
        return (choice - '0');
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
        System.out.println();
        System.out.println(errMessage);
        System.out.println("Press ENTER to try again....");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
