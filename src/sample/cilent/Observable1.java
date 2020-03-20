package sample.cilent;

import sample.Common.ChatMsg;
import sample.Common.Message;

import java.io.IOException;

interface Observable1 {

    void update(Client client) throws IOException;

}
