package sample.Server;

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

public class IRC extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("serverTest.fxml"));
        primaryStage.setTitle("Server Logs");
        primaryStage.setScene(new Scene(root, 500, 575));
        primaryStage.show();

    }

    static ArrayList<ClientConnection> clientConnection = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {

        launch(args);

        }
    }

        //launch(args);

        /*
        ServerSocket socket = new ServerSocket(800);
        Thread workerThread = new Thread(new ServerPublishThread());
        workerThread.start();
        while (true) {
            System.out.println("Before accept ");
            ClientConnection client = new ClientConnection(socket.accept());
            System.out.println("after accept ");
            clientConnection.add(client);
        }

            -Move Server socket into the UIcontroller for server;
            -update inside UIcontroller adds msg to a log display .
            -UIcontroller implement serverObserver
            -ServerPublishthread will impelment serverSubject

        if(args[0].equals("server")) {
            ServerSocket socket = new ServerSocket(800);
            Thread workerThread = new Thread(new ServerPublishThread());
            workerThread.start();
            while (true) {
                System.out.println("Before accept ");
                ClientConnection client = new ClientConnection(socket.accept());
                System.out.println("after accept ");
                clientConnection.add(client);
            }
        }
            else if(args[0].equals("client")){
                Thread.sleep(5000);
                Socket Clientsocket = new Socket("localhost",800);
                System.out.println("Before creating client ");
                Client client = new Client(Clientsocket);
                client.sendMessage("ch1","Text ...");
                client.shutdown();
            }
             */




