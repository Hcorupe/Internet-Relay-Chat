package sample.cilent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ClientController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        channel = " ";
        username = " ";
        enteredUsername = false;
        selectedChannel = false;
    }

    public void setLogIn() throws IOException {
        if(enteredUsername && selectedChannel) {
            VBox pane = FXMLLoader.load(getClass().getResource("ClientUI.fxml"));
            rootPane.getChildren().setAll(pane);
            //We need to resize the second screen
        }
        else{

        }

    }
    public void chooseChannel(ActionEvent Event){
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


}
