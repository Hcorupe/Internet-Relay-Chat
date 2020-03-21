package sample.cilent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import javax.swing.*;
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

    public Client temp;

    public ClientLoginController() throws IOException, InterruptedException {
    }

    public void setLogIn(ActionEvent event) throws IOException, InterruptedException {
        if(enteredUsername && selectedChannel) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ClientUI.fxml"));
            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);

            ClientController controller = loader.getController();
            controller.initData(temp);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
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
    

    //GETS Client ADDRESS
   /* InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("System IP Address : " +
                (localhost.getHostAddress()).trim());
    */
}
