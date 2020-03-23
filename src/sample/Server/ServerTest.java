package sample.Server;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServerTest implements Initializable {
    public TextField txtf_Log;
    ServerSocket socket;
    Thread workerThread;
    ClientConnection client;
    static ArrayList<ClientConnection> clientConnection = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ServerSocket socket = new ServerSocket(800);
        } catch (IOException e) {
            e.printStackTrace();
        }

        workerThread = new Thread(new ServerPublishThread());
        workerThread.start();

        while(true){
            System.out.println("Before accept ");

            try {
                client = new ClientConnection(socket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("after accept ");
            clientConnection.add(client);
        }

    }
}





    /*
                ServerSocket socket = new ServerSocket(800);
        Thread workerThread = new Thread(new ServerPublishThread());
        workerThread.start();
        while (true) {
            System.out.println("Before accept ");
            ClientConnection client = new ClientConnection(socket.accept());
            System.out.println("after accept ");
            clientConnection.add(client);

     */








