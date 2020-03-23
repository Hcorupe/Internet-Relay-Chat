package sample.Server;

import sample.Common.ChatMsg;

public interface ServerSubject {
    void addObserver(ServerObserver s);
    void notifyObserver(ChatMsg msg);
}
