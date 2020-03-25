package sample.Server;

import sample.Common.ChatMsg;
import sample.Common.JoinChannelMsg;

public interface ServerObserver {
    void update(ChatMsg msg);


}
