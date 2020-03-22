package sample.cilent;

import sample.Common.ChatMsg;

public interface ClientSubject {
    void addObserver(ClientObserver c);
    void notifyObserver(ChatMsg msg);
}
