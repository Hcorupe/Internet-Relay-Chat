package sample.Common;

public class ChatMsg extends Message {
    String channel;
    String data;
    String userName;

    public ChatMsg(String channel, String data,String userName) {
        this.channel = channel;
        this.data = data;
        this.type = MsgType.type.ChatMsg;
        this.userName = userName;
    }

    public String getChannel() {
        return channel;
    }

    public String getData() {
        return data;
    }
    public String getUser() {
        return userName;
    }
}
