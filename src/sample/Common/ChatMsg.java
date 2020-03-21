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
        System.out.println(channel);
        return channel;
    }

    public String getData() {
        System.out.println(data);
        return data;
    }
}
