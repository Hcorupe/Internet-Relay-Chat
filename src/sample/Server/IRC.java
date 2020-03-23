package sample.Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class IRC extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("This should be first, but its not?");
        Parent root = FXMLLoader.load(getClass().getResource("ServerUI.fxml"));
        System.out.println("This should be first, but its not?");
        primaryStage.setTitle("Server");
        System.out.println("This should be first, but its not?");
        primaryStage.setScene(new Scene(root, 400, 400));
        System.out.println("This should be first, but its not?");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        }
    }
