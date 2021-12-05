package com.paoim.paoim_javafx;

import com.paoim.paoim_javafx.api.GenerateData;
import com.paoim.paoim_javafx.api.Student;
import com.paoim.paoim_javafx.api.StudentCondition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentEditController implements Initializable {

    private Integer studentID;

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    @FXML
    public Button cancelButton;
    @FXML
    public Button editButton;
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    public ComboBox<StudentCondition> studentConditionComboBox;
    @FXML
    public TextField birthYearTextField;
    @FXML
    public TextField lastNameTextField;
    @FXML
    public TextField firstNameTextField;
    @FXML
    public TextField albumNumberTextField;

    @FXML
    public void editStudent(ActionEvent event) throws IOException {
        try{
            Student student = GenerateData.getStudentsContainer().getStudentsMap().get(studentID);
            student.setFirstName(firstNameTextField.getText());
            student.setLastName(lastNameTextField.getText());
            student.setBirthDate(Integer.parseInt(birthYearTextField.getText()));
            student.setStudentCondition(studentConditionComboBox.getValue());
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("main-view.fxml"));
            root = fxmlLoader.load();
            MainController mainController = fxmlLoader.getController();
            stage = (Stage) editButton.getScene().getWindow();
            stage.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void cancelEdit(ActionEvent event) {
        stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println(studentID);
        Platform.runLater(()->{
            Student student =GenerateData.getStudentsContainer().getStudentsMap().get(studentID);
            albumNumberTextField.setText(studentID.toString());
            albumNumberTextField.setEditable(false);
            firstNameTextField.setText(student.getFirstName());
            lastNameTextField.setText(student.getLastName());
            birthYearTextField.setText(String.valueOf(student.getBirthDate()));
            studentConditionComboBox.setValue(student.getStudentCondition());
            studentConditionComboBox.setItems(FXCollections.observableArrayList(StudentCondition.values()));});

    }
}


