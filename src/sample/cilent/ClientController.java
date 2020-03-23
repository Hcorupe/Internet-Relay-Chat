package sample.cilent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Common.ChatMsg;

import java.awt.*;
import java.awt.desktop.SystemSleepEvent;
import java.net.*;
import java.io.IOException;
import java.util.ResourceBundle;

public class ClientController implements ClientObserver {

    String channel;
    String username;
    String ipAdd;
    String currentMessage;

    @FXML
    Menu changeCh;
    @FXML
    TextField ipAddress;
    @FXML
    TextField sendBox;
    @FXML
    TextArea outputUI;

    Socket clientSocket = new Socket("localhost",8000);
    Client client = new Client(clientSocket);

    public ClientController() throws IOException, InterruptedException {
        System.out.println("Adding OBServe");
        client.addObserver(this);
    }

    public void initData(String name, String ch,String ip) throws IOException {
        System.out.println("ADDING INIT");
        username = name;
        channel = ch;
        ipAdd = ip;
        ipAddress.setText(ipAdd);
        client.joinChannel(channel);
    }

    public void send() throws IOException {
        System.out.println("USERNAME: " + username);
        System.out.println("CHANNEL: " + channel);
        currentMessage = sendBox.getText();
        client.sendMessage(channel,currentMessage);
        sendBox.setText("");
    }
    public void disconnect() throws IOException {
        client.shutdown();
    }
    public void changeChannel(ActionEvent Event) throws IOException {   //Changes the channel on the second UI
        MenuItem clickedButton = (MenuItem) Event.getTarget();
        channel = clickedButton.getText();
        changeCh.setText(channel);
        client.joinChannel(channel);
    }

    public void displayMsg(String message){
        outputUI.appendText(username + ": " + message + "\n");
    }

    @Override
    public void update(ChatMsg msg) {
        //Add msg text to text box
        displayMsg(msg.getData());
    }

}
