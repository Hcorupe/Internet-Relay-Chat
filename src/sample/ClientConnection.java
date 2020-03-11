package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection implements Runnable{

    Socket Socket;
    ArrayList<String> subScribedChannels;
    ObjectInputStream in;
    ObjectOutputStream out;
    Thread t1;


    public ClientConnection(Socket socket) throws IOException {
        this.Socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        subScribedChannels = new ArrayList<String>();
        t1 = new Thread( new ClientConnection(socket));
    }


    @Override
    public void run() {
        t1.start();
        while(true){
            try {
                Socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void publishMsg(){


    }

}
