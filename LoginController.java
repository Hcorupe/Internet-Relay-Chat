package Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Client.*;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class LoginController {
    @FXML
    public Button loginBtn;

    public TextField userNameText;
    public TextField IPText;
    public TextField PortText;


    public void Login(ActionEvent event) throws IOException {
        Client tempClient=new Client(IPText.getText(),Integer.parseInt(PortText.getText()),userNameText.getText());
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("ClientUI.fxml"));
        Parent tableViewParent=loader.load();

        Scene tableViewScene=new Scene(tableViewParent);
        ClientController controller=loader.getController();

        controller.userID.setText(userNameText.getText());
        controller.client=tempClient;
        controller.client.connect();

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
}
