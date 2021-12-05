package com.paoim.paoim_javafx;

import com.paoim.paoim_javafx.api.GenerateData;
import com.paoim.paoim_javafx.api.Student;
import com.paoim.paoim_javafx.api.StudentCondition;
import com.paoim.paoim_javafx.api.Subject;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentAddController implements Initializable {
    @FXML
    public Button cancelButton;
    @FXML
    public Button addButton;
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
    public void addStudent(ActionEvent event) throws IOException {
        try{Student newStudent = new Student(Integer.parseInt(albumNumberTextField.getText()),
                firstNameTextField.getText(),lastNameTextField.getText(),studentConditionComboBox.getValue(),
                Integer.parseInt(birthYearTextField.getText()));
            GenerateData.getStudentsContainer().addStudent(newStudent);

//            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("main-view.fxml"));
//            root = fxmlLoader.load();
//            MainController mainController = fxmlLoader.getController();
            stage = (Stage) addButton.getScene().getWindow();
            stage.close();
//            mainController.getStudentsTableView().getItems().add(newStudent);
//            mainController.getStudentsTableView().refresh();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void cancelAdding(ActionEvent event) {
        stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentConditionComboBox.setItems(FXCollections.observableArrayList(StudentCondition.values()));
    }
}
