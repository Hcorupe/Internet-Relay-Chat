package sample.Common;

public class JoinChannelMsg extends Message {

    String channel;
    String userName;

    public JoinChannelMsg(String channel,String userName){
        this.channel = channel;
        this.userName = userName;
        this.type = MsgType.type.JoinMsg;
    }

    public String getChannel() {
        return channel;
    }

    public String getUserName(){return userName; }


}
