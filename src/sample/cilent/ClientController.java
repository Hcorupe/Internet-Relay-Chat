package sample.cilent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    String channel;
    String username;
    String currentMessage;
    boolean enteredUsername;
    boolean selectedChannel;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        channel = " ";
        username = " ";
        currentMessage = " ";
        enteredUsername = false;
        selectedChannel = false;
    }

    public void setLogIn() throws IOException, InterruptedException {
        if(enteredUsername && selectedChannel) {
            opensSecondUI();
            //We need to resize the second screen
        }
        else{
            //Need to output corrections
        }

    }
    public void chooseChannel(ActionEvent Event) throws IOException {
        MenuItem clickedButton = (MenuItem) Event.getTarget();
        channel = clickedButton.getText();
        channelMenu.setText(channel);
        selectedChannel = true;
        client.joinChannel(channel);
        System.out.println(channel);
    }
    public void setUsername(){
            username = usernameEntry.getText();
            System.out.println(username);
            enteredUsername = true;
    }

    public void sendMessage() throws IOException {
        currentMessage = sendBox.getText();
        client.sendMessage(channel,currentMessage);
    }
    public void disconnect() throws IOException {
        client.shutdown();
    }
    public void changeChannel(ActionEvent Event) throws IOException {   //Changes the channel on the second UI
        MenuItem clickedButton = (MenuItem) Event.getTarget();
        channel = clickedButton.getText();
        changeCh.setText(channel);
        client.joinChannel(channel);
        System.out.println(channel);

    }
    public void opensSecondUI() throws IOException {
        VBox pane = FXMLLoader.load(getClass().getResource("ClientUI.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    //GETS Client ADDRESS
   /* InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("System IP Address : " +
                (localhost.getHostAddress()).trim());
    */
}
