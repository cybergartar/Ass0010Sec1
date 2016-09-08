package Ass0010Sec1;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Scanner;

class UI {

    private static Scanner input = new Scanner(System.in);
    private static char choice;

    int printMainMenu(){
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

    private void printStudentBar(){
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

    Student printStudentLogin(){
        String id, name, surname;
        boolean validLogin;
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

    int printStudentMenu(String studentName){
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

    int printTeacherMenu(){
        clearConsole();
        for(int i = 0; i < 80; i++)
            System.out.print("=");
        System.out.println();

        System.out.println("|_   _|__  __ _  ___| |__   ___ _ __ ");
        System.out.println("  | |/ _ \\/ _` |/ __| '_ \\ / _ \\ '__|");
        System.out.println("  | |  __/ (_| | (__| | | |  __/ | ");
        System.out.println("  |_|\\___|\\__,_|\\___|_| |_|\\___|_|");

        for(int i = 0; i < 80; i++)
            System.out.print("=");
        System.out.println();

        System.out.println("Hello Teacher!, What do you want to do today?");
        System.out.println("1. Grading");
        System.out.println("2. Print grade summary");
        System.out.println("0. Return to main menu");
        System.out.print("Enter number (0-2): ");

        choice = input.next().charAt(0);

        if(!isValidChoice(choice, '2')){
            printError("Invalid input! (Accept only 0 to 2)");
            return -1;
        }
        return (choice - '0');
    }

    void printAllSubjects(ArrayList<Subject.SubjectInfo> subject){
        clearConsole();
        System.out.println("+------------+-------------------------------------------------------+---------+");
        System.out.println("| Subject ID |                        Name                           | Credits |");
        System.out.println("+------------+-------------------------------------------------------+---------+");

        for(Subject.SubjectInfo i : subject){
            System.out.print("|  " + i.id + "  | " + i.name);
            for(int j = 0; j < 54-i.name.length(); j++)
                System.out.print(" ");
            System.out.println("|    " + i.credits + "    |");
        }

        System.out.println("+------------+-------------------------------------------------------+---------+");

    }

    private void printSummaryEnroll(Student student){
        printAllSubjects(student.getEnrolledSubject());
        System.out.println("You have enrolled " + student.getEnrolledSubject().size() + " subjects and cost " + student.getCredits() +" credits.");
        waitEnterKey("continue");
    }

    private void printStudentList(ArrayList<Student> students){
        clearConsole();
        System.out.println("+------------+-------------------------------------------------------+---------+");
        System.out.println("| Student ID |                   Name - Surname                      |  Grade  |");
        System.out.println("+------------+-------------------------------------------------------+---------+");

        for(Student s : students){
            if(s.isSubmitted()){
                System.out.print("|  " + s.getId() + "  | " + s.getName() + " " + s.getSurname());
                for(int j = 0; j < 54-(s.getName().length() + s.getSurname().length() + 1); j++)
                    System.out.print(" ");
                if(s.isGraded())
                    System.out.printf("|   %.2f  |\n", s.getGrade());
                else
                    System.out.println("|   N/A   |");
            }
        }

        System.out.println("+------------+-------------------------------------------------------+---------+");
    }

    int enrollMenu(Student student){
        System.out.println();
        System.out.println("Your current credits is " + student.getCredits() + "/23");
        System.out.print("Please enter subject ID you want to enroll, type \"See\" to see enrolled subject or \"Fin\" to finish : ");

        String command = input.next();

        if(command.equals("See"))
            if(student.getEnrolledSubject().size() == 0)
                printError("You haven't enrolled any subject yet!");
            else
                printAllSubjects(student.getEnrolledSubject());
        else if(command.equals("Fin"))
            if(student.getCredits() < 9)
                printError("Your current credits are less than 9. Please enroll more");
            else{
                printSummaryEnroll(student);
                return 1;
            }
        else{
            boolean found = false;
            for(Subject.SubjectInfo i : Subject.subjects){
                if(i.id.equals(command)){
                    found = true;
                    boolean isEnrolled = false;
                    for(Subject.SubjectInfo j : student.getEnrolledSubject())
                        if(j.id.equals(command)){
                            printError("You have enrolled this subject!");
                            isEnrolled = true;
                        }
                    if(!isEnrolled){
                        student.enroll(i);
                        student.setEnrollStatus(true);
                    }
                }

            }
            if(!found)
                printError("Subject not found or invalid subject ID!");
        }
        return -1;
    }

    void addMenu(Student student){
        boolean isFinished = false;
        do {
            System.out.println();
            System.out.println("Your current credits is " + student.getCredits() + "/23");
            System.out.print("Please enter subject ID you want to add or type \"See\" to see enrolled subject or type \"Cancel\" to go back: ");
            String command = input.next();
            if(command.equals("See"))
                printAllSubjects(student.getEnrolledSubject());
            else if(command.equals("Cancel"))
                isFinished = true;
            else{
                boolean found = false;
                for(Subject.SubjectInfo i : Subject.subjects){
                    if(i.id.equals(command)){
                        found = true;
                        boolean isEnrolled = false;
                        for(Subject.SubjectInfo j : student.getEnrolledSubject())
                            if(j.id.equals(command)){
                                printError("You have enrolled this subject!");
                                isEnrolled = true;
                            }
                        if(!isEnrolled){
                            student.enroll(i);
                            student.setEnrollStatus(true);
                            isFinished = true;
                            System.out.println("Add complete!");
                            waitEnterKey("continue");
                        }
                    }

                }
                if(!found)
                    printError("Subject not found or invalid subject ID!");
            }

        } while(!isFinished);

        printSummaryEnroll(student);
    }

    void changeMenu(Student student){
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
                printError("You have not enrolled this subject or invalid subject ID!");
                validFind = false;
            }

        } while(!validFind);

        do{
            validReplace = true;
            System.out.println();
            System.out.print("Please enter subject ID of which you want to change to: ");
            replaceID = input.next();
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
                printError("Subject not found or invalid subject ID!");
                validReplace = false;
            }
        } while(!validReplace);

        System.out.println("Change complete!");
        waitEnterKey("continue");
        printSummaryEnroll(student);

    }

    void removeMenu(Student student){
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

            boolean found = false;
            for(Subject.SubjectInfo i : student.getEnrolledSubject()){
                if(i.id.equals(removeID)) {
                    if(student.getCredits() - i.credits < 9){
                        printError("Cannot remove! Your credits will less than 9");
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
                printError("You have not enrolled this subject or invalid subject ID!");
                validRemove = false;
            }

        } while(!validRemove);

        System.out.println("Remove complete!");
        waitEnterKey("continue");
        printSummaryEnroll(student);

    }

    int submitMenu(){
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

    int gradingMenu(ArrayList<Student> students){
        printStudentList(students);
        System.out.print("Please enter student ID of whom you want to grade or type \"Cancel\" to go back: ");
        String command = input.next();
        if(command.equals("Cancel"))
            return 1;

        boolean found = false;
        for(Student s : students){
            if(s.isSubmitted() && s.getId().equals(command)){
                found = true;
                if(!s.isGraded()){
                    gradeTable(s);
                    System.out.println("Student " + s.getId() + " " + s.getName() + " " + s.getSurname() + " is now graded with " + s.getGrade() + " points.");
                    waitEnterKey("continue");
                }
                else{
                    printError("You have graded this student!");
                    return -1;
                }
            }

        }
        if(!found)
            printError("No student with that ID or invalid student ID!");
        return -1;
    }

    private void gradeTable(Student student){
        double grade = 0;

        clearConsole();
        System.out.println("+------------+-------------------------------------------------------+---------+");
        System.out.println("| Subject ID |                        Name                           |  Grade  |");
        System.out.println("+------------+-------------------------------------------------------+---------+");

        for(Subject.SubjectInfo i : student.getEnrolledSubject()){
            boolean validGrade;
            do{
                validGrade = true;
                System.out.print("|  " + i.id + "  | " + i.name);
                for(int j = 0; j < 54-i.name.length(); j++)
                    System.out.print(" ");
                System.out.print("|    ");
                char gradeChar = Character.toUpperCase(input.next().charAt(0));
                if(gradeChar < 'A' || gradeChar > 'F'){
                    printError("Invalid grade. Accepts only A, B, C, D or F");
                    validGrade = false;
                }
                else{
                    switch (gradeChar){
                        case 'A': grade += 4.00; break;
                        case 'B': grade += 3.00; break;
                        case 'C': grade += 2.00; break;
                        case 'D': grade += 1.00; break;
                        case 'F': grade += 0.00; break;
                    }
                    i.setGrade(gradeChar);
                }
            }while(!validGrade);
        }

        System.out.println("+------------+-------------------------------------------------------+---------+");

        student.setGrade(grade / student.getEnrolledSubject().size());
        student.setGradeStatus(true);
    }

    int gradeSummaryMenu(ArrayList<Student> students){
        printStudentList(students);
        System.out.print("Please enter student ID of whom you want to see detail or type \"Back\" to go back: ");
        String command = input.next();
        if(command.equals("Back"))
            return 1;

        boolean found = false;
        for(Student s : students){
            if(s.isSubmitted() && s.getId().equals(command)){
                found = true;
                if(s.isGraded()){
                    clearConsole();
                    System.out.println("+------------+-------------------------------------------------------+---------+");
                    System.out.println("| Subject ID |                        Name                           |  Grade  |");
                    System.out.println("+------------+-------------------------------------------------------+---------+");

                    for(Subject.SubjectInfo i : s.getEnrolledSubject()){
                        System.out.print("|  " + i.id + "  | " + i.name);
                        for(int j = 0; j < 54-i.name.length(); j++)
                            System.out.print(" ");
                        System.out.println("|    " + i.getGrade() + "    |");
                    }
                    System.out.println("+------------+-------------------------------------------------------+---------+");
                    System.out.println("Student " + s.getId() + " " + s.getName() + " " + s.getSurname() + " has " + s.getGrade() + " points.");
                    waitEnterKey("continue");
                }
                else{
                    printError("You haven't graded this student yet!");
                    return -1;
                }
            }
        }
        if(!found)
            printError("No student with that ID or invalid student ID!");
        return -1;
    }

    private void clearConsole(){
        try {
            final String os = System.getProperty("os.name");

            if(os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033[H\033[2J");
            System.out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidChoice(char choice, char maxBound){
        return !(!Character.isDigit(choice) || (choice > maxBound || choice < '0'));
    }

    void printBestDimensionNotifier(){
        System.out.println("This program run best on command line or terminal with 80*20 dimension");
        waitEnterKey("continue");
    }

    void printError(String errMessage){
        System.out.println();
        System.out.println(errMessage);
        waitEnterKey("try again");
    }

    private void waitEnterKey(String toWhat){
        System.out.println("Press ENTER to "+ toWhat + "....");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
