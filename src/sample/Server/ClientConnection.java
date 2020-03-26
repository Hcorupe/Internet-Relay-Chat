package sample.Server;

import sample.Common.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection implements Runnable{

    private Socket Socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Thread t1;
    private boolean shutdown = false;

    public ClientConnection(Socket socket) throws IOException {
        this.Socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
        t1 = new Thread( this);
        t1.start();
        System.out.println("Thread started");
    }

    //getThread getter
    @Override
    public void run() {
        System.out.println("Run started");
        while(!shutdown){
            if(!Socket.isConnected()){
                return;
            }
            try {
                Message msg = (Message)in.readObject();
                msg.setClient(this);
                ServerPublishThread.addMsg(msg);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void sendMessage(Message msg)  {
        try {
            out.writeObject(msg);
        }catch (IOException e){
            System.out.println("Fail to send Message "  + msg.getClient());
        }
    }

    public void shutdown() {
        this.shutdown = true;
    }



}
