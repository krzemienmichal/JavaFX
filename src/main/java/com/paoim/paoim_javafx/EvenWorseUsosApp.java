package com.paoim.paoim_javafx;

import com.paoim.paoim_javafx.api.GenerateData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class EvenWorseUsosApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GenerateData.generate();
//        System.out.println(Arrays.toString(GenerateData.getSubjectContainer().getSubjects().keySet().stream().toArray()));
        FXMLLoader fxmlLoader = new FXMLLoader(EvenWorseUsosApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 786, 448);
        stage.setResizable(false);
        stage.setTitle("EvenWorseUSOS!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}