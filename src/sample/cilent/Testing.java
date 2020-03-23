package sample.cilent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.Common.ChatMsg;
import sample.Common.Message;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Testing implements Initializable,ClientObserver {
    @FXML
        private TextArea txtf_DisplayMsg;

        @FXML
        private TextField txtf_SendMsg;

        @FXML
        private Button btn_SendMsg;

        @FXML
        private Button btn_channel1;

        @FXML
        private Button btn_channel2;

        public Pane Pane_scrollPane;

        String channel;
        String currentMessage;
        boolean selectedChannel;
        Socket Clientsocket;

    private final Button add = new Button("Add");
    private final VBox chatBox = new VBox(5);
    private List<Label> messagelist = new ArrayList<>();
    Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Thread.sleep(5000);
            Clientsocket = new Socket("localhost",800);
            client = new Client(Clientsocket);
            client.addObserver(this); //adds to list of things to get updated client will notify
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        channel = " ";
        currentMessage = " ";
    }

    public void sendMessage() throws IOException {
        currentMessage = txtf_SendMsg.getText();
        client.sendMessage(channel,currentMessage);
    }

    public void displayMsg(String message){

        //btn_SendMsg.setOnAction(e);
        txtf_DisplayMsg.appendText( "\n" + message);
        System.out.println("Current messss" + message);
    }

    public void chooseChannel(ActionEvent Event) throws IOException {
        Button clickedButton = (Button) Event.getTarget();
        channel = clickedButton.getText();
        selectedChannel = true;
        client.joinChannel(channel);
        System.out.println(channel);

        if(clickedButton == (Button) Event.getTarget()){
            txtf_DisplayMsg.setText(" "); // reset text box empty if channel changed or clicked
        }
    }
    
    @Override
    public void update(ChatMsg msg) {
        //Add msg text to text box
        displayMsg(msg.getData());

    }
}
