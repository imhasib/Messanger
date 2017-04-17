package com.hasib;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hasib.server.ServerManager;
import com.hasib.server.ServerMonitor;
import com.hasib.client.ClientManager;
import com.hasib.client.LoginFrame;

/**
 *
 * @author Hasib
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Number of arguments: " + args.length);
        System.out.println(args[0]);
        if("server".equalsIgnoreCase(args[0])) {
            ServerManager serverManager=new ServerManager();
            ServerMonitor monitor=new ServerMonitor(serverManager);

            monitor.setVisible(true);
        } else {
            ClientManager clientManager=new ClientManager();
            LoginFrame loginFrame=new LoginFrame(clientManager);
            loginFrame.setVisible(true);
        }
    }

}
