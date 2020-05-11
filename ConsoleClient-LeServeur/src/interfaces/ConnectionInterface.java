package interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectionInterface extends Remote {
    public void echo() throws RemoteException, InterruptedException;
    
    public ChatInterface connect(String user, String password) throws RemoteException, InterruptedException;
}