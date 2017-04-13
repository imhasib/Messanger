/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hasib.MessengSrserver;

/**
 *
 * @author Hasib
 */
public interface ClientListener
{
    void signIn(String userName);
    void signOut(String userNamme);
    void clientStatus(String status);
}
