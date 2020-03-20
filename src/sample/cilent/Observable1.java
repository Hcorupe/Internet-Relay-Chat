package sample.cilent;

import java.util.Observer;

public interface Observable1 {
    void addObserver(Client client);
    void notifyObservers();
}
