package sample.cilent;

import sample.Common.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client implements Runnable, ClientSubject  {

    ObjectInputStream in;
    ObjectOutputStream out;
    String userName;
    String channel;
    ArrayList<ClientObserver> myobservers = new ArrayList<>();

    public Client(Socket socket) throws IOException, InterruptedException {
        System.out.println("Before socket ");
        System.out.println("Before In ");
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
        System.out.println("Before thread ");
        Thread t2 = new Thread(this);
        t2.start();
        System.out.println("after thread ");
    }

    public void joinChannel(String channel) throws IOException {
        System.out.println("Inside join channel ");
        out.writeObject(new JoinChannelMsg(channel));
    }

    public void sendMessage(String channel, String data) throws IOException {
        System.out.println("Inside sendMessage ");
        out.writeObject(new ChatMsg(channel,data));
    }

    public void run(){
        try{
            while(true){
                System.out.println("Trying to read ");
                Message msg = (Message)in.readObject();
                if(msg.getType() == MsgType.type.ChatMsg){
                    processChatMsg((ChatMsg)msg);
                }
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private void processChatMsg(ChatMsg msg){
        System.out.println("NOTIFYING THE NOTIFIER");
        notifyObserver(msg);
        System.out.println( "ProcessChatmSg " + msg.getChannel() + msg.getData());
    }

    public void shutdown() throws IOException {
        out.writeObject(new ShutDownMsg());
        System.out.println("Client has disconnected Shut down");
    }

    public void setUserInfo(String user, String ch)  {
        System.out.println(user);
        userName = user;
        channel = ch;
    }

    public String getUsername(){
        return userName;
    }

    public String getChannel(){
        return channel;
    }

    @Override
    public void addObserver(ClientObserver c) {
        System.out.println("ADDING the CLIENT to the Obs");
        this.myobservers.add(c);
    }

    @Override
    public void notifyObserver(ChatMsg msg) {
        System.out.println("NOTIFY");
        for(ClientObserver c : this.myobservers){
            c.update(msg);
        }
    }

}
