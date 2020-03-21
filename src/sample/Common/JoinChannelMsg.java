package sample.Common;

public class JoinChannelMsg extends Message {

    String channel;

    public JoinChannelMsg(String channel){
        this.channel = channel;

        this.type = MsgType.type.JoinMsg;
    }

    public String getChannel() {
        return channel;
    }


}
