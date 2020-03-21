package sample.cilent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.InetAddress;

public class ClientLoginController {

    String channel;
    String username;
    String ipAdd;
    boolean enteredUsername;
    boolean selectedChannel;

    @FXML
    TextField usernameEntry;
    @FXML
    MenuButton channelMenu;
    @FXML
    Button logIn;
    @FXML
    VBox rootPane;

    public ClientLoginController(){
    }

    public void setLogIn(ActionEvent event) throws IOException {
        if(enteredUsername && selectedChannel) {
            InetAddress localhost = InetAddress.getLocalHost();
            ipAdd = localhost.getHostAddress().trim();
            System.out.println(ipAdd);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ClientUI.fxml"));
            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);

            ClientController controller = loader.getController();
            controller.initData(username,channel,ipAdd);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
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
