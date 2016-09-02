package Ass0010Sec1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
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
                boolean successLogIn;
                Student loggedInUser;

                do{
                    successLogIn = true;
                    loggedInUser = UI.printStudentLogin();
                    if(!students.isEmpty()){
                        for(Student i : students){
                            if(i.getId().equals(loggedInUser.getId())){
                                UI.printError("You cannot enroll twice!");
                                successLogIn = false;
                            }
                        }
                    }

                    students.add(loggedInUser);
                }while(!successLogIn);

                do{
                    choice = UI.printStudentMenu(loggedInUser.getName());
                }while(choice < 0);

                if(choice == 1){
                    UI.printAllSubjects();
                    System.out.println("Your current credits is " + loggedInUser.getCredits() + "/23");
                    System.out.print("Please enter subject ID you want to enroll or type \"See\" to see enrolled subject: ");


                }


            }
        }

        //TODO: handle teacher menu


    }




}
