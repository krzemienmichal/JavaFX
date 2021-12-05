package com.paoim.paoim_javafx.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectContainer {
    Map<String, Subject> subjects = new HashMap<>();

    public Map<String, Subject> getSubjects() {
        return subjects;
    }

    public void addClass(String subjectName, Subject subject) {
        this.subjects.put(subjectName, subject);
    }

    public void removeClass(String subjectName) {
        this.subjects.remove(subjectName);
    }

    public List<Subject> findEmptySubject() {
        List<Subject> emptySubjects = new ArrayList<>();
        for(Subject klasa : this.subjects.values()){
            if(klasa.studentList.size() == 0){
                emptySubjects.add(klasa);
            }
        }
        return emptySubjects;
    }

    public void summary(){
        for(Map.Entry<String, Subject> entry: this.subjects.entrySet()){
            System.out.printf("Grupa %s jest zapelniona w %.2f procent %n", entry.getKey(),
                    1.0*entry.getValue().studentList.size()/entry.getValue().maxSize *100);
        }
    }
}
