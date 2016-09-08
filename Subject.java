package Ass0010Sec1;

import java.util.ArrayList;

class Subject {
    static class SubjectInfo{
        String id, name;
        int credits;

        private SubjectInfo(String id, String name, int credits) {
            this.id = id;
            this.name = name;
            this.credits = credits;
        }
    }

    final static ArrayList<SubjectInfo> subjects = new ArrayList<SubjectInfo>(){{
        add(new SubjectInfo("01006003", "ENGINEERING MATHEMATICS 3", 3));
        add(new SubjectInfo("01076232", "ELECTRONICS FOR COMPUTER ENGINEERING", 3));
        add(new SubjectInfo("01076233", "CIRCUITS AND ELECTRONICS LABORATORY", 1));
        add(new SubjectInfo("01076235", "COMPUTER PROGRAMMING 2", 3));
        add(new SubjectInfo("01076242", "DIGITAL CIRCUIT AND LOGIC DESIGN", 3));
        add(new SubjectInfo("01076243", "DIGITAL CIRCUIT LABORATORY", 1));
        add(new SubjectInfo("01076249", "DATA STRUCTURES AND ALGORITHMS", 3));
        add(new SubjectInfo("01076250", "DATA STRUCTURES AND ALGORITHMS LABORATORY", 1));
        add(new SubjectInfo("90201003", "ENGLISH FOR ACADEMIC PURPOSES", 3));
        add(new SubjectInfo("90201013", "ENGLISH FOR MANAGEMENT", 3));
        add(new SubjectInfo("90201016", "ENGLISH FOR PROFESSIONAL COMMUNICATION", 3));
        add(new SubjectInfo("90201019", "ENGLISH FOR DEVELOPING READING SKILLS", 3));
        add(new SubjectInfo("90201020", "ENGLISH FOR INDUSTRY", 3));
        add(new SubjectInfo("90201022", "ENGLISH FOR FURTHER STUDIES", 3));
        add(new SubjectInfo("90303005", "INTRODUCTION TO PHYSICAL EDUCATION", 3));
        add(new SubjectInfo("90303007", "FUNDAMENTAL RECREATION", 3));
        add(new SubjectInfo("4", "FIRST AIDS", 3)); //90303008
        add(new SubjectInfo("3", "PRINCIPLES OF WORK SAFETY", 3)); //90303009
        add(new SubjectInfo("2", "LIBRARY USAGE AND INFORMATION", 3)); //90304001
        add(new SubjectInfo("1", "THAI CIVILIZATION", 3)); //90305001
    }};

}
