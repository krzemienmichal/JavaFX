package com.paoim.paoim_javafx;

import com.paoim.paoim_javafx.api.GenerateData;
import com.paoim.paoim_javafx.api.Student;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    private Stage stage;
    private  Scene scene;
    private Parent root;


    @FXML
    private TableView<Student> studentsTableView;

//    String[] something = {"a", "b", "c"}

    @FXML
    private TableColumn<Student, String> firstNameColumn;

    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student,Integer> albumNumberColumn;


    @FXML
    protected void showStudentSubjects(ActionEvent event) throws IOException {
       try{
           TableView.TableViewSelectionModel<Student> selectionModel = studentsTableView.getSelectionModel();
           selectionModel.setSelectionMode(SelectionMode.SINGLE);
           ObservableList<Student> selectedItem = selectionModel.getSelectedItems();
           Integer studentID = selectedItem.get(0).getId();

           FXMLLoader loader = new FXMLLoader(SubjectsViewController.class.getResource("subjects-view.fxml"));
           root = loader.load();

           SubjectsViewController subjectsViewController = loader.getController();
           subjectsViewController.loadSubjects(studentID);

           selectionModel.clearSelection();
//           root = FXMLLoader.load(MainController.class.getResource("subjects-view.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root,786, 448);
           stage.setScene(scene);
           stage.setResizable(false);
           stage.show();
       }
       catch (IOException e){
           e.printStackTrace();
       }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            albumNumberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            showStudentsTable();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    public void removeStudent(ActionEvent event) throws IOException {
        TableView.TableViewSelectionModel<Student> selectionModel = studentsTableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Student> selectedItem = selectionModel.getSelectedItems();
        Integer studentID = selectedItem.get(0).getId();
        studentsTableView.getItems().remove(GenerateData.getStudentsContainer().getStudentsMap().get(studentID));
        GenerateData.getStudentsContainer().removeStudent(studentID);
    }
    @FXML
    public void addStudent(ActionEvent event) throws IOException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(StudentAddController.class.getResource("student-add.fxml"));
            Parent parent = fxmlLoader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(parent,355,200);
            newStage.setResizable(false);
            newStage.setScene(newScene);
            newStage.show();
            newStage.setOnCloseRequest((WindowEvent e)->{showStudentsTable();});
//            newScene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, (WindowEvent e)->{showStudentsTable();});
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showStudentsTable(){
        studentsTableView.getItems().removeAll();
        final ObservableList<Student> data = FXCollections.observableArrayList();

        List<Student> students = new ArrayList<>(GenerateData.getStudentsContainer().getStudentsMap().values());

        data.addAll(students);

        studentsTableView.setItems(data);


    }
    public void addToTable(Student student){
//        this.studentsTableView.getItems().add(student);
//        this.studentsTableView.refresh();
        showStudentsTable();
    }
    @FXML
    public void editStudent(ActionEvent event) {
        try {
            TableView.TableViewSelectionModel<Student> selectionModel = studentsTableView.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Student> selectedItem = selectionModel.getSelectedItems();
            Integer studentID = selectedItem.get(0).getId();

            FXMLLoader fxmlLoader = new FXMLLoader(StudentEditController.class.getResource("student-edit.fxml"));
            Parent parent = fxmlLoader.load();
            System.out.println(studentID);
            StudentEditController studentEditController = fxmlLoader.getController();
            studentEditController.setStudentID(studentID);

            Stage newStage = new Stage();
            Scene newScene = new Scene(parent,355,200);
            newStage.setResizable(false);
            newStage.setScene(newScene);
            newStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
