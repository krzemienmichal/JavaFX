package com.paoim.paoim_javafx;

import com.paoim.paoim_javafx.api.*;
import com.paoim.paoim_javafx.tablemodels.SubjectTableModel;
import javafx.beans.NamedArg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

public class SubjectsViewController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TableView<SubjectTableModel> allSubjectView;
    @FXML
    public TableColumn<SubjectTableModel, String> allSubjectGradesColumn;
    @FXML
    public TableColumn<SubjectTableModel, String> allSubjectStudentStatusColumn;
    @FXML
    public TableColumn<SubjectTableModel, String> allSubjectAllGradesColumn;
    @FXML
    public TableColumn<SubjectTableModel, Double> allSubjectAverageGradeColumn;
    @FXML
    public TableColumn<SubjectTableModel, String> allSubjectSubjectName;
    @FXML
    private ListView<String> subjectsList;
    @FXML
    private TableView<SubjectTableModel> subjectInfoTableView;
    @FXML
    private TableColumn<SubjectTableModel, String> studentStatusColumn;
    @FXML
    private TableColumn<SubjectTableModel, List<Double>> gradesColumn;
    @FXML
    private TableColumn<SubjectTableModel,String> allGradesColumn;
    @FXML
    private TableColumn<SubjectTableModel, Double> averageGradeColumn;

    private Integer studentID;

    public void loadSubjects(Integer studentID){
        List<String> subjectNames = new ArrayList<>(0);
        subjectNames.add("Dowolny");
        for(Subject subject : GenerateData.getStudentsContainer().getStudentsMap().get(studentID).getSubjects()) {
            System.out.println(subject.getSubjectName());
            subjectNames.add(subject.getSubjectName());
        }
        subjectsList.getItems().addAll(subjectNames);
        this.studentID = studentID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Set<String> value = GenerateData.getSubjectContainer().getSubjects().keySet();
//        List<String> values = new ArrayList<>(value);
//        subjectsList.getItems().addAll(GenerateData.getSubjectContainer().getSubjects().keySet());
        subjectsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println(studentID);
                String subjectName = subjectsList.getSelectionModel().getSelectedItem();
                System.out.println(subjectName);
                try {
                    if(subjectName.equals("Dowolny")){
                        showAllSubjects();
                    }
                    else {
                        showSubjectInfo(subjectName);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void showAllSubjects(){
        Student studentToShow = GenerateData.getStudentsContainer().getStudentsMap().get(studentID);
        final ObservableList<SubjectTableModel> data = FXCollections.observableArrayList();
        SubjectTableModel student;

//        for(Subject subject: studentToShow.getSubjects()){
//            student = new SubjectTableModel(studentToShow.getStudentSubjectStatusMap().get(subject.getSubjectName()).toString(),
//                    studentToShow.getGradesFromSubject(subject.getSubjectName()), studentToShow.getAverage(subject.getSubjectName()),
//                    subject.getSubjectName());
//            data.add(student);
//        }

        for(Subject subject: GenerateData.getSubjectContainer().getSubjects().values()){
            String status;
            if(studentToShow.getStudentSubjectStatusMap().get(subject.getSubjectName()) != null)
                status = studentToShow.getStudentSubjectStatusMap().get(subject.getSubjectName()).toString();
            else status = StudentSubjectStatus.NOT_SIGNED_IN.toString();
            System.out.println(status);
            student = new SubjectTableModel(status,
                    studentToShow.getGradesFromSubject(subject.getSubjectName()), studentToShow.getAverage(subject.getSubjectName()),
                    subject.getSubjectName());
            data.add(student);
        }
        subjectInfoTableView.setVisible(false);
        allSubjectView.setVisible(true);

        allSubjectSubjectName.setCellValueFactory(new PropertyValueFactory<>("subject"));
        allSubjectStudentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        allSubjectAllGradesColumn.setCellValueFactory(new PropertyValueFactory<>("grades"));
        allSubjectAverageGradeColumn.setCellValueFactory(new PropertyValueFactory<>("average"));
        allSubjectView.setItems(data);
    }

    private void showSubjectInfo(String subjectName){
        Student studentToShow = GenerateData.getStudentsContainer().getStudentsMap().get(studentID);
        final ObservableList<SubjectTableModel> data = FXCollections.observableArrayList();
        SubjectTableModel student;

        student = new SubjectTableModel(studentToShow.getStudentSubjectStatusMap().get(subjectName).toString(),
                studentToShow.getGradesFromSubject(subjectName), studentToShow.getAverage(subjectName));
        data.add(student);
        subjectInfoTableView.setVisible(true);
        allSubjectView.setVisible(false);
        studentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        allGradesColumn.setCellValueFactory(new PropertyValueFactory<>("grades"));
        averageGradeColumn.setCellValueFactory(new PropertyValueFactory<>("average"));
        subjectInfoTableView.setItems(data);

    }
    @FXML
    public void signInToSubject(ActionEvent event){
        try {
            TableView.TableViewSelectionModel<SubjectTableModel> selectionModel = allSubjectView.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<SubjectTableModel> selectedItem = selectionModel.getSelectedItems();
            if(selectedItem.get(0).getStatus().equals("nie zapisany")){
                String subjectName = selectedItem.get(0).getSubject();
                if(GenerateData.getSubjectContainer().getSubjects().get(subjectName).getStudentList().size() <
                        GenerateData.getSubjectContainer().getSubjects().get(subjectName).getMaxSize())
                {
                    GenerateData.getStudentsContainer().getStudentsMap().get(studentID).addSubject(
                            GenerateData.getSubjectContainer().getSubjects().get(subjectName));
                    showAllSubjects();
                    subjectsList.getItems().add(subjectName);
    //                loadSubjects(studentID);
                    subjectsList.refresh();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void singOutFromSubject(ActionEvent event) {
        try {
            TableView.TableViewSelectionModel<SubjectTableModel> selectionModel = allSubjectView.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<SubjectTableModel> selectedItem = selectionModel.getSelectedItems();
            if (selectedItem.get(0).getStatus().equals("zapisany")) {
                String subjectName = selectedItem.get(0).getSubject();
                GenerateData.getStudentsContainer().getStudentsMap().get(studentID).removeSubject(
                        GenerateData.getSubjectContainer().getSubjects().get(subjectName));
                showAllSubjects();
                subjectsList.getItems().remove(subjectName);
//                loadSubjects(studentID);
//                subjectsList.refresh();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void backToStudentsList(ActionEvent event)throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("main-view.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,786, 448);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void changeGroup(ActionEvent event) {
        try {
            TableView.TableViewSelectionModel<SubjectTableModel> selectionModel = allSubjectView.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<SubjectTableModel> selectedItem = selectionModel.getSelectedItems();
            if(selectedItem.get(0).getStatus().equals("zapisany")){
                String subjectName = selectedItem.get(0).getSubject();
                 GenerateData.getStudentsContainer().getStudentsMap().get(studentID).changeSubjectStatus(
                            GenerateData.getSubjectContainer().getSubjects().get(subjectName), StudentSubjectStatus.RELOCATED);
                    showAllSubjects();
                }
            } catch (Exception e){
            e.printStackTrace();
        }
    }

}
