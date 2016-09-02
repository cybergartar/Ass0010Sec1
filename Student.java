package Ass0010Sec1;

import java.util.ArrayList;

/**
 * Created by ultimate on 9/2/16.
 */
class Student {
    private String id, name, surname;
    private int credits;
    private ArrayList<String> enrolledSubject = new ArrayList<>();

    public Student(String... info) {
        this.id = info[0];
        this.name = info[1];
        this.surname = info[2];
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getNumEnrolledSubject(){
        return enrolledSubject.size();
    }

    public int getCredits(){
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean enrollSubject(String subject, int subjectCredit){
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
