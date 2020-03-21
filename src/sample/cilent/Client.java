package sample.cilent;
import sample.Common.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
//observer - clients are observing UIcontroller for text coming in to put into the message
public class Client implements Runnable,Observer1 {

    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    String userName;
    List<Observer1> observers = new ArrayList<>();

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

    public void addClientName(String userName){
        this.userName = userName;
        System.out.println(userName);
    }

    public String getUserName() {
        return userName;
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
        notifyObservers(msg);
        System.out.println( "ProcessChatmSg " + msg.getChannel() + msg.getData());
    }

    public void shutdown() throws IOException {
        out.writeObject(new ShutDownMsg());
        System.out.println("Client has disconnected Shut down");
    }

    @Override
    public void addObserver(Client client) {
        System.out.println("Added observer " + client);
        this.observers.add(client);
    }

    @Override
    public void notifyObservers(ChatMsg msg) {
        for(Observer1 client: this.observers){
            System.out.println("Notify Observer " + client);
            client.notifyObservers(msg);
            System.out.println("Messge = " + msg);
        }
    }
}
