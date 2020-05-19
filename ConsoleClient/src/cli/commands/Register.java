package cli.commands;

import api.PDPublicAPI;
import cli.commands.messagerie.*;
import cli.framework.Command;
import cli.framework.Shell;
import interfaces.ChatInterface;
import interfaces.ClientObject;
import interfaces.ConnectionInterface;
import interfaces.StaticInfo;
import logging.Logger;

import java.rmi.Naming;
import java.rmi.Remote;
import java.util.List;

public class Register extends Command<PDPublicAPI> {

    private String login;
    private String password;
    private String pseudo;

    @Override
    public String identifier() {
        return "create your account";
    }

    @Override
    public void load(List<String> args) {
        this.login = args.get(0);
        this.password = args.get(1);
        this.pseudo = args.get(2);
    }

    @Override
    public void execute() throws Exception {
        try{
            String address = StaticInfo.getConnection();
            Remote r = Naming.lookup("rmi://" + address + "/Connection");
            ChatInterface chatInterface;
            ConnectionInterface connectionInterface = ((ConnectionInterface) r);
            chatInterface = connectionInterface.register(login, password, pseudo, new ClientObject());

            if (chatInterface == null){
                Logger.getLogger().println("you are missing some arguments, check ?");
            }
            else {
                Logger.getLogger().println("");
                Logger.getLogger().println("Welcome "+login + " !");
                Logger.getLogger().println("Type back to disconnect and ? for help.");
                Logger.getLogger().println("");

                StaticInfo.setChatInterface(chatInterface);

                Shell<PDPublicAPI> shell = new Shell<>();
                shell.system = new PDPublicAPI();
                shell.invite = "Discord";
                shell.register(
                        GetListGroup.class,
                        GetMyListGroup.class,
                        VisualiseGroup.class,
                        JoinGroup.class,
                        GetPrivateMessages.class,
                        SendPrivateMessage.class
                );
                shell.run();
            }

        }
        catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    @Override
    public String describe() {
        return "put your username your password and your pseudo to register a new account";
    }
}
