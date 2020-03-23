package sample.Server;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Common.ChatMsg;
import sample.cilent.ClientController;
import sample.cilent.ClientObserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServerController implements ServerObserver, Initializable {

    public TextField txtf_log;
    ServerSocket socket;
    Thread workerThread;
    static ArrayList<ClientConnection> clientConnection = new ArrayList<>();

    public ServerController() throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            socket = new ServerSocket(800);
            workerThread = new Thread( new ServerPublishThread());
            workerThread.start();
            while(true){
                System.out.println("Before accept ");
                ClientConnection client = new ClientConnection(socket.accept());
                System.out.println("after accept ");
                clientConnection.add(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void displayLog(String message) {
        txtf_log.appendText("\n" + message);
        System.out.println("Log message -- " + message);
    }

    @Override
    public void update(ChatMsg msg) {
        System.out.println("UPDATING");
        displayLog(msg.getData());
    }

}
