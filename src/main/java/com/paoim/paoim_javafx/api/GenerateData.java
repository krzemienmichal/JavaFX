package com.paoim.paoim_javafx.api;

import java.util.ArrayList;
import java.util.List;

public class GenerateData {

    static SubjectContainer subjectContainer = new SubjectContainer();
    static StudentsContainer studentsContainer = new StudentsContainer();

    public static SubjectContainer getSubjectContainer() {
        return subjectContainer;
    }
    public static StudentsContainer getStudentsContainer(){ return studentsContainer;}

    public static void generate(){
//        List<Student> studentsContainter = new ArrayList<>();
        List<Student> students2 = new ArrayList<>();
        Subject opt;
        Subject subject1;
        Subject subject2;
        studentsContainer.addStudent(new Student(1,"Jerzy", "Nowak",
                StudentCondition.ZDROWY, 1992));
        studentsContainer.addStudent(new Student(2,"Andrzej", "Nowakowski",
                StudentCondition.ZDROWY, 2000));
        studentsContainer.addStudent(new Student(3,"Jurek", "Perlik",
                StudentCondition.CHORY, 1992));
        studentsContainer.addStudent(new Student(4,"Jan", "Nowicki",
                StudentCondition.ZDROWY, 2001));
        studentsContainer.addStudent(new Student(5,"Anna", "Nowak",
                StudentCondition.ZDROWY, 2000));
        studentsContainer.addStudent(new Student(6,"Celina", "Walaszek",
                StudentCondition.ZDROWY, 1996));
        studentsContainer.addStudent(new Student(7,"Maciej", "Antoniusz",
                StudentCondition.CHORY, 1992));
        studentsContainer.addStudent(new Student(8,"Jerzy", "Pawlak",
                StudentCondition.ZDROWY, 1992));
        studentsContainer.addStudent(new Student(9,"Mrzygłód", "Szczepan",
                StudentCondition.ZDROWY, 1992));
        studentsContainer.addStudent(new Student(10,"Sebastian", "Grabowski",
                StudentCondition.ZDROWY, 1992));


       opt = new Subject("PAOiM",  23);
       subject1 = new Subject("Optym", 34);
       subject2 = new Subject("PKMIS", 23);

       subject1.addStudent(studentsContainer.getStudentsMap().get(1));
       subject1.addStudent(studentsContainer.getStudentsMap().get(2));
       subject1.addStudent(studentsContainer.getStudentsMap().get(3));
       subject1.addStudent(studentsContainer.getStudentsMap().get(4));
       subject1.addStudent(studentsContainer.getStudentsMap().get(5));
       subject2.addStudent(studentsContainer.getStudentsMap().get(1));
       subject2.addStudent(studentsContainer.getStudentsMap().get(2));
       subject2.addStudent(studentsContainer.getStudentsMap().get(3));
       subject2.addStudent(studentsContainer.getStudentsMap().get(4));
       subject2.addStudent(studentsContainer.getStudentsMap().get(5));

        subjectContainer.addClass(opt.getSubjectName(), opt);
        subjectContainer.addClass(subject2.getSubjectName(), subject2);
        subjectContainer.addClass(subject1.getSubjectName(), subject1);

       System.out.println(studentsContainer.getStudentsMap().get(1).getLastName());
       System.out.println(opt.subjectName);
        studentsContainer.getStudentsMap().get(1).addSubject(subjectContainer.getSubjects().get("PAOiM"));
        studentsContainer.getStudentsMap().get(1).addSubject(subjectContainer.getSubjects().get("Optym"));
        studentsContainer.getStudentsMap().get(1).changeSubjectStatus("PAOiM", StudentSubjectStatus.SINGED_IN);
        studentsContainer.getStudentsMap().get(1).changeSubjectStatus("Optym", StudentSubjectStatus.SINGED_IN);
        studentsContainer.getStudentsMap().get(1).addGradeToSubject(5.0, "PAOiM");
        studentsContainer.getStudentsMap().get(1).addGradeToSubject(3.0, "PAOiM");
        studentsContainer.getStudentsMap().get(1).addGradeToSubject(2.0, "PAOiM");
        studentsContainer.getStudentsMap().get(1).addGradeToSubject(5.0, "PAOiM");
        studentsContainer.getStudentsMap().get(1).addGradeToSubject(4.0, "PAOiM");




    }
}
