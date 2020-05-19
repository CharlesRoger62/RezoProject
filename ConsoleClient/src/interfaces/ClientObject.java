package interfaces;

import logging.Logger;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientObject extends UnicastRemoteObject implements ClientInterface {
    public ClientObject() throws RemoteException {
        super();
    }

    @Override
    public void retreiveMessage(String message) throws RemoteException, InterruptedException {
        Logger.getLogger().println(message);
    }
}
