package Ass0010Sec1;

import java.util.ArrayList;

class Student {
    private String id, name, surname;
    private int credits;
    private ArrayList<String> enrolledSubject = new ArrayList<>();
    private boolean submitStatus;

    Student(String... info) {
        this.id = info[0];
        this.name = info[1];
        this.surname = info[2];
        this.submitStatus = false;
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    int getCredits(){
        return credits;
    }

    void setCredits(int credits) {
        this.credits = credits;
    }

    boolean enroll(String subject, int subjectCredit){
        int currentCredit = getCredits();
        if(currentCredit + subjectCredit > 22){
            UI.printError("Cannot enroll! (Maximum credits exceed)");
            return false;
        }

        enrolledSubject.add(subject);
        setCredits(currentCredit + subjectCredit);
        return true;
    }
}
