package Ass0010Sec1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UI ui = new UI();
        ArrayList<Student> students = new ArrayList<>();
        int choice;

        ui.printBestDimensionNotifier(); // print best dimension console

        while(true){ // keep doing unless user exit

            do{
                choice = ui.printMainMenu(); // get choice user input from main menu
            }while (choice < 0);

            if(choice == 0) // exit
                System.exit(0);

            if(choice == 1){ // navigate to student menu
                studentMenu(ui, students);
            }
            else{ // navigate to teacher menu
                teacherMenu(ui, students);
            }
        }
    }

    private static void studentMenu(UI ui, ArrayList<Student> students){
        int choice;

        boolean successLogIn;
        Student loggedInUser;

        do{
            boolean found = false;
            successLogIn = true;
            loggedInUser = ui.printStudentLogin(); // get user from student login
            if(loggedInUser == null) // if returned object is null
                return;
            for(Student i : students){ // iterate through students list
                if(i.getId().equals(loggedInUser.getId())){ // if logged in user id match one in list
                    if(i.isSubmitted()){ // if he submitted enroll, he can't use this menu again
                        ui.printError("You cannot enroll twice!");
                        successLogIn = false;
                    }
                    else{
                        loggedInUser = i;
                        found = true;
                    }
                }
            }
            if(!found) // if logged in use not in student list
                students.add(loggedInUser); // add him to list
        }while(!successLogIn);

        while (true){ // do until go back to main menu
            do{
                choice = ui.printStudentMenu(loggedInUser.getName());
            }while(choice < 0);

            if(choice == 1){ // if student choose enroll menu
                if(!loggedInUser.isEnrolled()){ // if student hasn't enrolled
                    ui.printAllSubjects(Subject.subjects);
                    boolean finished;
                    do{ // call enroll method
                        finished = (ui.enrollMenu(loggedInUser) == 1);
                    }while(!finished);
                }
                else // print error cannot enroll again
                    ui.printError("You have enrolled! If you want to add subject, please use Add menu");
            }
            else if(choice != 0){
                if(loggedInUser.isEnrolled()){
                    if(choice == 2){ // add method
                        ui.printAllSubjects(Subject.subjects);
                        ui.addMenu(loggedInUser);
                    }
                    else if(choice == 3){ // change method
                        ui.changeMenu(loggedInUser);
                    }
                    else if(choice == 4){ // remove method
                        ui.removeMenu(loggedInUser);
                    }
                    else if(choice == 5){ // submit confirmation
                        choice = ui.submitMenu();
                        if(choice == 1){
                            loggedInUser.setSubmitStatus(true);
                            break;
                        }
                    }
                }
                else{ // above menu will be available after enrolled
                    ui.printError("You haven't enrolled yet!");
                }
            }
            else
                break;
        }
    }

    private static void teacherMenu(UI ui, ArrayList<Student> students){
        int choice, submittedStudent = 0;
        for(Student s : students){ // check number of submitted student
            if(s.isSubmitted())
                submittedStudent++;
        }

        if(submittedStudent == 0){ // can't use teacher menu if no student enrolled
            ui.printError("No student has submitted!");
            return;
        }

        while(true){
            do{
                choice = ui.printTeacherMenu();
            }while(choice < 0);

            if(choice == 0)
                return;

            if(choice == 1){ // grading manu
                do{
                    choice = ui.gradingMenu(students);
                }while(choice != 1);
            }
            else { // display all student menu
                do{
                    choice = ui.gradeSummaryMenu(students);
                }while(choice != 1);
            }
        }
    }

}
