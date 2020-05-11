package interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chat {
    private static Chat chat;

    private  List<String> allGroups;
    private  Map<User, List<String>> groupSubscribedPerson;

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
    }

    public static Chat getChat()  {
        if (chat == null){
            chat = new Chat();
        }
        return chat;
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
