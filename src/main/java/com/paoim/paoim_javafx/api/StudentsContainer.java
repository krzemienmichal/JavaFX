package com.paoim.paoim_javafx.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StudentsContainer {

    private  Map<Integer,Student> studentsMap = new HashMap<>();

//    public StudentsContainer(Map<Integer, Student> studentsMap) {
//        this.studentsMap = studentsMap;
//    }

    public Map<Integer, Student> getStudentsMap() {
        return studentsMap;
    }

    public void addStudent(Student studentToAdd){
        this.studentsMap.put(studentToAdd.getId(), studentToAdd);
    }

    public void removeStudent(Integer id){
        this.studentsMap.remove(id);
    }
    public Integer findStudentID(String firstName, String lastName){
        for(Student student : studentsMap.values()){
            if(student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)){
                return student.getId();
            }
        }
        return -1;
    }

    public void removeStudent(Student studentToRemove){
        this.studentsMap.remove(studentToRemove.getId());
    }
}
