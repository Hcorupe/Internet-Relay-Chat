package sample.Server;

import sample.Common.JoinChannelMsg;

public interface ServerJoinMsgSubject {

    void addJoinServerMsg(ServerJoinMsgObserver c);
    void NotifyJoinServerObserver(JoinChannelMsg msg);
}
