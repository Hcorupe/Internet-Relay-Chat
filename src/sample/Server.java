package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Server extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    ArrayList<ClientConnection> clientConnection = new ArrayList<>();
    ArrayList<BlockingQueue> blockingQueue = new ArrayList<>();
    //ArrayList<PublishQueue> publishQueue = new ArrayList<>();




    public static void main(String[] args) throws IOException {
        //launch(args);

        ServerSocket socket = new ServerSocket(800);
        while(true){
            socket.accept();

            //ClientConnection = new ClientConnection(socket);
        }




    }
}
