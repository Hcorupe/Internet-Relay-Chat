package sample.Client;

import sample.Common.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
public class Client implements Runnable, ClientSubject,ClientJoinMsgSubject,ClientLeaveMsgSubject {

    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    ArrayList<ClientObserver> myobservers = new ArrayList<>();
    ArrayList<ClientJoinMsgObserver> JoinMsgObservers = new ArrayList<>();
    ArrayList<ClientLeaveMsgObserver> LeaveMsgObservers = new ArrayList<>();

    public Client(Socket socket) throws IOException {
        System.out.println("Before socket ");
        this.socket = socket;
        System.out.println("Before In ");
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
        System.out.println("Before thread ");
        Thread t2 = new Thread(this);
        t2.start();
        System.out.println("after thread ");
    }

    public void joinChannel(String channel,String userName) throws IOException {
        System.out.println("Inside joinChannel ");
        out.writeObject(new JoinChannelMsg(channel,userName));
    }
    public void sendMessage(String channel, String data,String userName) throws IOException {
        System.out.println("Inside sendMessage ");
        out.writeObject(new ChatMsg(channel,data,userName));
    }
    public void LeaveMessage(String channel,String userName) throws IOException {
        System.out.println("Inside Leave msg");
        out.writeObject(new LeaveChannelMsg(channel,userName));
    }

    public void run(){
        try{
            while(true){
                System.out.println("Trying to read ");
                Message msg = (Message)in.readObject();

                if(msg.getType() == MsgType.type.JoinMsg){
                    processJoinChannelMsg((JoinChannelMsg)msg);
                }
                else if(msg.getType() == MsgType.type.ChatMsg){
                    processChatMsg((ChatMsg)msg);
                }
                else if(msg.getType() == MsgType.type.LeaveMsg){
                    processLeaveChannelMsg((LeaveChannelMsg)msg);
                }
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private void processChatMsg(ChatMsg msg){
        System.out.println("PROCESSING chat");
        notifyObserver(msg);
        System.out.println( "ProcessChatMsg " + msg.getChannel() + msg.getData() );
    }

    private void processJoinChannelMsg(JoinChannelMsg msg){
        System.out.println("PROCESSING join");
        NotifyJoinChannelObserver(msg);
        System.out.println( "ProcessJoinChannelMsg " );
    }

    private void processLeaveChannelMsg(LeaveChannelMsg msg){
        System.out.println("Processing leave msg");
        NotifyLeaveChannelObserver(msg);

    }

    public void shutdown() throws IOException {
        out.writeObject(new ShutDownMsg());
        System.out.println("Client has disconnected Shut down");
    }

    @Override
    public void addObserver(ClientObserver c) {
        this.myobservers.add(c);
    }

    @Override
    public void notifyObserver(ChatMsg msg) {
        for(ClientObserver c : this.myobservers){
            c.update(msg);
        }
    }

    @Override
    public void addJoinChannelMsg(ClientJoinMsgObserver c) {
        this.JoinMsgObservers.add(c);
    }

    @Override
    public void NotifyJoinChannelObserver(JoinChannelMsg msg) {
        for(ClientJoinMsgObserver c: this.JoinMsgObservers){
            c.updateJoinChannel(msg);
        }
    }

    @Override
    public void addLeaveChannelMsg(ClientLeaveMsgObserver c) {
        this.LeaveMsgObservers.add(c);
    }

    @Override
    public void NotifyLeaveChannelObserver(LeaveChannelMsg msg) {
        for(ClientLeaveMsgObserver c : this.LeaveMsgObservers){
            c.updateLeaveChannel(msg);
        }
    }
}