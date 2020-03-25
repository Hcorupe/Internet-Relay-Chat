package sample.Client;

import sample.Client.ClientObserver;
import sample.Common.ChatMsg;
import sample.Common.JoinChannelMsg;

public interface ClientSubject {
    void addObserver(ClientObserver c);
    void notifyObserver(ChatMsg msg);

    //void addJoinChannelMsg(ClientObserver c);
    //void NotifyJoinChannelObserver(JoinChannelMsg msg);

}
