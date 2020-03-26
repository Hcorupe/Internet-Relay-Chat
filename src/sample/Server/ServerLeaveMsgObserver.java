package sample.Server;

import sample.Common.LeaveChannelMsg;

public interface ServerLeaveMsgObserver {
    void updateLeaveChannel(LeaveChannelMsg msg);
}
