package com.paoim.paoim_javafx.api;

import java.util.*;

public class Student implements Comparable<Student>{




    private int id;
    String firstName, lastName;
    StudentCondition studentCondition;
    private int birthDate;
    private List<Subject> subjects;
    private Map<String, List<Double>> grades;
    private Map<String, StudentSubjectStatus> studentSubjectStatusMap;


    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStudentCondition(StudentCondition studentCondition) {
        this.studentCondition = studentCondition;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public Map<String, StudentSubjectStatus> getStudentSubjectStatusMap() {
        return studentSubjectStatusMap;
    }

    public int getId() {
        return id;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentCondition getStudentCondition() {
        return studentCondition;
    }

    public int getBirthDate() {
        return birthDate;
    }


    public Student(int id,String firstName, String lastName, StudentCondition studentCondition, int birthYear,
                   List<Double> points) {
        if(id > 0 && id < Integer.MAX_VALUE)
            this.id = id;
        else throw new IllegalArgumentException("ID has to be positive number or has to be less than int_max");
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentCondition = studentCondition;
        this.birthDate = birthYear;
        this.subjects = new ArrayList<>(0);
        this.grades = new HashMap<>();
        this.studentSubjectStatusMap = new HashMap<>();
    }

    public Student(int id,String imie, String nazwisko, StudentCondition stanStudenta, int rokUrodzenia) {
        this(id, imie, nazwisko, stanStudenta, rokUrodzenia, new ArrayList<>(0));
    }

    public void addGradeToSubject(double grade, Subject subject){
       if(this.grades.containsKey(subject.getSubjectName()))
               this.grades.get(subject.getSubjectName()).add(grade);
       else
           this.grades.put(subject.getSubjectName(),new ArrayList<>(Arrays.asList(grade)));
    }
    public void addGradeToSubject(double grade, String subject){
        if(this.grades.containsKey(subject))
            this.grades.get(subject).add(grade);
        else
            this.grades.put(subject,new ArrayList<>(Arrays.asList(grade)));
    }


    public void print(){
        System.out.println("Dane studenta");
        System.out.printf("Imie i nazwisko: %s %s %n", firstName, lastName);
        System.out.printf("Urodzony: %d%n", birthDate);
        System.out.printf("Status studenta: %s%n", studentCondition);
    }

    public List<Double> getGradesFromSubject(Subject subject){
        if(grades.isEmpty() || !grades.containsKey(subject.getSubjectName()))
            return new ArrayList<>(0);
        else
            return this.grades.get(subject.getSubjectName());
    }
    public List<Double> getGradesFromSubject(String subject){
        if(grades.isEmpty() || !grades.containsKey(subject))
            return new ArrayList<>(0);
        else
            return this.grades.get(subject);
    }

    public void addSubject(Subject subject){
        if (this.subjects.stream().noneMatch(anyStudent -> subject.getSubjectName().toLowerCase(Locale.ROOT).equals(anyStudent.getSubjectName().toLowerCase(Locale.ROOT)))) {
            this.subjects.add(subject);
            changeSubjectStatus(subject, StudentSubjectStatus.WAITING);
        }
    }

    public void changeSubjectStatus(Subject subject, StudentSubjectStatus status){
        studentSubjectStatusMap.put(subject.getSubjectName(), status);
    }
    public void changeSubjectStatus(String subject, StudentSubjectStatus status){
        studentSubjectStatusMap.put(subject, status);
    }

    public void removeSubject(Subject subject){
        this.subjects.remove(subject);
        studentSubjectStatusMap.replace(subject.getSubjectName(), StudentSubjectStatus.SIGNED_OUT);
    }

//    public Double getAverage(Subject subject){
//        return this.grades.get(subject.getSubjectName()).stream().mapToDouble(a->a).average().orElse(0.0);
//    }

    public Double getAverage(String subject){
        if(!this.grades.containsKey(subject))
            return 0.0;
        else
            return this.grades.get(subject).stream().mapToDouble(a -> a).average().orElse(0.0);
    }

    @Override
    public int compareTo(Student person) {
            return this.lastName.compareTo(person.lastName);
    }

}

