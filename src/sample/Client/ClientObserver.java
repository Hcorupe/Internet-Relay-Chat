package sample.Client;

import sample.Common.ChatMsg;

public interface ClientObserver {
    void update(ChatMsg msg);
}
