package sample.Client;

import sample.Common.JoinChannelMsg;
import sample.Common.LeaveChannelMsg;

public interface ClientLeaveMsgObserver {
    void updateLeaveChannel(LeaveChannelMsg msg);
}
