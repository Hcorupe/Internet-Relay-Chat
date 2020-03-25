package sample.Common;

import sample.Server.ClientConnection;

import java.io.Serializable;

public class Message implements Serializable {
    MsgType.type type;
    transient ClientConnection client;


    public MsgType.type getType(){
    return type;
    }

    public ClientConnection getClient() {
        return client;
    }

    public void setClient(ClientConnection client) {
        this.client = client;
    }
}
