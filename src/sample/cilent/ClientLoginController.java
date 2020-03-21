package sample.cilent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.*;
import java.io.IOException;


public class ClientLoginController {

    String channel;
    String username;
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

    public ClientLoginController() throws IOException, InterruptedException {
    }

    public void setLogIn() throws IOException, InterruptedException {
        if(enteredUsername && selectedChannel) {
            opensSecondUI();
            //We need to resize the second screen
        }
    }
    public void chooseChannel(ActionEvent Event) throws IOException {
        MenuItem clickedButton = (MenuItem) Event.getTarget();
        channel = clickedButton.getText();
        channelMenu.setText(channel);
        selectedChannel = true;
        System.out.println(channel);
    }
    public void setUsername(){
        username = usernameEntry.getText();
        System.out.println(username);
        enteredUsername = true;
    }

    public void opensSecondUI() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ClientUI.fxml"));
        VBox pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    //GETS Client ADDRESS
   /* InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("System IP Address : " +
                (localhost.getHostAddress()).trim());
    */
}
