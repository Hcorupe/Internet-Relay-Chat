package sample.cilent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import java.net.*;
import java.io.IOException;

public class ClientController {

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

    Socket Clientsocket = new Socket("localhost",800);
    Client client = new Client(Clientsocket);

    public ClientController() throws IOException, InterruptedException {
        System.out.println("NOT WORKING");
    }

    public void initData(String name, String ch,String ip){
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
        currentMessage = sendBox.getText();
        System.out.println("CHANNEL: " + channel);
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

}
