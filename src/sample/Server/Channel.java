package sample.Server;

import sample.Common.ChatMsg;
import sample.Common.JoinChannelMsg;
import java.io.IOException;
import java.util.ArrayList;

public class Channel {
    ArrayList<ClientConnection> clients = new ArrayList<ClientConnection>();

    public Channel() {

    }

    public void addClient(JoinChannelMsg msg) throws IOException {
        if(clients.contains(msg.getClient())){
            return; // prevent client from adding multpule times
        }
        for(ClientConnection c : clients){
            c.sendMessage(msg);
        }
        clients.add(msg.getClient());
    }

    public void PublishToChannel(ChatMsg msg) throws IOException {
        for(ClientConnection c : clients){
            c.sendMessage(msg);
        }
        System.out.println(clients.size());
    }
}
