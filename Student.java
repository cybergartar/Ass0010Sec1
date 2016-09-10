package Ass0010Sec1;

import java.util.ArrayList;

class Student {
    private String id, name, surname;
    private int credits; // student's enrolled subject
    private ArrayList<Subject.SubjectInfo> enrolledSubject = new ArrayList<>(); // student's enrolled subject
    private boolean submitted, enrolled, graded; // student status
    private double grade; // student GPA

    private UI ui = new UI();
    Student(String... info) { // constructors
        this.id = info[0];
        this.name = info[1];
        this.surname = info[2];
        this.submitted = false;
        this.enrolled = false;
        this.graded = false;
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

    private void setCredits(int credits) {
        this.credits = credits;
    }

    boolean isSubmitted() {
        return submitted;
    }

    void setSubmitStatus(boolean submitStatus) {
        this.submitted = submitStatus;
    }

    boolean isGraded() {
        return graded;
    }

    void setGradeStatus(boolean graded) {
        this.graded = graded;
    }

    boolean isEnrolled() {
        return enrolled;
    }

    void setEnrollStatus(boolean enrollStatus) {
        this.enrolled = enrollStatus;
    }

    double getGrade() {
        return grade;
    }

    void setGrade(double grade) {
        this.grade = grade;
    }

    ArrayList<Subject.SubjectInfo> getEnrolledSubject() {
        return enrolledSubject;
    }

    boolean enroll(Subject.SubjectInfo subject){ // enroll method
        int currentCredit = getCredits();
        if(currentCredit + subject.credits > 22){ // check if credit after enroll will be more than limit
            ui.printError("Cannot enroll! (Maximum credits exceed)"); // if yes, print error
            return false;
        }

        enrolledSubject.add(subject); // else add subject to enrolled list
        setCredits(currentCredit + subject.credits); // update student's credit
        return true;
    }

    boolean remove(Subject.SubjectInfo subject){ // remove subject method
        int currentCredit = getCredits();
        if(currentCredit - subject.credits < 9){ // check if credit after enroll will be less than limit
            ui.printError("Cannot remove! Your credits will less than 9"); // if yes, print error
            return false;
        }

        enrolledSubject.remove(subject); // else remove subject from enrolled list
        setCredits(currentCredit - subject.credits); // update student's credit
        return true;
    }
}
