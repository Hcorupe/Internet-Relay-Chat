package sample.Server;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.beans.Transient;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ServerTest implements Initializable {
    public TextField txtf_Log;
    ServerSocket socket;
    Thread workerThread;
    ClientConnection client;
    static ArrayList<ClientConnection> clientConnection = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        new Thread(() -> {
            System.out.println("inside Thread");
            try {
                Thread workerThread = new Thread(new ServerPublishThread());
                workerThread.start();
                socket = new ServerSocket(800);
                System.out.println("After socket");
                Platform.runLater(() -> txtf_Log.appendText("New Server start at " + new Date() + '\n'));
                while (true) {
                    System.out.println("Before accept ");
                    ClientConnection client = new ClientConnection(socket.accept());
                    System.out.println("after accept ");
                    clientConnection.add(client);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();






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








