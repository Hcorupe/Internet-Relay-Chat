package sample.Server;

import sample.Common.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
public class ServerPublishThread implements Runnable,ServerSubject,ServerJoinMsgSubject,ServerLeaveMsgSubject{

    static LinkedBlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<Message>();
    HashMap<String, Channel> channels = new HashMap<String, Channel>();
    ArrayList<ServerObserver> myobservers = new ArrayList<>();
    ArrayList<ServerJoinMsgObserver> JoinMsgObservers = new ArrayList<>();
    ArrayList<ServerLeaveMsgObserver> LeaveMsgObservers = new ArrayList<>();

    public void run() { // pull from q and check msg type .. then handle
        while(true){
            try {
                System.out.println("Trying to take from queue ");
                Message msg = blockingQueue.take();
                if (msg.getType() == MsgType.type.JoinMsg) {
                    processMsgJoinMsg((JoinChannelMsg) msg);
                } else if (msg.getType() == MsgType.type.ChatMsg) {
                    processChatMsg((ChatMsg) msg);
                }
                if(msg.getType() == MsgType.type.ShutDownMsg){
                    msg.getClient().shutdown();
                }
                if(msg.getType() == MsgType.type.LeaveMsg){
                    addLeaveChannelMsg((ServerLeaveMsgObserver) msg);
                    msg.getClient().shutdown();
                }
            }catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void processMsgJoinMsg(JoinChannelMsg msg) throws IOException {
        Channel channel = channels.get(msg.getChannel()); // Pass String msg.channel Pull channel from hashmap
        if(channel == null){
            channel = new Channel();
            channels.put(msg.getChannel(),channel);  // puts string and new channel into hashmap
        }
        for (Channel value : channels.values()) {
            value.deleteClient(msg.getClient());
        }
        NotifyJoinChannelObserver(msg);
        channel.addClient(msg);   // adds client connection to Channels client arrayList

        System.out.println(msg.getChannel());
    }
    public void processChatMsg(ChatMsg msg) throws IOException {
        Channel channel = channels.get(msg.getChannel());
        System.out.println(msg.getData());
        notifyObserver(msg);
        channel.PublishToChannel(msg);
    }

    static void addMsg(Message msg){
        System.out.println("Adding msg to queue of type " + msg.getType());
        blockingQueue.add(msg);
    }

    @Override
    public void addObserver(ServerObserver s) {
        System.out.println("ADDING OBS");
        this.myobservers.add(s);
    }


    @Override
    public void notifyObserver(ChatMsg msg) {
        System.out.println("NOTIFYING OBS");
        for(ServerObserver s: this.myobservers){
            s.update(msg);
        }
    }

    @Override
    public void addJoinChannelMsg(ServerJoinMsgObserver c) {
        this.JoinMsgObservers.add(c);
    }

    @Override
    public void NotifyJoinChannelObserver(JoinChannelMsg msg) {
        System.out.println(msg.getUserName() + "BEING CALLED");
        for(ServerJoinMsgObserver c: this.JoinMsgObservers){
            c.updateJoinChannel(msg);
        }
    }
    @Override
    public void addLeaveChannelMsg(ServerLeaveMsgObserver c){
        this.LeaveMsgObservers.add(c);

    }
    @Override
    public void NotifyLeaveChannelObserver(LeaveChannelMsg msg){
        for(ServerLeaveMsgObserver c : this.LeaveMsgObservers){
            c.updateLeaveChannel(msg);
        }
    }
}
