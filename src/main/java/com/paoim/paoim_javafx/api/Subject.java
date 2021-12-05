package com.paoim.paoim_javafx.api;

import java.util.*;

public class Subject {


    String subjectName;
    List<Student> studentList;
    int maxSize;

    public Subject(String subjectName, List<Student> studentList, int maxSize) {
        this.subjectName = subjectName;
        if(studentList.size() <= maxSize)
            this.studentList = studentList;
        else throw new IllegalArgumentException("Zbyt duza lista studentow");
        this.maxSize = maxSize;
    }
    public Subject(String subjectName, int maxSize) {
        this(subjectName, new ArrayList(0), maxSize);
    }

    public void addStudent(Student newStudent){
        if(this.studentList.size() < this.maxSize) {
            if (this.studentList.stream().noneMatch(anyStudent -> newStudent.getLastName().toLowerCase(Locale.ROOT).equals(anyStudent.getLastName().toLowerCase(Locale.ROOT))))
                this.studentList.add(newStudent);
            else
            System.err.println("Student o takim nazwisku istnieje");
        }
        else System.err.println("Grupa jest pelna");

    }

    public void addGrade(Student gradedStudent, double grade){
        if(this.studentList.contains(gradedStudent))
        {
            this.studentList.get(this.studentList.indexOf(gradedStudent)).addGradeToSubject(grade, this.subjectName);
        }
        else System.err.println("W tej grupie nie ma takiego studenta");
    }

    public void removeStudent(Student checkedStudent){
        if(this.studentList.get(this.studentList.indexOf(checkedStudent)) != null)
        {
            this.studentList.remove(checkedStudent);
        }
    }

    public void changeCondition(Student person, StudentCondition condition){
        if(this.studentList.contains(person))
            this.studentList.get(this.studentList.indexOf(person)).studentCondition = condition;
        else System.err.println("W tej grupie nie ma takiego studenta");
    }

    public List<Student> searchByLastName(String lastName){
        List<Student> matchingStudents = new ArrayList<>(0);
        Comparator<String> lastNameComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
            for(Student person: this.studentList){
                if(lastNameComparator.compare(person.lastName, lastName) == 0){
                    matchingStudents.add(person);
                }
            }
            if(matchingStudents.size() != 0)
                return matchingStudents;
            else{
                System.out.println("Nie znaleziono osób o takim nazwisku");
                return matchingStudents;
            }
    }

    public List<Student> searchPartial(String lastName){
        List<Student> matchingStudent = new ArrayList<>();
        for(Student person : this.studentList){
            if(person.lastName.contains(lastName)){
                matchingStudent.add(person);
            }
            else if(person.firstName.contains(lastName)){
                matchingStudent.add(person);
            }
        }
        return matchingStudent;
    }

    public int countByCondition(StudentCondition condition){
        int amountOfStudents = 0;
        for(Student person : this.studentList){
            if (person.studentCondition.equals(condition)){
                amountOfStudents++;
            }
        }
        return amountOfStudents;
    }

    public void summary(){
        if(this.studentList.size() == 0)
        {
            System.out.printf("Grupa %s jest pusta%n", this.subjectName);
            return;
        }
        for(Student person: this.studentList){
            person.print();
        }
    }

//    public void sortByName(){
//        Collections.sort(this.studentList);
//    }

//    private class DescPointComparator implements Comparator<Student>{
//        @Override
//        public int compare(Student o1, Student o2){
//            return Double.compare(o2.getGrades(), o1.getGrade());
//        }
//    }
//    private class AscPointComparator implements Comparator<Student>{
//        @Override
//        public int compare(Student o1, Student o2){
//            return Double.compare(o1.getGrade(), o2.getGrade());
//        }
//    }
//
//    public void sortByGrades(){
//        this.studentList.sort(new DescPointComparator());
//    }
//
//    public Student max(){
//       return Collections.max(this.studentList,new AscPointComparator());
//    }

    public String getSubjectName() {
        return subjectName;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public int getMaxSize() {
        return maxSize;
    }
}

