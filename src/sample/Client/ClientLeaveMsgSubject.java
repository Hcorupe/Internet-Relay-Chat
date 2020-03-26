package sample.Client;

import sample.Common.JoinChannelMsg;
import sample.Common.LeaveChannelMsg;

public interface ClientLeaveMsgSubject {
    void addLeaveChannelMsg(ClientLeaveMsgObserver c);
    void NotifyLeaveChannelObserver(LeaveChannelMsg msg);
}
