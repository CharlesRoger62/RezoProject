package interfaces;

//import javax.jms.JMSException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatInterface extends Remote {
    public void echo() throws RemoteException, InterruptedException;

    public void broadcastMessage(String message) throws RemoteException, InterruptedException;

    public List<String> getListGroup() throws RemoteException, InterruptedException;

    public List<String> getMyListGroup() throws RemoteException, InterruptedException;

    public boolean joinGroup(String group) throws RemoteException, InterruptedException;

    public void connectedTopic(String topic) throws RemoteException, InterruptedException;

    public List<String> getGroupConnection(String group) throws RemoteException, InterruptedException;//, JMSException;

    public List<String> getPrivateMessages() throws RemoteException, InterruptedException;

    public boolean isNewPrivateMessage() throws RemoteException, InterruptedException;

    public void sendPrivateMessage(String pseudo, String message) throws RemoteException, InterruptedException;
}
