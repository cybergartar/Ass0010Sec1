package Ass0010Sec1;

public class Main {

    public static void main(String[] args) {
        int choice;

        UI.printBestDimensionNotifier();

        do{
            choice = UI.printMainMenu();
        }while (choice < 1);

        System.out.println(choice); //DBG

        //TODO: handle student menu

        //TODO: handle teacher menu


    }




}
