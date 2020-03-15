package sample.Server;

import sample.Common.ChatMsg;
import sample.Common.JoinChannelMsg;
import sample.Common.MsgType;
import sample.Common.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerPublishThread implements Runnable{
//bool keeppublish
    //setKeepPub(..)

    //get next msg to process
    //find out which client (Cn) need it
    //write msg to outputstream

    //while(keepPublishing)
    //pull from queue
    //find out who needs it ( client connection)
    static LinkedBlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<Message>();
    HashMap<String, Channel> channels = new HashMap<String, Channel>();

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
        channel.addClient(msg);   // adds client connection to Channels client arrayList
        System.out.println(msg.getChannel());
    }
    public void processChatMsg(ChatMsg msg) throws IOException {
        Channel channel = channels.get(msg.getChannel());
        channel.PublishToChannel(msg);
    }

    static void addMsg(Message msg){
        System.out.println("Adding msg to queue of type " + msg.getType());
        blockingQueue.add(msg);
    }


}
