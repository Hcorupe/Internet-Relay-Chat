package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import sample.Server.Channel;
import sample.Server.ServerPublishThread;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextArea txtArea_SendMsg;
    @FXML
    public TextArea txtArea_DisplayMsg;
    @FXML
    public Button btn_1;
    @FXML
    private CheckBox CheckBox_Channel1;
    @FXML
    private CheckBox CheckBox_Channel2;
    private String channel;

    CheckBox checkbox1 = new CheckBox();
    CheckBox checkbox2 = new CheckBox();
    TextArea sendmsg = new TextArea();
    TextArea displayMsg = new TextArea();

    ServerPublishThread pt = new ServerPublishThread();

    public void initialize(URL location, ResourceBundle resources){
        this.checkbox1 = CheckBox_Channel1;
        this.checkbox2 = CheckBox_Channel2;
        this.sendmsg = txtArea_SendMsg;
        this.displayMsg = txtArea_DisplayMsg;
    }

    public void handleDisplayMsg(){
        
    }

    public void handleCheckBox1(){
        if(checkbox1.isSelected()){
            this.channel = "Channel 1";
            checkbox2.setSelected(false);
        }
    }

    public void handleCheckBox2(){
        if(checkbox2.isSelected()){
            this.channel = "Channel 2";
            checkbox1.setSelected(false);
        }
    }

    public String getChannel(){
        return channel;
    }

    public CheckBox getCheckBox_Channel1() {
        return CheckBox_Channel1;
    }

    public void setCheckBox_Channel1(CheckBox checkBox_Channel1) {
        CheckBox_Channel1 = checkBox_Channel1;
    }

    public CheckBox getCheckBox_Channel2() {
        return CheckBox_Channel2;
    }

    public void setCheckBox_Channel2(CheckBox checkBox_Channel2) {
        CheckBox_Channel2 = checkBox_Channel2;
    }
}
