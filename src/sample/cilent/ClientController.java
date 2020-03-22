package sample.cilent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import sample.Common.ChatMsg;

import java.awt.desktop.SystemSleepEvent;
import java.net.*;
import java.io.IOException;
import java.util.ResourceBundle;

public class ClientController implements ClientObserver{

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
    TextField outputUI;

    Socket clientSocket = new Socket("localhost",800);
    Client client = new Client(clientSocket);

    public ClientController() throws IOException, InterruptedException {

    }

    public void initData(String name, String ch,String ip){
        System.out.println("ADDING INIT");
        client.addObserver(this);
        client.setUserInfo(name ,ch);
        username = name;
        channel = ch;
        ipAdd = ip;
        ipAddress.setText(ipAdd);
    }

    public void sendMessage() throws IOException {
        username = client.getUsername();
        channel = client.getChannel();
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
        outputUI.appendText( "\n" + message);
        System.out.println("Current messss" + message);
    }

    @Override
    public void update(ChatMsg msg) {
        //Add msg text to text box
        System.out.println("DISPLAYYEDDDD");
        displayMsg(msg.getData());
    }

}
