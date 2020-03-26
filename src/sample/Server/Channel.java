package sample.Server;

import sample.Client.ClientJoinMsgObserver;
import sample.Client.ClientObserver;
import sample.Common.ChatMsg;
import sample.Common.JoinChannelMsg;
import java.io.IOException;
import java.util.ArrayList;

public class Channel implements ServerJoinMsgSubject{
    ArrayList<ClientConnection> clients = new ArrayList<ClientConnection>();
<<<<<<< HEAD
    ArrayList<ClientObserver> myobservers = new ArrayList<>();
    ArrayList<ClientJoinMsgObserver> JoinMsgObservers = new ArrayList<>();
    public Channel() {
    }
=======
    public Channel() { }
>>>>>>> 019aaa51a82f09183cccb4e1024f62d49ced0aa9

    public void addClient(JoinChannelMsg msg) throws IOException {
        System.out.println("Adding Client");
        if(clients.contains(msg.getClient())){
            return; // prevent client from adding multiple times
        }
        for(ClientConnection c : clients){
            c.sendMessage(msg);
        }
        clients.add(msg.getClient());
    }
    public void deleteClient(ClientConnection client) {
        System.out.println("Remove client connection");
        clients.remove(client);
    }
    public void PublishToChannel(ChatMsg msg){
        System.out.println("PUBLISHING TO CHANNELS");
        for(ClientConnection c : clients){
            c.sendMessage(msg);
        }
        System.out.println(clients.size());
    }
    @Override
    public void addJoinServerMsg(ClientConnection c){
        this.addJoinServerMsg(c);
    }
    @Override
    public void NotifyJoinServerObserver( ){

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
}
