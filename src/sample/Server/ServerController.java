package sample.Server;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.Common.ChatMsg;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ServerController implements ServerObserver, Initializable {
    @FXML
    public TextField txtf_log;
    ServerSocket socket;
    Thread workerThread;
    static ArrayList<ClientConnection> clientConnection = new ArrayList<>();

    public ServerController() throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            System.out.println("inside Thread");
            try {
                Thread workerThread = new Thread(new ServerPublishThread());
                workerThread.start();
                socket = new ServerSocket(8000);
                System.out.println("After socket");
                Platform.runLater(() -> txtf_log.appendText("New Server start at "
                        + new Date() + '\n'  ));
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
    public void displayLog(String message) {
        txtf_log.appendText("\n" + message);
    }

    @Override
    public void update(ChatMsg msg) {
        displayLog(msg.getData());
    }

}
