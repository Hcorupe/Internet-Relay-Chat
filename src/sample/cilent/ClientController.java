package sample.cilent;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import sample.Common.ChatMsg;
import sample.Common.Message;
import sample.Server.ClientConnection;

import java.net.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class ClientController implements Initializable,Observable1 {

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

    Socket Clientsocket = null;
    Client client;

    public ClientController() throws IOException, InterruptedException {
        System.out.println("NOT WORKING");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Clientsocket = new Socket("localhost",800);
            client = new Client(Clientsocket);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        channel = " ";
        username = " ";
        currentMessage = " ";
        enteredUsername = false;
        selectedChannel = false;
    }

    public void setLogIn() throws IOException, InterruptedException {
        if(enteredUsername && selectedChannel) {
            opensSecondUI();
            //need to resize the second screen
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
            //System.out.println(username);
            client.addClientName(username);
            enteredUsername = true;
    }

    public void sendMessage() throws IOException {
        currentMessage = sendBox.getText();
        this.notifyObserver(channel,currentMessage);
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

    @Override
    public void update(ChatMsg msg) throws IOException {
        System.out.println("Update called");
        client.sendMessage(channel,this.currentMessage);
    }




    //GETS Client ADDRESS
   /* InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("System IP Address : " +
                (localhost.getHostAddress()).trim());
    */
}
