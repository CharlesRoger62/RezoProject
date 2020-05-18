package interfaces;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatObject extends UnicastRemoteObject implements ChatInterface {

    User user;
    Chat chat;

    protected ChatObject(int port) throws RemoteException {
        super(port);
    }

    protected ChatObject(User user) throws RemoteException {
        this.user = user;
        this.chat = Chat.getChat();
    }

    public void echo() throws RemoteException, InterruptedException {
        Thread.sleep(10000);
    }

    @Override
    public synchronized void broadcastMessage(String message, String topic) throws RemoteException, InterruptedException {
        chat.sendMessageAndIncrease(message, topic, user);
        for(ChatClientInterface chatClientInterface : chat.getChatClientInterfaces()){
            if(chatClientInterface.getTopic().equals(topic)) {
                if(chat.getGroupSubscribedPerson().get(chatClientInterface.getChatInterface().getUser()).get(topic) > 0){
                    chatClientInterface.retreiveMessage(chat.getMessagesAndDecrease(topic, user));
                }
            }
        }
    }

    public List<String> getListGroup() throws RemoteException, InterruptedException{
        return chat.getAllGroups();
    }

    public List<String> getMyListGroup() throws RemoteException, InterruptedException{
        List<String> list = new ArrayList<>();
        chat.getGroupSubscribedPerson().get(user).forEach((k, v)->{
            list.add(k);
        });
        return list;
    }

    public boolean joinGroup(String group) throws RemoteException, InterruptedException {

        System.out.println(user.getLogin() + " is trying to join topic #" + group);

        if(chat.getAllGroups().contains(group)) {
            if (!chat.getGroupSubscribedPerson().get(user).keySet().contains(group)) {
                chat.getGroupSubscribedPerson().get(user).put(group, 0);
            }

            try {
                String url = "rmi://localhost:1099/"+group;
                Naming.rebind(url, (ChatClientInterface)new ChatClientObject(this, group));
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            System.out.println(user.getLogin() + " has joined the topic #" + group);

            return true;
        }
        return false;
    }

    public User getUser() throws RemoteException, InterruptedException{
        return user;
    }

    @Override
    public List<String> getGroupConnection(String group) throws RemoteException, InterruptedException {

        System.out.println(user.getLogin() + " is trying to enter topic #" + group);

        joinGroup(group);

        List<String> list = new ArrayList<>();
        list.add("user");
        list.add("password");
        list.add(user.getPseudo());

        System.out.println(user.getLogin() + " has entered topic #" + group);

        return list;
    }
}
