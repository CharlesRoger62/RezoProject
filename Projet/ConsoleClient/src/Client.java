
import api.PDPublicAPI;
import cli.commands.Connect;
import cli.commands.Quit;
import interfaces.*;


import cli.framework.Shell;
import logging.Logger;


public class Client extends Shell<PDPublicAPI> {

    public Client(){
        super.system = new PDPublicAPI();
        super.invite = "Discord";

        // Registering the command available for the user
        register(
                // Exiting the client
                Quit.class,
                            Connect.class
                
        );
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0){
            Logger.getLogger().println(("No args given"));
        }
        else if (args.length == 1){
            StaticInfo.setConnection(args[0]);
            Client client = new Client();
            client.run();
        }
        else{
            Logger.getLogger().println(("Too many args given"));
        }
    }
}