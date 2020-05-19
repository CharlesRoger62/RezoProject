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

    public synchronized ChatInterface connect(String userLogin, String passwordLogin, ClientInterface clientInterface) throws RemoteException, InterruptedException{
        for (User user : usersList){
            if (user.getLogin().equals(userLogin) && user.getPassword().equals(passwordLogin)){
                ChatObject messagerieObject = new ChatObject(user);
                System.out.println(user.getLogin() + " is now connected");
                user.setClientInterface(clientInterface);
                user.setOnApp(true);
                return messagerieObject;
            }
        }
        return null;

    }

    @Override
    public ChatInterface register(String login, String password, String pseudo, ClientInterface clientInterface) throws RemoteException, InterruptedException {
        User user = new User(login, password, pseudo);
        Chat.getChat().addUser(user);
        usersList.add(user);
        ChatObject messagerieObject = new ChatObject(user);
        System.out.println(user.getLogin() + " is now registered");
        user.setClientInterface(clientInterface);
        user.setOnApp(true);
        return messagerieObject;
    }
} 