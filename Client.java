package Client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public String host;
    public int post;
    public String userName;
    public  boolean isConnect=false;
    Socket socket;
    byte[] sendBytes;
    OutputStream outputStream;

    void connect()throws IOException{
        System.out.println(host+post);
        socket= new Socket(host,post);
        //outputStream = socket.getOutputStream();

        isConnect=true;
    }

    void disconnect() throws IOException {
        if(isConnect){
            socket.close();
            isConnect=false;
        }else{
            System.out.println("Client not connect to the server!");
        }
    }

    void send(String msg) throws IOException {
        sendBytes = msg.getBytes("UTF-8");
        outputStream = socket.getOutputStream();
        outputStream.write(sendBytes.length >>8);
        outputStream.write(sendBytes.length);
        outputStream.write(sendBytes);
        outputStream.flush();
    }
    Client() throws IOException {
        host="127.0.0.1";
        post=55533;
    }

    public Client(String host, int post, String userName) throws IOException {
        this.host = host;
        this.post = post;
        this.userName = userName;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
