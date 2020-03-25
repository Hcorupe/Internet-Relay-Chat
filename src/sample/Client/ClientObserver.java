package sample.Client;

import sample.Common.ChatMsg;
import sample.Common.JoinChannelMsg;

public interface ClientObserver {
    void update(ChatMsg msg);
    //void updateJoinChannel(JoinChannelMsg msg);
}
