package sample.Common;

public class ChatMsg extends Message {
    String userName; //
    String channel;
    String data;


    public ChatMsg(String userName,String channel, String data) {
        this.userName = userName;
        this.channel = channel;
        this.data = data;
        this.type = MsgType.type.ChatMsg;
    }

/*
    public ChatMsg(String channel, String data) {
        this.channel = channel;
        this.data = data;
        this.type = MsgType.type.ChatMsg;
    }

 */

    public String getChannel() {
        return channel;
    }

    public String getData() {
        return data;
    }

    public String getUserName() {
        return userName;
    }

}
