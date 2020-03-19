package sample.cilent;

import sample.Common.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {

    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public Client(Socket socket) throws IOException, InterruptedException {
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

    public void joinChannel(String channel) throws IOException {
        System.out.println("Inside join channel ");
        out.writeObject(new JoinChannelMsg(channel));
    }

    public void sendMessage(String channel, String data) throws IOException {
        System.out.println("Inside sendmessage ");
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
        System.out.println( "ProcessChatmSg " + msg.getChannel() + msg.getData());
    }

    public void shutdown() throws IOException {
        out.writeObject(new ShutDownMsg());
        System.out.println("Client has disconnected Shut down");
    }


}
