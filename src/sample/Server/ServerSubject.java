package sample.Server;

import sample.Client.ClientObserver;
import sample.Common.ChatMsg;
import sample.Common.JoinChannelMsg;

public interface ServerSubject {
    void addObserver(ServerObserver s);
    void notifyObserver(ChatMsg msg);

}
