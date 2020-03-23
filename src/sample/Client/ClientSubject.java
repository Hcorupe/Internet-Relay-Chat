package sample.Client;

import sample.Client.ClientObserver;
import sample.Common.ChatMsg;

public interface ClientSubject {
    void addObserver(ClientObserver c);
    void notifyObserver(ChatMsg msg);
}
