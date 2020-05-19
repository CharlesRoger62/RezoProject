package cli.commands.messagerie;

import api.PDPublicAPI;
import cli.framework.Command;
import interfaces.StaticInfo;
import cli.framework.Shell;


import java.rmi.RemoteException;
import java.util.List;

public class VisualiseGroup extends Command<PDPublicAPI> {
    private String idTopic = null;
    private String pseudo = null;
    private String login = null;
    private String password = null;


    @Override
    public String identifier() {
        return "Enter a vocal chatroom";
    }

    @Override
    public void load(List<String> args) {
        this.idTopic = args.get(0);
    }

    @Override
    public void execute() throws Exception {

        List<String> list = StaticInfo.getChatInterface().getGroupConnection(idTopic);
        login = list.get(0);
        password = list.get(1);
        pseudo = list.get(2);
        System.out.println("");
        System.out.println("Connected to topic #"+idTopic+" as "+"\u001B[31m"+pseudo+"\u001B[0m");
        System.out.println("Type exit to leave.");
        System.out.println("");
        StaticInfo.getChatInterface().connectedTopic(idTopic);
        try
        {
            java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            while (true)
            {
                String s = stdin.readLine();
                if(s == null){
                    exit();
                    return;
                }
                else if (s.equals("exit")){
                    exit();
                    return;
                }
                else if (s.length()>0)
                {
                    StaticInfo.getChatInterface().broadcastMessage(s);
                }
            }
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    @Override
    public String describe() {
        return "Connect to a topic, see its messages and send messages to it. Arguments: topic id. Type exit to leave.";
    }


    private void exit()
    {
        try {
            StaticInfo.getChatInterface().connectedTopic(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("");
        System.out.println("\u001B[31m"+pseudo+"\u001B[0m"+" left topic #"+idTopic);
        System.out.println("");
    }
}
