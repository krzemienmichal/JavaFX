package com.paoim.paoim_javafx.tablemodels;

import com.paoim.paoim_javafx.api.Subject;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class SubjectTableModel {


    private StringProperty status;
    private StringProperty grades;
    private DoubleProperty average;
    private StringProperty subject;

    public SubjectTableModel(String status, List<Double> grades, Double average, String subject) {
        this.status = new SimpleStringProperty(status);
        if(grades.isEmpty())
            this.grades = new SimpleStringProperty("brak ocen");
        else
            this.grades = new SimpleStringProperty(grades.toString());
        this.average = new SimpleDoubleProperty(average);
        this.subject = new SimpleStringProperty(subject);
    }
    public SubjectTableModel(String status, List<Double> grades, Double average) {
        this(status, grades, average, "");
    }

    public String getSubject() {
        return subject.get();
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }
    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getGrades() {
        return grades.get();
    }

    public StringProperty gradesProperty() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades.set(grades);
    }

    public double getAverage() {
        return average.get();
    }

    public DoubleProperty averageProperty() {
        return average;
    }

    public void setAverage(double average) {
        this.average.set(average);
    }
}
