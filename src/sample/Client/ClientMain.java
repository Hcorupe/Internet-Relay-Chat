package sample.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("This one is first in the Client.");
        Parent root = FXMLLoader.load(getClass().getResource("ClientLogin.fxml"));
        System.out.println("This one is first in the Client.");
        primaryStage.setTitle("Client Login");
        System.out.println("This one is first in the Client.");
        primaryStage.setScene(new Scene(root));
        System.out.println("This one is first in the Client.");
        primaryStage.show();
    }
    public static void main(String[] args){
            launch(args);
    }
}
