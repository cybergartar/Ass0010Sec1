package Ass0010Sec1;

import java.io.IOException;
import java.util.Scanner;

class UI {

    private static Scanner input = new Scanner(System.in);
    private static char choice;
    private static boolean validChoice;

    static int printMainMenu(){


        clearConsole();
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
        System.out.print("Enter number (1-2): ");

        choice = input.next().charAt(0);
        validChoice  = isValidChoice(choice);
        if(!validChoice){
            printError("Invalid input! (Aceept only 1 or 2)");
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
                Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidChoice(char choice){
        if(!Character.isDigit(choice) || (choice > '2' || choice <= '0'))
            return false;

        return true;
    }

    static void printBestDimensionNotifier(){
        System.out.println("This program run best on command line or terminal with 80*40 dimension console");
        System.out.println("Press ENTER to continue....");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printError(String errMessage){
        System.out.println(errMessage);
        System.out.println("Press ENTER to try again....");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
