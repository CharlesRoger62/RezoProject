package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    public void retreiveMessage(String message) throws RemoteException, InterruptedException;

}
