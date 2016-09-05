package Ass0010Sec1;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        int choice;

        UI.printBestDimensionNotifier();

        while(true){

            do{
                choice = UI.printMainMenu();
            }while (choice < 0);

            System.out.println(choice); //DBG
            if(choice == 0)
                System.exit(0);

            //TODO: handle student menu
            if(choice == 1){
                boolean successLogIn, found = false;
                Student loggedInUser;

                do{
                    successLogIn = true;
                    loggedInUser = UI.printStudentLogin();
                    if(!students.isEmpty()){
                        for(Student i : students){
                            if(i.getId().equals(loggedInUser.getId())){
                                if(i.isSubmitStatus()){
                                    UI.printError("You cannot enroll twice!");
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
                        choice = UI.printStudentMenu(loggedInUser.getName());
                    }while(choice < 0);

                    if(choice == 1){
                        if(!loggedInUser.isEnrolled()){
                            UI.printAllSubjects(Subject.subjects);
                            boolean finished;
                            do{
                                finished = (UI.enrollMenu(loggedInUser) == 1);
                            }while(!finished);
                        }
                        else
                            UI.printError("You have enrolled! If you want to add subject, please use Add menu");
                    }
                    else if(choice == 2){
                        UI.printAllSubjects(Subject.subjects);
                        UI.addMenu(loggedInUser);

                    }
                    else if (choice == 0)
                        break;
                }

            }
        }

        //TODO: handle teacher menu


    }




}
