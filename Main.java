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
                boolean successLogIn, found = false, backCommand = false;
                Student loggedInUser;

                do{
                    successLogIn = true;
                    loggedInUser = ui.printStudentLogin();
                    if(loggedInUser.getId().equals("00000000")){
                        backCommand = true;
                    }
                    if(!students.isEmpty()){
                        for(Student i : students){
                            if(i.getId().equals(loggedInUser.getId())){
                                if(i.isSubmitStatus()){
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

                if(backCommand)
                    continue;

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
                            ui.printSummaryEnroll(loggedInUser);
                        }
                        else
                            ui.printError("You have enrolled! If you want to add subject, please use Add menu");
                    }
                    else if(choice != 0){
                        if(loggedInUser.isEnrolled()){
                            if(choice == 2){
                                ui.printAllSubjects(Subject.subjects);
                                ui.addMenu(loggedInUser);
                                ui.printSummaryEnroll(loggedInUser);
                            }
                            else if(choice == 3){
                                ui.changeMenu(loggedInUser);
                                ui.printSummaryEnroll(loggedInUser);
                            }
                            else if(choice == 4){
                                ui.removeMenu(loggedInUser);
                                ui.printSummaryEnroll(loggedInUser);
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
        }

        //TODO: handle teacher menu


    }




}
