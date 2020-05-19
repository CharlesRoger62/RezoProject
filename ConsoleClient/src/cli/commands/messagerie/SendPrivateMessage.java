package cli.commands.messagerie;

import api.PDPublicAPI;
import cli.framework.Command;
import interfaces.StaticInfo;

import java.util.List;

public class SendPrivateMessage extends Command<PDPublicAPI> {
    private String sendPseudo;

    @Override
    public String identifier() {
        return "send a private message to a user";
    }

    @Override
    public void load(List<String> args) {
        this.sendPseudo = args.get(0);
    }

    @Override
    public void execute() throws Exception {
        try
        {
            java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            String s = stdin.readLine();
            if(s != null && s.length()>0)
            {
                StaticInfo.getChatInterface().sendPrivateMessage(sendPseudo, s);
            }
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    @Override
    public String describe() {
        return "type the pseudo of the user you want to contact";
    }
}
