/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hasib.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 *
 * @author Hasib
 */
public class Clients implements Runnable
{
    Socket client;
    ObjectInputStream input;
    ObjectOutputStream output;
    boolean keepListening;
    ServerManager serverManager;
    int clientNumber;
    ClientListener clientListener;

    public Clients(ClientListener getClientListener,Socket getClient,ServerManager getServerManager,int getClientNumber)
    {
        client=getClient;
        clientListener=getClientListener;
        try
        {
            serverManager=getServerManager;
            clientNumber=getClientNumber;
            input = new ObjectInputStream(client.getInputStream());
            output = new ObjectOutputStream(client.getOutputStream());
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        keepListening=true;
    }

    public void run()
    {
        String message="",name="";
        boolean sameName=false;
        while(keepListening)
        {
            try
            {
                message = (String) input.readObject();
                System.out.println("Server is receiving   "+ message);
                StringTokenizer tokens=new StringTokenizer(message);
                String header=tokens.nextToken();
                name=tokens.nextToken();
                if(header.equalsIgnoreCase("login"))
                {
                    serverManager.sendNameToAll(message);
                    ServerManager.clientTracker[clientNumber]=name;

                    for(int i=0;i<serverManager.clientNumber;i++) //Create & send  users list
                    {
                        String userName=ServerManager.clientTracker[i];
                        if(!userName.equalsIgnoreCase(""))
                        {
                            output.writeObject("login "+userName);
                            output.flush();
                        }
                    }

                    clientListener.signIn(name);
                    clientListener.clientStatus(name+": is signIn , IPaddress :"+client.getInetAddress()+"  ,portNumber :"+client.getPort()+" by hasib");
                }
                else if(header.equalsIgnoreCase(ServerConstant.DISCONNECT_STRING))
                {
                    clientListener.signOut(name);
                    serverManager.sendNameToAll(message);
                    ServerManager.clientTracker[clientNumber]="";
                    keepListening=false;
                }
                else
                {
                    serverManager.sendInfo(message);
                }
                
            }
            catch (IOException ex)
            {
                clientListener.signOut(name);
                serverManager.sendNameToAll(ServerConstant.DISCONNECT_STRING + " " + name);
                ServerManager.clientTracker[clientNumber]="";
                break;
            }
            catch (ClassNotFoundException ex)
            {
            }
        }
    }
}
