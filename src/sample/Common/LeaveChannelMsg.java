package sample.Common;

public class LeaveChannelMsg extends Message {

    String channel;
    String userName;

    public LeaveChannelMsg(String channel,String userName){
        this.channel = channel;
        this.userName = userName;
        this.type = MsgType.type.LeaveMsg;
    }

    public String getChannel() {
        return channel;
    }

    public String getUserName(){return userName; }

}
