package sample.cilent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Common.JoinChannelMsg;

import java.net.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ClientController {

    String channel;
    String username;
    String currentMessage;

    @FXML
    TextField usernameEntry;
    @FXML
    MenuItem whichChannel;
    @FXML
    MenuButton channelMenu;
    @FXML
    Button logIn;
    @FXML
    VBox rootPane;
    //Second UI
    @FXML
    Menu changeCh;
    @FXML
    TextField ipAddress;
    @FXML TextField sendBox;

    Socket Clientsocket = new Socket("localhost",800);
    Client client = new Client(Clientsocket);

    public ClientController() throws IOException, InterruptedException {
        System.out.println("NOT WORKING");
    }

    public void sendMessage() throws IOException {
        username = client.getUsername();
        channel = client.getChannel();
        System.out.println("USERNAME:" + username);
        currentMessage = sendBox.getText();
        System.out.println("CHANNEL" + channel);
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
        client.joinChannel(channel,username);
    }

    //GETS Client ADDRESS
   /* InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("System IP Address : " +
                (localhost.getHostAddress()).trim());
    */
}
