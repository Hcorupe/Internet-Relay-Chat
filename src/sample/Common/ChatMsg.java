package sample.Common;

public class ChatMsg extends Message {
    String channel;
    String data;

    public ChatMsg(String channel, String data) {
        this.channel = channel;
        this.data = data;
        this.type = MsgType.type.ChatMsg;
    }

    public String getChannel() {
        return channel;
    }

    public String getData() {
        return data;
    }
}
