package sample.Server;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Common.ChatMsg;
import sample.Common.JoinChannelMsg;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ServerController implements ServerObserver, Initializable ,ServerJoinMsgObserver {
    @FXML
    public TextArea txta_log;

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
                ServerPublishThread spThread=new ServerPublishThread();
                spThread.addObserver(this);
                Thread workerThread = new Thread(spThread);
                workerThread.start();
                socket = new ServerSocket(8000);
                System.out.println("After socket");
                Platform.runLater(() -> txta_log.appendText("New Server start at "
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
        txta_log.appendText("\n" + message);
        System.out.println("Log message -- " + message);
    }

    @Override
    public void update(ChatMsg msg) {
        System.out.println("UPDATING");
        displayLog(msg.getChannel()+" "+msg.getUser()+" "+msg.getData());
    }
    @Override
    public void updateJoinServer() {
        txta_log.appendText("Client");
    }
    }


