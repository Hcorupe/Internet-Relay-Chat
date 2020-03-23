
package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Client.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    public Button sendBtn;
    public Button disconnectBtn;
    public TextArea msgType;
    public ListView msgView;
    public Label userID;
    public String userName;


    public Client client;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userID.setText(userName);
    }


    public void sendMsg(MouseEvent mouseEvent) throws IOException {
        client.send(msgType.getText());
        msgType.setText("");
    }

    public void typeServer(ActionEvent actionEvent) {



    }
}