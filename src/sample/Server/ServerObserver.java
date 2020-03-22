package sample.Server;

import sample.Common.ChatMsg;

public interface ServerObserver {
    void update(ChatMsg msg);
}
