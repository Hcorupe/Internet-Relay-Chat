package sample.Server;

import sample.Common.ChatMsg;
import sample.cilent.ClientObserver;

public interface ServerSubject {
    void addObserver(ServerObserver s);
    void notifyObserver(ChatMsg msg);
}
