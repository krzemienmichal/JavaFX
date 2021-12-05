package com.paoim.paoim_javafx;

import com.paoim.paoim_javafx.api.GenerateData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private TextField firsNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private Button logInButton;
    @FXML
    private Button cancelButton;

    @FXML
    public void loginClick(ActionEvent event){
        try {
            Integer studentID = GenerateData.getStudentsContainer().findStudentID(firsNameTF.getText(),lastNameTF.getText());
            if(studentID != -1){
                FXMLLoader loader = new FXMLLoader(SubjectsViewController.class.getResource("subjects-view.fxml"));
                root = loader.load();

                SubjectsViewController subjectsViewController = loader.getController();
                subjectsViewController.loadSubjects(studentID);

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root,786, 448);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void cancelButtonClick(ActionEvent event){
        stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
