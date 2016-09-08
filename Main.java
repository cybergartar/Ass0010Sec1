package Ass0010Sec1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UI ui = new UI();
        ArrayList<Student> students = new ArrayList<>();
        int choice;

        ui.printBestDimensionNotifier();

        while(true){

            do{
                choice = ui.printMainMenu();
            }while (choice < 0);

            if(choice == 0)
                System.exit(0);

            if(choice == 1){
                studentMenu(ui, students);
            }
            else{
                teacherMenu(ui, students);
            }
                //TODO: handle teacher menu
        }



    }

    public static void studentMenu(UI ui, ArrayList<Student> students){
        int choice;

        boolean successLogIn;
        Student loggedInUser;

        do{
            boolean found = false;
            successLogIn = true;
            loggedInUser = ui.printStudentLogin();
            if(loggedInUser.getId().equals("00000000"))
                return;
            if(!students.isEmpty()){
                for(Student i : students){
                    if(i.getId().equals(loggedInUser.getId())){
                        if(i.isSubmitted()){
                            ui.printError("You cannot enroll twice!");
                            successLogIn = false;
                        }
                        else{
                            loggedInUser = i;
                            found = true;
                        }
                    }
                }
            }
            if(!found)
                students.add(loggedInUser);
        }while(!successLogIn);

        while (true){
            do{
                choice = ui.printStudentMenu(loggedInUser.getName());
            }while(choice < 0);

            if(choice == 1){
                if(!loggedInUser.isEnrolled()){
                    ui.printAllSubjects(Subject.subjects);
                    boolean finished;
                    do{
                        finished = (ui.enrollMenu(loggedInUser) == 1);
                    }while(!finished);
                }
                else
                    ui.printError("You have enrolled! If you want to add subject, please use Add menu");
            }
            else if(choice != 0){
                if(loggedInUser.isEnrolled()){
                    if(choice == 2){
                        ui.printAllSubjects(Subject.subjects);
                        ui.addMenu(loggedInUser);
                    }
                    else if(choice == 3){
                        ui.changeMenu(loggedInUser);
                    }
                    else if(choice == 4){
                        ui.removeMenu(loggedInUser);
                    }
                    else if(choice == 5){
                        choice = ui.submitMenu();
                        if(choice == 1){
                            loggedInUser.setSubmitStatus(true);
                            break;
                        }
                    }
                }
                else{
                    ui.printError("You haven't enrolled yet!");
                }
            }
            else
                break;
        }
    }

    public static void teacherMenu(UI ui, ArrayList<Student> students){
        int choice;

//        if(students.size() == 0){
//            ui.printError("No student have enrolled!");
//            return;
//        }

        while(true){
            do{
                choice = ui.printTeacherMenu();
            }while(choice < 0);

            if(choice == 0)
                return;

            if(choice == 1){
                do{
                    choice = ui.gradingMenu(students);
                }while(choice != 1);
            }
        }
    }


}
