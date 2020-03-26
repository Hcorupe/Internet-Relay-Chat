package sample.Server;

import sample.Common.LeaveChannelMsg;

public interface ServerLeaveMsgSubject {
    void addLeaveChannelMsg(ServerLeaveMsgObserver c);
    void NotifyLeaveChannelObserver(LeaveChannelMsg msg);
}
