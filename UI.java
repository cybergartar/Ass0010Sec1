package Ass0010Sec1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class UI {

    private static Scanner input = new Scanner(System.in);
    private static char choice;

    int printMainMenu(){    // print select role menu
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

        if(!isValidChoice(choice, '2')){ // validate choice
            printError("Invalid input! (Accept only 0 to 2)");
            return -1;
        }
        return (choice - '0');

    }

    private void printStudentBar(){ // print student top bar
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

    Student printStudentLogin(){ // print student login menu
        String id, name, surname;
        boolean validLogin;

        do{
            validLogin = true;
            printStudentBar();

            System.out.println("Please enter student ID, name, and surname or type \"Back\" to go back");
            System.out.print("Student ID: ");   id = input.next();
            if(id.equals("Back")){ // if user wants to back
                return null; // return null object
            }
            System.out.print("Name: ");   name = input.next();
            System.out.print("Surname: ");   surname = input.next();

            if(id.length() != 8) // validate id
                validLogin = false;
            else
                for (int i = 0; i < 8; i++)
                    if(!Character.isDigit(id.charAt(i)))
                        validLogin = false;

            if(!validLogin)
                printError("Invalid student ID!");

        }while (!validLogin); // keep doing while not valid login

        return new Student(id, name, surname); // return student object of whom login
    } //pppppp

    int printStudentMenu(String studentName){ // print student main menu
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

        if(!isValidChoice(choice, '5')){ // validate choice
            printError("Invalid input! (Accept only 0 to 5)");
            return -1;
        }
        return (choice - '0');

    }

    int printTeacherMenu(){ // print teacher main menu
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

        if(!isValidChoice(choice, '2')){ // validate choice
            printError("Invalid input! (Accept only 0 to 2)");
            return -1;
        }
        return (choice - '0');
    }

    void printAllSubjects(ArrayList<Subject.SubjectInfo> subject){ // print subject from param, use in printing both student's subject and all subject
        clearConsole();
        System.out.println("+------------+-------------------------------------------------------+---------+");
        System.out.println("| Subject ID |                        Name                           | Credits |");
        System.out.println("+------------+-------------------------------------------------------+---------+");

        for(Subject.SubjectInfo i : subject){ // iterate through subjects arraylist
            System.out.print("|  " + i.id + "  | " + i.name);
            for(int j = 0; j < 54-i.name.length(); j++)
                System.out.print(" ");
            System.out.println("|    " + i.credits + "    |");
        }

        System.out.println("+------------+-------------------------------------------------------+---------+");

    }

    private void printSummaryEnroll(Student student){ // print student's summary after each action
        printAllSubjects(student.getEnrolledSubject());
        System.out.println("You have enrolled " + student.getEnrolledSubject().size() + " subjects and cost " + student.getCredits() +" credits.");
        waitEnterKey("continue");
    }

    private void printStudentList(ArrayList<Student> students){ // print all students who's submitted enroll
        clearConsole();
        System.out.println("+------------+-------------------------------------------------------+---------+");
        System.out.println("| Student ID |                   Name - Surname                      |  Grade  |");
        System.out.println("+------------+-------------------------------------------------------+---------+");

        for(Student s : students){
            if(s.isSubmitted()){ // check if a student has submitted his enroll
                System.out.print("|  " + s.getId() + "  | " + s.getName() + " " + s.getSurname());
                for(int j = 0; j < 54-(s.getName().length() + s.getSurname().length() + 1); j++)
                    System.out.print(" ");
                if(s.isGraded()) // if teacher has graded this student
                    System.out.printf("|   %.2f  |\n", s.getGrade()); // print his grade
                else // else print N/A
                    System.out.println("|   N/A   |");
            }
        }

        System.out.println("+------------+-------------------------------------------------------+---------+");
    }

    int enrollMenu(Student student){ // print enroll menu for student, return 1 means finished while -1 means not yet
        System.out.println();
        System.out.println("Your current credits is " + student.getCredits() + "/22"); // print current credits of student
        System.out.print("Please enter subject ID you want to enroll, type \"See\" to see enrolled subject or \"Fin\" to finish : ");

        String command = input.next();

        if(command.equals("See")) // print student's enrolled subject
            if(student.getEnrolledSubject().size() == 0) // if he has none, print error
                printError("You haven't enrolled any subject yet!");
            else
                printAllSubjects(student.getEnrolledSubject());
        else if(command.equals("Fin")) // if student complete enrolling
            if(student.getCredits() < 9) // if sum of subjects credits less than 9, print error
                printError("Your current credits are less than 9. Please enroll more");
            else{
                printSummaryEnroll(student); // else print summary and return to student menu
                student.setEnrollStatus(true); // set his status to enrolled (but not submitted)
                return 1;
            }
        else{
            boolean found = false;
            for(Subject.SubjectInfo i : Subject.subjects){ // iterate through all available subject
                if(i.id.equals(command)){ // if input match available subject
                    found = true;
                    boolean isEnrolledThisSubject = false;
                    for(Subject.SubjectInfo j : student.getEnrolledSubject()) // iterate through student's enrolled subject
                        if(j.id.equals(command)){ // if input match one of subject he has enrolled
                            printError("You have enrolled this subject!"); // print error because he has enrolled
                            isEnrolledThisSubject = true;
                        }
                    if(!isEnrolledThisSubject){ // else
                        student.enroll(i); // enroll input subject for him
                    }
                }

            }
            if(!found) // if input subject not match, print error
                printError("Subject not found or invalid subject ID!");
        }
        return -1;
    }

    void addMenu(Student student){ // print add menu for student
        boolean isFinished = false;
        do {
            System.out.println();
            System.out.println("Your current credits is " + student.getCredits() + "/22"); // print current credits
            System.out.print("Please enter subject ID you want to add or type \"See\" to see enrolled subject or type \"Cancel\" to go back: ");
            String command = input.next();

            if(command.equals("See")) // print student's enrolled subject
                printAllSubjects(student.getEnrolledSubject());
            else if(command.equals("Cancel")) // if he wants to finished
                isFinished = true; // set status to "finished adding"
            else{
                boolean found = false;
                for(Subject.SubjectInfo i : Subject.subjects){ // iterate through all available subjects
                    if(i.id.equals(command)){ // if input match one of available subjects
                        found = true;
                        boolean isEnrolled = false;
                        for(Subject.SubjectInfo j : student.getEnrolledSubject()) // iterate through student's enrolled subject
                            if(j.id.equals(command)){ // if input match one of his enrolled
                                printError("You have enrolled this subject!"); // print error
                                isEnrolled = true;
                            }
                        if(!isEnrolled){ // if he has not enrolled in input subject
                            student.enroll(i); // enroll for him
                            isFinished = true; // set status to "finished adding"
                            System.out.println("Add complete!"); // notify him
                            waitEnterKey("continue");
                        }
                    }

                }
                if(!found) // if input not match any available subject
                    printError("Subject not found or invalid subject ID!"); // print error
            }

        } while(!isFinished); // keep doing this menu while not finished

        printSummaryEnroll(student); // print summary
    }

    void changeMenu(Student student){ // print change menu for student
        String findID, replaceID; // findID = subject that want to change, replaceID = subject that want to change to
        boolean validFind, validReplace;
        Subject.SubjectInfo findSubject = null, replaceSubject = null;

        printAllSubjects(student.getEnrolledSubject()); // print student's enrolled subject

        do{
            validFind = false;
            System.out.println();
            System.out.print("Please enter subject ID of which you want to change or type \"Cancel\" to cancel: ");
            findID = input.next(); // input subject that want to change
            if(findID.equals("Cancel")) // if he wants to cancel changing
                return;

            for(Subject.SubjectInfo i : student.getEnrolledSubject()){ // iterate through student's enrolled subject
                if(i.id.equals(findID)) { // if input match one of enrolled subject
                    findSubject = i; // mark this subject
                    validFind = true;
                    break;
                }
            }
            if(!validFind) { // if not found in enrolled subject
                printError("You have not enrolled this subject or invalid subject ID!"); // print error
            }

        } while(!validFind); // keep doing while not found id

        do{
            validReplace = false;
            System.out.println();
            System.out.print("Please enter subject ID of which you want to change to or type \"Cancel\" to cancel: ");
            replaceID = input.next();
            if(replaceID.equals("Cancel")) // if he wants to cancel changing
                return;

            for(Subject.SubjectInfo i : Subject.subjects){ // iterate through all available subject
                if(i.id.equals(replaceID)) { // if input match available subject
                    replaceSubject = i; // mark this subject
                    validReplace = true;
                    break;
                }
            }
            if(!validReplace) {
                printError("Subject not found or invalid subject ID!");
            }
        } while(!validReplace); // keep doing while not found id

        if(findSubject != null && replaceSubject != null) {
            student.remove(findSubject); // remove marked subject
            student.enroll(replaceSubject); // enroll marked subject
        }

        System.out.println("Change complete!");
        waitEnterKey("continue");
        printSummaryEnroll(student); // print summary

    }

    void removeMenu(Student student){ // print remove menu for student
        String removeID;
        boolean validRemove;

        printAllSubjects(student.getEnrolledSubject()); // print student enrolled subject
        do{
            validRemove = true;
            System.out.println();
            System.out.print("Please enter subject ID of which you want to remove or type \"Cancel\" to cancel: ");
            removeID = input.next();
            if(removeID.equals("Cancel"))
                return;

            boolean found = false;
            for(Subject.SubjectInfo i : student.getEnrolledSubject()){ // iterate through student enrolled subject
                if(i.id.equals(removeID)) { // if input match one of enrolled subject
                    validRemove = student.remove(i); // remove subject for him
                    found = true;
                    break;
                }
            }
            if(!found) { // if input not match any enrolled subject
                printError("You have not enrolled this subject or invalid subject ID!"); // print error
                validRemove = false;
            }

        } while(!validRemove); // keep doing while not finished

        System.out.println("Remove complete!");
        waitEnterKey("continue");
        printSummaryEnroll(student); // print summary

    }

    int submitMenu(){ // print submit confirmation menu
        printStudentBar();

        System.out.println("Are you sure to do this? This action cannot be undone");
        System.out.println("1. I'm sure!");
        System.out.println("2. No");
        System.out.print("Enter number (1 or 2): ");

        choice = input.next().charAt(0);

        if(!isValidChoice(choice, '2')){ // validate input
            printError("Invalid input! (Accept only 1 or 2)");
            return -1;
        }
        return (choice - '0');
    }

    int gradingMenu(ArrayList<Student> students){ // print grading menu for teacher. shows only student who's submitted enrolling
        printStudentList(students);
        System.out.print("Please enter student ID of whom you want to grade or type \"Cancel\" to go back: ");
        String command = input.next();
        if(command.equals("Cancel")) // if cancel
            return 1; // return finished status

        boolean found = false;
        for(Student s : students){  // iterate through all students
            if(s.isSubmitted() && s.getId().equals(command)){ // if input match one of a student who's submitted
                found = true;
                if(!s.isGraded()){ // if he hasn't been graded yet
                    gradeTable(s); // call fill grade method
                    System.out.println("Student " + s.getId() + " " + s.getName() + " " + s.getSurname() + " is now graded with " + s.getGrade() + " points."); // summary his grade
                    waitEnterKey("continue");
                }
                else{ // print error teacher has graded him
                    printError("You have graded this student!");
                    return -1;
                }
            }

        }
        if(!found) // if input not match any student or invalid
            printError("No student with that ID or invalid student ID!");
        return -1;
    }

    private void gradeTable(Student student){ // fill grade method, should be called by only grading menu
        double grade = 0;

        clearConsole();
        System.out.println("+------------+-------------------------------------------------------+---------+");
        System.out.println("| Subject ID |                        Name                           |  Grade  |");
        System.out.println("+------------+-------------------------------------------------------+---------+");

        for(Subject.SubjectInfo i : student.getEnrolledSubject()){ // iterate through student's enrolled subject
            boolean validGrade;
            do{
                validGrade = true;
                System.out.print("|  " + i.id + "  | " + i.name);
                for(int j = 0; j < 54-i.name.length(); j++)
                    System.out.print(" ");
                System.out.print("|    ");
                char gradeChar = Character.toUpperCase(input.next().charAt(0));
                if(gradeChar == 'E' || gradeChar < 'A' || gradeChar > 'F'){ // validate input
                    printError("Invalid grade. Accepts only A, B, C, D or F");
                    validGrade = false;
                }
                else{
                    switch (gradeChar){ // add grade to student
                        case 'A': grade += 4.00; break;
                        case 'B': grade += 3.00; break;
                        case 'C': grade += 2.00; break;
                        case 'D': grade += 1.00; break;
                        default: break;
                    }
                    i.setGrade(gradeChar); // set grade for each subject
                }
            }while(!validGrade);
        }

        System.out.println("+------------+-------------------------------------------------------+---------+");

        student.setGrade(grade / student.getEnrolledSubject().size()); // calculate GPA
        student.setGradeStatus(true); // mark this student as graded
    }

    int gradeSummaryMenu(ArrayList<Student> students){
        printStudentList(students);
        System.out.print("Please enter student ID of whom you want to see detail or type \"Back\" to go back: ");
        String command = input.next();
        if(command.equals("Back"))
            return 1;

        boolean found = false;
        for(Student s : students){ //iterate through students
            if(s.isSubmitted() && s.getId().equals(command)){ // if a student match input and he is submitted
                found = true;
                if(s.isGraded()){ // if he is graded
                    clearConsole();
                    System.out.println("+------------+-------------------------------------------------------+---------+");
                    System.out.println("| Subject ID |                        Name                           |  Grade  |");
                    System.out.println("+------------+-------------------------------------------------------+---------+");

                    for(Subject.SubjectInfo i : s.getEnrolledSubject()){ // iterate through his enrolled subject
                        System.out.print("|  " + i.id + "  | " + i.name);
                        for(int j = 0; j < 54-i.name.length(); j++)
                            System.out.print(" ");
                        System.out.println("|    " + i.getGrade() + "    |"); // print grade from his subject
                    }
                    System.out.println("+------------+-------------------------------------------------------+---------+");
                    System.out.println("Student " + s.getId() + " " + s.getName() + " " + s.getSurname() + " has " + s.getGrade() + " points."); // summary GPA
                    waitEnterKey("continue");
                }
                else{ // print error this student hasn't been graded
                    printError("You haven't graded this student yet!");
                    return -1;
                }
            }
        }
        if(!found) // print error not found input
            printError("No student with that ID or invalid student ID!");
        return -1;
    }

    private void clearConsole(){ // clear console method
        try {
            final String os = System.getProperty("os.name"); // identify OS name

            if(os.contains("Windows")) // if OS is Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // fork process and run cls
            else
                System.out.print("\033[H\033[2J"); // if Unix, print clear control character
            System.out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidChoice(char choice, char maxBound){ // validate choice method should be number and should not exceed maxBound
        return !(!Character.isDigit(choice) || (choice > maxBound || choice < '0'));
    }

    void printBestDimensionNotifier(){ // print best console dimension before main menu run
        System.out.println("This program run best on command line or terminal with 80*20 dimension");
        waitEnterKey("continue");
    }

    void printError(String errMessage){ // print error with errMessage message
        System.out.println();
        System.out.println(errMessage);
        waitEnterKey("try again");
    }

    private void waitEnterKey(String toWhat){ // wait for ENTER key pressed method
        System.out.println("Press ENTER to "+ toWhat + "....");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}