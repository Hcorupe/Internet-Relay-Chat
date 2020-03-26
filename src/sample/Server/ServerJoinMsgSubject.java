package sample.Server;

import sample.Common.JoinChannelMsg;

public interface ServerJoinMsgSubject {
    void addJoinChannelMsg(ServerJoinMsgObserver c);
    void NotifyJoinChannelObserver(JoinChannelMsg msg);
}
