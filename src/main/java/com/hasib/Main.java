package com.hasib;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hasib.MessengSrserver.ServerManager;
import com.hasib.MessengSrserver.ServerMonitor;
import com.hasib.messengerclient.ClientManager;
import com.hasib.messengerclient.LoginFrame;

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
