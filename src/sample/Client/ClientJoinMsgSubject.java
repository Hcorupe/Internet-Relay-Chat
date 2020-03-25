package sample.Client;

import sample.Common.JoinChannelMsg;

public interface ClientJoinMsgSubject {

    void addJoinChannelMsg(ClientJoinMsgObserver c);
    void NotifyJoinChannelObserver(JoinChannelMsg msg);
}
