/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE_BE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author andres
 */
public class ReadingThread extends Thread  {
    
    public static boolean check = true;
    Socket socket;
    BufferedReader reader;
    
    public ReadingThread(Socket socket) throws IOException {
        this.socket = socket;
        this.reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public void run ()
    {
        try
        {
            
            while (reader.readLine()!=null && check==true)
            {
                if (check)
                {
                    Thread.sleep(11000);
                    System.out.println("ACK");   
                }
                else
                {
                check=false;
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
