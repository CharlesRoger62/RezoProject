package interfaces;

import java.util.*;

public class Chat {
    private static Chat chat;

    private List<String> allGroups;
    private Map<User, List<String>> groupSubscribedPerson;
    private Map<String, List<Message>> messages;

    Chat()  {
         allGroups = new ArrayList<>();
         groupSubscribedPerson = new HashMap<>();

        allGroups.add("1"); // here, 1 is the name of a channel (not very good idea, should better have 
        allGroups.add("2"); // been channel1, for instance!  But I've kept this so this is in line
        allGroups.add("3"); // with the provided documentation (see the PDF file)

        User user = new User("Pierre" , "1234" , "PierroDu06");
        User user2 = new User("Alice" , "4567" , "AliceDu74");

        groupSubscribedPerson.put(user, new ArrayList<>());
        groupSubscribedPerson.put(user2, new ArrayList<>());
        messages = new HashMap<>();
        messages.put("1", new ArrayList<>());
        messages.put("2", new ArrayList<>());
        messages.put("3", new ArrayList<>());
    }

    public static Chat getChat()  {
        if (chat == null){
            chat = new Chat();
        }
        return chat;
    }

    public Map<String, List<Message>> getMessages() {
        return messages;
    }

    public synchronized void addMessages(String topic, Message message) {
        messages.get(topic).add(message);
    }

    public List<String> getAllGroups() {
        return allGroups;
    }

    public Map<User, List<String>> getGroupSubscribedPerson() {
        return groupSubscribedPerson;
    }

    public List<User> getAllUser(){
        return new ArrayList<>(groupSubscribedPerson.keySet());
    }
}
