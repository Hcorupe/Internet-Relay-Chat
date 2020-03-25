package sample.Client;

import sample.Common.JoinChannelMsg;

public interface ClientJoinMsgObserver {
    void updateJoinChannel(JoinChannelMsg msg);
}
