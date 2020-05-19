package interfaces;

//import javax.jms.JMSException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectionInterface extends Remote {
    public void echo() throws RemoteException, InterruptedException;
    
    public ChatInterface connect(String user, String password, ClientInterface clientInterface) throws RemoteException, InterruptedException;//, JMSException;

    public ChatInterface register(String login, String password, String pseudo, ClientInterface clientInterface) throws RemoteException,  InterruptedException;
}