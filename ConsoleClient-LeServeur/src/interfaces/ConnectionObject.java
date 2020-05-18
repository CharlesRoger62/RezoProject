package interfaces;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ConnectionObject extends UnicastRemoteObject
        implements ConnectionInterface {

    List<User> usersList;

    public ConnectionObject() throws RemoteException {
        usersList = Chat.getChat().getAllUser();
    }

    public ConnectionObject(int port) throws RemoteException {
        super(port);
    }

    public void echo() throws RemoteException, InterruptedException {
        Thread.sleep(10000);
    }

    public ChatInterface connect(String userLogin, String passwordLogin) throws RemoteException, InterruptedException{
        for (User user : usersList){
            if (user.getLogin().equals(userLogin) && user.getPassword().equals(passwordLogin)){
                ChatObject messagerieObject = new ChatObject(user);
                System.out.println(user.getLogin() + " is now connected");
                return messagerieObject;
            }
        }
        return null;
    }
} 