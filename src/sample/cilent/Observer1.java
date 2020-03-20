package sample.cilent;

import sample.Common.ChatMsg;
import sample.Common.Message;

import java.util.Observer;

public interface Observer1 {
    void addObserver(Client client);
    void notifyObservers(ChatMsg msg);

}
