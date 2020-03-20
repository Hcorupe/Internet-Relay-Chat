package sample.cilent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sample.Common.*;
import sample.Server.ClientConnection;
import sample.Server.ServerPublishThread;
import sample.cilent.Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientMain extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ClientLogin.fxml"));
        primaryStage.setTitle("Client Login");
        primaryStage.setScene(new Scene(root, 250, 400));
        primaryStage.show();
    }

    static ArrayList<ClientConnection> clientConnection = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
            //System.out.println(args[0]);
            launch(args);


    }
}
